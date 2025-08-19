package alura.ForumHub.controller;

import alura.ForumHub.model.User;
import alura.ForumHub.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> listarTodos() {
        return ResponseEntity.ok(userService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> buscarPorId(@PathVariable Long id) {
        User user = userService.encontrarPorId(id);
        if (user == null) {
            throw new EntityNotFoundException("Usuário não encontrado com o ID: " + id);
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> criar(@RequestBody @Valid User user) {
        User novoUser = userService.salvar(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novoUser.getId())
                .toUri();
        return ResponseEntity.created(location).body(novoUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> atualizar(@PathVariable Long id, @RequestBody @Valid User user) {
        User userAtualizado = userService.atualizar(id, user);
        if (userAtualizado == null) {
            throw new EntityNotFoundException("Usuário não encontrado para atualização com o ID: " + id);
        }
        return ResponseEntity.ok(userAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        userService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}