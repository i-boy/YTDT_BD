package com.iesvn.yte.dieutri.ajax;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.BenhNhanDelegate;
import com.iesvn.yte.dieutri.entity.TiepDon;


public class GetBenhNhanFromTheBHAction extends Action {
	
	@Override
    public String performAction(String request) {
    	
		System.out.println("##### GetBenhNhanFromTheBHAction #####");
        
		StringBuffer buf = new StringBuffer();
        buf.append("<list>");
        
        try {
            BenhNhanDelegate bnDelegate = BenhNhanDelegate.getInstance();
            TiepDon td = bnDelegate.findBySoTheBHYT(request);
            
            if (td == null){
            	buf.append("</list>");
                return buf.toString();
            }
            System.out.println("td ma " + td.getTiepdonMa());

            // ----------------------------------------------------------------------------------------------
            // 20110524 bao.ttc: nhung thong tin bi comment la thong tin khong can load lai tu lan kham truoc
            // ----------------------------------------------------------------------------------------------
            
            String maphieuk = ""; 		//td.getTiepdonMaphieuk() == null ? "" : td.getTiepdonMaphieuk();
            String ngaygio = "";
            String benhnhan = td.getBenhnhanMa() == null ? "" : td.getBenhnhanMa().getBenhnhanMa();
            String doituong = td.getDoituongMa() == null ? "" : td.getDoituongMa().getDmdoituongMa();
            String phanloai = td.getTiepdonPhanloai() == null ? "" : td.getTiepdonPhanloai();
            String tainan = ""; 		//td.getTainanMa() == null ? "" : td.getTainanMa().getDmtainanMa();
            String donvigoi = ""; 		//td.getTiepdonDonvigoi() == null ? "" : td.getTiepdonDonvigoi().getDmbenhvienMa();
            
            String khobh = td.getKhoibhytMa() == null ? "" : td.getKhoibhytMa().getDtdmkhoibhytMa();
            String tinhbh = td.getTinhbhytMa() == null ? "" : td.getTinhbhytMa().getDmtinhBHYT();
            String dpbh = td.getDpbhytMa() == null ? "" : td.getDpbhytMa();
            String macoquan = td.getTiepdonMacoquan() == null ? "" : td.getTiepdonMacoquan();
            String sothebh = td.getTiepdonSothebh() == null ? "" : td.getTiepdonSothebh();
            String nambh = td.getTiepdonNambhyt() == null ? "" : td.getTiepdonNambhyt();
            String sonambh = td.getTiepdonSonambh() == null ? "" : td.getTiepdonSonambh().toString();
            String tuyen = td.getTiepdonTuyen() == null ? "" : td.getTiepdonTuyen().toString();
            String kcb = td.getKcbbhytMa() == null ? "" : td.getKcbbhytMa().getDmbenhvienMa();
            
            String giatri1 = "";
            String giatri2 = "";
            String giatri3 = "";
            String giatri4 = "";
            String moc1 = "";
            String moc2 = "";
            String moc3 = "";
            
            String khaisinh = td.getTiepdonKhaisinh() == null ? "" : td.getTiepdonKhaisinh();
            String chungsinh = td.getTiepdonChungsinh() == null ? "" : td.getTiepdonChungsinh();
            String sothete = td.getTiepdonSothete() == null ? "" : td.getTiepdonSothete();
            String thengheo = td.getTiepdonThengheo() == null ? "" : td.getTiepdonThengheo();
            String bankham = td.getTiepdonBankham() == null ? "" : td.getTiepdonBankham().getDtdmbankhamMa();
            String sott = ""; 			//td.getTiepdonThutu() == null ? "" : td.getTiepdonThutu().toString();
            String sotts = ""; 			//td.getTiepdonThutus() == null ? "" : td.getTiepdonThutus().toString();
            String bntra = ""; 			//td.getTiepdonBntra() == null ? "" : td.getTiepdonBntra().toString();
            String loaikham = td.getTiepdonLoaikham();
            
            String doituongbh = td.getTiepdonDoituongbhyt() == null ? "" : td.getTiepdonDoituongbhyt().getDtdmphloaibhytMa();
            String tylebh = td.getTiepdonTylebh() == null ? "" : td.getTiepdonTylebh().toString();
            String bacsi = td.getTiepdonBacsi() == null ? "" : td.getTiepdonBacsi().getDtdmnhanvienMa();
            String nguoiduyet = td.getTiepdonNguoiduyet() == null ? "" : td.getTiepdonNguoiduyet().getDtdmnhanvienMa();
            String kyhieu = ""; 		//td.getTiepdonKyhieu() == null ? "" : td.getTiepdonKyhieu();
            String quyen = ""; 			//td.getTiepdonQuyen() == null ? "" : td.getTiepdonQuyen();
            String bienlai = ""; 		//td.getTiepdonBienlai() == null ? "" : td.getTiepdonBienlai();
            String maphieud = ""; 		//td.getTiepdonMaphieud() == null ? "" : td.getTiepdonMaphieud();
            
            String nhanviencn = ""; 	//td.getTiepdonNhanviencn() == null ? "" : td.getTiepdonNhanviencn().getDtdmnhanvienMa();
            String thungan = ""; 		//td.getTiepdonThungan() == null ? "" : td.getTiepdonThungan().getDtdmnhanvienMa();
            String phuongtien = ""; 	//td.getDmptgtnMaso() == null ? "" : td.getDmptgtnMaso().getDmptgtnMa();
            String nguyennhan = ""; 	//td.getNguyenhanMa() == null ? "" : td.getNguyenhanMa();
            String diadiem = ""; 		//td.getDiadiemMa() == null ? "" : td.getDiadiemMa().getDmdiadiemMa();
            String ruoubia = ""; 		//td.getTiepdonRuoubia() == null ? "" : td.getTiepdonRuoubia().toString();
            String lydovaov = ""; 		//td.getTiepdonLydovaov() == null ? "" : td.getTiepdonLydovaov();
            String chandoantt = ""; 	//td.getTiepdonMachdoanb0() == null ? "" : td.getTiepdonMachdoanb0().getDmbenhicdMa();
            String dgchandoantt = ""; 	//td.getTiepdonDgchdoanb0() == null ? "" : td.getTiepdonDgchdoanb0();
            String chandoanbd = ""; 	//td.getTiepdonMachdoanbd() == null ? "" : td.getTiepdonMachdoanbd().getDmbenhicdMa();
            String dgchandoanbd = ""; 	//td.getTiepdonDgchdoanbd() == null ? "" : td.getTiepdonDgchdoanbd();
            
            String madich = ""; 		//td.getTiepdonMadich() == null ? "" : td.getTiepdonMadich().getDmbtnMaso().toString();
            String vuottuyen = td.getTiepdonVuottuyen() == null ? "" : td.getTiepdonVuottuyen();
            String trongluong = td.getTiepdonTrluong() == null ? "" : td.getTiepdonTrluong().toString();
            String chieucao = td.getTiepdonChieucao() == null ? "" : td.getTiepdonChieucao().toString();
            String nhommau = td.getTiepdonNhommau() == null ? "" : td.getTiepdonNhommau();
            String rh = td.getTiepdonRh() == null ? "" : td.getTiepdonRh();
            String giuong = ""; 		//td.getTiepdonGiuong() == null ? "" : td.getTiepdonGiuong();
            String baotin = ""; 		//td.getTiepdonBaotin() == null ? "" : td.getTiepdonBaotin();
            String ketqua = ""; 		//td.getKetquaMa() == null ? "" : td.getKetquaMa().getDmkqdtMa();
            String dieutri = ""; 		//td.getDieutriMa() == null ? "" : td.getDieutriMa().getDmdieutriMa();
            String nghe = td.getTiepdonNghe() == null ? "" : td.getTiepdonNghe();
            
            String para1 = ""; 			//td.getTiepdonPara1() == null ? "" : td.getTiepdonPara1();
            String para2 = ""; 			//td.getTiepdonPara2() == null ? "" : td.getTiepdonPara2();
            String para3 = ""; 			//td.getTiepdonPara3() == null ? "" : td.getTiepdonPara3();
            String para4 = ""; 			//td.getTiepdonPara4() == null ? "" : td.getTiepdonPara4();
            String chkhoa = ""; 		//td.getTiepdonChkhoa() == null ? "" : td.getTiepdonChkhoa().getDmkhoaMa();
            String chvien = ""; 		//td.getTiepdonChvien() == null ? "" : td.getTiepdonChvien().getDmbenhvienMa();
            String ngaygiora = "";
            String tinhtrangra = ""; 	//td.getTiepdonTitrangra() == null ? "" : td.getTiepdonTitrangra();
            String bschuyen = ""; 		//td.getTiepdonBschuyen() == null ? "" : td.getTiepdonBschuyen().getDtdmnhanvienMa();
            String lydochv = ""; 		//td.getTiepdonLydochvi() == null ? "" : td.getTiepdonLydochvi().getDtdmlydocvMa();
            
            String taikham = "";
            String loidan = ""; 		//td.getTiepdonLoidan() == null ? "" : td.getTiepdonLoidan();
            String tuvong = ""; 		//td.getTiepdonTuvong() == null ? "" : td.getTiepdonTuvong().getDmbenhicdMa();
            String cum = ""; 			//td.getTiepdonCum() == null ? "" : td.getTiepdonCum();
            String ngaygiocn = "";
            String status = ""; 		//td.getTiepdonStatus() == null ? "" : td.getTiepdonStatus();
            String ngaytt = "";
            
            
//            if (td.getTiepdonNgaygio() != null) {
//                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//                Date cal = td.getTiepdonNgaygio();
//                ngaygio = df.format(cal.getTime());
//            }
            
//            if (td.getTiepdonNgaygiora() != null) {
//                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//                Date cal = td.getTiepdonNgaygiora();
//                ngaygiora = df.format(cal.getTime());
//            }
            
//            if (td.getTiepdonTaikham() != null) {
//                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//                Date cal = td.getTiepdonTaikham();
//                taikham = df.format(cal.getTime());
//            }
            
//            if (td.getTiepdonNgaygiocn() != null) {
//                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//                Date cal = td.getTiepdonNgaygiocn();
//                ngaygiocn = df.format(cal.getTime());
//            }  
            
//            if (td.getTiepdonNgaytt() != null) {
//                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//                Date cal = td.getTiepdonNgaytt();
//                ngaytt = df.format(cal.getTime());
//            }
            
            if (td.getTiepdonGiatri1() != null) {
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                Date cal = td.getTiepdonGiatri1();
                giatri1 = df.format(cal.getTime());
            }
            if (td.getTiepdonGiatri2() != null) {
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                Date cal = td.getTiepdonGiatri2();
                giatri2 = df.format(cal.getTime());
            }
            
            if (td.getTiepdonGiatri3() != null) {
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                Date cal = td.getTiepdonGiatri3();
                giatri3 = df.format(cal.getTime());
            }
            if (td.getTiepdonGiatri4() != null) {
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                Date cal = td.getTiepdonGiatri4();
                giatri4 = df.format(cal.getTime());
            }
            
            if (td.getTiepdonMoc1() != null) {
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                Date cal = td.getTiepdonMoc1();
                moc1 = df.format(cal.getTime());
            }
            if (td.getTiepdonMoc2() != null) {
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                Date cal = td.getTiepdonMoc2();
                moc2 = df.format(cal.getTime());
            }
            
            if (td.getTiepdonMoc3() != null) {
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                Date cal = td.getTiepdonMoc3();
                moc3 = df.format(cal.getTime());
            }
            
            Boolean coGiayChuyenVien = td.getTiepdonCoGiayGioiThieu();
            String sCoGiayGioiThieu;
            if (coGiayChuyenVien != null && coGiayChuyenVien == true){
            	sCoGiayGioiThieu = "1";
            }else{
            	sCoGiayGioiThieu = "0";
            }
            
            Boolean khamDaLieu = td.getTiepdonKhamDaLieu();
            String sKhamDaLieu;
            if (khamDaLieu != null && khamDaLieu == true){
            	sKhamDaLieu = "1";
            }else{
            	sKhamDaLieu = "0";
            }
            
            buf.append("<TIEP_DON MAPHU='' TIEPDON_MA='" + "" +
                    "' TIEPDON_MAPHIEUK='" + maphieuk +
                    "' TIEPDON_NGAYGIO='" + ngaygio +
                    "' BENHNHAN_MA='" + benhnhan +
                    "' DOITUONG_MA='" + doituong +
                    "' TIEPDON_PHANLOAI='" + phanloai +
                    "' TAINAN_MA='" + tainan +
                    "' TIEPDON_DONVIGOI='" + donvigoi +
                    "' KHOIBHYT_MA='" + khobh + 
                    "' TINHBHYT_MA='" + tinhbh +
                    "' DPBHYT_MA='" + dpbh +
                    "' TIEPDON_MACOQUAN='" + macoquan +
                    "' TIEPDON_SOTHEBH='" + sothebh +
                    "' TIEPDON_NAMBHYT='" + nambh +
                    "' TIEPDON_SONAMBH='" + sonambh +
                    "' TIEPDON_TUYEN='" + tuyen +
                    "' KCBBHYT_MA='" + kcb +
                    "' TIEPDON_GIATRI1='" + giatri1 +
                    "' TIEPDON_GIATRI2='" + giatri2 +
                    "' TIEPDON_KHAISINH='" + khaisinh +
                    "' TIEPDON_CHUNGSINH='" + chungsinh +
                    "' TIEPDON_SOTHETE='" + sothete +
                    "' TIEPDON_THENGHEO='" + thengheo +
                    "' TIEPDON_BANKHAM='" + bankham +
                    "' TIEPDON_THUTU='" + sott +
                    "' TIEPDON_THUTUS='" + sotts +
                    "' TIEPDON_LOAIKHAM='" + loaikham +
                    "' TIEPDON_BNTRA='" + bntra +
                    "' TIEPDON_DOITUONGBHYT='" + doituongbh +
                    "' TIEPDON_TYLEBH='" + tylebh +
                    "' TIEPDON_BACSI='" + bacsi +
                    "' TIEPDON_NGUOIDUYET='" + nguoiduyet +
                    "' TIEPDON_KYHIEU='" + kyhieu +
                    "' TIEPDON_QUYEN='" + quyen +
                    "' TIEPDON_BIENLAI='" + bienlai +
                    "' TIEPDON_MAPHIEUD='" + maphieud +
                    "' TIEPDON_NHANVIENCN='" + nhanviencn +
                    "' TIEPDON_THUNGAN='" + thungan +
                    "' PHUONGTIEN_MA='" + phuongtien +
                    "' NGUYENHAN_MA='" + nguyennhan +
                    "' DIADIEM_MA='" + diadiem +
                    "' TIEPDON_RUOUBIA='" + ruoubia +
                    "' TIEPDON_LYDOVAOV='" + lydovaov +
                    "' TIEPDON_MACHDOANB0='" + chandoantt +
                    "' TIEPDON_DGCHDOANB0='" + dgchandoantt +
                    "' TIEPDON_MACHDOANBD='" + chandoanbd +
                    "' TIEPDON_DGCHDOANBD='" + dgchandoanbd +
                    "' TIEPDON_MADICH='" + madich +
                    "' TIEPDON_VUOTTUYEN='" + vuottuyen +
                    "' TIEPDON_TRLUONG='" + trongluong +
                    "' TIEPDON_CHIEUCAO='" + chieucao +
                    "' TIEPDON_NHOMMAU='" + nhommau +
                    "' TIEPDON_RH='" + rh +
                    "' TIEPDON_GIUONG='" + giuong +
                    "' TIEPDON_BAOTIN='" + baotin +
                    "' KETQUA_MA='" + ketqua +
                    "' DIEUTRI_MA='" + dieutri +
                    "' TIEPDON_NGHE='" + nghe +
                    "' TIEPDON_PARA1='" + para1 +
                    "' TIEPDON_PARA2='" + para2 +
                    "' TIEPDON_PARA3='" + para3 +
                    "' TIEPDON_PARA4='" + para4 +
                    "' TIEPDON_CHKHOA='" + chkhoa +
                    "' TIEPDON_CHVIEN='" + chvien +
                    "' TIEPDON_NGAYGIORA='" + ngaygiora +
                    "' TIEPDON_TITRANGRA='" + tinhtrangra +
                    "' TIEPDON_BSCHUYEN='" + bschuyen +
                    "' TIEPDON_LYDOCHVI='" + lydochv +
                    "' TIEPDON_TAIKHAM='" + taikham +
                    "' TIEPDON_LOIDAN='" + loidan +
                    "' TIEPDON_TUVONG='" + tuvong +
                    "' TIEPDON_CUM='" + cum +
                    "' TIEPDON_NGAYGIOCN='" + ngaygiocn +
                    "' TIEPDON_STATUS='" + status +
                    "' TIEPDON_GIATRI3='" + giatri3 +
                    "' TIEPDON_GIATRI4='" + giatri4 +
                    "' TIEPDON_MOC1='" + moc1 +
                    "' TIEPDON_MOC2='" + moc2 +
                    "' TIEPDON_MOC3='" + moc3 +
                    "' TIEPDON_CO_GIAY_GIOI_THIEU='" + sCoGiayGioiThieu +
                    "' TIEPDON_KHAM_DA_LIEU='" + sKhamDaLieu +
                    "' TIEPDON_NGAYTT='" + ngaytt + "'/>");
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        buf.append("</list>");
        //System.out.println(buf.toString()); //bao.ttc
        return buf.toString();

    }
}

