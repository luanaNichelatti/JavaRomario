package primeiroSpring.aula1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import primeiroSpring.aula1.model.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Cliente findByNome(String nome);
}
