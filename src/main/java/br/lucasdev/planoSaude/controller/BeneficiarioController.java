package br.lucasdev.planoSaude.controller;

import br.lucasdev.planoSaude.dto.request.AtualizarBeneficiarioDto;
import br.lucasdev.planoSaude.dto.request.CadastroBeneficiarioDto;
import br.lucasdev.planoSaude.model.Beneficiario;
import br.lucasdev.planoSaude.model.Documento;
import br.lucasdev.planoSaude.service.IBeneficiarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "beneficiarios")
@Tag(name = "Beneficiários", description = "Gerenciamento de beneficiários")
public class BeneficiarioController {
    private final IBeneficiarioService beneficiarioService;
    private final ModelMapper modelMapper;

    @Autowired
    public BeneficiarioController(ModelMapper modelMapper, IBeneficiarioService beneficiarioService) {
        this.modelMapper = modelMapper;
        this.beneficiarioService = beneficiarioService;
    }

    @GetMapping
    @Operation(summary = "Listar Beneficiários")
    public ResponseEntity<List<Beneficiario>> listar() {
        return new ResponseEntity<>(this.beneficiarioService.obterBeneficiarios(), HttpStatus.OK);
    }
    @GetMapping(path = "{id}/documentos")
    @Operation(summary = "Listar Documentos do beneficiário")
    public ResponseEntity<List<Documento>> listarDocumentos(@PathVariable Long id) {
        Beneficiario beneficiario = this.beneficiarioService.obterBeneficiario(id);
        return new ResponseEntity<>(this.beneficiarioService.obterDocumentos(beneficiario), HttpStatus.OK);
    }
    @PostMapping
    @Operation(summary = "Cadastrar beneficiário")
    public ResponseEntity<Beneficiario> cadastrar(@Valid @RequestBody CadastroBeneficiarioDto cadastroBeneficiarioDto) {
        Beneficiario novoBeneficiario = modelMapper.map(cadastroBeneficiarioDto, Beneficiario.class);
        Set<Documento> documentos = cadastroBeneficiarioDto
                .getDocumentos()
                .stream()
                .map(cadastroDocumentoDto -> modelMapper.map(cadastroDocumentoDto, Documento.class))
                .collect(Collectors.toSet());

        Beneficiario beneficiarioCadastrado = beneficiarioService.cadastrarBeneficiario(novoBeneficiario, documentos);
        return new ResponseEntity<>(beneficiarioCadastrado, HttpStatus.OK);
    }
    @PutMapping(path = "{id}")
    @Operation(summary = "Atualizar beneficiário")
    public ResponseEntity<Beneficiario> atualizar(@PathVariable Long id, @Valid @RequestBody AtualizarBeneficiarioDto atualizarBeneficiarioDto) {
        Beneficiario beneficiario = this.beneficiarioService.obterBeneficiario(id);
        modelMapper.map(atualizarBeneficiarioDto, beneficiario);
        beneficiario = this.beneficiarioService.atualizarBeneficiario(beneficiario);
        return new ResponseEntity<>(beneficiario, HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    @Operation(summary = "Excluir beneficiário")
    public ResponseEntity<Beneficiario> excluir(@PathVariable Long id) {
        Beneficiario beneficiario = this.beneficiarioService.obterBeneficiario(id);
        this.beneficiarioService.excluirBeneficiario(beneficiario);
        return new ResponseEntity<>(beneficiario, HttpStatus.OK);
    }
}
