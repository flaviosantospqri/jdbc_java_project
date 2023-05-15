package org.example.entities.aplication;


import org.example.entities.models.Department;
import org.example.entities.models.Seller;
import org.example.entities.models.dao.DaoFactory;
import org.example.entities.models.dao.SellerDAO;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Department dp = new Department(1, "Books");

        Seller sl = new Seller(1, "Jose","jose@jose.com.br",new Date(),1200.00,dp);

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

        System.out.println("/n === TEST 1: SELLER INSERT");

        Seller newSeller = new Seller(null, "Greg", "greg@greg.com", new Date(), 4000.0, department);

        sellerDAO.insert(newSeller);

        System.out.println("Inserted" + newSeller.getId());

        System.out.println("/n === TEST 1: SELLER UPDATE");

        seller = sellerDAO.findById(1);

        seller.setName("Marta Waine");

        sellerDAO.update(seller);

        System.out.println("Update completed");

        System.out.println("/n === TEST 1: SELLER DELETE");

        System.out.println("Delete number id");
        int id = sc.nextInt();
        sellerDAO.deleteById(id);
        System.out.println("Delete completed");

    }
}