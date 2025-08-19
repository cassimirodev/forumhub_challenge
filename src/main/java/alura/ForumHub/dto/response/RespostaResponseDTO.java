package alura.ForumHub.dto.response;

import alura.ForumHub.model.Resposta;

import java.time.Instant;

public record RespostaResponseDTO(
        Long id,
        String mensagem,
        Instant dataCriacao,
        Boolean solucao,
        String nomeAutor,
        Long topicoId
) {
    public RespostaResponseDTO(Resposta resposta) {
        this(
                resposta.getId(),
                resposta.getMensagem(),
                resposta.getDataCriacao(),
                resposta.getSolucao(),
                resposta.getUser().getNome(),
                resposta.getTopico().getId()
        );
    }
}