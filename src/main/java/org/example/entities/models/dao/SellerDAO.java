package org.example.entities.models.dao;
import org.example.entities.models.Department;
import org.example.entities.models.Seller;

import java.util.List;

public interface SellerDAO {
    void insert(Seller obj);

    void update(Seller obj);

    void deleteById(Integer id);

    Seller findById(Integer id);

    List<Seller> findAll();

    List<Seller> findByDepartment(Department department);
}
