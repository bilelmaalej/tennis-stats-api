package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class TennisPlayersStatisticsDto {

    private String countryWithHighestWinRatio;
    private double averageIMC;
    private double medianHeight;

}
