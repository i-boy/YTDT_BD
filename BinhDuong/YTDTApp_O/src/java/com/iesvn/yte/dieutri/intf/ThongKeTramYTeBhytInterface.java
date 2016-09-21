/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.ThongKeTramYTeBhyt;
import java.util.List;

/**
 *
 * @author thanh
 */
public interface ThongKeTramYTeBhytInterface {
    void create(ThongKeTramYTeBhyt thongKeTramYTeBhyt);

    void edit(ThongKeTramYTeBhyt thongKeTramYTeBhyt);

    void remove(ThongKeTramYTeBhyt thongKeTramYTeBhyt);

    ThongKeTramYTeBhyt find(Object id);

    List<ThongKeTramYTeBhyt> findAll();
    
     public String luuTruTTThongKeTramYTeBHYT (List<ThongKeTramYTeBhyt> lstTKTram);
     public List<ThongKeTramYTeBhyt> findByThangNamTramYTe(String thang, String nam, String matramyte,Boolean noitru) ;
}
