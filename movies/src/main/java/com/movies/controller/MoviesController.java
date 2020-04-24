package com.movies.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movies.exception.MovieError;
import com.movies.exception.MovieNotFoundException;
import com.movies.model.Constants;
import com.movies.model.Movie;
import com.movies.service.MoviesService;

@RestController
@RequestMapping("/api")
public class MoviesController {

	@Autowired
	MoviesService movieService;
	
	// Get All Movies
    @GetMapping("/movies")
    public List<Movie> getAllMovies() {
        return movieService.findAll();
    }

    // Create a new Movie
    @PostMapping("/movies/add")
    public ResponseEntity<Object> createMovie(@RequestBody Movie movie) {
    	if( 0.5 > movie.getRating() || movie.getRating() >5.0) {
    		return ResponseEntity.badRequest().body(new MovieError(Constants.ERROR_CODE, Constants.RATING_ERROR_MESSAGE));
    	}
        return ResponseEntity.ok(movieService.addMovie(movie));
    }

    // Get a Single Movie
    @GetMapping("/movies/{id}")
    public Movie getMovieById(@PathVariable(value = "id") Long movieId) throws MovieNotFoundException {
        return movieService.find(movieId);
    }

    // Update a Movie
    @PutMapping("/movies/{id}")
    public ResponseEntity<Object> updateMovie(@PathVariable(value = "id") Long movieId,
                           @Valid @RequestBody Movie movieDetails) throws MovieNotFoundException {
    	if( 0.5 > movieDetails.getRating() || movieDetails.getRating() >5.0) {
    		return ResponseEntity.badRequest().body(new MovieError(Constants.ERROR_CODE, Constants.RATING_ERROR_MESSAGE));
    	}
    	return	ResponseEntity.ok(movieService.editMovie(movieDetails, movieId));
    }

    // Delete a Movie
    @DeleteMapping("/movies/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable(value = "id") Long movieId) throws MovieNotFoundException {
    	movieService.deleteMovie(movieId);
    	return ResponseEntity.ok("Movie with id: "+movieId+" deleted successfully");
    }
}
