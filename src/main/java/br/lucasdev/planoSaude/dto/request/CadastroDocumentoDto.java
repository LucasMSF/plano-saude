package br.lucasdev.planoSaude.dto.request;

import br.lucasdev.planoSaude.enums.TipoDocumento;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CadastroDocumentoDto {
    @NotNull
    private TipoDocumento tipoDocumento;
    @NotNull
    private String descricao;
}
