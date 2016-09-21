/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;


import com.iesvn.yte.dieutri.entity.HsThtoankBackup;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface HsThtoankBackupInterface {

    public void create(HsThtoankBackup hsThtoankBackup);

    public void edit(HsThtoankBackup hsThtoankBackup);

    public void remove(HsThtoankBackup hsThtoankBackup);

    public HsThtoankBackup find(Object id);

    public List<HsThtoankBackup> findAll();
    
    public HsThtoankBackup findByMaPhieu(String maPhieuTT) ;
    
    public HsThtoankBackup findBytiepdonMa(String tiepdonMa, int recordReturn) ;

}


