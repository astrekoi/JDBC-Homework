package model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {

    private Long id;

    private String firstName;

    private String lastName;

    private String gender;

    private Integer age;

    private City cityId;
}
