package br.lucasdev.planoSaude.advice;

import br.lucasdev.planoSaude.dto.response.ErroDto;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomControllerAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    public final ResponseEntity<Object> handleDBEntityNotFound(EntityNotFoundException ex, WebRequest webRequest) {
        ErroDto erro = new ErroDto();
        erro.setCodigo(HttpStatus.NOT_FOUND) ;
        List<String> mensagem = new ArrayList<>();
        mensagem.add(ex.getMessage());
        erro.setMensagem(mensagem);
        return new ResponseEntity<Object>(erro, erro.getCodigo());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        ErroDto erro = new ErroDto();
        erro.setCodigo(HttpStatus.BAD_REQUEST);
        List<String> mensagem = new ArrayList<String>();
        List<String> collect = ex.getBindingResult().getFieldErrors().stream().filter(Objects::nonNull)
                .map(m -> (m.getField() + " " + m.getDefaultMessage())).collect(Collectors.toList());
        mensagem.addAll(collect);
        erro.setMensagem(mensagem);
        return new ResponseEntity<Object>(erro, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ApiResponse(responseCode = "500", description = "Erro interno",
            content = @Content(schema = @Schema(implementation = ErroDto.class)))
    public ResponseEntity<Object> handleError(Exception ex) {
        ErroDto erro = new ErroDto();
        erro.setCodigo(HttpStatus.INTERNAL_SERVER_ERROR);
        List<String> mensagem = new ArrayList<String>();
        mensagem.add(ex.getMessage());
        erro.setMensagem(mensagem);
        return new ResponseEntity<Object>(erro, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}