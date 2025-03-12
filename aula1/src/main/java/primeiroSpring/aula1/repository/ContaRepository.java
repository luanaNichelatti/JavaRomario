package primeiroSpring.aula1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import primeiroSpring.aula1.model.entity.Conta;

import java.util.List;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {
    List<Conta> findByTitular_NomeContainsAndNumeroOrderByNumero(String nomeTitular, Integer numero);
    List<Conta> findBySaldoGreaterThan(Double saldo);
}
