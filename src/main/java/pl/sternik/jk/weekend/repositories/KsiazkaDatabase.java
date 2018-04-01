package pl.sternik.jk.weekend.repositories;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import pl.sternik.jk.weekend.entities.Ksiazka;
import pl.sternik.jk.weekend.entities.Stan;

import java.util.ArrayList;
import java.util.List;

@Repository
@Qualifier("tablica")
public class KsiazkaDatabase implements KsiazkaRepository {
//// TODO: zamienic tablice na liste

    Ksiazka[] baza;
    public KsiazkaDatabase()
    {
        baza = new Ksiazka[20];
        Ksiazka k = new Ksiazka();
        k.setNumerKatalogowy(0L);
        k.setAutor("Jan Kowalski");
        k.setCena(129.99);
        k.setDataWydania(KsiazkaUtils.getDateWithYearAndMonthForDay(2014,5,2));
        k.setGatunek("Liryka");
        k.setOpis("No książka");
        k.setStatus(Stan.DO_SPRZEDANIA);
        k.setTytul("Tutuł");
        baza[0] = k;

        k = new Ksiazka();
        k.setNumerKatalogowy(2L);
        k.setAutor("Kowal Jankowski");
        k.setCena(14.99);
        k.setDataWydania(KsiazkaUtils.getDateWithYearAndMonthForDay(2017,2,12));
        k.setGatunek("Fantasy");
        k.setOpis("No książka do czytania");
        k.setStatus(Stan.NOWA);
        k.setTytul("Havana banana");
        //??
        baza[2] = k;

    }
    public KsiazkaDatabase(int rozmiarBazy)
    {
        baza = new Ksiazka[rozmiarBazy];
    }
    @Override
    public Ksiazka create(Ksiazka ksiazka) throws KsiazkaAlreadyExistsException {

        if (ksiazka.getNumerKatalogowy() != null && baza[ksiazka.getNumerKatalogowy().intValue()] != null) {
            if (ksiazka.getNumerKatalogowy().equals(baza[ksiazka.getNumerKatalogowy().intValue()].getNumerKatalogowy())) {
                throw new KsiazkaAlreadyExistsException("Już jest ksiazka o takim numerze.");
            }
        }
        for (int i = 0; i < baza.length; i++) {
            if (baza[i] == null) {
                baza[i] = ksiazka;
                ksiazka.setNumerKatalogowy((long) i);
                return ksiazka;
            }
        }
        throw new RuntimeException("Brak miejsca w tablicy");

    }

    @Override
    public Ksiazka readById(Long id) throws NoSuchKsiazkaException {
        int _id = id.intValue();
        if (!sprawdzPoprawnoscNumeruKatalogowego(_id) || czyWolne(_id)) {
            throw new NoSuchKsiazkaException();
        }
        return baza[_id];
    }
    private boolean czyWolne(int id) {
        if(baza[id]!= null)
            return false;
        return true;
    }

    @Override
    public Ksiazka update(Ksiazka ksiazka) throws NoSuchKsiazkaException {
        int numerKatalogowy = ksiazka.getNumerKatalogowy().intValue();
        if (!sprawdzPoprawnoscNumeruKatalogowego(numerKatalogowy)) {
            throw new NoSuchKsiazkaException("Nie poprawny numer katologowy");
        }

        Ksiazka m = baza[ksiazka.getNumerKatalogowy().intValue()];
        if (m == null) {
            throw new NoSuchKsiazkaException("Brak takiej monety.");
        } else {
            baza[ksiazka.getNumerKatalogowy().intValue()] = ksiazka;
        }
        return ksiazka;
    }

    @Override
    public void deleteById(Long id) throws NoSuchKsiazkaException {
        int numerKatalogowy = id.intValue();
        if (!sprawdzPoprawnoscNumeruKatalogowego(numerKatalogowy)) {
            throw new NoSuchKsiazkaException("Nie poprawny numer katologowy");
        }
        baza[numerKatalogowy] = null;
    }

    @Override
    public List<Ksiazka> findAll() {
        List<Ksiazka> tmp = new ArrayList<>();
        for (int i = 0; i < baza.length; i++) {
            if (baza[i] != null)
                tmp.add(baza[i]);
        }
        return tmp;
    }
    private boolean sprawdzPoprawnoscNumeruKatalogowego(int numerKatalogowy) {
        if (numerKatalogowy < 0 || numerKatalogowy >= baza.length) {
            System.out.println("Zły numer katalogowy");
            return false;
        }
        return true;
    }
}
