package br.lucasdev.planoSaude.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class CadastroBeneficiarioDto {
    @NotNull
    private String nome;
    @NotNull
    private String telefone;
    @NotNull
    private LocalDate dataNascimento;
    private Set<CadastroDocumentoDto> documentos;
}
