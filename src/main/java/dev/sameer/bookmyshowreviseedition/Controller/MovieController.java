package dev.sameer.bookmyshowreviseedition.Controller;

import dev.sameer.bookmyshowreviseedition.DTOs.MovieRequestDTO;
import dev.sameer.bookmyshowreviseedition.DTOs.MovieResponseDTO;
import dev.sameer.bookmyshowreviseedition.DTOs.MovieUpdateRequestDTO;
import dev.sameer.bookmyshowreviseedition.Service.MovieService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/addMovie")
    public ResponseEntity<MovieResponseDTO> addMovie(@RequestBody MovieRequestDTO movieRequestDTO) {
        return ResponseEntity.ok(movieService.createMovie(movieRequestDTO));
    }

    @GetMapping("/getMovieById/{movieId}")
    public ResponseEntity<MovieResponseDTO> getMovieById(@PathVariable UUID movieId) {
        return ResponseEntity.ok(movieService.getMovieById(movieId));
    }

    @GetMapping("/getMovieByName/{movieName}")
    public ResponseEntity<MovieResponseDTO> getMovieById(@PathVariable String movieName) {
        return ResponseEntity.ok(movieService.getMovieByName(movieName));
    }

    @GetMapping("/getMovies")
    public ResponseEntity<List<MovieResponseDTO>> getMovieById() {
        return ResponseEntity.ok(movieService.getMovies());
    }

    @PutMapping("/updateMovie/{movieId}")
    public ResponseEntity<MovieResponseDTO> updateMovie(@PathVariable UUID movieId, @RequestBody MovieUpdateRequestDTO movieUpdateRequestDTO) {
        return ResponseEntity.ok(movieService.updateMovie(movieId, movieUpdateRequestDTO));
    }

    @DeleteMapping("deleteMovie/{movieId}")
    public ResponseEntity<Boolean> deleteMovie(@PathVariable UUID movieId) {
        return ResponseEntity.ok(movieService.deleteMovie(movieId));
    }
}
