package dev.sameer.bookmyshowreviseedition.Service;

import dev.sameer.bookmyshowreviseedition.DTOs.MovieRequestDTO;
import dev.sameer.bookmyshowreviseedition.DTOs.MovieResponseDTO;
import dev.sameer.bookmyshowreviseedition.DTOs.MovieUpdateRequestDTO;
import dev.sameer.bookmyshowreviseedition.Entity.Actor;
import dev.sameer.bookmyshowreviseedition.Entity.Movie;
import dev.sameer.bookmyshowreviseedition.Exceptions.MovieNotFoundException;
import dev.sameer.bookmyshowreviseedition.Mapper.EntityToDTOMapper;
import dev.sameer.bookmyshowreviseedition.Repo.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class MovieServiceImpl implements MovieService{

    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private ActorService actorService;

    @Override
    public MovieResponseDTO createMovie(MovieRequestDTO movieRequestDTO) {
        Movie movie = new Movie();
        movie.setMovieName(movieRequestDTO.getMovieName());
        movie.setReleaseDate(movieRequestDTO.getReleaseDate());
        movie.setDirectorName(movieRequestDTO.getDirectorName());
        movie.setMovieDuration(movieRequestDTO.getMovieDuration());

        List<String> actorListInMovie = movieRequestDTO.getActorsName();
        List<Actor> actors = new ArrayList<>();
        for(String actorName : actorListInMovie) {
            Actor actor = actorService.findActor(actorName);
            if(actor == null)
                 actor = actorService.createActor(actorName);
            actors.add(actor);
        }
        movie.setActors(actors);

        movieRepo.save(movie);
        return EntityToDTOMapper.convertMovieEntitytoDTO(movie);
    }

    @Override
    public MovieResponseDTO getMovieById(UUID movieId) {
        Movie movie = movieRepo.findById(movieId).orElseThrow(
                () -> new MovieNotFoundException("Movie not found")
        );
        return EntityToDTOMapper.convertMovieEntitytoDTO(movie);
    }

    @Override
    public MovieResponseDTO getMovieByName(String movieName) {
        Movie movie = movieRepo.findMovieByMovieNameIgnoreCase(movieName).orElseThrow(
                () -> new MovieNotFoundException("Movie not found")
        );
        return EntityToDTOMapper.convertMovieEntitytoDTO(movie);
    }

    @Override
    public List<MovieResponseDTO> getMovies() {
        List<Movie> movies = movieRepo.findAll();
        List<MovieResponseDTO> movieResponseDTOS = new ArrayList<>();
        for(Movie movie : movies)
            movieResponseDTOS.add(EntityToDTOMapper.convertMovieEntitytoDTO(movie));
        return movieResponseDTOS;
    }

    @Override
    public MovieResponseDTO updateMovie(UUID movieId, MovieUpdateRequestDTO movieUpdateRequestDTO) {
        Movie movie = movieRepo.findById(movieId).orElseThrow(
                () -> new MovieNotFoundException("Movie not found")
        );
        movie.setMovieName(movieUpdateRequestDTO.getMovieName());
        movie.setReleaseDate(movieUpdateRequestDTO.getReleaseDate());
        movie.setDirectorName(movieUpdateRequestDTO.getDirectorName());
        movie.setMovieDuration(movieUpdateRequestDTO.getMovieDuration());

        List<String> actorListInMovie = movieUpdateRequestDTO.getActorsName();
        List<Actor> actors = new ArrayList<>();
        for(String actorName : actorListInMovie) {
            Actor actor = actorService.findActor(actorName);
            if(actor == null)
                actor = actorService.createActor(actorName);
            actors.add(actor);
        }
        movie.setActors(actors);

        movieRepo.save(movie);
        return EntityToDTOMapper.convertMovieEntitytoDTO(movie);
    }

    @Override
    public Boolean deleteMovie(UUID movieId) {
        Movie movie = movieRepo.findById(movieId).orElseThrow(
                () -> new MovieNotFoundException("Movie not found")
        );
        movieRepo.delete(movie);
        return true;
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
