package api.jogos.unicesumar.apijogos.controller;
import api.jogos.unicesumar.apijogos.dto.LoginRequest;
import api.jogos.unicesumar.apijogos.model.Jogo;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class JogoController {

    private List<Jogo> jogos = new ArrayList<>();

    public JogoController() {

        jogos.add(new Jogo(
                1L,
                "Jogo inserido no código 1",
                "Aventura",
                10,
                "teste 1"
        ));

        jogos.add(new Jogo(
                2L,
                "Jogo inserido no código 2",
                "Esporte",
                7,
                "teste 2"
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        if (
                "usuario@esoft.com".equals(request.getEmail()) &&
                        "Abc123".equals(request.getPassword())
        ) {

            Map<String, String> response = new HashMap<>();
            response.put("token", UUID.randomUUID().toString());

            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/jogos")
    public ResponseEntity<List<Jogo>> listarJogos() {
        return ResponseEntity.ok(jogos);
    }

    @GetMapping("/jogos/{id}")
    public ResponseEntity<Jogo> buscarPorId(@PathVariable Long id) {

        for (Jogo jogo : jogos) {
            if (id.equals(jogo.getId())) {
                return ResponseEntity.ok(jogo);
            }
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/jogos")
    public ResponseEntity<Jogo> criarJogo(@Valid @RequestBody Jogo jogo) {

        Long novoId = (long) (jogos.size() + 1);

        jogo.setId(novoId);

        jogos.add(jogo);

        return ResponseEntity.status(HttpStatus.CREATED).body(jogo);
    }

    @PutMapping("/jogos/{id}")
    public ResponseEntity<Jogo> atualizarJogo(
            @PathVariable Long id,
            @Valid @RequestBody Jogo jogoAtualizado
    ) {


        for (Jogo jogo : jogos) {

            if (id.equals(jogo.getId())) {

                jogo.setNome(jogoAtualizado.getNome());
                jogo.setTipo(jogoAtualizado.getTipo());
                jogo.setNota(jogoAtualizado.getNota());
                jogo.setReview(jogoAtualizado.getReview());

                return ResponseEntity.ok(jogo);
            }
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/jogos/{id}")
    public ResponseEntity<Void> deletarJogo(@PathVariable Long id) {

        Iterator<Jogo> iterator = jogos.iterator();

        while (iterator.hasNext()) {

            Jogo jogo = iterator.next();

            if (id.equals(jogo.getId())) {

                iterator.remove();

                return ResponseEntity.noContent().build();
            }
        }

        return ResponseEntity.notFound().build();
    }
}
