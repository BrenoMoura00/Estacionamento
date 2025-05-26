package br.com.estacionamento.repositories;

import br.com.estacionamento.model.Cliente;
import br.com.estacionamento.model.Ticket;
import br.com.estacionamento.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Ticket findByVeiculo(Veiculo veiculo);
    List<Ticket> findByCliente(Cliente cliente);
}
