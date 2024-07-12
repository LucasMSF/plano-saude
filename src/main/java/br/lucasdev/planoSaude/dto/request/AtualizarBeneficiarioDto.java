package br.lucasdev.planoSaude.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class AtualizarBeneficiarioDto {
    @NotNull
    private String nome;
    @NotNull
    private String telefone;
    @NotNull
    private LocalDate dataNascimento;
}
