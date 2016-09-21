/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.YteSequence;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface YteSequenceInterface {

    public void create(YteSequence yteSequence);

    public void edit(YteSequence yteSequence);

    public void remove(YteSequence yteSequence);

    public YteSequence find(Object id);

    public List<YteSequence> findAll();

    public java.lang.String formatMaPhieu(javax.persistence.EntityManager em, java.lang.String ma);

    public java.lang.String formatMa(javax.persistence.EntityManager em, java.lang.String ma);

    public java.lang.String getMa(javax.persistence.EntityManager em, int loaiMa);

    public java.lang.String getMaPhieu(javax.persistence.EntityManager em, int loaiMa);
    
    public String getMaDkKhamBenh();
    
    public  String startMyTimer(long interval) ;

    public String resetMaPhieu();    
}


