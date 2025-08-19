package alura.ForumHub.controller;

import alura.ForumHub.model.Curso;
import alura.ForumHub.service.CursoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService cursoService;

    @Autowired
    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public ResponseEntity<List<Curso>> listarTodos() {
        return ResponseEntity.ok(cursoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscarPorId(@PathVariable Long id) {
        Curso curso = cursoService.encontrarPorId(id);
        if (curso == null) {
            throw new EntityNotFoundException("Curso não encontrado com o ID: " + id);
        }
        return ResponseEntity.ok(curso);
    }

    @PostMapping
    public ResponseEntity<Curso> criar(@RequestBody @Valid Curso curso) {
        Curso novoCurso = cursoService.salvar(curso);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novoCurso.getId())
                .toUri();
        return ResponseEntity.created(location).body(novoCurso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> atualizar(@PathVariable Long id, @RequestBody @Valid Curso curso) {
        Curso cursoAtualizado = cursoService.atualizar(id, curso);
        if (cursoAtualizado == null) {
            throw new EntityNotFoundException("Curso não encontrado para atualização com o ID: " + id);
        }
        return ResponseEntity.ok(cursoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        cursoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}