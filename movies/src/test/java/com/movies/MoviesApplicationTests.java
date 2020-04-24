package com.movies;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.movies.dao.MoviesRepository;
import com.movies.model.Movie;
import com.movies.service.MoviesService;
import com.movies.service.MoviesServiceImpl;

@SpringBootTest
public class MoviesApplicationTests {

	@Autowired
	MoviesServiceImpl movieService;
	
	@MockBean
	MoviesRepository movieDao;
	
	@Test
	public void addMovieTest() {
		Movie movie =new Movie();
		when(movieService.addMovie(movie)).thenReturn(movie);
	}
	

}
