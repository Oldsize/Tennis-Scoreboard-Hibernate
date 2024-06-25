package com.example.hibernate_practice.Entity;


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
    
    public Match(Player player1, Player player2, Player winner) {
        this.player1 = player1;
        this.player2 = player2;
        this.winner = winner;
    }
}
