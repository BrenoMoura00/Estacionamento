package br.com.estacionamento.service;

import br.com.estacionamento.model.Cliente;

public interface ClienteService {
    void cadastrarCliente(Cliente cliente);
    Cliente buscarClientePorCpf(String cpf);
    void atualizarCliente(Cliente cliente);
    void excluirCliente(String cpf);
}
