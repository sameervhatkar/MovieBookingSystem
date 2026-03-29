package com.sameervhatkar.MovieBookingSystem.controller;

import com.sameervhatkar.MovieBookingSystem.dto.*;
import com.sameervhatkar.MovieBookingSystem.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/admin/movies")
    public ResponseEntity<CreateMovieResponseDTO> createMovie(@Valid @RequestBody CreateMovieRequestDTO requestDTO) {
        CreateMovieResponseDTO response = movieService.createMovie(requestDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/admin/movies/{id}")
    public ResponseEntity<GetMovieResponseDTOForClient> getMovieByIdForClient(@PathVariable Long id) {
        GetMovieResponseDTOForClient responseDTOForClient = movieService.getMovieById(id);
        return ResponseEntity.ok(responseDTOForClient);
    }

    @DeleteMapping("/admin/movies/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/admin/movies")
    public ResponseEntity<CreateMovieResponseDTO> updateMovie(@RequestBody UpdateMovieRequestDTO requestDTO) {
        CreateMovieResponseDTO response = movieService.updateMovie(requestDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/movies/title/{title}")
    public ResponseEntity<GetMovieResponseDTOForUser> getMovieByTitleForUser(@PathVariable String title) {
        GetMovieResponseDTOForUser movieResponseDTOForUser = movieService.getMovieByTitle(title);
        return ResponseEntity.ok(movieResponseDTOForUser);
    }

    @GetMapping("/movies")
    public ResponseEntity<List<GetMovieResponseDTOForUser>> getAllActiveMovies() {
        List<GetMovieResponseDTOForUser> movieResponseDTOForUserList = movieService.getAllMoviesWhoseStatusIsActive();
        return ResponseEntity.ok(movieResponseDTOForUserList);
    }

}
