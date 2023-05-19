package org.example.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.TennisPlayersStatisticsDto;
import org.example.exception.DataNotMentionedException;
import org.example.exception.PlayerNotFoundException;
import org.example.model.Player;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
@CacheConfig(cacheNames = "playersCache")
public class PlayerServiceImpl implements PlayerService {

//     Si pour les 3 services on va utiliser les memes donnée :
//     On peut stocker les données dans une mémoire cahce pour gagner en performance (on peut utiliser cafeine )
//    Cache
//    <String, Player> cache = Caffeine.newBuilder()
//    .expireAfterWrite(1, TimeUnit.MINUTES)
//    .maximumSize(100)
//    .build();
//    private static final String CACHE_LIST_PLAYERS = "players";
//    @Cacheable(value = CACHE_LIST_PLAYERS)

    /**
     * @param jsonFile
     * @return La liste des joueurs triée du meilleur au moins bon.
     */
    @Override
    public List<Player> getAllPlayersSortedByRank(String jsonFile) {
        // Initialisation de la liste des joueurs à partir du JSON fourni
        List<Player> listPlayers;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonFile);
            listPlayers = objectMapper.convertValue(jsonNode.get("players"), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de la lecture du JSON des joueurs.", e);
        }
        // On ajoute des informations pour chaque joueurs pour utiliser dans les statistiques
        listPlayers.stream().forEach(p -> {
            p.setTotalWonMatch(p.getData().getLast().stream().mapToInt(Integer::valueOf).sum());
            double heightInMeters = p.getData().getHeight() / 100.0;
            double weightInKg = p.getData().getWeight() / 1000.0;
            // Arrondir l'IMC avec deux chiffre àpres la virgule pour plus de lisibilité
            double imc = Math.round(weightInKg / (heightInMeters * heightInMeters) * 100.0) / 100.0;
            p.setImc(imc);
        });
        // On trie les joueurs du meilleur au moins bon
        listPlayers.sort(Comparator.comparingInt(p -> p.getData().getRank()));

        return listPlayers;
    }

    /**
     * @param id : Identifiant du joueur
     * @return Le Joueur ayant l'identifiant demandé, si l'identifiant n'existe pas en lève une exception
     * @throws PlayerNotFoundException
     */
    @Override
    public Player getPlayerById(Long id, String jsonFile) throws PlayerNotFoundException {
        return this.getAllPlayersSortedByRank(jsonFile).stream().filter(p -> id.equals(p.getId())).findFirst().orElseThrow(() -> new PlayerNotFoundException("Joueur non trouvé avec l'ID : " + id));
    }

    /**
     * Permet de faire les statistiques pour la liste des joueurs passée en parametre
     *
     * @param jsonFile
     * @return
     */
    @Override
    public TennisPlayersStatisticsDto getPlayerStatistics(String jsonFile) throws DataNotMentionedException {

        List<Player> players = getAllPlayersSortedByRank(jsonFile);
        // On crée une map pour stocker le pays et le nombre de match gagné
        Map<String, Integer> CountryWon = new HashMap<>();
        players.stream().forEach(p -> {
            if (p.getCountry() != null) {
                if (CountryWon.containsKey(p.getCountry().getCode())) {
                    CountryWon.put(p.getCountry().getCode(), CountryWon.get(p.getCountry().getCode()) + p.getTotalWonMatch());
                } else {
                    CountryWon.put(p.getCountry().getCode(), p.getTotalWonMatch());
                }
            }
        });
        // On récupère le pays qui a plus de match gagné (on se basant sur les 5 derniers matchs)
        String bestCountryCode = Collections.max(CountryWon.entrySet(), Map.Entry.comparingByValue()).getKey();

        // L'ennoncé du test n'est pas explicite:
        // S'il s'agit du pays du joueur qui a gagné plus de match lors des 5 derniers match (on se basant sur l'attribut last du DATA)
        // on décommenté les deux lignes suivante et on supprime les lignes de 84 à 95
//        String bestCountryCode = players.stream()
//                .max(Comparator.comparingInt(Player::getTotalWonMatch)).get().getCountry().getCode();

        // Calcul de la moyenne des IMC des joueurs
        double avgImc = players.stream().map(Player::getImc).mapToDouble(Double::valueOf).average().orElseThrow((() -> new DataNotMentionedException("Il manque des données pour la taille d'un joueeur  ")));
        final List<Integer> totalHeight = new ArrayList<>();
        players.stream().map(Player::getData).forEach(d -> totalHeight.add(d.getHeight()));
        // Calcule de la taille médiane
        double medianHeight = this.getMedianHeight(totalHeight);

        return new TennisPlayersStatisticsDto(bestCountryCode, avgImc, medianHeight);

    }

    /**
     * Méthode permet de calculer la médiane de la taille des joueurs
     *
     * @return median
     */
    public double getMedianHeight(List<Integer> totalHeight) {
        int size = totalHeight.size();
        if (size % 2 == 0) {
            int middleIndex = size / 2;
            return (totalHeight.get(middleIndex - 1) + totalHeight.get(middleIndex)) / 2;
        } else {
            return totalHeight.get(size / 2);
        }
    }
}
