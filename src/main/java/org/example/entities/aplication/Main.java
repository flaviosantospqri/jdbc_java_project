package org.example.entities.aplication;


import org.example.entities.models.Department;
import org.example.entities.models.Seller;
import org.example.entities.models.dao.DaoFactory;
import org.example.entities.models.dao.SellerDAO;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Department dp = new Department(1, "Books");

        Seller sl = new Seller(1, "Jose","jose@jose.com.br",LocalDate.now(),1200.00,dp);

        SellerDAO sellerDAO = DaoFactory.createSellerDao();

        System.out.println("=== TEST 1: SELLER FINDBYID");
        Seller seller = sellerDAO.findById(3);
        System.out.println(seller);

        System.out.println("/n === TEST 1: SELLER FINDBYDEPARTMENT");
        Department department = new Department(2,null);
        List<Seller> list = sellerDAO.findByDepartment(department);

        for (Seller obj : list){
            System.out.println(obj);
        }

        System.out.println("/n === TEST 1: SELLER FINDALL");
        list = sellerDAO.findAll();

        for (Seller obj : list){
            System.out.println(obj);
        }



    }
}