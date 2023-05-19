package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Player {

    private Long id;
    private String firstname;
    private String lastname;
    private String shortname;
    private String sex;
    private Country country;
    private String picture;
    private Data data;
    @JsonIgnore
    private Integer totalWonMatch;
    @JsonIgnore
    private Double imc;
}