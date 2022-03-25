package com.huloteam.kbe.orm;

import com.huloteam.kbe.model.ProductEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Creates queries to search for product information.
 * psql -h localhost -p 5432 -U postgres -d kbestorage
 */
public class DatabaseCommunicator {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnitName");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();

    /**
     * Searches for a product and send it back.
     * @param productName is a name of a product which will be searched.
     * @return a list of all ProductEntity's.
     */
    public List<ProductEntity> findProductEntity(String productName) {
        try {
            transaction.begin();

            TypedQuery<ProductEntity> productEntityTypedQuery = entityManager.createNamedQuery("ProductEntity.findOneProduct", ProductEntity.class);
            productEntityTypedQuery.setParameter(1, productName);

            transaction.commit();

            if (productEntityTypedQuery.getResultList().size() != 0) {
                return productEntityTypedQuery.getResultList();
            } else {
                return null;
            }
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }

            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public void pushNewObjectToDatabase(String productName, String provider, int providerPrice, Timestamp storedSince) {
        ProductEntity newProductEntity = new ProductEntity();
        newProductEntity.setName(productName);
        newProductEntity.setProvider(provider);
        newProductEntity.setProviderprice(providerPrice);
        newProductEntity.setStoredsince(storedSince);

        try {
            transaction.begin();

            entityManager.persist(newProductEntity);

            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }

            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
