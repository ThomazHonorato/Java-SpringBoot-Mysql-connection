package medi.voll.api.infrastructure.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratar404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratar400(MethodArgumentNotValidException ex) {
        var erro = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erro.stream().map(DadosErroValidacao::new).toList());
    }

    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro) {
            this(
                    erro.getField(),
                    erro.getDefaultMessage()
                    );
        }
    }
}

   /* @ExceptionHandler({MethodArgumentNotValidException.class, Exception.class})
    public ResponseEntity<List<DadosErroValidacao>> tratar400(MethodArgumentNotValidException ex, Exception exc){
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        String[] className = new String[1];
        String[] methodName = new String[1];
        StackTraceElement[] stackTrace = ex.getStackTrace();
        for (StackTraceElement element : stackTrace) {
            if (element.getClassName().contains("Controller")) {
                className[0] = element.getClassName();
                methodName[0] = element.getMethodName();
                break;
            }
        }
        List<DadosErroValidacao> errors = fieldErrors.stream()
                .map(error -> new DadosErroValidacao(error.getField(), error.getDefaultMessage(), className[0], methodName[0]))
                .toList();
        return ResponseEntity.badRequest().body(errors);
    }

    private record DadosErroValidacao(String campo, String mensagem, String classe, String metodo){}
}*/

