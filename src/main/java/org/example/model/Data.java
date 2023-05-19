package org.example.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Entity
@Getter
@Setter
public class Data {
    @NotNull
    private Integer rank;
    @NotNull
    private Integer points;
    @NotNull
    private Integer weight;
    @NotNull
    private Integer height;
    @NotNull
    private Integer age;
    @NotNull
    private List<Integer> last;
}
