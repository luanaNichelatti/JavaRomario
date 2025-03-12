package primeiroSpring.aula1.model.dto.conta;

import jakarta.validation.constraints.*;
import primeiroSpring.aula1.model.entity.Cliente;
import primeiroSpring.aula1.model.entity.Conta;

public record ContaPostRequestDTO (
        @NotNull
        Integer idtitular,
        @Positive @NotNull
        Integer numero,
        @PositiveOrZero
        Double limite){

    public Conta convert(Cliente cliente) {
        return Conta.builder().titular(cliente).numero(numero).limite(limite).build();
    }
}