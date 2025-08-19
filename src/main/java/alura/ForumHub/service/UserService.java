package alura.ForumHub.service;

import alura.ForumHub.model.User;
import alura.ForumHub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> listarTodos() {
        return userRepository.findAll();
    }

    public User encontrarPorId(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User salvar(User user) {
        return userRepository.save(user);
    }

    public void excluir(Long id) {
        userRepository.deleteById(id);
    }

    public User atualizar(Long id, User usuarioAtualizado) {
        return userRepository.findById(id)
                .map(usuarioExistente -> {
                    usuarioExistente.setNome(usuarioAtualizado.getNome());
                    usuarioExistente.setEmail(usuarioAtualizado.getEmail());
                    return userRepository.save(usuarioExistente);
                }).orElse(null);
    }
}