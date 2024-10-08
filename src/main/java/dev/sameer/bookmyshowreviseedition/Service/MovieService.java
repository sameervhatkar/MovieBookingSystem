package dev.sameer.bookmyshowreviseedition.Service;


import dev.sameer.bookmyshowreviseedition.DTOs.MovieRequestDTO;
import dev.sameer.bookmyshowreviseedition.DTOs.MovieResponseDTO;
import dev.sameer.bookmyshowreviseedition.DTOs.MovieUpdateRequestDTO;
import dev.sameer.bookmyshowreviseedition.Entity.Movie;

import java.util.List;
import java.util.UUID;

public interface MovieService {
    MovieResponseDTO createMovie(MovieRequestDTO movieRequestDTO);

    MovieResponseDTO getMovieById(UUID movieId);
    MovieResponseDTO getMovieByName(String movieName);
    List<MovieResponseDTO> getMovies();

    MovieResponseDTO updateMovie(UUID movieId, MovieUpdateRequestDTO movieUpdateRequestDTO);

    Boolean deleteMovie(UUID movieId);


    int duration(UUID movieId);
    Movie getMovie(UUID movieId);
}
