package com.movies.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movies.model.Movie;

public interface MoviesRepository extends JpaRepository<Movie, Long> { 

}
