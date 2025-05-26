package br.com.estacionamento.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "clientes")
public class Cliente extends Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "placa", length = 7, nullable = false, unique = true)
    private String placa;

    @ManyToOne
    @JoinColumn(name = "estacionamento_id")
    private Estacionamento estacionamento;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Builder.Default
    private List<Veiculo> veiculos = new java.util.ArrayList<>();

    @OneToMany(mappedBy = "cliente")
    private List<Ticket> tickets;

    @Embedded
    private Endereco endereco;

    private boolean ativo;

    private LocalDate data_cadastro;

    @ManyToOne
    @JoinColumn(name = "convenio_id")
    private Convenio convenio;

    public void adicionarVeiculo(Veiculo veiculo) {
        veiculos.add(veiculo);
        veiculo.setCliente(this);
    }

    @Override
    public String getTipo() {
        return "CLIENTE";
    }

    @Override
    public String toString() {
        return "Cliente [endereco=" + endereco + ", ativo=" + ativo + ", data_cadastro=" + data_cadastro
                + ", convenio=" + convenio + "]";
    }
}