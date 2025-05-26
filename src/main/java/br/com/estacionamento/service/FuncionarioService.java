package br.com.estacionamento.service;

import br.com.estacionamento.model.Funcionario;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioService {
    void cadastrarFuncionario(Funcionario funcionario);
    Funcionario buscarFuncionarioPorCpf(String cpf);
    void atualizarFuncionario(Funcionario funcionario);
    void excluirFuncionario(String cpf);
}
