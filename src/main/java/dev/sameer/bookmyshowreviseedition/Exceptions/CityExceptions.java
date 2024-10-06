package dev.sameer.bookmyshowreviseedition.Exceptions;

import dev.sameer.bookmyshowreviseedition.DTOs.ExceptionResponseDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CityExceptions {

    @ExceptionHandler(CityNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> handlesCityNotFound(CityNotFoundException cityNotFoundException) {
        ExceptionResponseDTO responseDTO = new ExceptionResponseDTO(
                cityNotFoundException.getMessage(),
                404
        );
        return new ResponseEntity(responseDTO.getCode(), HttpStatus.NOT_FOUND);
    }
}
