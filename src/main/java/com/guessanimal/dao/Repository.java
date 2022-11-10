package com.guessanimal.dao;

import com.guessanimal.entity.BaseEntity;

import java.io.Serializable;
import java.util.Optional;

public interface Repository<K extends Serializable, E extends BaseEntity<K>> {
    E save(E entity);

    void update(E entity);

    Optional<E> findById(K id);
}
