package pl.sternik.jk.weekend.repositories;

import pl.sternik.jk.weekend.entities.Ksiazka;

import java.util.List;

public interface KsiazkaRepository {
    Ksiazka create(Ksiazka moneta) throws KsiazkaAlreadyExistsException;
    Ksiazka readById(Long id) throws NoSuchKsiazkaException;
    Ksiazka update(Ksiazka moneta) throws NoSuchKsiazkaException;
    void deleteById(Long id) throws NoSuchKsiazkaException;
    List<Ksiazka> findAll();
}
