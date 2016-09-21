/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import com.iesvn.yte.entity.DmKhoa;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
//import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author root
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "TUAN_MO")
@NamedQueries({})
public class TuanMo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "TUANMO_MA", nullable = false)
    private String tuanmoMa;
    @Column(name = "TUANMO_TUAN")
    private String tuanmoTuan;
    @Column(name = "TUANMO_NGAYDAU")
    @Temporal(TemporalType.DATE)
    private Date tuanmoNgaydau;
    @Column(name = "TUANMO_NGAYCUOI")
    @Temporal(TemporalType.DATE)
    private Date tuanmoNgaycuoi;
    @Column(name = "TUANMO_GHICHU")
    private String tuanmoGhichu;
    @Column(name = "TUANMO_CHUTOA")
    private String tuanmoChutoa;
    @Column(name = "TUANMO_CHUCVUCT")
    private String tuanmoChucvuct;
    @Column(name = "TUANMO_THUKY")
    private String tuanmoThuky;
    @Column(name = "TUANMO_CHUCVUTK")
    private String tuanmoChucvutk;
    @Column(name = "TUANMO_THANHPHAN")
    private String tuanmoThanhphan;
    @Column(name = "TUANMO_YKIEN1")
    private String tuanmoYkien1;
    @Column(name = "TUANMO_YKIEN2")
    private String tuanmoYkien2;
    @Column(name = "TUANMO_KETLUAN1")
    private String tuanmoKetluan1;
    @Column(name = "TUANMO_KETLUAN2")
    private String tuanmoKetluan2;
    @Column(name = "TUANMO_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tuanmoNgaygiocn;
    @JoinColumn(name = "KHOA_MA", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa khoaMa;
    @JoinColumn(name = "NHANVIEN_MA", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien nhanvienMa;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tuanmoMa")
//    private Collection<LichMo> lichMoCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tuanmoMa1")
//    private Collection<LichMo> lichMoCollection1;

    public TuanMo() {
    }

    public TuanMo(String tuanmoMa) {
        this.tuanmoMa = tuanmoMa;
    }

    public String getTuanmoMa() {
        return tuanmoMa;
    }

    public void setTuanmoMa(String tuanmoMa) {
        this.tuanmoMa = tuanmoMa;
    }

    public String getTuanmoTuan() {
        return tuanmoTuan;
    }

    public void setTuanmoTuan(String tuanmoTuan) {
        this.tuanmoTuan = tuanmoTuan;
    }

    public Date getTuanmoNgaydau() {
        return tuanmoNgaydau;
    }

    public void setTuanmoNgaydau(Date tuanmoNgaydau) {
        this.tuanmoNgaydau = tuanmoNgaydau;
    }

    public Date getTuanmoNgaycuoi() {
        return tuanmoNgaycuoi;
    }

    public void setTuanmoNgaycuoi(Date tuanmoNgaycuoi) {
        this.tuanmoNgaycuoi = tuanmoNgaycuoi;
    }

    public String getTuanmoGhichu() {
        return tuanmoGhichu;
    }

    public void setTuanmoGhichu(String tuanmoGhichu) {
        this.tuanmoGhichu = tuanmoGhichu;
    }

    public String getTuanmoChutoa() {
        return tuanmoChutoa;
    }

    public void setTuanmoChutoa(String tuanmoChutoa) {
        this.tuanmoChutoa = tuanmoChutoa;
    }

    public String getTuanmoChucvuct() {
        return tuanmoChucvuct;
    }

    public void setTuanmoChucvuct(String tuanmoChucvuct) {
        this.tuanmoChucvuct = tuanmoChucvuct;
    }

    public String getTuanmoThuky() {
        return tuanmoThuky;
    }

    public void setTuanmoThuky(String tuanmoThuky) {
        this.tuanmoThuky = tuanmoThuky;
    }

    public String getTuanmoChucvutk() {
        return tuanmoChucvutk;
    }

    public void setTuanmoChucvutk(String tuanmoChucvutk) {
        this.tuanmoChucvutk = tuanmoChucvutk;
    }

    public String getTuanmoThanhphan() {
        return tuanmoThanhphan;
    }

    public void setTuanmoThanhphan(String tuanmoThanhphan) {
        this.tuanmoThanhphan = tuanmoThanhphan;
    }

    public String getTuanmoYkien1() {
        return tuanmoYkien1;
    }

    public void setTuanmoYkien1(String tuanmoYkien1) {
        this.tuanmoYkien1 = tuanmoYkien1;
    }

    public String getTuanmoYkien2() {
        return tuanmoYkien2;
    }

    public void setTuanmoYkien2(String tuanmoYkien2) {
        this.tuanmoYkien2 = tuanmoYkien2;
    }

    public String getTuanmoKetluan1() {
        return tuanmoKetluan1;
    }

    public void setTuanmoKetluan1(String tuanmoKetluan1) {
        this.tuanmoKetluan1 = tuanmoKetluan1;
    }

    public String getTuanmoKetluan2() {
        return tuanmoKetluan2;
    }

    public void setTuanmoKetluan2(String tuanmoKetluan2) {
        this.tuanmoKetluan2 = tuanmoKetluan2;
    }

    public Date getTuanmoNgaygiocn() {
        return tuanmoNgaygiocn;
    }

    public void setTuanmoNgaygiocn(Date tuanmoNgaygiocn) {
        this.tuanmoNgaygiocn = tuanmoNgaygiocn;
    }

    public DmKhoa getKhoaMa(boolean create) {
if(create && khoaMa == null) khoaMa = new DmKhoa();
return khoaMa;
}
    public DmKhoa getKhoaMa() {
        return khoaMa;
    }

    public void setKhoaMa(DmKhoa khoaMa) {
        this.khoaMa = khoaMa;
    }

    public DtDmNhanVien getNhanvienMa(boolean create) {
if(create && nhanvienMa == null) nhanvienMa = new DtDmNhanVien();
return nhanvienMa;
}
    public DtDmNhanVien getNhanvienMa() {
        return nhanvienMa;
    }

    public void setNhanvienMa(DtDmNhanVien nhanvienMa) {
        this.nhanvienMa = nhanvienMa;
    }


//    public Collection<LichMo> getLichMoCollection() {
//        return lichMoCollection;
//    }
//
//    public void setLichMoCollection(Collection<LichMo> lichMoCollection) {
//        this.lichMoCollection = lichMoCollection;
//    }

//    public Collection<LichMo> getLichMoCollection1() {
//        return lichMoCollection1;
//    }
//
//    public void setLichMoCollection1(Collection<LichMo> lichMoCollection1) {
//        this.lichMoCollection1 = lichMoCollection1;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tuanmoMa != null ? tuanmoMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TuanMo)) {
            return false;
        }
        TuanMo other = (TuanMo) object;
        if ((this.tuanmoMa == null && other.tuanmoMa != null) || (this.tuanmoMa != null && !this.tuanmoMa.equals(other.tuanmoMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.TuanMo[tuanmoMa=" + tuanmoMa + "]";
    }

}


