package br.com.estacionamento.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "responsavel")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Responsavel extends Pessoa {

    @Embedded
    private Endereco endereco;

    @OneToMany(mappedBy = "responsavel", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Estacionamento> estacionamentos = new ArrayList<>();

    @Override
    public String getTipo() {
        return "RESPONSAVEL";
    }
}