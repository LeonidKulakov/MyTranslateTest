package ru.kli.translation.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
public class Sentence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String input;
    private String output;
    private String ip;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sentence")
    private List<Words> words;
}
