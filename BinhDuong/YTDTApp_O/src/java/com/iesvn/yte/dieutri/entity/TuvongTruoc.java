/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author quang
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "TUVONG_TRUOC")
@NamedQueries({
    @NamedQuery(name = "TuvongTruoc.findAll", query = "SELECT t FROM TuvongTruoc t"),
    @NamedQuery(name = "TuvongTruoc.findByTvtMa", query = "SELECT t FROM TuvongTruoc t WHERE t.tvtMa = :tvtMa"),
    @NamedQuery(name = "TuvongTruoc.findByTvtNgaygio", query = "SELECT t FROM TuvongTruoc t WHERE t.tvtNgaygio = :tvtNgaygio"),
    @NamedQuery(name = "TuvongTruoc.findByTvtDonvigoi", query = "SELECT t FROM TuvongTruoc t WHERE t.tvtDonvigoi = :tvtDonvigoi"),
    @NamedQuery(name = "TuvongTruoc.findByTvtBacsiTruc", query = "SELECT t FROM TuvongTruoc t WHERE t.tvtBacsiTruc = :tvtBacsiTruc"),
    @NamedQuery(name = "TuvongTruoc.findByTvtBacsiNvtiep", query = "SELECT t FROM TuvongTruoc t WHERE t.tvtBacsiNvtiep = :tvtBacsiNvtiep"),
    @NamedQuery(name = "TuvongTruoc.findByTvtNhanviencn", query = "SELECT t FROM TuvongTruoc t WHERE t.tvtNhanviencn = :tvtNhanviencn"),
    @NamedQuery(name = "TuvongTruoc.findByTvtThungan", query = "SELECT t FROM TuvongTruoc t WHERE t.tvtThungan = :tvtThungan"),
    @NamedQuery(name = "TuvongTruoc.findByDiadiemMa", query = "SELECT t FROM TuvongTruoc t WHERE t.diadiemMa = :diadiemMa"),
    @NamedQuery(name = "TuvongTruoc.findByTvtLydovaov", query = "SELECT t FROM TuvongTruoc t WHERE t.tvtLydovaov = :tvtLydovaov"),
    @NamedQuery(name = "TuvongTruoc.findByTvtTenNguoiduaden", query = "SELECT t FROM TuvongTruoc t WHERE t.tvtTenNguoiduaden = :tvtTenNguoiduaden"),
    @NamedQuery(name = "TuvongTruoc.findByTvtTuoiNguoiduaden", query = "SELECT t FROM TuvongTruoc t WHERE t.tvtTuoiNguoiduaden = :tvtTuoiNguoiduaden"),
    @NamedQuery(name = "TuvongTruoc.findByTvtGioitinhNguoiduaden", query = "SELECT t FROM TuvongTruoc t WHERE t.tvtGioitinhNguoiduaden = :tvtGioitinhNguoiduaden"),
    @NamedQuery(name = "TuvongTruoc.findByTvtLhbnNguoiduaden", query = "SELECT t FROM TuvongTruoc t WHERE t.tvtLhbnNguoiduaden = :tvtLhbnNguoiduaden"),
    @NamedQuery(name = "TuvongTruoc.findByTvtBenhsu", query = "SELECT t FROM TuvongTruoc t WHERE t.tvtBenhsu = :tvtBenhsu"),
    @NamedQuery(name = "TuvongTruoc.findByTvtPpdtTruoc", query = "SELECT t FROM TuvongTruoc t WHERE t.tvtPpdtTruoc = :tvtPpdtTruoc"),
    @NamedQuery(name = "TuvongTruoc.findByTvtTuvongluc", query = "SELECT t FROM TuvongTruoc t WHERE t.tvtTuvongluc = :tvtTuvongluc"),
    @NamedQuery(name = "TuvongTruoc.findByTvtTiensu", query = "SELECT t FROM TuvongTruoc t WHERE t.tvtTiensu = :tvtTiensu"),
    @NamedQuery(name = "TuvongTruoc.findByTvtTinhtrangchung", query = "SELECT t FROM TuvongTruoc t WHERE t.tvtTinhtrangchung = :tvtTinhtrangchung"),
    @NamedQuery(name = "TuvongTruoc.findByTvtTrigiac", query = "SELECT t FROM TuvongTruoc t WHERE t.tvtTrigiac = :tvtTrigiac"),
    @NamedQuery(name = "TuvongTruoc.findByTvtDaniem", query = "SELECT t FROM TuvongTruoc t WHERE t.tvtDaniem = :tvtDaniem"),
    @NamedQuery(name = "TuvongTruoc.findByTvtDongtu", query = "SELECT t FROM TuvongTruoc t WHERE t.tvtDongtu = :tvtDongtu"),
    @NamedQuery(name = "TuvongTruoc.findByTvtMach", query = "SELECT t FROM TuvongTruoc t WHERE t.tvtMach = :tvtMach"),
    @NamedQuery(name = "TuvongTruoc.findByTvtHuyetap", query = "SELECT t FROM TuvongTruoc t WHERE t.tvtHuyetap = :tvtHuyetap"),
    @NamedQuery(name = "TuvongTruoc.findByTvtNhietdo", query = "SELECT t FROM TuvongTruoc t WHERE t.tvtNhietdo = :tvtNhietdo"),
    @NamedQuery(name = "TuvongTruoc.findByTvtTimmach", query = "SELECT t FROM TuvongTruoc t WHERE t.tvtTimmach = :tvtTimmach"),
    @NamedQuery(name = "TuvongTruoc.findByTvtHohap", query = "SELECT t FROM TuvongTruoc t WHERE t.tvtHohap = :tvtHohap"),
    @NamedQuery(name = "TuvongTruoc.findByTvtThuongtonbenhlychinh", query = "SELECT t FROM TuvongTruoc t WHERE t.tvtThuongtonbenhlychinh = :tvtThuongtonbenhlychinh"),
    @NamedQuery(name = "TuvongTruoc.findByTvtDientamdo", query = "SELECT t FROM TuvongTruoc t WHERE t.tvtDientamdo = :tvtDientamdo"),
    @NamedQuery(name = "TuvongTruoc.findByTvtChandoansobo", query = "SELECT t FROM TuvongTruoc t WHERE t.tvtChandoansobo = :tvtChandoansobo"),
    @NamedQuery(name = "TuvongTruoc.findByTvtCapcuu", query = "SELECT t FROM TuvongTruoc t WHERE t.tvtCapcuu = :tvtCapcuu"),
    @NamedQuery(name = "TuvongTruoc.findByTvtCanthiepkhac", query = "SELECT t FROM TuvongTruoc t WHERE t.tvtCanthiepkhac = :tvtCanthiepkhac"),
    @NamedQuery(name = "TuvongTruoc.findByTvtGiuxac", query = "SELECT t FROM TuvongTruoc t WHERE t.tvtGiuxac = :tvtGiuxac"),
    @NamedQuery(name = "TuvongTruoc.findByTvtXinxackhongkhieunai", query = "SELECT t FROM TuvongTruoc t WHERE t.tvtXinxackhongkhieunai = :tvtXinxackhongkhieunai"),
    @NamedQuery(name = "TuvongTruoc.findByTvtNguoixinxac", query = "SELECT t FROM TuvongTruoc t WHERE t.tvtNguoixinxac = :tvtNguoixinxac"),
    @NamedQuery(name = "TuvongTruoc.findByTvtNguoixinxacTuoi", query = "SELECT t FROM TuvongTruoc t WHERE t.tvtNguoixinxacTuoi = :tvtNguoixinxacTuoi"),
    @NamedQuery(name = "TuvongTruoc.findByTvtNguoixinxacGioitinh", query = "SELECT t FROM TuvongTruoc t WHERE t.tvtNguoixinxacGioitinh = :tvtNguoixinxacGioitinh"),
    @NamedQuery(name = "TuvongTruoc.findByTvtNguoixinxacLienhebn", query = "SELECT t FROM TuvongTruoc t WHERE t.tvtNguoixinxacLienhebn = :tvtNguoixinxacLienhebn"),
    @NamedQuery(name = "TuvongTruoc.findByTvtTaisanbenhnhan", query = "SELECT t FROM TuvongTruoc t WHERE t.tvtTaisanbenhnhan = :tvtTaisanbenhnhan"),
    @NamedQuery(name = "TuvongTruoc.findByTvtCobienbantaisan", query = "SELECT t FROM TuvongTruoc t WHERE t.tvtCobienbantaisan = :tvtCobienbantaisan")})
public class TuvongTruoc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TVT_MA")
    private String tvtMa;
    @Column(name = "TVT_NGAYGIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tvtNgaygio;
    @Column(name = "TVT_DONVIGOI")
    private Integer tvtDonvigoi;
    @Column(name = "TVT_BACSI_TRUC")
    private Integer tvtBacsiTruc;
    @Column(name = "TVT_BACSI_NVTIEP")
    private Integer tvtBacsiNvtiep;
    @Column(name = "TVT_NHANVIENCN")
    private Integer tvtNhanviencn;
    @Column(name = "TVT_THUNGAN")
    private Integer tvtThungan;
    @Column(name = "DIADIEM_MA")
    private Integer diadiemMa;
    @Column(name = "TVT_LYDOVAOV")
    private String tvtLydovaov;
    @Column(name = "TVT_TEN_NGUOIDUADEN")
    private String tvtTenNguoiduaden;
    @Column(name = "TVT_TUOI_NGUOIDUADEN")
    private String tvtTuoiNguoiduaden;
    @Column(name = "TVT_GIOITINH_NGUOIDUADEN")
    private Boolean tvtGioitinhNguoiduaden;
    @Column(name = "TVT_DIACHI_NGUOIDUADEN")
    private String tvtDiachiNguoiduaden;
    @Column(name = "TVT_PHUONGTIENVANCHUYEN")
    private String tvtPhuongtienvanchuyen;
    @Column(name = "TVT_PHUONGTIENVANCHUYEN_SOXE")
    private String tvtPhuongtienvanchuyenSoxe;
    @Column(name = "TVT_LHBN_NGUOIDUADEN")
    private String tvtLhbnNguoiduaden;
    @Column(name = "TVT_BENHSU")
    private String tvtBenhsu;
    @Column(name = "TVT_PPDT_TRUOC")
    private String tvtPpdtTruoc;
    @Column(name = "TVT_TUVONGLUC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tvtTuvongluc;
    @Column(name = "TVT_TIENSU")
    private String tvtTiensu;
    @Column(name = "TVT_TINHTRANGCHUNG")
    private String tvtTinhtrangchung;
    @Column(name = "TVT_TRIGIAC")
    private String tvtTrigiac;
    @Column(name = "TVT_DANIEM")
    private String tvtDaniem;
    @Column(name = "TVT_DONGTU")
    private String tvtDongtu;
    @Column(name = "TVT_MACH")
    private String tvtMach;
    @Column(name = "TVT_HUYETAP")
    private String tvtHuyetap;
    @Column(name = "TVT_NHIETDO")
    private String tvtNhietdo;
    @Column(name = "TVT_TIMMACH")
    private String tvtTimmach;
    @Column(name = "TVT_HOHAP")
    private String tvtHohap;
    @Column(name = "TVT_THUONGTONBENHLYCHINH")
    private String tvtThuongtonbenhlychinh;
    @Column(name = "TVT_DIENTAMDO")
    private String tvtDientamdo;
    @Column(name = "TVT_CHANDOANSOBO")
    private String tvtChandoansobo;
    @Column(name = "TVT_CAPCUU")
    private String tvtCapcuu;
    @Column(name = "TVT_CANTHIEPKHAC")
    private String tvtCanthiepkhac;
    @Column(name = "TVT_GIUXAC")
    private Boolean tvtGiuxac;
    @Column(name = "TVT_XINXACKHONGKHIEUNAI")
    private Boolean tvtXinxackhongkhieunai;
    @Column(name = "TVT_NGUOIXINXAC")
    private String tvtNguoixinxac;
    @Column(name = "TVT_NGUOIXINXAC_TUOI")
    private String tvtNguoixinxacTuoi;
    @Column(name = "TVT_NGUOIXINXAC_GIOITINH")
    private Boolean  tvtNguoixinxacGioitinh;
    @Column(name = "TVT_NGUOIXINXAC_LIENHEBN")
    private String tvtNguoixinxacLienhebn;
    @Column(name = "TVT_TAISANBENHNHAN")
    private Boolean tvtTaisanbenhnhan;
    @Column(name = "TVT_COBIENBANTAISAN")
    private Boolean tvtCobienbantaisan;
    @JoinColumn(name = "TVT_BANKHAM", referencedColumnName = "DTDMBANKHAM_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmBanKham tvtBankham;
    @JoinColumn(name = "TVT_THAMKHAM", referencedColumnName = "THAMKHAM_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private ThamKham tvtThamkham;
    @JoinColumn(name = "TVT_BENHNHAN", referencedColumnName = "BENHNHAN_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private BenhNhan tvtBenhnhan;

    public TuvongTruoc() {
    }

    public TuvongTruoc(String tvtMa) {
        this.tvtMa = tvtMa;
    }

    public String getTvtMa() {
        return tvtMa;
    }

    public void setTvtMa(String tvtMa) {
        this.tvtMa = tvtMa;
    }

    public Date getTvtNgaygio() {
        return tvtNgaygio;
    }

    public void setTvtNgaygio(Date tvtNgaygio) {
        this.tvtNgaygio = tvtNgaygio;
    }

    public Integer getTvtDonvigoi() {
        return tvtDonvigoi;
    }

    public void setTvtDonvigoi(Integer tvtDonvigoi) {
        this.tvtDonvigoi = tvtDonvigoi;
    }

    public Integer getTvtBacsiTruc() {
        return tvtBacsiTruc;
    }

    public void setTvtBacsiTruc(Integer tvtBacsiTruc) {
        this.tvtBacsiTruc = tvtBacsiTruc;
    }

    public Integer getTvtBacsiNvtiep() {
        return tvtBacsiNvtiep;
    }

    public void setTvtBacsiNvtiep(Integer tvtBacsiNvtiep) {
        this.tvtBacsiNvtiep = tvtBacsiNvtiep;
    }

    public Integer getTvtNhanviencn() {
        return tvtNhanviencn;
    }

    public void setTvtNhanviencn(Integer tvtNhanviencn) {
        this.tvtNhanviencn = tvtNhanviencn;
    }

    public Integer getTvtThungan() {
        return tvtThungan;
    }

    public void setTvtThungan(Integer tvtThungan) {
        this.tvtThungan = tvtThungan;
    }

    public Integer getDiadiemMa() {
        return diadiemMa;
    }

    public void setDiadiemMa(Integer diadiemMa) {
        this.diadiemMa = diadiemMa;
    }

    public String getTvtLydovaov() {
        return tvtLydovaov;
    }

    public void setTvtLydovaov(String tvtLydovaov) {
        this.tvtLydovaov = tvtLydovaov;
    }

    public String getTvtTenNguoiduaden() {
        return tvtTenNguoiduaden;
    }

    public void setTvtTenNguoiduaden(String tvtTenNguoiduaden) {
        this.tvtTenNguoiduaden = tvtTenNguoiduaden;
    }

    public String getTvtTuoiNguoiduaden() {
        return tvtTuoiNguoiduaden;
    }

    public void setTvtTuoiNguoiduaden(String tvtTuoiNguoiduaden) {
        this.tvtTuoiNguoiduaden = tvtTuoiNguoiduaden;
    }

    
    public String getTvtLhbnNguoiduaden() {
        return tvtLhbnNguoiduaden;
    }

    public void setTvtLhbnNguoiduaden(String tvtLhbnNguoiduaden) {
        this.tvtLhbnNguoiduaden = tvtLhbnNguoiduaden;
    }

    public String getTvtBenhsu() {
        return tvtBenhsu;
    }

    public void setTvtBenhsu(String tvtBenhsu) {
        this.tvtBenhsu = tvtBenhsu;
    }

    public String getTvtPpdtTruoc() {
        return tvtPpdtTruoc;
    }

    public void setTvtPpdtTruoc(String tvtPpdtTruoc) {
        this.tvtPpdtTruoc = tvtPpdtTruoc;
    }

    public Date getTvtTuvongluc() {
        return tvtTuvongluc;
    }

    public void setTvtTuvongluc(Date tvtTuvongluc) {
        this.tvtTuvongluc = tvtTuvongluc;
    }

    
    public String getTvtTiensu() {
        return tvtTiensu;
    }

    public void setTvtTiensu(String tvtTiensu) {
        this.tvtTiensu = tvtTiensu;
    }

    public String getTvtTinhtrangchung() {
        return tvtTinhtrangchung;
    }

    public void setTvtTinhtrangchung(String tvtTinhtrangchung) {
        this.tvtTinhtrangchung = tvtTinhtrangchung;
    }

    public String getTvtTrigiac() {
        return tvtTrigiac;
    }

    public void setTvtTrigiac(String tvtTrigiac) {
        this.tvtTrigiac = tvtTrigiac;
    }

    public String getTvtDaniem() {
        return tvtDaniem;
    }

    public void setTvtDaniem(String tvtDaniem) {
        this.tvtDaniem = tvtDaniem;
    }

    public String getTvtDongtu() {
        return tvtDongtu;
    }

    public void setTvtDongtu(String tvtDongtu) {
        this.tvtDongtu = tvtDongtu;
    }

    public String getTvtMach() {
        return tvtMach;
    }

    public void setTvtMach(String tvtMach) {
        this.tvtMach = tvtMach;
    }

    public String getTvtHuyetap() {
        return tvtHuyetap;
    }

    public void setTvtHuyetap(String tvtHuyetap) {
        this.tvtHuyetap = tvtHuyetap;
    }

    public String getTvtNhietdo() {
        return tvtNhietdo;
    }

    public void setTvtNhietdo(String tvtNhietdo) {
        this.tvtNhietdo = tvtNhietdo;
    }

    public String getTvtTimmach() {
        return tvtTimmach;
    }

    public void setTvtTimmach(String tvtTimmach) {
        this.tvtTimmach = tvtTimmach;
    }

    public String getTvtHohap() {
        return tvtHohap;
    }

    public void setTvtHohap(String tvtHohap) {
        this.tvtHohap = tvtHohap;
    }

    public String getTvtThuongtonbenhlychinh() {
        return tvtThuongtonbenhlychinh;
    }

    public void setTvtThuongtonbenhlychinh(String tvtThuongtonbenhlychinh) {
        this.tvtThuongtonbenhlychinh = tvtThuongtonbenhlychinh;
    }

    public String getTvtDientamdo() {
        return tvtDientamdo;
    }

    public void setTvtDientamdo(String tvtDientamdo) {
        this.tvtDientamdo = tvtDientamdo;
    }

    public String getTvtChandoansobo() {
        return tvtChandoansobo;
    }

    public void setTvtChandoansobo(String tvtChandoansobo) {
        this.tvtChandoansobo = tvtChandoansobo;
    }

    public String getTvtCapcuu() {
        return tvtCapcuu;
    }

    public void setTvtCapcuu(String tvtCapcuu) {
        this.tvtCapcuu = tvtCapcuu;
    }

    public String getTvtCanthiepkhac() {
        return tvtCanthiepkhac;
    }

    public void setTvtCanthiepkhac(String tvtCanthiepkhac) {
        this.tvtCanthiepkhac = tvtCanthiepkhac;
    }

    public Boolean getTvtGiuxac() {
        return tvtGiuxac;
    }

    public void setTvtGiuxac(Boolean tvtGiuxac) {
        this.tvtGiuxac = tvtGiuxac;
    }

    public Boolean getTvtXinxackhongkhieunai() {
        return tvtXinxackhongkhieunai;
    }

    public void setTvtXinxackhongkhieunai(Boolean tvtXinxackhongkhieunai) {
        this.tvtXinxackhongkhieunai = tvtXinxackhongkhieunai;
    }

    public String getTvtNguoixinxac() {
        return tvtNguoixinxac;
    }

    public void setTvtNguoixinxac(String tvtNguoixinxac) {
        this.tvtNguoixinxac = tvtNguoixinxac;
    }

    public String getTvtNguoixinxacTuoi() {
        return tvtNguoixinxacTuoi;
    }

    public void setTvtNguoixinxacTuoi(String tvtNguoixinxacTuoi) {
        this.tvtNguoixinxacTuoi = tvtNguoixinxacTuoi;
    }

    public Boolean getTvtGioitinhNguoiduaden() {
        return tvtGioitinhNguoiduaden;
    }

    public void setTvtGioitinhNguoiduaden(Boolean tvtGioitinhNguoiduaden) {
        this.tvtGioitinhNguoiduaden = tvtGioitinhNguoiduaden;
    }

    public Boolean getTvtNguoixinxacGioitinh() {
        return tvtNguoixinxacGioitinh;
    }

    public void setTvtNguoixinxacGioitinh(Boolean tvtNguoixinxacGioitinh) {
        this.tvtNguoixinxacGioitinh = tvtNguoixinxacGioitinh;
    }

  

    public String getTvtNguoixinxacLienhebn() {
        return tvtNguoixinxacLienhebn;
    }

    public void setTvtNguoixinxacLienhebn(String tvtNguoixinxacLienhebn) {
        this.tvtNguoixinxacLienhebn = tvtNguoixinxacLienhebn;
    }

    public Boolean getTvtTaisanbenhnhan() {
        return tvtTaisanbenhnhan;
    }

    public void setTvtTaisanbenhnhan(Boolean tvtTaisanbenhnhan) {
        this.tvtTaisanbenhnhan = tvtTaisanbenhnhan;
    }

    public Boolean getTvtCobienbantaisan() {
        return tvtCobienbantaisan;
    }

    public void setTvtCobienbantaisan(Boolean tvtCobienbantaisan) {
        this.tvtCobienbantaisan = tvtCobienbantaisan;
    }

    public DtDmBanKham getTvtBankham(boolean create) {
        if(create&&tvtBankham==null)
           tvtBankham = new DtDmBanKham();
        return tvtBankham;
    }
    public ThamKham getTvtThamkham(boolean create) {
            if(create&&tvtThamkham==null)
               tvtThamkham = new ThamKham();
            return tvtThamkham;
    }
    public BenhNhan getTvtBenhnhan(boolean create) {
        if(create&&tvtBenhnhan==null)
           tvtBenhnhan = new BenhNhan();
        return tvtBenhnhan;
    }

    public DtDmBanKham getTvtBankham() {
        return tvtBankham;
    }

    public void setTvtBankham(DtDmBanKham tvtBankham) {
        this.tvtBankham = tvtBankham;
    }

    public BenhNhan getTvtBenhnhan() {
        return tvtBenhnhan;
    }

    public void setTvtBenhnhan(BenhNhan tvtBenhnhan) {
        this.tvtBenhnhan = tvtBenhnhan;
    }

    public ThamKham getTvtThamkham() {
        return tvtThamkham;
    }

    public void setTvtThamkham(ThamKham tvtThamkham) {
        this.tvtThamkham = tvtThamkham;
    }

    public String getTvtDiachiNguoiduaden() {
        return tvtDiachiNguoiduaden;
    }

    public void setTvtDiachiNguoiduaden(String tvtDiachiNguoiduaden) {
        this.tvtDiachiNguoiduaden = tvtDiachiNguoiduaden;
    }

    public String getTvtPhuongtienvanchuyen() {
        return tvtPhuongtienvanchuyen;
    }

    public void setTvtPhuongtienvanchuyen(String tvtPhuongtienvanchuyen) {
        this.tvtPhuongtienvanchuyen = tvtPhuongtienvanchuyen;
    }

    public String getTvtPhuongtienvanchuyenSoxe() {
        return tvtPhuongtienvanchuyenSoxe;
    }

    public void setTvtPhuongtienvanchuyenSoxe(String tvtPhuongtienvanchuyenSoxe) {
        this.tvtPhuongtienvanchuyenSoxe = tvtPhuongtienvanchuyenSoxe;
    }

        


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tvtMa != null ? tvtMa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TuvongTruoc)) {
            return false;
        }
        TuvongTruoc other = (TuvongTruoc) object;
        if ((this.tvtMa == null && other.tvtMa != null) || (this.tvtMa != null && !this.tvtMa.equals(other.tvtMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.TuvongTruoc[tvtMa=" + tvtMa + "]";
    }

}
