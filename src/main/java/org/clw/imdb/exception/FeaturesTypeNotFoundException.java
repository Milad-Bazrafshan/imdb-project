package org.clw.imdb.exception;

public class FeaturesTypeNotFoundException extends ImdbExceptionHandling {
    public FeaturesTypeNotFoundException() {
        super();
    }

    public FeaturesTypeNotFoundException(String message) {
        super(message);
    }

    public FeaturesTypeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public FeaturesTypeNotFoundException(Throwable cause) {
        super(cause);
    }
}
