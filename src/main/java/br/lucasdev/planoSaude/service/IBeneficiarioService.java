package br.lucasdev.planoSaude.service;

import br.lucasdev.planoSaude.model.Beneficiario;
import br.lucasdev.planoSaude.model.Documento;

import java.util.List;
import java.util.Set;

public interface IBeneficiarioService {
    List<Beneficiario> obterBeneficiarios();
    Beneficiario obterBeneficiario(Long idBeneficiario);
    List<Documento> obterDocumentos(Beneficiario beneficiario);
    Beneficiario cadastrarBeneficiario(Beneficiario beneficiario, Set<Documento> documentos);
    Beneficiario atualizarBeneficiario(Beneficiario beneficiario);
    void excluirBeneficiario(Beneficiario beneficiario);

}
