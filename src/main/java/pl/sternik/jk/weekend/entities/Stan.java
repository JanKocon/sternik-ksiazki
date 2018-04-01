package pl.sternik.jk.weekend.entities;

public enum Stan {
    NOWA("Nowa"),
    UZYWANA("Używana"),
    DO_SPRZEDANIA("Do sprzedania"),
    DUBLET("Dublet");


    public static final Stan[] ALL = { NOWA, DO_SPRZEDANIA, DUBLET };

    private final String name;
    private Stan(final String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
}