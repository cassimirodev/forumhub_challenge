package alura.ForumHub.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicoRequestDTO(
        @NotBlank(message = "O título é obrigatório")
        String titulo,

        @NotBlank(message = "A mensagem é obrigatória")
        String mensagem,

        @NotNull(message = "O ID do curso é obrigatório")
        Long cursoId,

        @NotNull(message = "O ID do usuario é obrigatório")
        Long userId
) {
}