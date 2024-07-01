package com.example.hibernate_practice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(schema = "PUBLIC", name = "PLAYERS")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "NAME")
    private String name;

    public Player(String name) {
        this.name = name;
    }
}
