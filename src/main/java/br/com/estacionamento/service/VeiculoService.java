package br.com.estacionamento.service;

import br.com.estacionamento.model.Veiculo;

public interface VeiculoService {
    void  cadastrarVeiculo(Veiculo veiculo);
    Veiculo buscarVeiculoPorPlaca(String placa);
    void atualizarVeiculo(Veiculo veiculo);
    void removerVeiculo(String placa);
}
