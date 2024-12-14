package az.edu.turing.domain.dao;

import java.util.Collection;
import java.util.Optional;

public interface Dao <E, T>{

    E create(E e);

    Collection<E> getAll();

    Optional<E> getById(T id);

    E deleteById(T id);

    E update(T id,E e);

    boolean existsById(T id);
}
