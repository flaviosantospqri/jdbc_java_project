package org.example.entities.models.dao.impl;

import org.example.entities.db.DB;
import org.example.entities.db.DbException;
import org.example.entities.models.Department;
import org.example.entities.models.Seller;
import org.example.entities.models.dao.SellerDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class SellerDaoJDBC implements SellerDAO {
    private Connection conn;

    public SellerDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Seller obj) {

    }

    @Override
    public void update(Seller obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                    "SELECT seller. *,department.Name as DepName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentID = departmentId "
                    + "WHERE seller.Id = ?"
            );

            st.setInt(1, id);
            rs = st.executeQuery();

            if(rs.next()){
                Department dp = new Department();

                dp.setId(rs.getInt("DepartmentId"));
                dp.setName(rs.getString("DepName"));

                Seller obj = new Seller();
                obj.setId(rs.getInt("Id"));
                obj.setEmail(rs.getString("Name"));
                obj.setEmail(rs.getString("Email"));
                obj.setBaseSalary(rs.getDouble("BaseSalary"));
                obj.setBirthDate(rs.getDate("birthDate").toLocalDate());
                obj.setDepartment(dp);

                return obj;

            }else {
                return null;
            }

        }catch (SQLException e){
            throw new DbException("Error" + e.getMessage());
        }finally {
            DB.closeResulSet(rs);
            DB.closeStatement(st);
        }
    }

    @Override
    public List<Seller> findAll() {
        return null;
    }
}
