package br.com.estacionamento.repositories;

import br.com.estacionamento.model.Estacionamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstacionamentoRepository extends JpaRepository<Estacionamento,Long> {
    Estacionamento findByNome(String nome);
}
