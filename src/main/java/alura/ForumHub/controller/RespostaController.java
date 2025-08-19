package alura.ForumHub.controller;

import alura.ForumHub.dto.request.RespostaRequestDTO;
import alura.ForumHub.dto.response.RespostaResponseDTO;
import alura.ForumHub.service.RespostaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/respostas")
public class RespostaController {

    private final RespostaService respostaService;

    @Autowired
    public RespostaController(RespostaService respostaService) {
        this.respostaService = respostaService;
    }

    @GetMapping
    public ResponseEntity<List<RespostaResponseDTO>> listarTodas() {
        List<RespostaResponseDTO> respostas = respostaService.listarTodas().stream()
                .map(RespostaResponseDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(respostas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespostaResponseDTO> buscarPorId(@PathVariable Long id) {
        var resposta = respostaService.encontrarPorId(id);
        return ResponseEntity.ok(new RespostaResponseDTO(resposta));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<RespostaResponseDTO> criar(@RequestBody @Valid RespostaRequestDTO dados) {
        var novaResposta = respostaService.criar(dados);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novaResposta.getId())
                .toUri();
        return ResponseEntity.created(location).body(new RespostaResponseDTO(novaResposta));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<RespostaResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid RespostaRequestDTO dados) {
        // Apenas a mensagem pode ser atualizada
        var respostaAtualizada = respostaService.atualizar(id, dados.mensagem());
        return ResponseEntity.ok(new RespostaResponseDTO(respostaAtualizada));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        respostaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}