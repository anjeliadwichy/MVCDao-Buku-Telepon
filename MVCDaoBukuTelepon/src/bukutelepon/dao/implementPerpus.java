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
public interface implementPerpus {
    public void insert(perpus b);
    public void update(perpus b);
    public void delete(int id);
    public List<perpus> getAll();
    public List<perpus> getCariNama(String nama);
}
