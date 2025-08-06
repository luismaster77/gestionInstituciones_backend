package com.co.lep.gestion.estudiantes.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class BaseRepository {
	private EntityManager entityManager;
    private final String persistenceUnitName;

    public BaseRepository(String persistenceUnitName) {
        this.persistenceUnitName = persistenceUnitName;
    }

    protected EntityManager getEntityManager() {
        if (entityManager == null) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnitName);
            entityManager = emf.createEntityManager();
        }
        return entityManager;
    }
}
