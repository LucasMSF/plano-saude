package br.lucasdev.planoSaude.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Schema(description = "Objeto de erro da API")
public class ErroDto {
    @Schema(description = "Código HTTP do erro", example = "NOT_FOUND")
    private HttpStatus codigo;
    @Schema(description = "Mensagens de erro detalhadas")
    private List<String> mensagem;
}
