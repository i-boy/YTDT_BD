/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.entity;

import com.iesvn.yte.entity.DmTaiNan;
import com.iesvn.yte.entity.DmDiaDiem;
import com.iesvn.yte.entity.DmBenhTruyenNhiem;
import com.iesvn.yte.entity.DmBenhVien;
import com.iesvn.yte.entity.DmDieuTri;
import com.iesvn.yte.entity.DmPhuongThucGayTaiNan;
import com.iesvn.yte.entity.DmDoiTuong;
import com.iesvn.yte.entity.DmBenhIcd;
import com.iesvn.yte.entity.DmKetQuaDieuTri;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.entity.DmTinh;
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
@Table(name = "TIEP_DON")
@NamedQueries({})
public class TiepDon implements Serializable {

    private static long serialVersionUID = 1L;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }
    @Id
    @Column(name = "TIEPDON_MA", nullable = false)
    private String tiepdonMa;
    @Column(name = "TIEPDON_MAPHIEUK")
    private String tiepdonMaphieuk;
    @Column(name = "TIEPDON_NGAYGIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tiepdonNgaygio;
    @Column(name = "TIEPDON_PHANLOAI")
    private String tiepdonPhanloai;
    @Column(name = "DPBHYT_MA")
    private String dpbhytMa;
    @Column(name = "TIEPDON_MACOQUAN")
    private String tiepdonMacoquan;
    @Column(name = "TIEPDON_SOTHEBH")
    private String tiepdonSothebh;
    @Column(name = "TIEPDON_NAMBHYT")
    private String tiepdonNambhyt;
    @Column(name = "TIEPDON_SONAMBH")
    private Short tiepdonSonambh;
    @Column(name = "TIEPDON_TUYEN")
    private Short tiepdonTuyen;

    @Column(name = "TIEPDON_GIATRI1")
    @Temporal(TemporalType.DATE)
    private Date tiepdonGiatri1;

    @Column(name = "TIEPDON_GIATRI2")
    @Temporal(TemporalType.DATE)
    private Date tiepdonGiatri2;

    @Column(name = "TIEPDON_GIATRI3")
    @Temporal(TemporalType.DATE)
    private Date tiepdonGiatri3;

    @Column(name = "TIEPDON_GIATRI4")
    @Temporal(TemporalType.DATE)
    private Date tiepdonGiatri4;

    @Column(name = "TIEPDON_MOC1")
    @Temporal(TemporalType.DATE)
    private Date tiepdonMoc1;
    @Column(name = "TIEPDON_MOC2")
    @Temporal(TemporalType.DATE)
    private Date tiepdonMoc2;
    @Column(name = "TIEPDON_MOC3")
    @Temporal(TemporalType.DATE)
    private Date tiepdonMoc3;
    @Column(name = "TIEPDON_TTVANCHUYEN")
    private Boolean tiepdonTTVanChuyen;
    @Column(name = "TIEPDON_KHAISINH")
    private String tiepdonKhaisinh;
    @Column(name = "TIEPDON_CHUNGSINH")
    private String tiepdonChungsinh;
    @Column(name = "TIEPDON_SOTHETE")
    private String tiepdonSothete;
    @Column(name = "TIEPDON_THENGHEO")
    private String tiepdonThengheo;
    @Column(name = "TIEPDON_THUTU")
    private Short tiepdonThutu;
    @Column(name = "TIEPDON_THUTUS")
    private Short tiepdonThutus;
    @Column(name = "TIEPDON_BNTRA")
    private Integer tiepdonBntra;
    @Column(name = "TIEPDON_TYLEBH")
    private Short tiepdonTylebh;
    @Column(name = "TIEPDON_KYHIEU")
    private String tiepdonKyhieu;
    @Column(name = "TIEPDON_QUYEN")
    private String tiepdonQuyen;
    @Column(name = "TIEPDON_BIENLAI")
    private String tiepdonBienlai;
    @Column(name = "TIEPDON_MAPHIEUD")
    private String tiepdonMaphieud;
    @Column(name = "NGUYENHAN_MA")
    private String nguyenhanMa;
    @Column(name = "TIEPDON_RUOUBIA")
    private Boolean tiepdonRuoubia;
    @Column(name = "TIEPDON_CODOIMU")
    private Boolean tiepdonCodoimu;
    @Column(name = "TIEPDON_MUVO")
    private Boolean tiepdonMuvo;
    @Column(name = "TIEPDON_KHONGCAIDAY")
    private Boolean tiepdonKhongcaiday;
    @Column(name = "TIEPDON_KHONGRONGUONGOC")
    private Boolean tiepdonKhongronguongoc;
    @Column(name = "TIEPDON_LYDOVAOV")
    private String tiepdonLydovaov;
    @Column(name = "TIEPDON_MACH")
    private Double tiepdonMach;
    @Column(name = "TIEPDON_NHIETDO")
    private Double tiepdonNhietdo;
    @Column(name = "TIEPDON_NHIPTHO")
    private Double tiepdonNhiptho;
    @Column(name = "TIEPDON_HAMAX")
    private Double tiepdonHamax;
    @Column(name = "TIEPDON_HAMIN")
    private Double tiepdonHamin;
    @Column(name = "TIEPDON_DGCHDOANB0")
    private String tiepdonDgchdoanb0;
    @Column(name = "TIEPDON_DGCHDOANBD")
    private String tiepdonDgchdoanbd;
    @Column(name = "TIEPDON_VUOTTUYEN")
    private String tiepdonVuottuyen;
    @Column(name = "TIEPDON_TRLUONG")
    private Double tiepdonTrluong;
    @Column(name = "TIEPDON_CHIEUCAO")
    private Double tiepdonChieucao;
    @Column(name = "TIEPDON_NHOMMAU")
    private String tiepdonNhommau;
    @Column(name = "TIEPDON_RH")
    private String tiepdonRh;
    @Column(name = "TIEPDON_GIUONG")
    private String tiepdonGiuong;
    @Column(name = "TIEPDON_BAOTIN")
    private String tiepdonBaotin;
    @Column(name = "TIEPDON_NGHE")
    private String tiepdonNghe;
    @Column(name = "TIEPDON_PARA1")
    private String tiepdonPara1;
    @Column(name = "TIEPDON_PARA2")
    private String tiepdonPara2;
    @Column(name = "TIEPDON_PARA3")
    private String tiepdonPara3;
    @Column(name = "TIEPDON_PARA4")
    private String tiepdonPara4;
    @Column(name = "TIEPDON_NGAYGIORA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tiepdonNgaygiora;
    @Column(name = "TIEPDON_TITRANGRA")
    private String tiepdonTitrangra;
    @Column(name = "TIEPDON_TAIKHAM")
    @Temporal(TemporalType.DATE)
    private Date tiepdonTaikham;
    @Column(name = "TIEPDON_LOIDAN")
    private String tiepdonLoidan;
    @Column(name = "TIEPDON_CUM")
    private String tiepdonCum;
    @Column(name = "TIEPDON_NGAYGIOCN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tiepdonNgaygiocn;
    @Column(name = "TIEPDON_STATUS")
    private String tiepdonStatus;
    @Column(name = "TIEPDON_NGAYTT")
    @Temporal(TemporalType.DATE)
    private Date tiepdonNgaytt;

    @Column(name = "TIEPDON_CO_GIAY_GIOI_THIEU")
    private Boolean tiepdonCoGiayGioiThieu;
    @Column(name = "TIEPDON_KHAM_DA_LIEU")
    private Boolean tiepdonKhamDaLieu;


//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tiepdonMa")
//    private Collection<HsThtoank> hsThtoankCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tiepdonMa1")
//    private Collection<HsThtoank> hsThtoankCollection1;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tiepdonMa")
//    private Collection<PhieuXuatBh> phieuXuatBhCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tiepdonMa")
//    private Collection<XetGiamKham> xetGiamKhamCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tiepdonMa1")
//    private Collection<XetGiamKham> xetGiamKhamCollection1;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tiepdonMa")
//    private Collection<HoanUngKham> hoanUngKhamCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tiepdonMa1")
//    private Collection<HoanUngKham> hoanUngKhamCollection1;
//    @OneToMany(mappedBy = "tiepdonMa")
//    private Collection<ThamKham> thamKhamCollection;
//    @OneToMany(mappedBy = "tiepdonMa1")
//    private Collection<ThamKham> thamKhamCollection1;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tiepdonMa")
//    private Collection<TamUngKham> tamUngKhamCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tiepdonMa1")
//    private Collection<TamUngKham> tamUngKhamCollection1;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tiepdonMa")
//    private Collection<HoanThuKham> hoanThuKhamCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tiepdonMa1")
//    private Collection<HoanThuKham> hoanThuKhamCollection1;
    @JoinColumn(name = "BENHNHAN_MA", referencedColumnName = "BENHNHAN_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private BenhNhan benhnhanMa;
    @JoinColumn(name = "DIADIEM_MA", referencedColumnName = "DMDIADIEM_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmDiaDiem diadiemMa;
    @JoinColumn(name = "DIEUTRI_MA", referencedColumnName = "DMDIEUTRI_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmDieuTri dieutriMa;
    @JoinColumn(name = "DMPTGTN_MASO", referencedColumnName = "DMPTGTN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmPhuongThucGayTaiNan dmptgtnMaso;
    @JoinColumn(name = "DOITUONG_MA", referencedColumnName = "DMDOITUONG_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmDoiTuong doituongMa;

    @JoinColumn(name = "KCBBHYT_MA", referencedColumnName = "DMBENHVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmBenhVien kcbbhytMa;

    @JoinColumn(name = "KETQUA_MA", referencedColumnName = "DMKQDT_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKetQuaDieuTri ketquaMa;
    @JoinColumn(name = "KHOIBHYT_MA", referencedColumnName = "DTDMKHOIBHYT_MA")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmKhoiBhyt khoibhytMa;
    @JoinColumn(name = "TAINAN_MA", referencedColumnName = "DMTAINAN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmTaiNan tainanMa;
    @JoinColumn(name = "TIEPDON_BACSI", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien tiepdonBacsi;
    @JoinColumn(name = "TIEPDON_BANKHAM", referencedColumnName = "DTDMBANKHAM_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmBanKham tiepdonBankham;
    @JoinColumn(name = "TIEPDON_BSCHUYEN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien tiepdonBschuyen;
    @JoinColumn(name = "TIEPDON_CHKHOA", referencedColumnName = "DMKHOA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmKhoa tiepdonChkhoa;
    @JoinColumn(name = "TIEPDON_CHVIEN", referencedColumnName = "DMBENHVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmBenhVien tiepdonChvien;
    @JoinColumn(name = "TIEPDON_DOITUONGBHYT", referencedColumnName = "DTDMPHLOAIBHYT_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmPlBhyt tiepdonDoituongbhyt;
    @JoinColumn(name = "TIEPDON_DONVIGOI", referencedColumnName = "DMBENHVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmBenhVien tiepdonDonvigoi;
    
    
//     @JoinColumn(name = "TIEPDON_LOAIKHAM", referencedColumnName = "DTDMCLSBG_MASO")
//    @ManyToOne (fetch=FetchType.LAZY)
//    private DtDmClsBangGia tiepdonLoaikham;
    
     @Column(name = "TIEPDON_LOAIKHAM")
    private String tiepdonLoaikham;
   
      @Column(name = "TIEPDON_SOTT")
    private Integer tiepdonSoTT;
    
    @JoinColumn(name = "TIEPDON_LYDOCHVI", referencedColumnName = "DTDMLYDOCV_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmLyDoCv tiepdonLydochvi;
    @JoinColumn(name = "TIEPDON_MACHDOANB0", referencedColumnName = "DMBENHICD_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmBenhIcd tiepdonMachdoanb0;
    @JoinColumn(name = "TIEPDON_MACHDOANBD", referencedColumnName = "DMBENHICD_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmBenhIcd tiepdonMachdoanbd;
    @JoinColumn(name = "TIEPDON_MADICH", referencedColumnName = "DMBTN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmBenhTruyenNhiem tiepdonMadich;
    @JoinColumn(name = "TIEPDON_NGUOIDUYET", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien tiepdonNguoiduyet;
    @JoinColumn(name = "TIEPDON_NHANVIENCN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien tiepdonNhanviencn;
    @JoinColumn(name = "TIEPDON_THUNGAN", referencedColumnName = "DTDMNHANVIEN_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmNhanVien tiepdonThungan;


    @JoinColumn(name = "TIEPDON_TUVONG", referencedColumnName = "DMBENHICD_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmBenhIcd tiepdonTuvong;

    @Column(name = "TIEPDON_TUVONG_PHU1")
    private Integer tiepdonTuvongphu1;

    @Column(name = "TIEPDON_TUVONG_PHU2")
    private Integer tiepdonTuvongphu2;

    @Column(name = "TIEPDON_TUVONG_PHU3")
    private Integer tiepdonTuvongphu3;

    @Column(name = "TIEPDON_TUVONG_PHU4")
    private Integer tiepdonTuvongphu4;

    @Column(name = "TIEPDON_TUVONG_PHU5")
    private Integer tiepdonTuvongphu5;





    
    @JoinColumn(name = "TINHBHYT_MA", referencedColumnName = "DMTINH_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DmTinh tinhbhytMa;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tiepdonMa")
//    private Collection<DeNghiCc> deNghiCcCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tiepdonMa1")
//    private Collection<DeNghiCc> deNghiCcCollection1;
//    @OneToMany(mappedBy = "tiepdonMa")
//    private Collection<Hsba> hsbaCollection;
//    @OneToMany(mappedBy = "tiepdonMa1")
//    private Collection<Hsba> hsbaCollection1;
    public TiepDon() {
    }

    public TiepDon(String tiepdonMa) {
        this.tiepdonMa = tiepdonMa;
    }

    public String getTiepdonMa() {
        return tiepdonMa;
    }

    public void setTiepdonMa(String tiepdonMa) {
        this.tiepdonMa = tiepdonMa;
    }

    public String getTiepdonMaphieuk() {
        return tiepdonMaphieuk;
    }

    public void setTiepdonMaphieuk(String tiepdonMaphieuk) {
        this.tiepdonMaphieuk = tiepdonMaphieuk;
    }

    public Date getTiepdonNgaygio() {
        return tiepdonNgaygio;
    }

    public void setTiepdonNgaygio(Date tiepdonNgaygio) {
        this.tiepdonNgaygio = tiepdonNgaygio;
    }

    public String getTiepdonPhanloai() {
        return tiepdonPhanloai;
    }

    public void setTiepdonPhanloai(String tiepdonPhanloai) {
        this.tiepdonPhanloai = tiepdonPhanloai;
    }

    public String getDpbhytMa() {
        return dpbhytMa;
    }

    public void setDpbhytMa(String dpbhytMa) {
        this.dpbhytMa = dpbhytMa;
    }

    public String getTiepdonMacoquan() {
        return tiepdonMacoquan;
    }

    public void setTiepdonMacoquan(String tiepdonMacoquan) {
        this.tiepdonMacoquan = tiepdonMacoquan;
    }

    public String getTiepdonSothebh() {
        return tiepdonSothebh;
    }

    public void setTiepdonSothebh(String tiepdonSothebh) {
        this.tiepdonSothebh = tiepdonSothebh;
    }

    public String getTiepdonNambhyt() {
        return tiepdonNambhyt;
    }

    public void setTiepdonNambhyt(String tiepdonNambhyt) {
        this.tiepdonNambhyt = tiepdonNambhyt;
    }

    public Short getTiepdonSonambh() {
        return tiepdonSonambh;
    }

    public void setTiepdonSonambh(Short tiepdonSonambh) {
        this.tiepdonSonambh = tiepdonSonambh;
    }

    public Short getTiepdonTuyen() {
        return tiepdonTuyen;
    }

    public void setTiepdonTuyen(Short tiepdonTuyen) {
        this.tiepdonTuyen = tiepdonTuyen;
    }

    public Date getTiepdonGiatri1() {
        return tiepdonGiatri1;
    }

    public void setTiepdonGiatri1(Date tiepdonGiatri1) {
        this.tiepdonGiatri1 = tiepdonGiatri1;
    }

    public Date getTiepdonGiatri2() {
        return tiepdonGiatri2;
    }

    public void setTiepdonGiatri2(Date tiepdonGiatri2) {
        this.tiepdonGiatri2 = tiepdonGiatri2;
    }

    public String getTiepdonKhaisinh() {
        return tiepdonKhaisinh;
    }

    public void setTiepdonKhaisinh(String tiepdonKhaisinh) {
        this.tiepdonKhaisinh = tiepdonKhaisinh;
    }

    public String getTiepdonChungsinh() {
        return tiepdonChungsinh;
    }

    public void setTiepdonChungsinh(String tiepdonChungsinh) {
        this.tiepdonChungsinh = tiepdonChungsinh;
    }

    public String getTiepdonSothete() {
        return tiepdonSothete;
    }

    public void setTiepdonSothete(String tiepdonSothete) {
        this.tiepdonSothete = tiepdonSothete;
    }

    public String getTiepdonThengheo() {
        return tiepdonThengheo;
    }

    public void setTiepdonThengheo(String tiepdonThengheo) {
        this.tiepdonThengheo = tiepdonThengheo;
    }

    public Short getTiepdonThutu() {
        return tiepdonThutu;
    }

    public void setTiepdonThutu(Short tiepdonThutu) {
        this.tiepdonThutu = tiepdonThutu;
    }

    public Short getTiepdonThutus() {
        return tiepdonThutus;
    }

    public void setTiepdonThutus(Short tiepdonThutus) {
        this.tiepdonThutus = tiepdonThutus;
    }

    public Integer getTiepdonBntra() {
        return tiepdonBntra;
    }

    public void setTiepdonBntra(Integer tiepdonBntra) {
        this.tiepdonBntra = tiepdonBntra;
    }

    public Short getTiepdonTylebh() {
        return tiepdonTylebh;
    }

    public void setTiepdonTylebh(Short tiepdonTylebh) {
        this.tiepdonTylebh = tiepdonTylebh;
    }

    public String getTiepdonKyhieu() {
        return tiepdonKyhieu;
    }

    public void setTiepdonKyhieu(String tiepdonKyhieu) {
        this.tiepdonKyhieu = tiepdonKyhieu;
    }

    public String getTiepdonQuyen() {
        return tiepdonQuyen;
    }

    public void setTiepdonQuyen(String tiepdonQuyen) {
        this.tiepdonQuyen = tiepdonQuyen;
    }

    public String getTiepdonBienlai() {
        return tiepdonBienlai;
    }

    public void setTiepdonBienlai(String tiepdonBienlai) {
        this.tiepdonBienlai = tiepdonBienlai;
    }

    public String getTiepdonMaphieud() {
        return tiepdonMaphieud;
    }

    public void setTiepdonMaphieud(String tiepdonMaphieud) {
        this.tiepdonMaphieud = tiepdonMaphieud;
    }

    public String getNguyenhanMa() {
        return nguyenhanMa;
    }

    public void setNguyenhanMa(String nguyenhanMa) {
        this.nguyenhanMa = nguyenhanMa;
    }

    public Boolean getTiepdonRuoubia() {
        return tiepdonRuoubia;
    }

    public void setTiepdonRuoubia(Boolean tiepdonRuoubia) {
        this.tiepdonRuoubia = tiepdonRuoubia;
    }

    public Boolean getTiepdonCodoimu() {
        return tiepdonCodoimu;
    }

    public void setTiepdonCodoimu(Boolean tiepdonCodoimu) {
        this.tiepdonCodoimu = tiepdonCodoimu;
    }

    public Boolean getTiepdonKhongcaiday() {
        return tiepdonKhongcaiday;
    }

    public void setTiepdonKhongcaiday(Boolean tiepdonKhongcaiday) {
        this.tiepdonKhongcaiday = tiepdonKhongcaiday;
    }

    public Boolean getTiepdonKhongronguongoc() {
        return tiepdonKhongronguongoc;
    }

    public void setTiepdonKhongronguongoc(Boolean tiepdonKhongronguongoc) {
        this.tiepdonKhongronguongoc = tiepdonKhongronguongoc;
    }

    public Boolean getTiepdonMuvo() {
        return tiepdonMuvo;
    }

    public void setTiepdonMuvo(Boolean tiepdonMuvo) {
        this.tiepdonMuvo = tiepdonMuvo;
    }

    
    public String getTiepdonLydovaov() {
        return tiepdonLydovaov;
    }

    public void setTiepdonLydovaov(String tiepdonLydovaov) {
        this.tiepdonLydovaov = tiepdonLydovaov;
    }

    public String getTiepdonDgchdoanb0() {
        return tiepdonDgchdoanb0;
    }

    public void setTiepdonDgchdoanb0(String tiepdonDgchdoanb0) {
        this.tiepdonDgchdoanb0 = tiepdonDgchdoanb0;
    }

    public String getTiepdonDgchdoanbd() {
        return tiepdonDgchdoanbd;
    }

    public void setTiepdonDgchdoanbd(String tiepdonDgchdoanbd) {
        this.tiepdonDgchdoanbd = tiepdonDgchdoanbd;
    }

    public String getTiepdonVuottuyen() {
        return tiepdonVuottuyen;
    }

    public void setTiepdonVuottuyen(String tiepdonVuottuyen) {
        this.tiepdonVuottuyen = tiepdonVuottuyen;
    }

    public Double getTiepdonTrluong() {
        return tiepdonTrluong;
    }

    public void setTiepdonTrluong(Double tiepdonTrluong) {
        this.tiepdonTrluong = tiepdonTrluong;
    }

    public Double getTiepdonChieucao() {
        return tiepdonChieucao;
    }

    public void setTiepdonChieucao(Double tiepdonChieucao) {
        this.tiepdonChieucao = tiepdonChieucao;
    }

    public String getTiepdonNhommau() {
        return tiepdonNhommau;
    }

    public void setTiepdonNhommau(String tiepdonNhommau) {
        this.tiepdonNhommau = tiepdonNhommau;
    }

    public String getTiepdonRh() {
        return tiepdonRh;
    }

    public void setTiepdonRh(String tiepdonRh) {
        this.tiepdonRh = tiepdonRh;
    }

    public String getTiepdonGiuong() {
        return tiepdonGiuong;
    }

    public void setTiepdonGiuong(String tiepdonGiuong) {
        this.tiepdonGiuong = tiepdonGiuong;
    }

    public String getTiepdonBaotin() {
        return tiepdonBaotin;
    }

    public void setTiepdonBaotin(String tiepdonBaotin) {
        this.tiepdonBaotin = tiepdonBaotin;
    }

    public String getTiepdonNghe() {
        return tiepdonNghe;
    }

    public void setTiepdonNghe(String tiepdonNghe) {
        this.tiepdonNghe = tiepdonNghe;
    }

    public String getTiepdonPara1() {
        return tiepdonPara1;
    }

    public void setTiepdonPara1(String tiepdonPara1) {
        this.tiepdonPara1 = tiepdonPara1;
    }

    public String getTiepdonPara2() {
        return tiepdonPara2;
    }

    public void setTiepdonPara2(String tiepdonPara2) {
        this.tiepdonPara2 = tiepdonPara2;
    }

    public String getTiepdonPara3() {
        return tiepdonPara3;
    }

    public void setTiepdonPara3(String tiepdonPara3) {
        this.tiepdonPara3 = tiepdonPara3;
    }

    public String getTiepdonPara4() {
        return tiepdonPara4;
    }

    public void setTiepdonPara4(String tiepdonPara4) {
        this.tiepdonPara4 = tiepdonPara4;
    }

    public Date getTiepdonNgaygiora() {
        return tiepdonNgaygiora;
    }

    public void setTiepdonNgaygiora(Date tiepdonNgaygiora) {
        this.tiepdonNgaygiora = tiepdonNgaygiora;
    }

    public String getTiepdonTitrangra() {
        return tiepdonTitrangra;
    }

    public void setTiepdonTitrangra(String tiepdonTitrangra) {
        this.tiepdonTitrangra = tiepdonTitrangra;
    }

    public Date getTiepdonTaikham() {
        return tiepdonTaikham;
    }

    public void setTiepdonTaikham(Date tiepdonTaikham) {
        this.tiepdonTaikham = tiepdonTaikham;
    }

    public String getTiepdonLoidan() {
        return tiepdonLoidan;
    }

    public void setTiepdonLoidan(String tiepdonLoidan) {
        this.tiepdonLoidan = tiepdonLoidan;
    }

    public String getTiepdonCum() {
        return tiepdonCum;
    }

    public void setTiepdonCum(String tiepdonCum) {
        this.tiepdonCum = tiepdonCum;
    }

    public Date getTiepdonNgaygiocn() {
        return tiepdonNgaygiocn;
    }

    public void setTiepdonNgaygiocn(Date tiepdonNgaygiocn) {
        this.tiepdonNgaygiocn = tiepdonNgaygiocn;
    }

    public String getTiepdonStatus() {
        return tiepdonStatus;
    }

    public void setTiepdonStatus(String tiepdonStatus) {
        this.tiepdonStatus = tiepdonStatus;
    }

    public Date getTiepdonNgaytt() {
        return tiepdonNgaytt;
    }

    public void setTiepdonNgaytt(Date tiepdonNgaytt) {
        this.tiepdonNgaytt = tiepdonNgaytt;
    }

//    public Collection<HsThtoank> getHsThtoankCollection() {
//        return hsThtoankCollection;
//    }
//
//    public void setHsThtoankCollection(Collection<HsThtoank> hsThtoankCollection) {
//        this.hsThtoankCollection = hsThtoankCollection;
//    }

//    public Collection<HsThtoank> getHsThtoankCollection1() {
//        return hsThtoankCollection1;
//    }
//
//    public void setHsThtoankCollection1(Collection<HsThtoank> hsThtoankCollection1) {
//        this.hsThtoankCollection1 = hsThtoankCollection1;
//    }

//    public Collection<PhieuXuatBh> getPhieuXuatBhCollection() {
//        return phieuXuatBhCollection;
//    }
//
//    public void setPhieuXuatBhCollection(Collection<PhieuXuatBh> phieuXuatBhCollection) {
//        this.phieuXuatBhCollection = phieuXuatBhCollection;
//    }

//    public Collection<XetGiamKham> getXetGiamKhamCollection() {
//        return xetGiamKhamCollection;
//    }
//
//    public void setXetGiamKhamCollection(Collection<XetGiamKham> xetGiamKhamCollection) {
//        this.xetGiamKhamCollection = xetGiamKhamCollection;
//    }

//    public Collection<XetGiamKham> getXetGiamKhamCollection1() {
//        return xetGiamKhamCollection1;
//    }
//
//    public void setXetGiamKhamCollection1(Collection<XetGiamKham> xetGiamKhamCollection1) {
//        this.xetGiamKhamCollection1 = xetGiamKhamCollection1;
//    }

//    public Collection<HoanUngKham> getHoanUngKhamCollection() {
//        return hoanUngKhamCollection;
//    }
//
//    public void setHoanUngKhamCollection(Collection<HoanUngKham> hoanUngKhamCollection) {
//        this.hoanUngKhamCollection = hoanUngKhamCollection;
//    }

//    public Collection<HoanUngKham> getHoanUngKhamCollection1() {
//        return hoanUngKhamCollection1;
//    }
//
//    public void setHoanUngKhamCollection1(Collection<HoanUngKham> hoanUngKhamCollection1) {
//        this.hoanUngKhamCollection1 = hoanUngKhamCollection1;
//    }

//    public Collection<ThamKham> getThamKhamCollection() {
//        return thamKhamCollection;
//    }
//
//    public void setThamKhamCollection(Collection<ThamKham> thamKhamCollection) {
//        this.thamKhamCollection = thamKhamCollection;
//    }

//    public Collection<ThamKham> getThamKhamCollection1() {
//        return thamKhamCollection1;
//    }
//
//    public void setThamKhamCollection1(Collection<ThamKham> thamKhamCollection1) {
//        this.thamKhamCollection1 = thamKhamCollection1;
//    }

//    public Collection<TamUngKham> getTamUngKhamCollection() {
//        return tamUngKhamCollection;
//    }
//
//    public void setTamUngKhamCollection(Collection<TamUngKham> tamUngKhamCollection) {
//        this.tamUngKhamCollection = tamUngKhamCollection;
//    }

//    public Collection<TamUngKham> getTamUngKhamCollection1() {
//        return tamUngKhamCollection1;
//    }
//
//    public void setTamUngKhamCollection1(Collection<TamUngKham> tamUngKhamCollection1) {
//        this.tamUngKhamCollection1 = tamUngKhamCollection1;
//    }

//    public Collection<HoanThuKham> getHoanThuKhamCollection() {
//        return hoanThuKhamCollection;
//    }
//
//    public void setHoanThuKhamCollection(Collection<HoanThuKham> hoanThuKhamCollection) {
//        this.hoanThuKhamCollection = hoanThuKhamCollection;
//    }

//    public Collection<HoanThuKham> getHoanThuKhamCollection1() {
//        return hoanThuKhamCollection1;
//    }
//
//    public void setHoanThuKhamCollection1(Collection<HoanThuKham> hoanThuKhamCollection1) {
//        this.hoanThuKhamCollection1 = hoanThuKhamCollection1;
//    }
    public BenhNhan getBenhnhanMa(boolean create) {
        if (create && getBenhnhanMa() == null) {
            setBenhnhanMa(new BenhNhan());
        }
        return getBenhnhanMa();
    }

    public BenhNhan getBenhnhanMa() {
        return benhnhanMa;
    }

    public void setBenhnhanMa(BenhNhan benhnhanMa) {
        this.benhnhanMa = benhnhanMa;
    }

    public DmDiaDiem getDiadiemMa(boolean create) {
        if (create && getDiadiemMa() == null) {
            setDiadiemMa(new DmDiaDiem());
        }
        return getDiadiemMa();
    }

    public DmDiaDiem getDiadiemMa() {
        return diadiemMa;
    }

    public void setDiadiemMa(DmDiaDiem diadiemMa) {
        this.diadiemMa = diadiemMa;
    }

    public DmDieuTri getDieutriMa(boolean create) {
        if (create && getDieutriMa() == null) {
            setDieutriMa(new DmDieuTri());
        }
        return getDieutriMa();
    }

    public DmDieuTri getDieutriMa() {
        return dieutriMa;
    }

    public void setDieutriMa(DmDieuTri dieutriMa) {
        this.dieutriMa = dieutriMa;
    }

    public DmPhuongThucGayTaiNan getDmptgtnMaso(boolean create) {
        if (create && getDmptgtnMaso() == null) {
            setDmptgtnMaso(new DmPhuongThucGayTaiNan());
        }
        return getDmptgtnMaso();
    }

    public DmPhuongThucGayTaiNan getDmptgtnMaso() {
        return dmptgtnMaso;
    }

    public void setDmptgtnMaso(DmPhuongThucGayTaiNan dmptgtnMaso) {
        this.dmptgtnMaso = dmptgtnMaso;
    }

    public DmDoiTuong getDoituongMa(boolean create) {
        if (create && getDoituongMa() == null) {
            setDoituongMa(new DmDoiTuong());
        }
        return getDoituongMa();
    }

    public DmDoiTuong getDoituongMa() {
        return doituongMa;
    }

    public void setDoituongMa(DmDoiTuong doituongMa) {
        this.doituongMa = doituongMa;
    }

    public DmBenhVien getKcbbhytMa(boolean create) {
        if (create && getKcbbhytMa() == null) {
            setKcbbhytMa(new DmBenhVien());
        }
        return getKcbbhytMa();
    }

    public DmBenhVien getKcbbhytMa() {
        return kcbbhytMa;
    }

    public void setKcbbhytMa(DmBenhVien kcbbhytMa) {
        this.kcbbhytMa = kcbbhytMa;
    }

    public DmKetQuaDieuTri getKetquaMa(boolean create) {
        if (create && getKetquaMa() == null) {
            setKetquaMa(new DmKetQuaDieuTri());
        }
        return getKetquaMa();
    }

    public DmKetQuaDieuTri getKetquaMa() {
        return ketquaMa;
    }

    public void setKetquaMa(DmKetQuaDieuTri ketquaMa) {
        this.ketquaMa = ketquaMa;
    }

    public DtDmKhoiBhyt getKhoibhytMa(boolean create) {
        if (create && getKhoibhytMa() == null) {
            setKhoibhytMa(new DtDmKhoiBhyt());
        }
        return getKhoibhytMa();
    }

    public DtDmKhoiBhyt getKhoibhytMa() {
        return khoibhytMa;
    }

    public void setKhoibhytMa(DtDmKhoiBhyt khoibhytMa) {
        this.khoibhytMa = khoibhytMa;
    }

    public DmTaiNan getTainanMa(boolean create) {
        if (create && getTainanMa() == null) {
            setTainanMa(new DmTaiNan());
        }
        return getTainanMa();
    }

    public DmTaiNan getTainanMa() {
        return tainanMa;
    }

    public void setTainanMa(DmTaiNan tainanMa) {
        this.tainanMa = tainanMa;
    }

    public DtDmNhanVien getTiepdonBacsi(boolean create) {
        if (create && getTiepdonBacsi() == null) {
            setTiepdonBacsi(new DtDmNhanVien());
        }
        return getTiepdonBacsi();
    }

    public DtDmNhanVien getTiepdonBacsi() {
        return tiepdonBacsi;
    }

    public void setTiepdonBacsi(DtDmNhanVien tiepdonBacsi) {
        this.tiepdonBacsi = tiepdonBacsi;
    }

    public DtDmBanKham getTiepdonBankham(boolean create) {
        if (create && getTiepdonBankham() == null) {
            setTiepdonBankham(new DtDmBanKham());
        }
        return getTiepdonBankham();
    }

    public DtDmBanKham getTiepdonBankham() {
        return tiepdonBankham;
    }

    public void setTiepdonBankham(DtDmBanKham tiepdonBankham) {
        this.tiepdonBankham = tiepdonBankham;
    }

    public DtDmNhanVien getTiepdonBschuyen(boolean create) {
        if (create && getTiepdonBschuyen() == null) {
            setTiepdonBschuyen(new DtDmNhanVien());
        }
        return getTiepdonBschuyen();
    }

    public DtDmNhanVien getTiepdonBschuyen() {
        return tiepdonBschuyen;
    }

    public void setTiepdonBschuyen(DtDmNhanVien tiepdonBschuyen) {
        this.tiepdonBschuyen = tiepdonBschuyen;
    }

    public DmKhoa getTiepdonChkhoa(boolean create) {
        if (create && getTiepdonChkhoa() == null) {
            setTiepdonChkhoa(new DmKhoa());
        }
        return getTiepdonChkhoa();
    }

    public DmKhoa getTiepdonChkhoa() {
        return tiepdonChkhoa;
    }

    public void setTiepdonChkhoa(DmKhoa tiepdonChkhoa) {
        this.tiepdonChkhoa = tiepdonChkhoa;
    }

    public DmBenhVien getTiepdonChvien(boolean create) {
        if (create && getTiepdonChvien() == null) {
            setTiepdonChvien(new DmBenhVien());
        }
        return getTiepdonChvien();
    }

    public DmBenhVien getTiepdonChvien() {
        return tiepdonChvien;
    }

    public void setTiepdonChvien(DmBenhVien tiepdonChvien) {
        this.tiepdonChvien = tiepdonChvien;
    }

    public DtDmPlBhyt getTiepdonDoituongbhyt(boolean create) {
        if (create && getTiepdonDoituongbhyt() == null) {
            setTiepdonDoituongbhyt(new DtDmPlBhyt());
        }
        return getTiepdonDoituongbhyt();
    }

    public DtDmPlBhyt getTiepdonDoituongbhyt() {
        return tiepdonDoituongbhyt;
    }

    public void setTiepdonDoituongbhyt(DtDmPlBhyt tiepdonDoituongbhyt) {
        this.tiepdonDoituongbhyt = tiepdonDoituongbhyt;
    }

    public DmBenhVien getTiepdonDonvigoi(boolean create) {
        if (create && getTiepdonDonvigoi() == null) {
            setTiepdonDonvigoi(new DmBenhVien());
        }
        return getTiepdonDonvigoi();
    }

    public DmBenhVien getTiepdonDonvigoi() {
        return tiepdonDonvigoi;
    }

    public void setTiepdonDonvigoi(DmBenhVien tiepdonDonvigoi) {
        this.tiepdonDonvigoi = tiepdonDonvigoi;
    }

    public String getTiepdonLoaikham() {
        return tiepdonLoaikham;
    }
//     public DtDmClsBangGia getTiepdonLoaikham(boolean create) {
//          if (create && tiepdonLoaikham == null) {
//            tiepdonLoaikham = new DtDmClsBangGia();
//        }
//        return tiepdonLoaikham;
//    }
    
    public void setTiepdonLoaikham(String tiepdonLoaikham) {
        this.tiepdonLoaikham = tiepdonLoaikham;
    }

    public DtDmLyDoCv getTiepdonLydochvi(boolean create) {
        if (create && getTiepdonLydochvi() == null) {
            setTiepdonLydochvi(new DtDmLyDoCv());
        }
        return getTiepdonLydochvi();
    }

    public DtDmLyDoCv getTiepdonLydochvi() {
        return tiepdonLydochvi;
    }

    public void setTiepdonLydochvi(DtDmLyDoCv tiepdonLydochvi) {
        this.tiepdonLydochvi = tiepdonLydochvi;
    }

    public DmBenhIcd getTiepdonMachdoanb0(boolean create) {
        if (create && getTiepdonMachdoanb0() == null) {
            setTiepdonMachdoanb0(new DmBenhIcd());
        }
        return getTiepdonMachdoanb0();
    }

    public DmBenhIcd getTiepdonMachdoanb0() {
        return tiepdonMachdoanb0;
    }

    public void setTiepdonMachdoanb0(DmBenhIcd tiepdonMachdoanb0) {
        this.tiepdonMachdoanb0 = tiepdonMachdoanb0;
    }

    public DmBenhIcd getTiepdonMachdoanbd(boolean create) {
        if (create && getTiepdonMachdoanbd() == null) {
            setTiepdonMachdoanbd(new DmBenhIcd());
        }
        return getTiepdonMachdoanbd();
    }

    public DmBenhIcd getTiepdonMachdoanbd() {
        return tiepdonMachdoanbd;
    }

    public void setTiepdonMachdoanbd(DmBenhIcd tiepdonMachdoanbd) {
        this.tiepdonMachdoanbd = tiepdonMachdoanbd;
    }

    public DmBenhTruyenNhiem getTiepdonMadich(boolean create) {
        if (create && getTiepdonMadich() == null) {
            setTiepdonMadich(new DmBenhTruyenNhiem());
        }
        return getTiepdonMadich();
    }

    public DmBenhTruyenNhiem getTiepdonMadich() {
        return tiepdonMadich;
    }

    public void setTiepdonMadich(DmBenhTruyenNhiem tiepdonMadich) {
        this.tiepdonMadich = tiepdonMadich;
    }

    public DtDmNhanVien getTiepdonNguoiduyet(boolean create) {
        if (create && getTiepdonNguoiduyet() == null) {
            setTiepdonNguoiduyet(new DtDmNhanVien());
        }
        return getTiepdonNguoiduyet();
    }

    public DtDmNhanVien getTiepdonNguoiduyet() {
        return tiepdonNguoiduyet;
    }

    public void setTiepdonNguoiduyet(DtDmNhanVien tiepdonNguoiduyet) {
        this.tiepdonNguoiduyet = tiepdonNguoiduyet;
    }

    public DtDmNhanVien getTiepdonNhanviencn(boolean create) {
        if (create && getTiepdonNhanviencn() == null) {
            setTiepdonNhanviencn(new DtDmNhanVien());
        }
        return getTiepdonNhanviencn();
    }

    public DtDmNhanVien getTiepdonNhanviencn() {
        return tiepdonNhanviencn;
    }

    public void setTiepdonNhanviencn(DtDmNhanVien tiepdonNhanviencn) {
        this.tiepdonNhanviencn = tiepdonNhanviencn;
    }

    public DtDmNhanVien getTiepdonThungan(boolean create) {
        if (create && getTiepdonThungan() == null) {
            setTiepdonThungan(new DtDmNhanVien());
        }
        return getTiepdonThungan();
    }

    public DtDmNhanVien getTiepdonThungan() {
        return tiepdonThungan;
    }

    public void setTiepdonThungan(DtDmNhanVien tiepdonThungan) {
        this.tiepdonThungan = tiepdonThungan;
    }

    public DmBenhIcd getTiepdonTuvong(boolean create) {
        if (create && getTiepdonTuvong() == null) {
            setTiepdonTuvong(new DmBenhIcd());
        }
        return getTiepdonTuvong();
    }

    public DmBenhIcd getTiepdonTuvong() {
        return tiepdonTuvong;
    }

    public void setTiepdonTuvong(DmBenhIcd tiepdonTuvong) {
        this.tiepdonTuvong = tiepdonTuvong;
    }

    public DmTinh getTinhbhytMa(boolean create) {
        if (create && getTinhbhytMa() == null) {
            setTinhbhytMa(new DmTinh());
        }
        return getTinhbhytMa();
    }

    public DmTinh getTinhbhytMa() {
        return tinhbhytMa;
    }

    public void setTinhbhytMa(DmTinh tinhbhytMa) {
        this.tinhbhytMa = tinhbhytMa;
    }

//    public Collection<DeNghiCc> getDeNghiCcCollection() {
//        return deNghiCcCollection;
//    }
//
//    public void setDeNghiCcCollection(Collection<DeNghiCc> deNghiCcCollection) {
//        this.deNghiCcCollection = deNghiCcCollection;
//    }

//    public Collection<DeNghiCc> getDeNghiCcCollection1() {
//        return deNghiCcCollection1;
//    }
//
//    public void setDeNghiCcCollection1(Collection<DeNghiCc> deNghiCcCollection1) {
//        this.deNghiCcCollection1 = deNghiCcCollection1;
//    }

//    public Collection<Hsba> getHsbaCollection() {
//        return hsbaCollection;
//    }
//
//    public void setHsbaCollection(Collection<Hsba> hsbaCollection) {
//        this.hsbaCollection = hsbaCollection;
//    }

//    public Collection<Hsba> getHsbaCollection1() {
//        return hsbaCollection1;
//    }
//
//    public void setHsbaCollection1(Collection<Hsba> hsbaCollection1) {
//        this.hsbaCollection1 = hsbaCollection1;
//    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getTiepdonMa() != null ? getTiepdonMa().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TiepDon)) {
            return false;
        }
        TiepDon other = (TiepDon) object;
        if ((this.getTiepdonMa() == null && other.getTiepdonMa() != null) || (this.getTiepdonMa() != null && !this.tiepdonMa.equals(other.tiepdonMa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.TiepDon[tiepdonMa=" + getTiepdonMa() + "]";
    }

    public Date getTiepdonGiatri3() {
        return tiepdonGiatri3;
    }

    public void setTiepdonGiatri3(Date tiepdonGiatri3) {
        this.tiepdonGiatri3 = tiepdonGiatri3;
    }

    public Date getTiepdonGiatri4() {
        return tiepdonGiatri4;
    }

    public void setTiepdonGiatri4(Date tiepdonGiatri4) {
        this.tiepdonGiatri4 = tiepdonGiatri4;
    }

    public Date getTiepdonMoc1() {
        return tiepdonMoc1;
    }

    public void setTiepdonMoc1(Date tiepdonMoc1) {
        this.tiepdonMoc1 = tiepdonMoc1;
    }

    public Date getTiepdonMoc2() {
        return tiepdonMoc2;
    }

    public void setTiepdonMoc2(Date tiepdonMoc2) {
        this.tiepdonMoc2 = tiepdonMoc2;
    }

    public Date getTiepdonMoc3() {
        return tiepdonMoc3;
    }

    public void setTiepdonMoc3(Date tiepdonMoc3) {
        this.tiepdonMoc3 = tiepdonMoc3;
    }

    public Boolean getTiepdonTTVanChuyen() {
        return tiepdonTTVanChuyen;
    }

    public void setTiepdonTTVanChuyen(Boolean tiepdonTTVanChuyen) {
        this.tiepdonTTVanChuyen = tiepdonTTVanChuyen;
    }

    public Double getTiepdonMach() {
        return tiepdonMach;
    }

    public void setTiepdonMach(Double tiepdonMach) {
        this.tiepdonMach = tiepdonMach;
    }

    public Double getTiepdonNhietdo() {
        return tiepdonNhietdo;
    }

    public void setTiepdonNhietdo(Double tiepdonNhietdo) {
        this.tiepdonNhietdo = tiepdonNhietdo;
    }

    public Double getTiepdonNhiptho() {
        return tiepdonNhiptho;
    }

    public void setTiepdonNhiptho(Double tiepdonNhiptho) {
        this.tiepdonNhiptho = tiepdonNhiptho;
    }

    public Double getTiepdonHamax() {
        return tiepdonHamax;
    }

    public void setTiepdonHamax(Double tiepdonHamax) {
        this.tiepdonHamax = tiepdonHamax;
    }

    public Double getTiepdonHamin() {
        return tiepdonHamin;
    }

    public void setTiepdonHamin(Double tiepdonHamin) {
        this.tiepdonHamin = tiepdonHamin;
    }

    public Integer getTiepdonSoTT() {
        return tiepdonSoTT;
    }

    public void setTiepdonSoTT(Integer tiepdonSoTT) {
        this.tiepdonSoTT = tiepdonSoTT;
    }

    /**
     * @return the tiepdonTuvongphu1
     */
    public Integer getTiepdonTuvongphu1() {
        return tiepdonTuvongphu1;
    }

    /**
     * @param tiepdonTuvongphu1 the tiepdonTuvongphu1 to set
     */
    public void setTiepdonTuvongphu1(Integer tiepdonTuvongphu1) {
        this.tiepdonTuvongphu1 = tiepdonTuvongphu1;
    }

    /**
     * @return the tiepdonTuvongphu2
     */
    public Integer getTiepdonTuvongphu2() {
        return tiepdonTuvongphu2;
    }

    /**
     * @param tiepdonTuvongphu2 the tiepdonTuvongphu2 to set
     */
    public void setTiepdonTuvongphu2(Integer tiepdonTuvongphu2) {
        this.tiepdonTuvongphu2 = tiepdonTuvongphu2;
    }

    /**
     * @return the tiepdonTuvongphu3
     */
    public Integer getTiepdonTuvongphu3() {
        return tiepdonTuvongphu3;
    }

    /**
     * @param tiepdonTuvongphu3 the tiepdonTuvongphu3 to set
     */
    public void setTiepdonTuvongphu3(Integer tiepdonTuvongphu3) {
        this.tiepdonTuvongphu3 = tiepdonTuvongphu3;
    }

    /**
     * @return the tiepdonTuvongphu4
     */
    public Integer getTiepdonTuvongphu4() {
        return tiepdonTuvongphu4;
    }

    /**
     * @param tiepdonTuvongphu4 the tiepdonTuvongphu4 to set
     */
    public void setTiepdonTuvongphu4(Integer tiepdonTuvongphu4) {
        this.tiepdonTuvongphu4 = tiepdonTuvongphu4;
    }

    /**
     * @return the tiepdonTuvongphu5
     */
    public Integer getTiepdonTuvongphu5() {
        return tiepdonTuvongphu5;
    }

    /**
     * @param tiepdonTuvongphu5 the tiepdonTuvongphu5 to set
     */
    public void setTiepdonTuvongphu5(Integer tiepdonTuvongphu5) {
        this.tiepdonTuvongphu5 = tiepdonTuvongphu5;
    }

    /**
     * @return the tiepdonCoGiayGioiThieu
     */
    public Boolean getTiepdonCoGiayGioiThieu() {
        return tiepdonCoGiayGioiThieu;
    }

    /**
     * @param tiepdonCoGiayGioiThieu the tiepdonCoGiayGioiThieu to set
     */
    public void setTiepdonCoGiayGioiThieu(Boolean tiepdonCoGiayGioiThieu) {
        this.tiepdonCoGiayGioiThieu = tiepdonCoGiayGioiThieu;
    }

    public Boolean getTiepdonKhamDaLieu() {
        return tiepdonKhamDaLieu;
    }

    public void setTiepdonKhamDaLieu(Boolean tiepdonKhamDaLieu) {
        this.tiepdonKhamDaLieu = tiepdonKhamDaLieu;
    }

 

    

}


