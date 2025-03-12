package primeiroSpring.aula1.model.dto.conta;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import primeiroSpring.aula1.model.entity.Cliente;
import primeiroSpring.aula1.model.entity.Conta;

public record ContaPutRequestDTO(
        @NotNull
        Cliente titular,
        @PositiveOrZero
        Double limite){

    public Conta convert() {
        return Conta.builder().titular(titular).limite(limite).build();
    }
}