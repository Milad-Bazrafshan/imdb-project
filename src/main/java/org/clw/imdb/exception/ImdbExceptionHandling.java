package org.clw.imdb.exception;

import java.io.Serial;
import java.util.Locale;
import java.util.ResourceBundle;

public class ImdbExceptionHandling extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -7034897190745766939L;
    private static String message;
    private static String localizedMessage;

    public ImdbExceptionHandling() {
        super();
    }

    public ImdbExceptionHandling(String errorMessage) {
        super(getTranslateMessage());
        message = errorMessage;
        printStackTrace();
    }

    public ImdbExceptionHandling(String message, Throwable cause) {
        super(message, cause);
    }

    public ImdbExceptionHandling(Throwable cause) {
        super(cause);
    }

    protected ImdbExceptionHandling(String message, Throwable cause,
                                    boolean enableSuppression,
                                    boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public static String getTranslateMessage() {
        Locale locale = new Locale("fa");
        ResourceBundle resourceBundle = ResourceBundle.getBundle("exception", locale);
        try {
            localizedMessage = resourceBundle.getString(getExceptionName());
        }catch (Exception e) {
            localizedMessage = resourceBundle.getString("UnknownError");
            e.printStackTrace();
        }
        return localizedMessage;
    }

    public static String getExceptionName() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String className = null;
        for (var element : stackTrace) {
            try {
                Class<?> clazz = Class.forName(element.getClassName());
                if (ImdbExceptionHandling.class.isAssignableFrom(clazz) && !clazz.equals(ImdbExceptionHandling.class)) {
                    className = clazz.getSimpleName();
                    break;
                }
            } catch (ClassNotFoundException ignored) {
            }
        }
        return className;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getLocalizedMessage() {
        return localizedMessage;
    }
}
