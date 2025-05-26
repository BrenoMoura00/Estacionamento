package br.com.estacionamento.service.impl;

import br.com.estacionamento.model.Cliente;
import br.com.estacionamento.repositories.ClienteRepository;
import br.com.estacionamento.service.ClienteService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    @Transactional
    public void cadastrarCliente(Cliente cliente) {
        this.clienteRepository.save(cliente);
    }

    @Override
    public Cliente buscarClientePorCpf(String cpf) {
        var cliente = this.clienteRepository.findByCpf(cpf);
        if (cliente == null) {
            throw new RuntimeException("Cliente não encontrado com o CPF: " + cpf);
        }

        return cliente;
    }

    @Override
    @Transactional
    public void atualizarCliente(Cliente cliente) {
        var clienteExistente = buscarClientePorCpf(cliente.getCpf());

        if (cliente.getName() != null && !cliente.getName().isEmpty()) {
            clienteExistente.setName(cliente.getName());
        }

        if (cliente.getTelefone() != null && !cliente.getTelefone().isEmpty()) {
            clienteExistente.setTelefone(cliente.getTelefone());
        }

        this.clienteRepository.save(clienteExistente);
    }

    @Override
    public void excluirCliente(String cpf) {
        this.clienteRepository.deleteByCpf(cpf);
    }
}
