package primeiroSpring.aula1.model.dto.cliente;

import primeiroSpring.aula1.model.dto.conta.ContaClienteResponseDTO;

import java.util.List;

public record ClienteResponseDTO(
        Integer id,
        String nome,
        Long cpf,
        List<ContaClienteResponseDTO> contas
) {
}
