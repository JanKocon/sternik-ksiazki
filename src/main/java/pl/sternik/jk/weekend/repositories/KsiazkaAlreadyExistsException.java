package pl.sternik.jk.weekend.repositories;

public class KsiazkaAlreadyExistsException extends Exception {
    private static final long serialVersionUID = -4576295597218170159L;

    public KsiazkaAlreadyExistsException() {
    }

    public KsiazkaAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
                                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public KsiazkaAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public KsiazkaAlreadyExistsException(String message) {
        super(message);
    }

    public KsiazkaAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
