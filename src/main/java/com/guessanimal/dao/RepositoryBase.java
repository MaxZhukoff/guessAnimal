package com.guessanimal.dao;

import com.guessanimal.entity.BaseEntity;
import com.guessanimal.util.HibernateSessionFactoryUtil;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.Optional;


@RequiredArgsConstructor
public abstract class RepositoryBase<K extends Serializable, E extends BaseEntity<K>> implements Repository<K, E> {

    private final Class<E> clazz;

    @Override
    public E save(E entity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return entity;
    }

    @Override
    public void update(E entity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
    }

    @Override
    public Optional<E> findById(K id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Optional<E> e = Optional.ofNullable(session.find(clazz, id));
        session.close();
        return e;
    }
}
