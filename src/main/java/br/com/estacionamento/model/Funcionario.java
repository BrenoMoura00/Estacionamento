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
@Table(name = "funcionario")
public class Funcionario extends Pessoa {
    @OneToMany(mappedBy = "funcionario")
    private List<Ticket> tickets;
}
