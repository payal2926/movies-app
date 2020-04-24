package com.movies.exception;

public class MovieNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;
	public MovieNotFoundException(long movieId) {
	        super(String.format("Movie is not found with id : '%s'", movieId));
	        }
	}