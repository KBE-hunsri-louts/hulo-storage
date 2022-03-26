package com.huloteam.kbe.orm;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import com.huloteam.kbe.model.ProductEntity;

/**
 * Creates queries to search for product information.
 */
public class DatabaseCommunicator {
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnitName");
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();
    private final EntityTransaction transaction = entityManager.getTransaction();

    /**
     * Searches for a product and sends it back.
     * @param productName is a string which will be used to search for a product.
     * @return a list of product information.
     */
    public List<String> findProductEntity(String productName) {
        List<String> returnStringList = new LinkedList<>();

        try {
            transaction.begin();

            TypedQuery<ProductEntity> productEntityTypedQuery = entityManager.createNamedQuery("ProductEntity.findOneProduct", ProductEntity.class);
            productEntityTypedQuery.setParameter("name", productName);

            transaction.commit();

            if (!productEntityTypedQuery.getResultList().isEmpty()) {
                returnStringList.add(productEntityTypedQuery.getResultList().get(0).getProvider());
                returnStringList.add(productEntityTypedQuery.getResultList().get(0).getProviderprice().toString());
                returnStringList.add(productEntityTypedQuery.getResultList().get(0).getStoredsince().toString());

                return returnStringList;
            } else {
                return null;
            }
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }

            // entityManager.close();
            // entityManagerFactory.close();
        }
    }

    /**
     * Inserts a new product into the postgres database.
     * @param productName is a string and is used to find a product
     * @param provider is a string and contains the provider of the product
     * @param providerPrice is an int and contains the price which the salesman buys it from the provider
     * @param storedSince is a timestamp which contains date and time
     */
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

            // entityManager.close();
            // entityManagerFactory.close();
        }
    }
}
