package org.example.entities.models.dao;
import org.example.entities.models.Department;

import java.util.List;

public interface DepartmentDAO {
    void insert(Department obj);

    void update(Department obj);

    void deleteById(Integer id);

    Department findById(Integer id);

    List<Department> findAll();
}
