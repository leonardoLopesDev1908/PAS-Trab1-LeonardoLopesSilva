package com.acmepolling.acme.common;

import com.acmepolling.acme.exceptions.ForaHorarioApuracaoException;
import com.acmepolling.acme.exceptions.ForaHorarioVotacaoException;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequest(BadRequestException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(ForaHorarioApuracaoException.class)
    public ResponseEntity<String> handleForaHorarioApuracaoException(ForaHorarioApuracaoException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fora do horário de apuração");
    }

    @ExceptionHandler(ForaHorarioVotacaoException.class)
    public ResponseEntity<String> handleForaHorarioVotacaoException(ForaHorarioVotacaoException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fora do horário de votação");
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Entidade não encontrada");
    }
}
