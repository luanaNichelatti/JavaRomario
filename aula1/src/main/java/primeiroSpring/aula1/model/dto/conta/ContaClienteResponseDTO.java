package primeiroSpring.aula1.model.dto.conta;

public record ContaClienteResponseDTO(
        Integer id,
        Double saldo,
        Double limite,
        Integer numero
) {


}
