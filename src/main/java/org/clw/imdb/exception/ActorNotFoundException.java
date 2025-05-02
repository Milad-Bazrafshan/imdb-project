package org.clw.imdb.exception;

public class ActorNotFoundException extends ImdbExceptionHandling {
    public ActorNotFoundException() {
        super();
    }

    public ActorNotFoundException(String message) {
        super(message);
    }

    public ActorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ActorNotFoundException(Throwable cause) {
        super(cause);
    }
}
