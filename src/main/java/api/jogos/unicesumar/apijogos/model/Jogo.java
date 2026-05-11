package api.jogos.unicesumar.apijogos.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Jogo {

    private Long id;

    @NotBlank(message = "Nome obrigatório")
    private String nome;

    @NotBlank(message = "Tipo obrigatório")
    private String tipo;

    @NotNull(message = "Nota obrigatória")
    private Integer nota;

    @NotBlank(message = "Review obrigatória")
    private String review;

    public Jogo() {
    }

    public Jogo(Long id, String nome, String tipo, Integer nota, String review) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.nota = nota;
        this.review = review;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}