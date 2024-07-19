package com.example.hibernate_practice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(schema = "PUBLIC", name = "MATCHES")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @ManyToOne
    @JoinColumn(name = "PLAYER1")
    private Player player1;
    @ManyToOne
    @JoinColumn(name = "PLAYER2")
    private Player player2;
    @ManyToOne
    @JoinColumn(name = "WINNER")
    private Player winner;
    @Transient
    private MatchScore matchScore;

    public Match(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.matchScore = new MatchScore();
    }
}
