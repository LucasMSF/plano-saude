package br.lucasdev.planoSaude.repository;

import br.lucasdev.planoSaude.model.Beneficiario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BeneficiarioRepository extends JpaRepository<Beneficiario, Long> {
}
