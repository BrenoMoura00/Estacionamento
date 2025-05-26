package br.com.estacionamento.service;

import br.com.estacionamento.model.Ticket;

import java.util.List;

public interface TicketService {
    void abrirTicket(Ticket ticket);
    Ticket buscarTicketPorId(Long id);
    List<Ticket> buscarTicketPorCpf(String cpf);
    List<Ticket> buscarTodosTickets();
    Ticket buscarTicketPorVeiculo(String placa);
    void fecharTicket(Long id);
}
