package model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class City {

    private Long id;

    @Column(name = "city_name", nullable = false)
    private String name;
}

