package br.com.estacionamento.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "estacionamentos")
public class Estacionamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String vaga;
    private String andar;
    private String bloco;
    private boolean disponivel;

    @OneToMany(mappedBy = "estacionamento")
    private List<Ticket> tickets;
}
