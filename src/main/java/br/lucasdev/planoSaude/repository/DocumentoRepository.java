package br.lucasdev.planoSaude.repository;

import br.lucasdev.planoSaude.model.Beneficiario;
import br.lucasdev.planoSaude.model.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {
    List<Documento> findAllByBeneficiario(Beneficiario beneficiario);
}
