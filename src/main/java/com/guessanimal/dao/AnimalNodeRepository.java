package com.guessanimal.dao;

import com.guessanimal.entity.AnimalNode;
import com.guessanimal.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class AnimalNodeRepository extends RepositoryBase<Integer, AnimalNode> {

    public AnimalNodeRepository() {
        super(AnimalNode.class);
    }

    public Optional<AnimalNode> getRootNode() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Optional<AnimalNode> animalNode = session
                .createQuery("select u from com.guessanimal.entity.AnimalNode u", AnimalNode.class)
                .setMaxResults(1).uniqueResultOptional();
        transaction.commit();
        session.close();
        return animalNode;
    }
}
