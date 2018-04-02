package pl.sternik.jk.weekend.services;

import pl.sternik.jk.weekend.entities.Ksiazka;

import java.util.List;
import java.util.Optional;




public interface KlaserService {
    List<Ksiazka> findAll();

    List<Ksiazka> findAllToSell();

    Optional<Ksiazka> findById(Long id);

    Optional<Ksiazka> create(Ksiazka ksiazka);

    Optional<Ksiazka> edit(Ksiazka ksiazka);

    Optional<Boolean> deleteById(Long id);

    List<Ksiazka> findLatest3();
}