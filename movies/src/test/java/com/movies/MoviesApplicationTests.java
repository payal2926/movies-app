package com.movies;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.movies.dao.MoviesRepository;
import com.movies.exception.MovieNotFoundException;
import com.movies.model.Movie;
import com.movies.service.MoviesService;

@SpringBootTest
public class MoviesApplicationTests {

	MoviesService movieService;

	MoviesRepository movieDao;

	@Before
	public void setUp() {
		movieDao = Mockito.mock(MoviesRepository.class);
		movieService = new MoviesService();
		movieService.setMovieDao(movieDao);
	}

	@Test
	public void editMovieTest() throws MovieNotFoundException {
		Movie movie = new Movie();
		movie.setCategory("Test Category");
		movie.setRating(2d);
		movie.setTitle("Movie Title");
		movie.setId(2l);
		when(movieDao.findById(2l)).thenReturn(Optional.of(movie));
		// rating updated
		movie.setRating(3d);
		when(movieDao.save(movie)).thenReturn(movie);
		// ASSERTIONS
		Movie updatedMovie = movieService.editMovie(movie, 2l);
		assertEquals(Double.valueOf(3), updatedMovie.getRating());
	}

	@Test
	public void addMovieTest() {
		Movie movie = new Movie();
		movie.setCategory("Test Category");
		movie.setRating(2d);
		movie.setTitle("Movie Title");
		when(movieDao.save(movie)).thenReturn(movie);
		Movie addedMovie = movieService.addMovie(movie);
		assertNotNull(addedMovie);
	}

	@Test
	public void getMovieById() throws MovieNotFoundException {
		Movie movie = new Movie();
		movie.setCategory("Test Category");
		movie.setRating(2d);
		movie.setTitle("Movie Title");
		movie.setId(2l);
		when(movieDao.findById(2l)).thenReturn(Optional.of(movie));
		movie = movieService.find(2l);
		assertNotNull(movie);
	}

	@Test
	public void deleteMovies() throws MovieNotFoundException {
		Movie movie = new Movie();
		movie.setId(2l);
		when(movieDao.findById(2l)).thenReturn(Optional.of(movie));
		movieService.deleteMovie(2l);
		// verifying that the method is called once
		verify(movieDao, times(1)).findById(2l);
		// No movie should return with id 2
		when(movieDao.findById(2l)).thenReturn(Optional.empty());
		// asserting that movie not found exception thrown
		Assertions.assertThrows(MovieNotFoundException.class, () -> {
			movieService.find(2l);
		});
	}

	@Test(expected = MovieNotFoundException.class)
	public void deleteMoviesException() throws MovieNotFoundException {
		when(movieDao.findById(2l)).thenReturn(Optional.empty());
		movieService.deleteMovie(2l);
	}
}
