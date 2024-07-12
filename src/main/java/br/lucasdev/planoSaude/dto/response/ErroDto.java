package br.lucasdev.planoSaude.dto.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class ErroDto {
    private HttpStatus codigo;
    private List<String> mensagem;

}
