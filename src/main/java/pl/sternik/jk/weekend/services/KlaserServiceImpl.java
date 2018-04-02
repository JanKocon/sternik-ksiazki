package pl.sternik.jk.weekend.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import pl.sternik.jk.weekend.entities.Ksiazka;
import pl.sternik.jk.weekend.repositories.KsiazkaAlreadyExistsException;
import pl.sternik.jk.weekend.repositories.KsiazkaRepository;
import pl.sternik.jk.weekend.repositories.NoSuchKsiazkaException;


@Service
@Qualifier("tablica")
public class KlaserServiceImpl implements KlaserService {

    @Autowired
    @Qualifier("tablica")
    private KsiazkaRepository bazaDanych;

    @Override
    public List<Ksiazka> findAll() {
        return bazaDanych.findAll();
    }

    @Override
    public List<Ksiazka> findAllToSell() {
        return bazaDanych.findAll();
    }

    @Override
    public Optional<Ksiazka> findById(Long id) {
        try {
            return Optional.of(bazaDanych.readById(id));
        } catch (NoSuchKsiazkaException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Ksiazka> create(Ksiazka ksiazka) {
        try {
            return Optional.of(bazaDanych.create(ksiazka));
        } catch (KsiazkaAlreadyExistsException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Ksiazka> edit(Ksiazka ksiazka) {
        try {
            return Optional.of(bazaDanych.update(ksiazka));
        } catch (NoSuchKsiazkaException e) {
            return Optional.empty();
        }

    }

    @Override
    public Optional<Boolean> deleteById(Long id) {
        try {
            bazaDanych.deleteById(id);
            return Optional.of(Boolean.TRUE);
        } catch (NoSuchKsiazkaException e) {
            return Optional.of(Boolean.FALSE);
        }
    }

    @Override
    public List<Ksiazka> findLatest3() {
        //TODO: 
        return Collections.emptyList();
    }

}
