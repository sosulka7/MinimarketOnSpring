package com.koshelev;

public class MainApp {
    public static void main(String[] args) {
        SessionFactoryUtils sessionFactory = new SessionFactoryUtils();
        try {
            sessionFactory.init();
            ProductDao productDao = new ProductDaoImpl(sessionFactory);
            //для теста update
            Product product = productDao.findById(3L);
            product.setCost(456.0);
            //для теста save
//            Product product = new Product("sauce", 56.23);
            System.out.println(productDao.saveOrUpdate(product).getCost());

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sessionFactory.shutdown();
        }
    }



}
