package alura.ForumHub.controller;

import alura.ForumHub.service.TopicoService;
import alura.ForumHub.dto.request.TopicoRequestDTO;
import alura.ForumHub.dto.response.TopicoResponseDTO;
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
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoService topicoService;

    @Autowired
    public TopicoController(TopicoService topicoService) {
        this.topicoService = topicoService;
    }

    @GetMapping
    public ResponseEntity<List<TopicoResponseDTO>> listarTodos() {
        List<TopicoResponseDTO> topicos = topicoService.listarTodos().stream()
                .map(TopicoResponseDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoResponseDTO> buscarPorId(@PathVariable Long id) {
        var topico = topicoService.encontrarPorId(id);
        return ResponseEntity.ok(new TopicoResponseDTO(topico));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicoResponseDTO> criar(@RequestBody @Valid TopicoRequestDTO dados) {
        var novoTopico = topicoService.criar(dados);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novoTopico.getId())
                .toUri();
        return ResponseEntity.created(location).body(new TopicoResponseDTO(novoTopico));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid TopicoRequestDTO dados) {
        var topicoAtualizado = topicoService.atualizar(id, dados);
        return ResponseEntity.ok(new TopicoResponseDTO(topicoAtualizado));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        topicoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}