package primeiroSpring.aula1.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import primeiroSpring.aula1.model.dto.ExceptionHandlerResponseDTO;
import primeiroSpring.aula1.model.exceptions.MesmoTitularException;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@RestControllerAdvice

// esse é entre a cadeira e o monitor
public class ControllerExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionHandlerResponseDTO handleGenericException(Exception e) {
        String mensagem = e.getMessage() != null ? e.getMessage() : "Erro interno no servidor";
        return new ExceptionHandlerResponseDTO(mensagem, LocalDateTime.now());
    }
    // esse é pra pegar erro do usuário
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionHandlerResponseDTO capturaDeErro(NoSuchElementException e){
        return new ExceptionHandlerResponseDTO(e.getMessage(), LocalDateTime.now());
    };

    @ExceptionHandler(MesmoTitularException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionHandlerResponseDTO capturaDeErro(MesmoTitularException e){
        return new ExceptionHandlerResponseDTO(e.getMessage(), LocalDateTime.now());
    }
}
