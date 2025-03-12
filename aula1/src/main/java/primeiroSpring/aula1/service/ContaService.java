package primeiroSpring.aula1.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import primeiroSpring.aula1.model.dto.conta.ContaPostRequestDTO;
import primeiroSpring.aula1.model.dto.conta.ContaPutRequestDTO;
import primeiroSpring.aula1.model.entity.Cliente;
import primeiroSpring.aula1.model.entity.Conta;
import primeiroSpring.aula1.repository.ContaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContaService {

    @NonNull
    private final ContaRepository contaRepository;
    @Lazy @Autowired
    private ClienteService clienteService;

    public Conta criarConta(ContaPostRequestDTO contaDTO) {
        Cliente cliente = clienteService.buscar(contaDTO.idtitular());
        Conta conta = contaDTO.convert(cliente);
        return contaRepository.save(conta);
    }

    public List<Conta> buscarContas() {
        return contaRepository.findAll();
    }

    public Page<Conta> buscarContas(Pageable pageable) {
        return contaRepository.findAll(pageable);
    }

    public Conta buscarConta(Integer id) {
        return contaRepository.findById(id).get();
    }


    public void deletarConta(Integer id) {
        contaRepository.deleteById(id);
    }

    public Conta atualizarConta(Integer id, ContaPutRequestDTO contaDTO ) {
        Conta contaAntiga = buscarConta(id);
        Conta contaEditada = contaDTO.convert();
        contaEditada.setId(id);
        contaEditada.setNumero(contaAntiga.getNumero());
        contaEditada.setSaldo(contaAntiga.getSaldo());
        return contaRepository.save(contaEditada);
    }

    public Conta alterarLimite(Integer id, Double limite) {
        Conta conta = buscarConta(id);
        conta.setLimite(limite);
        return contaRepository.save(conta);
    }
    public List<Conta> buscarContasFiltro(String nomeTitular, Integer numero) {
        return contaRepository.findByTitular_NomeContainsAndNumeroOrderByNumero(nomeTitular, numero);
    }

    public List<Conta> buscarContasPorSaldo(Double saldo) {
        return contaRepository.findBySaldoGreaterThan(saldo);
    }
}
