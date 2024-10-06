package dev.sameer.bookmyshowreviseedition.Service;


import dev.sameer.bookmyshowreviseedition.DTOs.MovieRequestDTO;
import dev.sameer.bookmyshowreviseedition.DTOs.MovieResponseDTO;
import dev.sameer.bookmyshowreviseedition.Entity.Movie;

import java.util.UUID;

public interface MovieService {
    MovieResponseDTO createMovie(MovieRequestDTO movieRequestDTO);

    int duration(UUID movieId);
    Movie getMovie(UUID movieId);
}
