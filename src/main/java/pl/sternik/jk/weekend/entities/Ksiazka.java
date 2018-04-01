package pl.sternik.jk.weekend.entities;

import java.math.BigDecimal;
import java.util.Date;

public class Ksiazka {

    private Long numerKatalogowy;
    private String tytul;
    private String opis;
    private double cena;
    private String autor;
    private String gatunek;
    private Date dataWydania;
    private Stan status;

    public static Ksiazka produceKsiazka(Long numerKatalogowy, String tytul, String opis, double cena, String autor, String gatunek, Date dataWydania, Stan status) {
       Ksiazka ksiaka = new Ksiazka();

        ksiaka.numerKatalogowy = numerKatalogowy;
        ksiaka.tytul = tytul;
        ksiaka.opis = opis;
        ksiaka.cena = cena;
        ksiaka.autor = autor;
        ksiaka.gatunek = gatunek;
        ksiaka.dataWydania = dataWydania;
        ksiaka.status = status;
        return ksiaka;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ksiazka ksiazka = (Ksiazka) o;

        if (Double.compare(ksiazka.cena, cena) != 0) return false;
        if (numerKatalogowy != null ? !numerKatalogowy.equals(ksiazka.numerKatalogowy) : ksiazka.numerKatalogowy != null)
            return false;
        if (tytul != null ? !tytul.equals(ksiazka.tytul) : ksiazka.tytul != null) return false;
        if (opis != null ? !opis.equals(ksiazka.opis) : ksiazka.opis != null) return false;
        if (autor != null ? !autor.equals(ksiazka.autor) : ksiazka.autor != null) return false;
        if (gatunek != null ? !gatunek.equals(ksiazka.gatunek) : ksiazka.gatunek != null) return false;
        if (dataWydania != null ? !dataWydania.equals(ksiazka.dataWydania) : ksiazka.dataWydania != null) return false;
        return status == ksiazka.status;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = numerKatalogowy != null ? numerKatalogowy.hashCode() : 0;
        result = 31 * result + (tytul != null ? tytul.hashCode() : 0);
        result = 31 * result + (opis != null ? opis.hashCode() : 0);
        temp = Double.doubleToLongBits(cena);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (autor != null ? autor.hashCode() : 0);
        result = 31 * result + (gatunek != null ? gatunek.hashCode() : 0);
        result = 31 * result + (dataWydania != null ? dataWydania.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Ksiazka{" +
                "numerKatalogowy=" + numerKatalogowy +
                ", tytul='" + tytul + '\'' +
                ", opis='" + opis + '\'' +
                ", cena=" + cena +
                ", autor='" + autor + '\'' +
                ", gatunek='" + gatunek + '\'' +
                ", dataWydania=" + dataWydania +
                ", status=" + status +
                '}';
    }


    // ----------------------------------GETTERS - SETTERS----------------------------------

    public Long getNumerKatalogowy() {
        return numerKatalogowy;
    }

    public void setNumerKatalogowy(Long numerKatalogowy) {
        this.numerKatalogowy = numerKatalogowy;
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

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGatunek() {
        return gatunek;
    }

    public void setGatunek(String gatunek) {
        this.gatunek = gatunek;
    }

    public Date getDataWydania() {
        return dataWydania;
    }

    public void setDataWydania(Date dataWydania) {
        this.dataWydania = dataWydania;
    }

    public Stan getStatus() {
        return status;
    }

    public void setStatus(Stan status) {
        this.status = status;
    }

}
