package alura.ForumHub.service;

import alura.ForumHub.model.Resposta;
import alura.ForumHub.repository.RespostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RespostaService {

    private final RespostaRepository respostaRepository;

    @Autowired
    public RespostaService(RespostaRepository respostaRepository) {
        this.respostaRepository = respostaRepository;
    }

    public List<Resposta> listarTodas() {
        return respostaRepository.findAll();
    }

    public Resposta encontrarPorId(Long id) {
        return respostaRepository.findById(id).orElse(null);
    }

    public Resposta salvar(Resposta resposta) {
        return respostaRepository.save(resposta);
    }

    public void excluir(Long id) {
        respostaRepository.deleteById(id);
    }

    public Resposta atualizar(Long id, Resposta respostaAtualizada) {
        return respostaRepository.findById(id)
                .map(respostaExistente -> {
                    respostaExistente.setMensagem(respostaAtualizada.getMensagem());
                    respostaExistente.setSolucao(respostaAtualizada.getSolucao());
                    return respostaRepository.save(respostaExistente);
                }).orElse(null);
    }
}