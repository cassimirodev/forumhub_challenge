package alura.ForumHub.dto.response;

import alura.ForumHub.model.Enums.StatusTopico;
import alura.ForumHub.model.Topico;

import java.time.Instant;

public record TopicoResponseDTO(
        Long id,
        String titulo,
        String mensagem,
        Instant dataCriacao,
        StatusTopico status,
        String nomeAutor,
        String nomeCurso
) {
    public TopicoResponseDTO(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getStatus(),
                topico.getUser().getNome(),
                topico.getCurso().getNome()
        );
    }
}