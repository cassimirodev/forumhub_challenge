package alura.ForumHub.service;

import alura.ForumHub.model.Perfil;
import alura.ForumHub.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfilService {

    private final PerfilRepository perfilRepository;

    @Autowired
    public PerfilService(PerfilRepository perfilRepository) {
        this.perfilRepository = perfilRepository;
    }

    public List<Perfil> listarTodos() {
        return perfilRepository.findAll();
    }

    public Perfil encontrarPorId(Long id) {
        return perfilRepository.findById(id).orElse(null);
    }

    public Perfil salvar(Perfil perfil) {
        return perfilRepository.save(perfil);
    }

    public void excluir(Long id) {
        perfilRepository.deleteById(id);
    }

    public Perfil atualizar(Long id, Perfil perfilAtualizado) {
        return perfilRepository.findById(id)
                .map(perfilExistente -> {
                    perfilExistente.setRoleUser(perfilAtualizado.getRoleUser());
                    return perfilRepository.save(perfilExistente);
                }).orElse(null);
    }
}