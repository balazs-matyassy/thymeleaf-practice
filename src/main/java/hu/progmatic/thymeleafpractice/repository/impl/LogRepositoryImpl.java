package hu.progmatic.thymeleafpractice.repository.impl;

import hu.progmatic.thymeleafpractice.model.Log;
import hu.progmatic.thymeleafpractice.repository.LogRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

// https://www.baeldung.com/hibernate-entitymanager
// A @Repository annotációra az interfésszel szemben itt mindenképpen szükség van.
@Repository
public class LogRepositoryImpl implements LogRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Iterable<Log> findAll() {
        List<Log> logs = entityManager.createQuery(
                        "SELECT l FROM Log l",
                        Log.class
                )
                .getResultList();

        return logs;
    }

    @Override
    public List<Log> findByMessageContaining(String search) {
        // JPQL query (majdnem SQL, de pl. tábla nevek helyett osztályok / entitások vannak)

        // Nem közvetlenül hozzáadni a query-hez felhasználótól származó bemenetet.
        // Pl. search = "; DROP TABLE user;" (SQL injection támadás)
        // setParameter véd az SQL injection ellen, mivel szövegként szúrja be (escaping)
        // a felhasználói bemenetet.
        // Ilyet NEM!!! ""SELECT l FROM Log l WHERE l.message LIKE %" + search + "%" (SQL injection)

        List<Log> logs = entityManager.createQuery(
                "SELECT l FROM Log l WHERE l.message LIKE :search",
                        Log.class
                )
                .setParameter("search", '%' + search + '%')
                .getResultList();

        return logs;
    }

    @Override
    public <S extends Log> S save(S entity) {
        // "felülünk a repülőre"
        // (nem történik semmi, még mindig a repülőtéren vagyunk)
        entityManager.persist(entity);

        // "felszáll a repülő" (minden korábban felszálló utas a levegőben van)
        // tranzakció nélkül szükség lehet flush-re
        // (ekkor történik meg ténylegesen az írás)
        // tranzakciók esetében automatikusan meghívódik
        // entityManager.flush();

        return entity;
    }

    @Override
    public <S extends Log> Iterable<S> saveAll(Iterable<S> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Log> findById(Long aLong) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean existsById(Long aLong) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<Log> findAllById(Iterable<Long> longs) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteById(Long aLong) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Log entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll(Iterable<? extends Log> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException();
    }
}
