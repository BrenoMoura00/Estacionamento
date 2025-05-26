package br.com.estacionamento.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "Convenio")
public class Convenio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_convenio", length = 100, nullable = false, unique = true)
    private String nome;

    @Column(name = "nome_empresa", length = 100, nullable = false)
    private String nomeEmpresa;

    @Column(name = "valor", nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(name = "qnt_horas", nullable = false)
    private Integer qntHoras;

    @Column(name = "qnt_vagas", nullable = false)
    private Integer qntVagas;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;

    @OneToMany(mappedBy = "convenio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cliente> clientes = new ArrayList<>();

    public void desativar() {
        this.ativo = false;
    }

    public BigDecimal calcularValorPorVaga() {
        return valor.divide(new BigDecimal(qntVagas), 2, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        return "Convenio [id=" + id + ", nome=" + nome + ", nomeEmpresa=" + nomeEmpresa + ", valor=" + valor
                + ", qntHoras=" + qntHoras + ", qntVagas=" + qntVagas + ", ativo=" + ativo + "]";
    }
    
}
