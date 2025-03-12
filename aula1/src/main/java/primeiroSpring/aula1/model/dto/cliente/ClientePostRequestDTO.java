package primeiroSpring.aula1.model.dto.cliente;

import primeiroSpring.aula1.model.entity.Cliente;

public record ClientePostRequestDTO(
        String nome,
        Long cpf
) {
    public Cliente convert() {
        return Cliente.builder().nome(nome).cpf(cpf).build();
    }
}