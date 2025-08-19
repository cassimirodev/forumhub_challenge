package alura.ForumHub.service;

import alura.ForumHub.dto.request.RespostaRequestDTO;
import alura.ForumHub.model.Resposta;
import alura.ForumHub.repository.RespostaRepository;
import alura.ForumHub.repository.TopicoRepository;
import alura.ForumHub.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class RespostaService {

    private final RespostaRepository respostaRepository;
    private final TopicoRepository topicoRepository;
    private final UserRepository userRepository;

    @Autowired
    public RespostaService(RespostaRepository respostaRepository, TopicoRepository topicoRepository, UserRepository userRepository) {
        this.respostaRepository = respostaRepository;
        this.topicoRepository = topicoRepository;
        this.userRepository = userRepository;
    }

    public List<Resposta> listarTodas() {
        return respostaRepository.findAll();
    }

    public Resposta encontrarPorId(Long id) {
        return respostaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Resposta não encontrada com o ID: " + id));
    }

    @Transactional
    public Resposta criar(RespostaRequestDTO dados) {
        var topico = topicoRepository.findById(dados.topicoId())
                .orElseThrow(() -> new EntityNotFoundException("Tópico não encontrado com o ID: " + dados.topicoId()));
        var usuario = userRepository.findById(dados.userId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o ID: " + dados.userId()));

        var resposta = new Resposta();
        resposta.setMensagem(dados.mensagem());
        resposta.setTopico(topico);
        resposta.setUser(usuario);
        resposta.setDataCriacao(Instant.now());
        resposta.setSolucao(false); // Por padrão, uma nova resposta não é a solução

        return respostaRepository.save(resposta);
    }

    @Transactional
    public Resposta atualizar(Long id, String novaMensagem) {
        var resposta = encontrarPorId(id);
        resposta.setMensagem(novaMensagem);
        return respostaRepository.save(resposta);
    }

    @Transactional
    public void excluir(Long id) {
        if (!respostaRepository.existsById(id)) {
            throw new EntityNotFoundException("Resposta não encontrada com o ID: " + id);
        }
        respostaRepository.deleteById(id);
    }
}