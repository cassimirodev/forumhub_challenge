package alura.ForumHub.service;

import alura.ForumHub.model.Enums.StatusTopico;
import alura.ForumHub.model.Topico;
import alura.ForumHub.repository.CursoRepository;
import alura.ForumHub.repository.TopicoRepository;
import alura.ForumHub.repository.UserRepository;
import alura.ForumHub.dto.request.TopicoRequestDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class TopicoService {

    private final TopicoRepository topicoRepository;
    private final CursoRepository cursoRepository;
    private final UserRepository userRepository;

    @Autowired
    public TopicoService(TopicoRepository topicoRepository, CursoRepository cursoRepository, UserRepository userRepository) {
        this.topicoRepository = topicoRepository;
        this.cursoRepository = cursoRepository;
        this.userRepository = userRepository;
    }

    public List<Topico> listarTodos() {
        return topicoRepository.findAll();
    }

    public Topico encontrarPorId(Long id) {
        return topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico não encontrado com o ID: " + id));
    }

    @Transactional
    public Topico criar(TopicoRequestDTO dados) {
        var autor = userRepository.findById(dados.userId())
                .orElseThrow(() -> new EntityNotFoundException("Autor não encontrado com o ID: " + dados.userId()));
        var curso = cursoRepository.findById(dados.cursoId())
                .orElseThrow(() -> new EntityNotFoundException("Curso não encontrado com o ID: " + dados.cursoId()));

        var topico = new Topico();
        topico.setTitulo(dados.titulo());
        topico.setMensagem(dados.mensagem());
        topico.setUser(autor);
        topico.setCurso(curso);
        topico.setDataCriacao(Instant.now());
        topico.setStatus(StatusTopico.PENDENTE);

        return topicoRepository.save(topico);
    }

    @Transactional
    public void excluir(Long id) {
        if (!topicoRepository.existsById(id)) {
            throw new EntityNotFoundException("Tópico não encontrado com o ID: " + id);
        }
        topicoRepository.deleteById(id);
    }

    @Transactional
    public Topico atualizar(Long id, TopicoRequestDTO dados) {
        var topico = encontrarPorId(id); // Reutiliza o método que já lança exceção se não encontrar

        // Atualiza apenas os campos permitidos
        topico.setTitulo(dados.titulo());
        topico.setMensagem(dados.mensagem());

        // O autor e o curso não são alterados na atualização, conforme a regra de negócio comum.
        // Se precisar permitir a alteração, adicione a lógica aqui.

        return topicoRepository.save(topico);
    }
}