
package com.ezequielcarracedo.gestionincidencias.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ezequielcarracedo.gestionincidencias.dto.ApiError;

@RestControllerAdvice
public class GlobalExceptionHandler {

        // PROPIES
        @ExceptionHandler({
                        IncidenciaNoEncontradaException.class,
                        UsuarioNoEncontradoException.class,

        })
        public ResponseEntity<ApiError> handleNotFound(RuntimeException ex) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(new ApiError(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
        }

        // PROPIES
        @ExceptionHandler({
                        IncidenciaNoCreadaException.class,
                        UsuarioNoCreadoException.class

        })
        public ResponseEntity<ApiError> handleNotCreated(RuntimeException ex) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(new ApiError(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
        }



        
        // VALIDACIONS
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex) {
                String msg = ex.getBindingResult().getFieldErrors().stream()
                                .findFirst()
                                .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
                                .orElse("Validació incorrecta");

                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(new ApiError(HttpStatus.BAD_REQUEST.value(), msg));
        }

        // Errors comuns
        @ExceptionHandler(Exception.class)
        public ResponseEntity<ApiError> handleAny(Exception ex) {

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error intern"));
        }

        // JSON invalid
        @ExceptionHandler(HttpMessageNotReadableException.class)
        public ResponseEntity<ApiError> handleNotReadable(HttpMessageNotReadableException ex) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(new ApiError(HttpStatus.BAD_REQUEST.value(),
                                                "JSON INVALIDO O TIPO DE DATO INCORRECTO"));
        }

}
