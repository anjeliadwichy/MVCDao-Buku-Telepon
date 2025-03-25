/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bukutelepon.dao;

import bukutelepon.koneksi.koneksi;
import bukutelepon.model.bukutelepon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.sql.Statement.RETURN_GENERATED_KEYS;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
        
/**
 *
 * @author LENOVO
 */
public class daoBukuTelepon implements implementBukuTelepon {
    Connection connection;
    final String insert = "INSERT INTO bukutelepon (nomer, nama, alamat) VALUES (?, ?, ?);";
    final String update = "UPDATE bukutelepon set nomer=?, nama=?, alamat=? where id=? ;";
    final String delete = "DELETE FROM bukutelepon where id=? ;";
    final String select = "SELECT * FROM bukutelepon;";
    final String carinama = "SELECT * FROM bukutelepon where nama like ?";
    
    public daoBukuTelepon() {
        connection = koneksi.connection();
    }
    
    @Override
    public void insert(bukutelepon b) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insert, RETURN_GENERATED_KEYS);
            statement.setString(1, b.getNomer());
            statement.setString(2, b.getNama());
            statement.setString(3, b.getAlamat());
            statement.executeUpdate();
            ResultSet rs =  statement.getGeneratedKeys();
            while (rs.next()) {
                b.setId(rs.getInt(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    @Override
    public void update(bukutelepon b) {
        PreparedStatement statement = null;
        try {
        statement = connection.prepareStatement (update);
        statement.setString (1, b.getNomer()); 
        statement.setString (2, b.getNama ()); 
        statement.setString (3, b.getAlamat());  
        statement.setInt (4, b.getId());
        statement.executeUpdate();
        
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void delete(int id) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement (delete);
            
            statement.setInt(1, id);
            statement.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {  
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<bukutelepon> getAll() {
        List<bukutelepon> lb = null;
        try {
            lb = new ArrayList<bukutelepon>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                bukutelepon b = new bukutelepon();
                b.setId(rs.getInt("id"));
                b.setNomer(rs.getString("nomer"));
                b.setNama(rs.getString("nama"));
                b.setAlamat(rs.getString("alamat"));
                lb.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(daoBukuTelepon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lb;
    }

    @Override
    public List<bukutelepon> getCariNama(String nama) {
        List<bukutelepon> lb = null;
        try {
            lb = new ArrayList<bukutelepon>();
            PreparedStatement st = connection.prepareStatement(carinama);
            st.setString(1, "%" + nama + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                bukutelepon b = new bukutelepon();
                b.setId(rs.getInt("id"));
                b.setNomer(rs.getString("nomer"));
                b.setNama(rs.getString("nama"));
                b.setAlamat(rs.getString("alamat"));
                lb.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(daoBukuTelepon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lb;
    }
}
