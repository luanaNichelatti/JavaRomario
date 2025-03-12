package primeiroSpring.aula1.model.dto;

import java.time.LocalDateTime;

public record ExceptionHandlerResponseDTO(
        String mensagem,
        LocalDateTime horario
) {

}
