/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bukutelepon.controller;

import bukutelepon.dao.daoPerpus;
import bukutelepon.dao.implementPerpus;
import bukutelepon.model.perpus;
import bukutelepon.model.tableModelPerpus;
import bukutelepon.view.FramePerpus;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author LENOVO
 */
public class controllerPerpus {
    FramePerpus frame;
    implementPerpus implPerpus;
    List<perpus> lb;
    
    controllerPerpus cbt;
    
    public controllerPerpus(FramePerpus frame) {
        this.frame = frame;
        implPerpus = new daoPerpus();
        lb = implPerpus.getAll();
    }
    
    public void reset() {
        frame.getTxtID().setText("");
        frame.getTxtNomor().setText("");
        frame.getTxtNama().setText("");
        frame.getTxtPenulis().setText("");
    }
    
    public void isiTable() {
        lb = implPerpus.getAll();
        tableModelPerpus tmb = new tableModelPerpus(lb);
        frame.getTabelData().setModel(tmb);
    }
    
    public void isiField(int row) {
        frame.getTxtID().setText(lb.get(row).getId().toString());
        frame.getTxtNomor().setText(lb.get(row).getNomor());
        frame.getTxtNama().setText(lb.get(row).getNama());
        frame.getTxtPenulis().setText(lb.get(row).getPenulis());

    }
    
    public void insert() {
        perpus b = new perpus();
        b.setNomor(frame.getTxtNomor().getText());
        b.setNama(frame.getTxtNama().getText());
        b.setPenulis(frame.getTxtPenulis().getText());
        implPerpus.insert(b);
    }
    
    public void update() {
        perpus b = new perpus();
        b.setNomor(frame.getTxtNomor().getText());
        b.setNama(frame.getTxtNama().getText());
        b.setPenulis(frame.getTxtPenulis().getText());
        b.setId(Integer.parseInt(frame.getTxtID().getText()));
        implPerpus.update(b);
    }
    
    public void delete() {
        if (!frame.getTxtID().getText().trim().isEmpty()) {
            int id = Integer.parseInt(frame.getTxtID().getText());
            implPerpus.delete(id);
        } else {
            JOptionPane.showMessageDialog(frame, "Pilih data yang akan dihapus");
        }
    }
    
    public void isiTableCariNama() {
        lb = implPerpus.getCariNama(frame.getTxtCariNama().getText());
        tableModelPerpus tmb = new tableModelPerpus(lb);
        frame.getTabelData().setModel(tmb);
    }
    
    public void cariNama() {
        if (!frame.getTxtCariNama().getText().trim().isEmpty()) {
            implPerpus.getCariNama(frame.getTxtCariNama().getText());
            isiTableCariNama();
        } else {
            JOptionPane.showMessageDialog(frame, "Silahkan Pilih Data");
        }
    }
}
