package pl.sternik.jk.weekend.entities;

import java.math.BigDecimal;
import java.util.Date;

public class Ksiazka {

    private Long numerKatalogowy;
    private long wartoscRynkowa;
    private String tytul;
    private String opis;
    private String waluta;
    private BigDecimal cenaNabycia;
    private Date dataWydania;
    private String autor;
    private Status status;

    @Override
    public String toString() {
        return "Ksiazka{" +
                "numerKatalogowy=" + numerKatalogowy +
                ", wartoscRynkowa=" + wartoscRynkowa +
                ", tytul='" + tytul + '\'' +
                ", opis='" + opis + '\'' +
                ", waluta='" + waluta + '\'' +
                ", cenaNabycia=" + cenaNabycia +
                ", dataWydania=" + dataWydania +
                ", autor='" + autor + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ksiazka ksiazka = (Ksiazka) o;

        if (wartoscRynkowa != ksiazka.wartoscRynkowa) return false;
        if (numerKatalogowy != null ? !numerKatalogowy.equals(ksiazka.numerKatalogowy) : ksiazka.numerKatalogowy != null)
            return false;
        if (tytul != null ? !tytul.equals(ksiazka.tytul) : ksiazka.tytul != null) return false;
        if (opis != null ? !opis.equals(ksiazka.opis) : ksiazka.opis != null) return false;
        if (waluta != null ? !waluta.equals(ksiazka.waluta) : ksiazka.waluta != null) return false;
        if (cenaNabycia != null ? !cenaNabycia.equals(ksiazka.cenaNabycia) : ksiazka.cenaNabycia != null) return false;
        if (dataWydania != null ? !dataWydania.equals(ksiazka.dataWydania) : ksiazka.dataWydania != null) return false;
        if (autor != null ? !autor.equals(ksiazka.autor) : ksiazka.autor != null) return false;
        return status == ksiazka.status;
    }

    @Override
    public int hashCode() {
        int result = numerKatalogowy != null ? numerKatalogowy.hashCode() : 0;
        result = 31 * result + (int) (wartoscRynkowa ^ (wartoscRynkowa >>> 32));
        result = 31 * result + (tytul != null ? tytul.hashCode() : 0);
        result = 31 * result + (opis != null ? opis.hashCode() : 0);
        result = 31 * result + (waluta != null ? waluta.hashCode() : 0);
        result = 31 * result + (cenaNabycia != null ? cenaNabycia.hashCode() : 0);
        result = 31 * result + (dataWydania != null ? dataWydania.hashCode() : 0);
        result = 31 * result + (autor != null ? autor.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    public static Ksiazka drukujKsiake(Long numerKatalogowy, long wartoscRynkowa, String tytul, String opis, String waluta, BigDecimal cenaNabycia, Date dataWydania, String autor, Status status) {
        Ksiazka ksiazka = new Ksiazka();
        ksiazka.numerKatalogowy = numerKatalogowy;
        ksiazka.wartoscRynkowa = wartoscRynkowa;
        ksiazka.tytul = tytul;
        ksiazka.opis = opis;
        ksiazka.waluta = waluta;
        ksiazka.cenaNabycia = cenaNabycia;
        ksiazka.dataWydania = dataWydania;
        ksiazka.autor = autor;
        ksiazka.status = status;
        return ksiazka;
    }

    // ----------------------------------GETTERS - SETTERS----------------------------------

    public Long getNumerKatalogowy() {
        return numerKatalogowy;
    }

    public void setNumerKatalogowy(Long numerKatalogowy) {
        this.numerKatalogowy = numerKatalogowy;
    }

    public long getWartoscRynkowa() {
        return wartoscRynkowa;
    }

    public void setWartoscRynkowa(long wartoscRynkowa) {
        this.wartoscRynkowa = wartoscRynkowa;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getWaluta() {
        return waluta;
    }

    public void setWaluta(String waluta) {
        this.waluta = waluta;
    }

    public BigDecimal getCenaNabycia() {
        return cenaNabycia;
    }

    public void setCenaNabycia(BigDecimal cenaNabycia) {
        this.cenaNabycia = cenaNabycia;
    }

    public Date getDataWydania() {
        return dataWydania;
    }

    public void setDataWydania(Date dataWydania) {
        this.dataWydania = dataWydania;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


}
