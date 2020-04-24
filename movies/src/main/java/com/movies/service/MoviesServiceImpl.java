package com.movies.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movies.dao.MoviesRepository;
import com.movies.exception.MovieNotFoundException;
import com.movies.model.Movie;

@Service
public class MoviesServiceImpl implements MoviesService{

	@Autowired
	MoviesRepository movieDao;
	
	@Override
	public Movie addMovie(Movie movie) {
		return movieDao.save(movie);
		
	}

	@Override
	public Movie editMovie(Movie movieDetails, long movieId) throws MovieNotFoundException {
		Movie movie = movieDao.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException(movieId));

		movie.setCategory(movieDetails.getCategory());
		movie.setRating(movieDetails.getRating());
		movie.setTitle(movieDetails.getTitle());
        Movie updatedMovie = movieDao.save(movie);
        return updatedMovie;
		
	}

	@Override
	public void deleteMovie(long movieId) throws MovieNotFoundException {
		Movie book = movieDao.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException(movieId));
		movieDao.delete(book);
		
	}

	@Override
	public Movie find(long movieId) throws MovieNotFoundException {
		 return movieDao.findById(movieId)
	                .orElseThrow(() -> new MovieNotFoundException(movieId));
	}

	@Override
	public List<Movie> findAll() {
		return movieDao.findAll();
	}

}
