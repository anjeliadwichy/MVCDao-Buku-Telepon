/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bukutelepon.model;

/**
 *
 * @author LENOVO
 */
public class perpus {
    private Integer id;
    private String nomor;
    private String nama ;
    private String penulis;
    
    public String getPenulis() {
         return penulis;
    }
    
    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }
    
    public Integer getId(){
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getNama() {
        return nama;
    }
    
    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public String getNomor() {
        return nomor;
    }
    
    public void setNomor(String nomor) {
        this.nomor = nomor;
    }
}
