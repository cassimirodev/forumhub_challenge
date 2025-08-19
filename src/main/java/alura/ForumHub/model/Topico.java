package alura.ForumHub.model;

import alura.ForumHub.model.Enums.RoleUser;
import alura.ForumHub.model.Enums.StatusTopico;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "topico")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "titulo", nullable = false)
    private String titulo;

    @NotNull
    @Column(name = "mensagem", nullable = false, columnDefinition = "TEXT")
    private String mensagem;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "data_criacao")
    private Instant dataCriacao;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusTopico status;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @OneToMany(mappedBy = "topico")
    private Set<Resposta> respostas = new LinkedHashSet<>();




}