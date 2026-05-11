package api.jogos.unicesumar.apijogos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // tratar body errado
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation() {

        Map<String, String> response = new HashMap<>();
        response.put("erro", "Dados inválidos");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // tratar requisição sem body
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleMissingBody() {

        Map<String, String> response = new HashMap<>();
        response.put("erro", "Body obrigatório");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // ID inválido
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, String>> handleTypeMismatch() {

        Map<String, String> response = new HashMap<>();
        response.put("erro", "ID inválido");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    //rota errada
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Map<String, String>> handleMethodNotAllowed() {

        Map<String, String> response = new HashMap<>();
        response.put("erro", "Método não permitido");

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(response);
    }

    //rota errada também
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, String>> handleNoSuchElement() {

        Map<String, String> response = new HashMap<>();
        response.put("erro", "Recurso não encontrado");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }


}