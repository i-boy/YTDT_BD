package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.GiayChungThuong;
import java.util.Date;
import java.util.List;

/**
 *
 * @author quang
 */
public interface GiayChungThuongInterface {

 void create(GiayChungThuong giayChungThuong);

    void edit(GiayChungThuong giayChungThuong);

    void remove(GiayChungThuong giayChungThuong);

    GiayChungThuong find(Object id);

    List<GiayChungThuong> findAll();

    public GiayChungThuong getGiayChungThuong(Integer thamkham);
}


