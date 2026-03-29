package com.sameervhatkar.MovieBookingSystem.service;

import com.sameervhatkar.MovieBookingSystem.Mapper.EntityToDTOMapper;
import com.sameervhatkar.MovieBookingSystem.dto.*;
import com.sameervhatkar.MovieBookingSystem.entity.Movie;
import com.sameervhatkar.MovieBookingSystem.enums.MovieStatus;
import com.sameervhatkar.MovieBookingSystem.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public CreateMovieResponseDTO createMovie(CreateMovieRequestDTO request) {
        Movie movie = new Movie();
        movie.setTitle(request.getTitle());
        movie.setDurationInMinutes(request.getDurationInMinutes());
        movie.setGenre(request.getGenre());
        movie.setOriginalLanguage(request.getOriginalLanguage());
        movie.setReleaseDate(request.getReleaseDate());
        movie.setStatus(MovieStatus.INACTIVE);
        Movie savedMovie = movieRepository.save(movie);

        return EntityToDTOMapper.convertCreateMovieEntityToDTOForClient(savedMovie);
    }

    public GetMovieResponseDTOForClient getMovieById(long id) {
        Movie movie = movieRepository.getReferenceById(id);
        return EntityToDTOMapper.convertMovieEntityToDTOForClient(movie);
    }

    public GetMovieResponseDTOForUser getMovieByTitle(String title) {
        Movie movie = movieRepository.getMovieByTitleIgnoreCase(title);
        return EntityToDTOMapper.convertMovieEntityToDTOForUser(movie);
    }

    public List<GetMovieResponseDTOForUser> getAllMoviesWhoseStatusIsActive() {
        List<Movie> movies = movieRepository.findAll();
        List<GetMovieResponseDTOForUser> responseDTOS = new ArrayList<>();
        for(Movie movie : movies) {
            responseDTOS.add(EntityToDTOMapper.convertMovieEntityToDTOForUser(movie));
        }

        return responseDTOS;
    }

    public void deleteMovie(long id) {
        movieRepository.deleteById(id);
    }

    public CreateMovieResponseDTO updateMovie(UpdateMovieRequestDTO request) {
        Movie movie = movieRepository.getReferenceById(request.getId());
        movie.setTitle(request.getTitle());
        movie.setDurationInMinutes(request.getDurationInMinutes());
        movie.setGenre(request.getGenre());
        movie.setOriginalLanguage(request.getOriginalLanguage());
        movie.setReleaseDate(request.getReleaseDate());
        movie.setStatus(MovieStatus.INACTIVE);
        Movie savedMovie = movieRepository.save(movie);

        return EntityToDTOMapper.convertCreateMovieEntityToDTOForClient(savedMovie);
    }
}
