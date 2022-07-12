package ru.job4j.accident.model;

import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Accident {
    private int id;
    @NonNull
    private String name;
    @NonNull
    private String text;
    @NonNull
    private String address;
    @NonNull
    private AccidentType type;
    @NonNull
    private Set<Rule> rules;
}
