package br.com.estacionamento.repositories;

import br.com.estacionamento.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario,Long> {
    Funcionario findByCpf(String cpf);
    void deleteByCpf(String cpf);
}
