package pl.sternik.jk.weekend.repositories;

public class NoSuchKsiazkaException extends Exception {
    private static final long serialVersionUID = -8555511053844242536L;

    public NoSuchKsiazkaException(String string) {
        super(string);
    }

    public NoSuchKsiazkaException() {
    }
}
