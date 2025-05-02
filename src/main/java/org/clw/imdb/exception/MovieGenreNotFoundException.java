package org.clw.imdb.exception;

public class MovieGenreNotFoundException extends ImdbExceptionHandling {
    public MovieGenreNotFoundException() {
        super();
    }

    public MovieGenreNotFoundException(String message) {
        super(message);
    }

    public MovieGenreNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MovieGenreNotFoundException(Throwable cause) {
        super(cause);
    }
}
