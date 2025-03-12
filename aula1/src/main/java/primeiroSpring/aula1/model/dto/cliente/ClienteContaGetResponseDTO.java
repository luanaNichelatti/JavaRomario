package primeiroSpring.aula1.model.dto.cliente;

public record ClienteContaGetResponseDTO(
        Integer id,
        String nome,
        Long cpf) {
}
