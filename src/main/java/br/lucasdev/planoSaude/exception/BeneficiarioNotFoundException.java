package br.lucasdev.planoSaude.exception;

import jakarta.persistence.EntityNotFoundException;

public class BeneficiarioNotFoundException extends EntityNotFoundException {
    public BeneficiarioNotFoundException() {
        super("Beneficiário não encontrado");
    }
}
