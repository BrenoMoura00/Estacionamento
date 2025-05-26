package br.com.estacionamento.service;

import br.com.estacionamento.model.Estacionamento;

import java.util.List;

public interface EstacionamentoService {
    void cadastrarEstacionamento(Estacionamento estacionamento);
    Estacionamento buscarEstacionamentoPorId(Long id);
    Estacionamento buscarEstacionamentoPorNome(String nome);
    void atualizarEstacionamento(Estacionamento estacionamento);
    void removerEstacionamento(Long id);
    List<Estacionamento> listarEstacionamentos();
}
