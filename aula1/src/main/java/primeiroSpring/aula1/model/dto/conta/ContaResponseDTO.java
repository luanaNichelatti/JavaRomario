package primeiroSpring.aula1.model.dto.conta;

import primeiroSpring.aula1.model.dto.cliente.ClienteContaGetResponseDTO;

public record ContaResponseDTO(
        Integer id,
        Integer numero,
        Double saldo,
        Double limite,
        ClienteContaGetResponseDTO titular) {


}
