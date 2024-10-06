package dev.sameer.bookmyshowreviseedition.Service;

import dev.sameer.bookmyshowreviseedition.DTOs.MovieRequestDTO;
import dev.sameer.bookmyshowreviseedition.DTOs.MovieResponseDTO;
import dev.sameer.bookmyshowreviseedition.Entity.Movie;
import dev.sameer.bookmyshowreviseedition.Exceptions.MovieNotFoundException;
import dev.sameer.bookmyshowreviseedition.Mapper.EntityToDTOMapper;
import dev.sameer.bookmyshowreviseedition.Repo.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class MovieServiceImpl implements MovieService{

    @Autowired
    private MovieRepo movieRepo;

    @Override
    public MovieResponseDTO createMovie(MovieRequestDTO movieRequestDTO) {
        Movie movie = new Movie();
        movie.setMovieName(movieRequestDTO.getMovieName());
        movie.setReleaseDate(movieRequestDTO.getReleaseDate());
        movie.setActorsName(movieRequestDTO.getActorsName());
        movie.setDirectorName(movieRequestDTO.getDirectorName());
        movie.setMovieDuration(movieRequestDTO.getMovieDuration());

        movieRepo.save(movie);
        return EntityToDTOMapper.convertMovieEntitytoDTO(movie);
    }

    @Override
    public int duration(UUID movieId) {
        return movieRepo.getReferenceById(movieId).getMovieDuration();
    }

    @Override
    public Movie getMovie(UUID movieId) {
        return movieRepo.findById(movieId).orElseThrow(
                () -> new MovieNotFoundException("Movie not found")
        );
    }
}
