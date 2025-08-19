package alura.ForumHub.controller;

import alura.ForumHub.model.Perfil;
import alura.ForumHub.service.PerfilService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/perfis")
public class PerfilController {

    private final PerfilService perfilService;

    @Autowired
    public PerfilController(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    @GetMapping
    public ResponseEntity<List<Perfil>> listarTodos() {
        return ResponseEntity.ok(perfilService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Perfil> buscarPorId(@PathVariable Long id) {
        Perfil perfil = perfilService.encontrarPorId(id);
        if (perfil == null) {
            throw new EntityNotFoundException("Perfil não encontrado com o ID: " + id);
        }
        return ResponseEntity.ok(perfil);
    }

    @PostMapping
    public ResponseEntity<Perfil> criar(@RequestBody @Valid Perfil perfil) {
        Perfil novoPerfil = perfilService.salvar(perfil);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novoPerfil.getId())
                .toUri();
        return ResponseEntity.created(location).body(novoPerfil);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Perfil> atualizar(@PathVariable Long id, @RequestBody @Valid Perfil perfil) {
        Perfil perfilAtualizado = perfilService.atualizar(id, perfil);
        if (perfilAtualizado == null) {
            throw new EntityNotFoundException("Perfil não encontrado para atualização com o ID: " + id);
        }
        return ResponseEntity.ok(perfilAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        perfilService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}