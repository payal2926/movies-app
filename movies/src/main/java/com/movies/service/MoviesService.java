package com.movies.service;

import java.util.List;

import com.movies.exception.MovieNotFoundException;
import com.movies.model.Movie;

public interface MoviesService {

	public Movie addMovie(Movie movie);
	 
	public Movie editMovie(Movie movie, long movieId) throws MovieNotFoundException;
 
	public void deleteMovie(long movieId) throws MovieNotFoundException;

	public Movie find(long movieId) throws MovieNotFoundException;
 
	public List<Movie> findAll();
}
