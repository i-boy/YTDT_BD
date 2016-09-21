/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.GiayCnSucKhoe;
import java.util.List;


/**
 *
 * @author James
 */
public interface GiayCnSucKhoeInterface {

    public void create(GiayCnSucKhoe giayCnSucKhoe);

    public void edit(GiayCnSucKhoe giayCnSucKhoe);

    public void remove(GiayCnSucKhoe giayCnSucKhoe);

    public GiayCnSucKhoe find(Object id);

    public List<GiayCnSucKhoe> findAll();

    public List<GiayCnSucKhoe> findByGiayCnSucKhoe(String maPhieu);

    public GiayCnSucKhoe findByMaThamKham(Integer iMaThamKham);

    public void capNhatGiayCnSucKhoe(GiayCnSucKhoe obj);
}
