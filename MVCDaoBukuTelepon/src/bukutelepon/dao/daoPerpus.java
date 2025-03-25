/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bukutelepon.dao;

import bukutelepon.koneksi.koneksi;
import bukutelepon.model.perpus;
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
public class daoPerpus implements implementPerpus {
    Connection connection;
    final String insert = "INSERT INTO perpus (nomor, nama, penulis) VALUES (?, ?, ?);";
    final String update = "UPDATE perpus set nomor=?, nama=?, penulis=? where id=? ;";
    final String delete = "DELETE FROM perpus where id=? ;";
    final String select = "SELECT * FROM perpus;";
    final String carinama = "SELECT * FROM perpus where nama like ?";
    
    public daoPerpus() {
        connection = koneksi.connection();
    }
    
    @Override
    public void insert(perpus b) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insert, RETURN_GENERATED_KEYS);
            statement.setString(1, b.getNomor());
            statement.setString(2, b.getNama());
            statement.setString(3, b.getPenulis());
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
    public void update(perpus b) {
        PreparedStatement statement = null;
        try {
        statement = connection.prepareStatement (update);
        statement.setString (1, b.getNomor()); 
        statement.setString (2, b.getNama ()); 
        statement.setString (3, b.getPenulis());  
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
    public List<perpus> getAll() {
        List<perpus> lb = null;
        try {
            lb = new ArrayList<perpus>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                perpus b = new perpus();
                b.setId(rs.getInt("id"));
                b.setNomor(rs.getString("nomor"));
                b.setNama(rs.getString("nama"));
                b.setPenulis(rs.getString("penulis"));
                lb.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(daoPerpus.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lb;
    }

    @Override
    public List<perpus> getCariNama(String nama) {
        List<perpus> lb = null;
        try {
            lb = new ArrayList<perpus>();
            PreparedStatement st = connection.prepareStatement(carinama);
            st.setString(1, "%" + nama + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                perpus b = new perpus();
                b.setId(rs.getInt("id"));
                b.setNomor(rs.getString("nomor"));
                b.setNama(rs.getString("nama"));
                b.setPenulis(rs.getString("penulis"));
                lb.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(daoPerpus.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lb;
    }
}
