package pl.sternik.jk.weekend.services;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import pl.sternik.jk.weekend.entities.Ksiazka;
import pl.sternik.jk.weekend.entities.Stan;
import pl.sternik.jk.weekend.repositories.KsiazkaAlreadyExistsException;
import pl.sternik.jk.weekend.repositories.KsiazkaRepository;
import pl.sternik.jk.weekend.repositories.NoSuchKsiazkaException;


@Service
@Qualifier("lista")
@Primary
public class KlaserServiceImpl implements KlaserService {

    @Autowired
    @Qualifier("lista")
    private KsiazkaRepository ksiazki;

    @Override
    public List<Ksiazka> findAll() {
        return ksiazki.findAll();
    }

    @Override
    public List<Ksiazka> findLatest3() {
        return ksiazki.findAll().stream().sorted((a, b) -> b.getDataWydania().compareTo(a.getDataWydania())).limit(5)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Ksiazka> findById(Long id) {
        try {
            return Optional.of(ksiazki.readById(id));
        } catch (NoSuchKsiazkaException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Ksiazka> create(Ksiazka ksiazka) {
        try {
            return Optional.of(ksiazki.create(ksiazka));
        } catch (KsiazkaAlreadyExistsException e) {
            try {
                return Optional.of(ksiazki.readById(ksiazka.getNumerKatalogowy()));
            } catch (NoSuchKsiazkaException e1) {
                return Optional.empty();
            }
        }

    }

    @Override
    public Optional<Ksiazka> edit(Ksiazka ksiazka) {
        try {
            return Optional.of(ksiazki.update(ksiazka));
        } catch (NoSuchKsiazkaException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Boolean> deleteById(Long id) {
        try {
            ksiazki.deleteById(id);
            return Optional.of(Boolean.TRUE);
        } catch (NoSuchKsiazkaException e) {
            return Optional.of(Boolean.FALSE);
        }
    }
    @Override
    public List<Ksiazka> findAllduplicate() {
        return ksiazki.findAll().stream().filter(p -> Objects.equals(p.getStatus(), Stan.DUBLET)).
                collect(Collectors.toList());
    }
    @Override
    public List<Ksiazka> findAllToSell() {
        return ksiazki.findAll().stream().filter(p -> Objects.equals(p.getStatus(), Stan.DO_SPRZEDANIA))
                .collect(Collectors.toList());
    }
}
