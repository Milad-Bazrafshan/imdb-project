package org.clw.imdb.exception;

public class ActorTypeException extends ImdbExceptionHandling {
    public ActorTypeException() {
    }

    public ActorTypeException(String message) {
        super(message);
    }

    public ActorTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ActorTypeException(Throwable cause) {
        super(cause);
    }
}
