package org.example.entities.models.dao.impl;

import org.example.entities.db.DB;
import org.example.entities.db.DbException;
import org.example.entities.models.Department;
import org.example.entities.models.Seller;
import org.example.entities.models.dao.SellerDAO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDAO {
    private Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Seller obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO seller "
                    + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                    + "VALUES "
                    + "(?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            st.setString(1, obj.getName());
            st.setString(2, obj.getEmail());
            st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
            st.setDouble(4, obj.getBaseSalary());
            st.setInt(5,obj.getDepartment().getId());

            int roewsAffected = st.executeUpdate();

            if (roewsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    obj.setId(id);

                    DB.closeResulSet(rs);
                }
            }else {
                throw new DbException("Unexpected error! ");
            }

            }catch (SQLException e){
            throw new DbException("Error" + e.getMessage());

        }finally {
            DB.closeStatement(st);
        }
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

            if (rs.next()) {
                Department dp = instatiatiNewDep(rs);

                return instatiatiNewSeller(rs, dp);

            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new DbException("Error" + e.getMessage());
        } finally {
            DB.closeResulSet(rs);
            DB.closeStatement(st);
        }
    }

    private Seller instatiatiNewSeller(ResultSet rs, Department dp) throws SQLException {
        Seller obj = new Seller();
        obj.setId(rs.getInt("Id"));
        obj.setName(rs.getString("Name"));
        obj.setEmail(rs.getString("Email"));
        obj.setBaseSalary(rs.getDouble("BaseSalary"));
        obj.setBirthDate(rs.getDate("birthDate"));
        obj.setDepartment(dp);

        return obj;
    }

    private Department instatiatiNewDep(ResultSet rs) throws SQLException {
        Department dp = new Department();

        dp.setId(rs.getInt("DepartmentId"));
        dp.setName(rs.getString("DepName"));

        return dp;
    }

    @Override
    public List<Seller> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                    "SELECT seller. *,department.Name as DepName "
                            + "FROM seller INNER JOIN department "
                            + "ON seller.DepartmentID = department.Id "
                            + "ORDER BY Name"
            );

            rs = st.executeQuery();

            List<Seller> sellers = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();

            while (rs.next()) {

                Department dep = map.get(rs.getInt("DepartmentId"));

                if(dep == null){
                    Department dp = instatiatiNewDep(rs);
                    map.put(rs.getInt("DepartmentId"), dp);
                }
                Seller obj = instatiatiNewSeller(rs, dep);
                sellers.add(obj);
            }
            return sellers;
        } catch (SQLException e) {
            throw new DbException("Error" + e.getMessage());
        } finally {
            DB.closeResulSet(rs);
            DB.closeStatement(st);
        }
    }

    @Override
    public List<Seller> findByDepartment(Department department) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                    "SELECT seller. *,department.Name as DepName "
                            + "FROM seller INNER JOIN department "
                            + "ON seller.DepartmentID = department.Id "
                            + "WHERE DepartmentId = ? "
                            + "ORDER BY Name"
            );

            st.setInt(1, department.getId());
            rs = st.executeQuery();

            List<Seller> sellers = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();

            while (rs.next()) {

                Department dep = map.get(rs.getInt("DepartmentId"));

                if(dep == null){
                    Department dp = instatiatiNewDep(rs);
                    map.put(rs.getInt("DepartmentId"), dp);
                }
                Seller obj = instatiatiNewSeller(rs, dep);
                sellers.add(obj);
            }
            return sellers;
        } catch (SQLException e) {
            throw new DbException("Error" + e.getMessage());
        } finally {
            DB.closeResulSet(rs);
            DB.closeStatement(st);
        }
    }
}
