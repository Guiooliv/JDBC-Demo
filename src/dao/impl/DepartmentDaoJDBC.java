package dao.impl;
import dao.DepartamentDao;
import db.DB;
import db.DbException;
import model.entites.Departament;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepartmentDaoJDBC implements DepartamentDao {

    private  Connection conn;

    public DepartmentDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Departament obj) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(
                    "INSERT INTO department "
                      + "(Name) "
                      + "VALUES "
                      + "(?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getName());

            int rowsAffected = st.executeUpdate();

            if(rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("Unexpected error! No rows affected!");
            }
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Departament obj) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(
                    "UPDATE department "
                            + "SET Name = ? "
                            + "WHERE Id = ?");

            st.setString(1, obj.getName());
            st.setInt(2, obj.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM department WHERE Id = ?");

            st.setInt(1, id);

            st.executeUpdate();

        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Departament findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                    "SELECT * from department "
                        + "where Id = ?");

            st.setInt(1, id);
            rs = st.executeQuery();

            if(rs.next()){
                return instantiateDepartment(rs);
            }
            return null;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
    @Override
    public List<Departament> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try   {
            st = conn.prepareStatement(
                    "SELECT * FROM department ORDER BY Name");

            rs = st.executeQuery();

            List<Departament> list = new ArrayList<>();

            while (rs.next()){
                list.add(instantiateDepartment(rs));
            }
            return list;
        } catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
    private Departament instantiateDepartment(ResultSet rs) throws SQLException {
        Departament dep = new Departament();
        dep.setId(rs.getInt("Id"));
        dep.setName(rs.getString("Name"));
        return dep;
    }
}
