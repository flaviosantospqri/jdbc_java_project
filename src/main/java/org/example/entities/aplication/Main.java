package org.example.entities.aplication;


import org.example.entities.models.Department;
import org.example.entities.models.Seller;
import org.example.entities.models.dao.DaoFactory;
import org.example.entities.models.dao.SellerDAO;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Department dp = new Department(1, "Books");

        Seller sl = new Seller(1, "Jose","jose@jose.com.br",LocalDate.now(),1200.00,dp);

        SellerDAO sellerDAO = DaoFactory.createSellerDao();

        System.out.println(dp);
        System.out.println(sl);
    }
}