package com.koshelev;

import org.hibernate.Session;

import java.util.List;

public class ProductDaoImpl implements ProductDao{
    private SessionFactoryUtils sessionFactory;

    public ProductDaoImpl(SessionFactoryUtils sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Product> findAll() {
        Session session = sessionFactory.getSession();
        session.beginTransaction();
        List<Product> products = session.createQuery("select product from Product product").getResultList();
        session.getTransaction().commit();
        session.close();
        return products;
    }

    @Override
    public Product findById(Long id) {
        try(Session session = sessionFactory.getSession();) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.getTransaction().commit();
            return product;
        }
    }

    @Override
    public void deleteById(Long id) {
        try(Session session = sessionFactory.getSession()) {
            session.beginTransaction();
            session.createQuery("delete Product where id = :param")
                    .setParameter("param", id).executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public Product saveOrUpdate(Product product) {
        try(Session session = sessionFactory.getSession()){
            session.beginTransaction();
            session.saveOrUpdate(product);
            Product newProd = session.get(Product.class, product.getId());
            session.getTransaction().commit();
            return newProd;
        }
    }
}
