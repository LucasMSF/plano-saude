package br.lucasdev.planoSaude.service.impl;

import br.lucasdev.planoSaude.exception.BeneficiarioNotFoundException;
import br.lucasdev.planoSaude.model.Beneficiario;
import br.lucasdev.planoSaude.model.Documento;
import br.lucasdev.planoSaude.repository.BeneficiarioRepository;
import br.lucasdev.planoSaude.repository.DocumentoRepository;
import br.lucasdev.planoSaude.service.IBeneficiarioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BeneficiarioService implements IBeneficiarioService {
    private final BeneficiarioRepository beneficiarioRepository;
    private final DocumentoRepository documentoRepository;

    @Autowired
    public BeneficiarioService(BeneficiarioRepository beneficiarioRepository, DocumentoRepository documentoRepository) {
        this.beneficiarioRepository = beneficiarioRepository;
        this.documentoRepository = documentoRepository;
    }

    @Override
    public List<Beneficiario> obterBeneficiarios() {
        return this.beneficiarioRepository.findAll();
    }

    @Override
    public List<Documento> obterDocumentos(Beneficiario beneficiario) {
        return this.documentoRepository.findAllByBeneficiario(beneficiario);
    }

    @Override
    public Beneficiario cadastrarBeneficiario(Beneficiario beneficiario, Set<Documento> documentos) {
        this.beneficiarioRepository.save(beneficiario);

        documentos = documentos
                .stream()
                .peek(documento -> documento.setBeneficiario(beneficiario)).collect(Collectors.toSet());
        this.documentoRepository.saveAll(documentos);

        beneficiario.setDocumentos(documentos);

        return beneficiario;
    }

    @Override
    public Beneficiario atualizarBeneficiario(Beneficiario beneficiario) {
        return this.beneficiarioRepository.save(beneficiario);
    }

    @Override
    public void excluirBeneficiario(Beneficiario beneficiario) {
        this.beneficiarioRepository.delete(beneficiario);
    }

    @Override
    public Beneficiario obterBeneficiario(Long idBeneficiario) {
        return this.beneficiarioRepository.findById(idBeneficiario).orElseThrow(BeneficiarioNotFoundException::new);
    }
}
