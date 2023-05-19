package PlayerTest;


import org.example.exception.DataNotMentionedException;
import org.example.exception.PlayerNotFoundException;
import org.example.model.Country;
import org.example.model.Data;
import org.example.model.Player;
import org.example.service.PlayerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {

    @InjectMocks
    private PlayerServiceImpl playerService;

    public static String jsonFile = "{\n" +
            "  \"players\": [\n" +
            "    {\n" +
            "      \"id\": 52,\n" +
            "      \"firstname\": \"Novak\",\n" +
            "      \"lastname\": \"Djokovic\",\n" +
            "      \"shortname\": \"N.DJO\",\n" +
            "      \"sex\": \"M\",\n" +
            "      \"country\": {\n" +
            "        \"picture\": \"https://data.latelier.co/training/tennis_stats/resources/Serbie.png\",\n" +
            "        \"code\": \"SRB\"\n" +
            "      },\n" +
            "      \"picture\": \"https://data.latelier.co/training/tennis_stats/resources/Djokovic.png\",\n" +
            "      \"data\": {\n" +
            "        \"rank\": 2,\n" +
            "        \"points\": 2542,\n" +
            "        \"weight\": 80000,\n" +
            "        \"height\": 188,\n" +
            "        \"age\": 31,\n" +
            "        \"last\": [1, 1, 1, 1, 1]\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 95,\n" +
            "      \"firstname\": \"Venus\",\n" +
            "      \"lastname\": \"Williams\",\n" +
            "      \"shortname\": \"V.WIL\",\n" +
            "      \"sex\": \"F\",\n" +
            "      \"country\": {\n" +
            "        \"picture\": \"https://data.latelier.co/training/tennis_stats/resources/USA.png\",\n" +
            "        \"code\": \"USA\"\n" +
            "      },\n" +
            "      \"picture\": \"https://data.latelier.co/training/tennis_stats/resources/Venus.webp\",\n" +
            "      \"data\": {\n" +
            "        \"rank\": 52,\n" +
            "        \"points\": 1105,\n" +
            "        \"weight\": 74000,\n" +
            "        \"height\": 185,\n" +
            "        \"age\": 38,\n" +
            "        \"last\": [0, 1, 0, 0, 1]\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 65,\n" +
            "      \"firstname\": \"Stan\",\n" +
            "      \"lastname\": \"Wawrinka\",\n" +
            "      \"shortname\": \"S.WAW\",\n" +
            "      \"sex\": \"M\",\n" +
            "      \"country\": {\n" +
            "        \"picture\": \"https://data.latelier.co/training/tennis_stats/resources/Suisse.png\",\n" +
            "        \"code\": \"SUI\"\n" +
            "      },\n" +
            "      \"picture\": \"https://data.latelier.co/training/tennis_stats/resources/Wawrinka.png\",\n" +
            "      \"data\": {\n" +
            "        \"rank\": 21,\n" +
            "        \"points\": 1784,\n" +
            "        \"weight\": 81000,\n" +
            "        \"height\": 183,\n" +
            "        \"age\": 33,\n" +
            "        \"last\": [1, 1, 1, 0, 1]\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 102,\n" +
            "      \"firstname\": \"Serena\",\n" +
            "      \"lastname\": \"Williams\",\n" +
            "      \"shortname\": \"S.WIL\",\n" +
            "      \"sex\": \"F\",\n" +
            "      \"country\": {\n" +
            "        \"picture\": \"https://data.latelier.co/training/tennis_stats/resources/USA.png\",\n" +
            "        \"code\": \"USA\"\n" +
            "      },\n" +
            "      \"picture\": \"https://data.latelier.co/training/tennis_stats/resources/Serena.png\",\n" +
            "      \"data\": {\n" +
            "        \"rank\": 10,\n" +
            "        \"points\": 3521,\n" +
            "        \"weight\": 72000,\n" +
            "        \"height\": 175,\n" +
            "        \"age\": 37,\n" +
            "        \"last\": [0, 1, 1, 1, 0]\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 17,\n" +
            "      \"firstname\": \"Rafael\",\n" +
            "      \"lastname\": \"Nadal\",\n" +
            "      \"shortname\": \"R.NAD\",\n" +
            "      \"sex\": \"M\",\n" +
            "      \"country\": {\n" +
            "        \"picture\": \"https://data.latelier.co/training/tennis_stats/resources/Espagne.png\",\n" +
            "        \"code\": \"ESP\"\n" +
            "      },\n" +
            "      \"picture\": \"https://data.latelier.co/training/tennis_stats/resources/Nadal.png\",\n" +
            "      \"data\": {\n" +
            "        \"rank\": 1,\n" +
            "        \"points\": 1982,\n" +
            "        \"weight\": 85000,\n" +
            "        \"height\": 185,\n" +
            "        \"age\": 33,\n" +
            "        \"last\": [1, 0, 0, 0, 1]\n" +
            "      }\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    @Test
    public void testGetAllPlayersSortedByRank() {
        List<Player> sortedPlayers = playerService.getAllPlayersSortedByRank(jsonFile);

        assertEquals(5, sortedPlayers.size());
        assertEquals(17, sortedPlayers.get(0).getId());
    }


    @Test
    public void testGetPlayerById() throws PlayerNotFoundException {
        Country countryRafa = new Country("https://data.latelier.co/training/tennis_stats/resources/Espagne.png", "ESP");
        Data dataRafa = new Data(1, 1982, 85000, 185, 33, Arrays.asList(1, 0, 0, 0, 1));
        Player player = new Player(17L, "Rafael", "Nadal", "R.NAD", "M", countryRafa, "https://data.latelier.co/training/tennis_stats/resources/Nadal.png", dataRafa, 2, 24.84);

        assertThat(player).usingRecursiveComparison().isEqualTo(playerService.getPlayerById(17L, jsonFile));
    }

    @Test
    public void testStatistic() throws DataNotMentionedException {

        assertEquals("SRB", playerService.getPlayerStatistics(jsonFile).getCountryWithHighestWinRatio());
        assertEquals(23.358, playerService.getPlayerStatistics(jsonFile).getAverageIMC());
        assertEquals(175, playerService.getPlayerStatistics(jsonFile).getMedianHeight());

    }

}
