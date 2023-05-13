package org.example.entities.models.dao;

import org.example.entities.models.dao.impl.SellerDaoJDBC;

public class DaoFactory {
    public static SellerDAO createSellerDao(){
       return new SellerDaoJDBC();
    }
}
