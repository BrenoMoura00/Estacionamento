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
@Table(name = "clientes")
public class Cliente extends Pessoa {

    @ManyToOne
    @JoinColumn(name = "estacionamento_id")
    private Estacionamento estacionamento;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Veiculo> veiculos;

    @OneToMany(mappedBy = "cliente")
    private List<Ticket> tickets;
}