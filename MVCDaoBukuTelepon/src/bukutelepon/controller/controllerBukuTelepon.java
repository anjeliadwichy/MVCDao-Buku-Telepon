/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bukutelepon.controller;

import bukutelepon.dao.daoBukuTelepon;
import bukutelepon.dao.implementBukuTelepon;
import bukutelepon.model.bukutelepon;
import bukutelepon.model.tableModelBukuTelepon;
import bukutelepon.view.FrameTelepon2;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author LENOVO
 */
public class controllerBukuTelepon {
     FrameTelepon2 frame;
    implementBukuTelepon implBukuTelepon;
    List<bukutelepon> lb;
    
    controllerBukuTelepon cbt;
    
    public controllerBukuTelepon(FrameTelepon2 frame) {
        this.frame = frame;
        implBukuTelepon = new daoBukuTelepon();
        lb = implBukuTelepon.getAll();
    }
    
    public void reset() {
        frame.getTxtID().setText("");
        frame.getTxtNoTelp().setText("");
        frame.getTxtNama().setText("");
        frame.getTxtAlamat().setText("");
    }
    
    public void isiTable() {
        lb = implBukuTelepon.getAll();
        tableModelBukuTelepon tmb = new tableModelBukuTelepon(lb);
        frame.getTabelData().setModel(tmb);
    }
    
    public void isiField(int row) {
        frame.getTxtID().setText(lb.get(row).getId().toString());
        frame.getTxtNoTelp().setText(lb.get(row).getNomer());
        frame.getTxtNama().setText(lb.get(row).getNama());
        frame.getTxtAlamat().setText(lb.get(row).getAlamat());

    }
    
    public void insert() {
        bukutelepon b = new bukutelepon();
        b.setNomer(frame.getTxtNoTelp().getText());
        b.setNama(frame.getTxtNama().getText());
        b.setAlamat(frame.getTxtAlamat().getText());
        implBukuTelepon.insert(b);
    }
    
    public void update() {
        bukutelepon b = new bukutelepon();
        b.setNomer(frame.getTxtNoTelp().getText());
        b.setNama(frame.getTxtNama().getText());
        b.setAlamat(frame.getTxtAlamat().getText());
        b.setId(Integer.parseInt(frame.getTxtID().getText()));
        implBukuTelepon.update(b);
    }
    
    public void delete() {
        if (!frame.getTxtID().getText().trim().isEmpty()) {
            int id = Integer.parseInt(frame.getTxtID().getText());
            implBukuTelepon.delete(id);
        } else {
            JOptionPane.showMessageDialog(frame, "Pilih data yang akan dihapus");
        }
    }
    
    public void isiTableCariNama() {
        lb = implBukuTelepon.getCariNama(frame.getTxtCariNama().getText());
        tableModelBukuTelepon tmb = new tableModelBukuTelepon(lb);
        frame.getTabelData().setModel(tmb);
    }
    
    public void cariNama() {
        if (!frame.getTxtCariNama().getText().trim().isEmpty()) {
            implBukuTelepon.getCariNama(frame.getTxtCariNama().getText());
            isiTableCariNama();
        } else {
            JOptionPane.showMessageDialog(frame, "Silahkan Pilih Data");
        }
    }
}
