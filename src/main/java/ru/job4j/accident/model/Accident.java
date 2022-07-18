package ru.job4j.accident.model;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accidents")
public class Accident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String name;
    @NonNull
    private String text;
    @NonNull
    private String address;

//    @ManyToOne
//    @JoinColumn(name = "type_id")
//    private AccidentType type;
//
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<Rule> rules = new HashSet<>();


}