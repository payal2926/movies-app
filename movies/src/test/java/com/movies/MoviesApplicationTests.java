package com.movies;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.movies.dao.MoviesRepository;
import com.movies.exception.MovieNotFoundException;
import com.movies.model.Movie;
import com.movies.service.MoviesService;

@SpringBootTest
public class MoviesApplicationTests {

	@Autowired
	MoviesService movieService;
	
	@MockBean
	MoviesRepository movieDao;
	
	@Test
	public void editMovieTest() throws MovieNotFoundException {
		Movie movie =new Movie();
		movie.setCategory("Test Category");
		movie.setRating(2);
		movie.setTitle("Movie Title");
		movie.setId(2l);
		when(movieDao.findById(2l)).thenReturn(Optional.of(movie));
		movieService.editMovie(movie,2l);
		//rating updated 
		movie.setRating(3);
		when(movieDao.save(movie)).thenReturn(movie);
		//ASSERTIONS
		assertEquals(3,movie.getRating());
	}
	
	@Test
	public void addMovieTest() {
		Movie movie =new Movie();
		movie.setCategory("Test Category");
		movie.setRating(2);
		movie.setTitle("Movie Title");
		when(movieService.addMovie(movie)).thenReturn(movie);
		assertNotNull(movie);
	}
	
	@Test
	public void getMovieById() throws MovieNotFoundException {
		Movie movie = new Movie();
		movie.setCategory("Test Category");
		movie.setRating(2);
		movie.setTitle("Movie Title");
		movie.setId(2l);
		when(movieDao.findById(2l)).thenReturn(Optional.of(movie));
	}
	
	@Test
	public void deleteMovies() throws MovieNotFoundException{
		Movie movie = new Movie();
		movie.setId(2l);
		when(movieDao.findById(2l)).thenReturn(Optional.of(movie));
		movieService.deleteMovie(2l);
	}
}
