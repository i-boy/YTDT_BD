/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.BenhNhanCheDoAn;
import com.iesvn.yte.dieutri.entity.BenhNhanGioAn;
import com.iesvn.yte.dieutri.entity.BenhNhanPhieuBaoAn;
import java.util.List;

/**
 *
 * @author james
 */
public interface BenhNhanPhieuBaoAnInterface {

    public void create(BenhNhanPhieuBaoAn benhNhanPhieuBaoAn);

    public void edit(BenhNhanPhieuBaoAn benhNhanPhieuBaoAn);

    public void remove(BenhNhanPhieuBaoAn benhNhanPhieuBaoAn);

    public BenhNhanPhieuBaoAn find(Object id);

    public List<BenhNhanPhieuBaoAn> findAll();
    
    public void myCreate(BenhNhanPhieuBaoAn benhNhanPhieuBaoAn, List<BenhNhanCheDoAn> listBnCda, List<BenhNhanGioAn> listBnGa, boolean isUpdate) ;
    
    public List<BenhNhanPhieuBaoAn> findByPbaMaso(Integer pbaMaso) ;
    
    public int removeByPbaMaso(Integer pbaMaso) ;
}
