package dev.sameer.bookmyshowreviseedition.Exceptions;

import dev.sameer.bookmyshowreviseedition.DTOs.ExceptionResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptions {

    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<ExceptionResponseDTO> handlesEmailExist(EmailAlreadyExistException exception) {
        ExceptionResponseDTO responseDTO = new ExceptionResponseDTO(
                exception.getMessage(),
                409
        );
        return new ResponseEntity(responseDTO.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UnauthorizeUserException.class)
    public ResponseEntity<ExceptionResponseDTO> handlesUnauthorizeUsers(UnauthorizeUserException exception) {
        ExceptionResponseDTO responseDTO = new ExceptionResponseDTO(
                exception.getMessage(),
                401
        );
        return new ResponseEntity(responseDTO.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
