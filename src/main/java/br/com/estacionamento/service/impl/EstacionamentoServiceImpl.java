package br.com.estacionamento.service.impl;

import br.com.estacionamento.model.Estacionamento;
import br.com.estacionamento.repositories.EstacionamentoRepository;
import br.com.estacionamento.service.EstacionamentoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstacionamentoServiceImpl implements EstacionamentoService {

    private final EstacionamentoRepository estacionamentoRepository;

    @Override
    @Transactional
    public void cadastrarEstacionamento(Estacionamento estacionamento) {
        this.estacionamentoRepository.save(estacionamento);
    }

    @Override
    public Estacionamento buscarEstacionamentoPorId(Long id) {
        var estacionamento = this.estacionamentoRepository.findById(id);

        if (estacionamento.isEmpty()) {
            throw new RuntimeException("Estacionamento não encontrado com o ID: " + id);
        }

        return estacionamento.get();
    }

    @Override
    public Estacionamento buscarEstacionamentoPorNome(String nome) {
        var estacionamento = this.estacionamentoRepository.findByNome((nome));

        if (estacionamento == null) {
            throw new RuntimeException("Estacionamento não encontrado com o nome: " + nome);
        }

        return estacionamento;
    }

    @Override
    @Transactional
    public void atualizarEstacionamento(Estacionamento estacionamento) {
        var estacionamentoExistente = this.estacionamentoRepository.findById(estacionamento.getId());

        if (estacionamentoExistente.isEmpty()) {
            throw new RuntimeException("Estacionamento não encontrado com o ID: " + estacionamento.getId());
        }

        if (estacionamento.getVaga() != null) {
            estacionamentoExistente.get().setVaga(estacionamento.getVaga());
        }

        this.estacionamentoRepository.save(estacionamentoExistente.get());
    }

    @Override
    public void removerEstacionamento(Long id) {
        this.estacionamentoRepository.deleteById(id);
    }

    @Override
    public List<Estacionamento> listarEstacionamentos() {
        var estacionamentos = this.estacionamentoRepository.findAll();

        if (estacionamentos.isEmpty()) {
            throw new RuntimeException("Nenhum estacionamento encontrado.");
        }

        return estacionamentos;
    }
}
