/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bukutelepon.dao;

import java.util.List;
import bukutelepon.model.*;

/**
 *
 * @author LENOVO
 */
public interface implementBukuTelepon {
    public void insert(bukutelepon b);
    public void update(bukutelepon b);
    public void delete(int id);
    public List<bukutelepon> getAll();
    public List<bukutelepon> getCariNama(String nama);
}
