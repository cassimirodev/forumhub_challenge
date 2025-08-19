package alura.ForumHub.service;

import alura.ForumHub.model.Curso;
import alura.ForumHub.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    @Autowired
    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public List<Curso> listarTodos() {
        return cursoRepository.findAll();
    }

    public Curso encontrarPorId(Long id) {
        return cursoRepository.findById(id).orElse(null);
    }

    public Curso salvar(Curso curso) {
        return cursoRepository.save(curso);
    }

    public void excluir(Long id) {
        cursoRepository.deleteById(id);
    }

    public Curso atualizar(Long id, Curso cursoAtualizado) {
        return cursoRepository.findById(id)
                .map(cursoExistente -> {
                    cursoExistente.setNome(cursoAtualizado.getNome());
                    cursoExistente.setCategoria(cursoAtualizado.getCategoria());
                    return cursoRepository.save(cursoExistente);
                }).orElse(null);
    }
}