package pl.sternik.jk.weekend.repositories;

import pl.sternik.jk.weekend.entities.Ksiazka;
import pl.sternik.jk.weekend.entities.Stan;
import pl.sternik.jk.weekend.entities.Status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class KsiazkaRepositoryImpl implements KsiazkaRepository {


private List<Ksiazka> ksiazki = new ArrayList<Ksiazka>(){
    private static final long serialVersionUID = 1L;
    {
        //Long numerKatalogowy, String tytul, String opis, double cena, String autor, String gatunek, Date dataWydania, Status status

        add(Ksiazka.produceKsiazka(1L,"W pustyni i w puszczy","Książka jak książka",100d,"Henryk Sienkiewicz","Literatura młodzieżowa",KsiazkaUtils.getDateWithYearAndMonthForDay(1911,1,2), Stan.UZYWANA));
        add(Ksiazka.produceKsiazka(2L,"Gra o tron","Książka jak książka",229.99,"George R.R. Martin","Fantasy",KsiazkaUtils.getDateWithYearAndMonthForDay(1996,8,1),Stan.NOWA));
        add(Ksiazka.produceKsiazka(3L,"Hobbit","Książka jak książka",45.50,"John Ronald Reuel Tolkien","Fantasy",KsiazkaUtils.getDateWithYearAndMonthForDay(1937,9,21),Stan.UZYWANA));
        add(Ksiazka.produceKsiazka(4L,"Lalka","Książka jak książka",25d,"Bolesław Prus","Powieść społeczna",KsiazkaUtils.getDateWithYearAndMonthForDay(1890,5,2),Stan.UZYWANA));
        add(Ksiazka.produceKsiazka(5L,"Thinking in java","Książka jak książka",119.99,"Bruce Eckel","Poradnik do javy",KsiazkaUtils.getDateWithYearAndMonthForDay(2017,3,3),Stan.NOWA));
        add(Ksiazka.produceKsiazka(6L,"Arrow","Książka jak książka",69.99,"Bruce Lee","Fikcja",KsiazkaUtils.getDateWithYearAndMonthForDay(2012,5,7),Stan.NOWA));
        add(Ksiazka.produceKsiazka(7L,"Egzekutor","Książka jak książka",74.99,"Chris Carter","Dreszczowiec",KsiazkaUtils.getDateWithYearAndMonthForDay(2013,5,4),Stan.DUBLET));
    }

};


    @Override
    public Ksiazka create(Ksiazka ksiazka) throws KsiazkaAlreadyExistsException {
        if (!ksiazki.isEmpty()) {
            ksiazka.setNumerKatalogowy(
                    this.ksiazki.stream().mapToLong(p -> p.getNumerKatalogowy()).max().getAsLong() + 1);
        } else {
            ksiazka.setNumerKatalogowy(1L);
        }
        this.ksiazki.add(ksiazka);
        return ksiazka;
    }

    @Override
    public Ksiazka readById(Long id) throws NoSuchKsiazkaException {
        return this.ksiazki.stream().filter(p -> Objects.equals(p.getNumerKatalogowy(), id)).findFirst()
                .orElseThrow(NoSuchKsiazkaException::new);
    }

    @Override
    public Ksiazka update(Ksiazka ksiazka) throws NoSuchKsiazkaException {

        for (int i = 0; i < this.ksiazki.size(); i++) {
            if (Objects.equals(this.ksiazki.get(i).getNumerKatalogowy(), ksiazka.getNumerKatalogowy())) {
                this.ksiazki.set(i, ksiazka);
                return ksiazka;
            }
        }
        throw new NoSuchKsiazkaException("Nie ma takiej ksiazki: " + ksiazka.getNumerKatalogowy());
    }

    @Override
    public void deleteById(Long id) throws NoSuchKsiazkaException {
        for (int i = 0; i < this.ksiazki.size(); i++) {
            if (Objects.equals(this.ksiazki.get(i).getNumerKatalogowy(), id)) {
                this.ksiazki.remove(i);
            }
        }
        throw new NoSuchKsiazkaException("Nie ma takiej ksiazki: " + id);
    }

    @Override
    public List<Ksiazka> findAll() {
        return this.ksiazki;
    }
}
