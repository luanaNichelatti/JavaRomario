package primeiroSpring.aula1.model.dto.cliente;

import primeiroSpring.aula1.model.entity.Cliente;
import primeiroSpring.aula1.model.entity.Conta;

import java.util.List;


public record ClientePutRequestDTO(
        String nome,
        Long cpf,
        List<Conta> contas
) {
    public Cliente convert() {
        return Cliente.builder().nome(this.nome).cpf(this.cpf).contas(this.contas).build();
    }
}
