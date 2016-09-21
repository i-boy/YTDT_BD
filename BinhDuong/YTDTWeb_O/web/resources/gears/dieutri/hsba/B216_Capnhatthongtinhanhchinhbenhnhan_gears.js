function init() {
	
    if (window.google && google.gears) {
    	
        //try {
        	//db = google.gears.factory.create('beta.database');
            //setAttrForCombobox_StoreValue(prefix_component + 'LOAITHUOC_MA','DM_LOAI_THUOC_span','DM_LOAI_THUOC','10',DmLoaiThuoc);
            	
            setAttrForCombobox(prefix_component + "DIADIEM_MA","DM_DIA_DIEM_span","DM_DIA_DIEM","getDmDiaDiem()","","","");
			setAttrForCombobox(prefix_component + 'DM_TAI_NAN_MA','DM_TAI_NAN_span', 'DM_TAI_NAN',"getDmTaiNan()","onChangePhuongThucGayTaiNan()","","");
 			setAttrForCombobox(prefix_component + 'DM_PHUONG_TIEN_MA','DM_PHUONG_TIEN_span', 'DM_PHUONG_THUC_GAY_TAI_NAN',"getDmPhuongThucGayTaiNanByTaiNanMa(\"" + prefix_component + "DM_TAI_NAN_MA_pk" + "\")","","","");
		

			setAttrForCombobox(prefix_component + "TINH_MA","DM_TINH_span","DM_TINH","getDmTinh()","resetDMHuyenXa()","","");
            setAttrForCombobox(prefix_component + "HUYEN_MA","DM_HUYEN_span","DM_HUYEN","getDmHuyen(\"" + prefix_component + "TINH_MA\")","resetDMXa()","","");
            setAttrForCombobox(prefix_component + "XA_MA","DM_XA_span","DM_XA","getDmXa(\"" + prefix_component + "HUYEN_MA\")","","","");
           
        	setAttrForCombobox(prefix_component + 'DM_DAN_TOC_MA','DM_DAN_TOC_span', 'DM_DAN_TOC',"getDmDanToc()","","","");
				
        	setAttrForComboboxForKCBBHYT(prefix_component + 'KCBBHYT_MA','DM_BENH_VIEN2_span','DM_BENH_VIEN__2',"getDmBenhVien()", "", "", "");
			setAttrForComboboxForTinhBHYT(prefix_component + 'TINHBHYT_MA','DM_TINH_BHYT_span', 'DM_TINH__2',"getDmTinh()","myOnblurTextboxDmNoiDKKCBBD(prefix_component + 'KCBBHYT_MA','DM_BENH_VIEN__2',document.getElementById(prefix_component+'TINHBHYT_MA').value);","","");
			setAttrForCombobox(prefix_component + 'DM_KHOI_BHYT_MA','DM_KHOI_BHYT_span', 'DT_DM_KHOI_BHYT',"getDtDmKhoiBhyt()","","","");
		
   		
   		 timer.setTimeout(function(){onCompleteGetInfor();},100); 
            
        //} catch (e) {
        //	alert("init error: " + e.description);
        //}
    }    
}

function onChangePhuongThucGayTaiNan(){
	document.getElementById(prefix_component + "DM_PHUONG_TIEN_MA_pk").value = "";
	document.getElementById(prefix_component + "DM_PHUONG_TIEN_MA").value = "";
	document.getElementById("DM_PHUONG_THUC_GAY_TAI_NAN").value = "";
}


function onCompleteGetInfor(){

	myOnblurTextbox(prefix_component + 'TINH_MA', 'DM_TINH');
	myOnblurTextbox(prefix_component + 'HUYEN_MA', 'DM_HUYEN');
	myOnblurTextbox(prefix_component + 'XA_MA', 'DM_XA');
	
	myOnblurTextbox(prefix_component + 'DM_DAN_TOC_MA', 'DM_DAN_TOC');
	myOnblurTextbox(prefix_component + 'DIADIEM_MA', 'DM_DIA_DIEM');
	myOnblurTextbox(prefix_component + 'DM_TAI_NAN_MA', 'DM_TAI_NAN');
	myOnblurTextbox(prefix_component + 'DM_PHUONG_TIEN_MA', 'DM_PHUONG_THUC_GAY_TAI_NAN');
	
	myOnblurTextbox(prefix_component + 'DM_KHOI_BHYT_MA', 'DT_DM_KHOI_BHYT');
	myOnblurTextboxTinhBHYT(prefix_component +'TINHBHYT_MA', 'DM_TINH__2');
	myOnblurTextbox(prefix_component + 'KCBBHYT_MA', 'DM_BENH_VIEN__2');
	
	var sobenhan = document.getElementById(prefix_component + "__sobenhan").value;
	if(sobenhan == ''){
		document.getElementById(prefix_component + "__sobenhan").focus();
	}
	
	/*
	if ( document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP").value == "1" ) {
          //alert(1);
			document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP:0").checked = true ;
		} else if ( document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP").value == "2") {
		 //alert(2);
			document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP:1").checked = true ;
			
		} else if ( document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP").value == "3") {
		 //alert(3);
			 document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP:2").checked = true;
		} else {
			document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP:0").checked = true ;
		}
    */
  
	if (document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP" + ":0").checked == false &&
		document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP" + ":1").checked == false &&
		document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP" + ":2").checked == false 
	){
		document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP" + ":0").checked = true;
	}
}


function onCompleteGetInfor_Reset(){

	myOnblurTextbox(prefix_component + 'TINH_MA', 'DM_TINH');
	myOnblurTextbox(prefix_component + 'HUYEN_MA', 'DM_HUYEN');
	myOnblurTextbox(prefix_component + 'XA_MA', 'DM_XA');
	
	myOnblurTextbox(prefix_component + 'DM_DAN_TOC_MA', 'DM_DAN_TOC');
	myOnblurTextbox(prefix_component + 'DIADIEM_MA', 'DM_DIA_DIEM');
	myOnblurTextbox(prefix_component + 'DM_TAI_NAN_MA', 'DM_TAI_NAN');
	myOnblurTextbox(prefix_component + 'DM_PHUONG_TIEN_MA', 'DM_PHUONG_THUC_GAY_TAI_NAN');
	
	myOnblurTextbox(prefix_component + 'DM_KHOI_BHYT_MA', 'DT_DM_KHOI_BHYT');
	myOnblurTextboxTinhBHYT(prefix_component +'TINHBHYT_MA', 'DM_TINH__2');
	myOnblurTextbox(prefix_component + 'KCBBHYT_MA', 'DM_BENH_VIEN__2');
	
	var sobenhan = document.getElementById(prefix_component + "__sobenhan").value;
	if(sobenhan == ''){
		document.getElementById(prefix_component + "__sobenhan").focus();
	}

 	setValueOnLoad();
 	
}

function checkForGioi(){
	var valueTen = document.getElementById(prefix_component + "__hoten").value;
	
	if (valueTen != null && valueTen != "" && ( valueTen.indexOf(thi_1) >= 0 || valueTen.indexOf(thi_2) >= 0)){
		
		//alert(valueTen.indexOf(thi_1));
		//alert( document.getElementById(prefix_component + "__gioitinh:1"));
		document.getElementById(prefix_component + "__gioitinh:1").checked = true;
		//alert(document.getElementById(prefix_component + "__nu").checked);	
	}
	
}

function setValueOnLoad(){
  try{
  	
  	//alert(document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP").value);
  	/*
        if ( document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP").value == "1" ) {
          //alert(1);
			document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP:0").checked = true ;
		} else if ( document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP").value == "2") {
		 //alert(2);
			document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP:1").checked = true ;
			
		} else if ( document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP").value == "3") {
		 //alert(3);
			 document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP:2").checked = true;
		} else {
			document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP:0").checked = true ;
		
		}
    */
  
    //alert(1);
	//document.getElementById(prefix_component + "__sobenhan").focus();
	if (document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP" + ":0").checked == false &&
		document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP" + ":1").checked == false &&
		document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP" + ":2").checked == false 
	){
		document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP" + ":0").checked = true;
	}
	
	
	setTimeout(function() {
			var dantoc = DmDanToc.getByFieldValue("DMDANTOC_DEFA", 1);
			if (dantoc) {
				document.getElementById(prefix_component + "DM_DAN_TOC_MA").value = dantoc.Ma;
				dojo.byId("DM_DAN_TOC").value = dantoc.Ten;
			}
			
			var tinh = DmTinh.getByFieldValue("DMTINH_DEFA", 1);
			if (tinh) {
				document.getElementById(prefix_component + "TINH_MA").value = tinh.Ma;
				dojo.byId("DM_TINH").value = tinh.Ten;
			}
			
			var huyen = DmHuyen.getByFieldValue("DMHUYEN_DEFA", 1);
			if (huyen) {
				document.getElementById(prefix_component + "HUYEN_MA").value = huyen.Ma;
				dojo.byId("DM_HUYEN").value = huyen.Ten;
			}
			
			var xa = DmXa.getByFieldValue("DMXA_DEFA", 1);
			if (xa) {
				document.getElementById(prefix_component + "XA_MA").value = xa.Ma;
				dojo.byId("DM_XA").value = xa.Ten;
			}
			
			var gt = DmGioiTinh.getByFieldValue("DMGT_DEFA", 1);
			if (gt) {
				if (gt.Ma == "0") {
					document.getElementById(prefix_component + "__gioitinh" + ":1").checked = true;
				} else if (gt.Ma == "1") {
					document.getElementById(prefix_component + "__gioitinh" + ":0").checked = true;
				}
			}
			
			
			
			
		}, 200);
	
	
  }catch(e){
    alert(e.description);
  }

  function disableBH() {
		document.getElementById(prefix_component + "__sothe").value="";
		document.getElementById(prefix_component + "__sothe").disabled = true;
		
		document.getElementById(prefix_component + "DM_KHOI_BHYT_MA").value = "";
	    myOnblurTextbox(prefix_component + 'DM_KHOI_BHYT_MA', 'DT_DM_KHOI_BHYT');
		document.getElementById(prefix_component + "DM_KHOI_BHYT_MA").disabled = true;
		if (document.getElementById( "DT_DM_KHOI_BHYT").disabled == false){
	        	changeDisabledAttr("DT_DM_KHOI_BHYT");
	    }
	    
		document.getElementById(prefix_component + "TINHBHYT_MA").value = "";
		myOnblurTextbox(prefix_component + 'TINHBHYT_MA', 'DM_TINH__2');
		document.getElementById(prefix_component + "TINHBHYT_MA").disabled = true;
		if (document.getElementById( "DM_TINH__2").disabled == false){
	        	changeDisabledAttr("DM_TINH__2");
	    }
		
		document.getElementById(prefix_component + "KCBBHYT_MA").value = "";
		myOnblurTextbox(prefix_component + 'KCBBHYT_MA', 'DM_BENH_VIEN__2');
		document.getElementById(prefix_component + "KCBBHYT_MA").disabled = true;
		if (document.getElementById( "DM_BENH_VIEN__2").disabled == false){
	        	changeDisabledAttr("DM_BENH_VIEN__2");  
	    }
	}
  
  function enableBH() {
		document.getElementById(prefix_component + "DM_KHOI_BHYT_MA").disabled = false;
		if (document.getElementById( "DT_DM_KHOI_BHYT").disabled == true){
	        	changeDisabledAttr("DT_DM_KHOI_BHYT");  
	    }
		
		document.getElementById(prefix_component + "TINHBHYT_MA").disabled = false;
		if (document.getElementById( "DM_TINH__2").disabled == true){
	        	changeDisabledAttr("DM_TINH__2");  
	    }
		
		document.getElementById(prefix_component + "KCBBHYT_MA").disabled = false;
		if (document.getElementById( "DM_BENH_VIEN__2").disabled == true){
	        	changeDisabledAttr("DM_BENH_VIEN__2");  
	    }
	}

	function disableTGBH() {
		document.getElementById(prefix_component + "TIEPDON_GIATRI1").disabled = true;
	    document.getElementById(prefix_component + "TIEPDON_GIATRI1").value="";
	    
	    document.getElementById(prefix_component + "TIEPDON_GIATRI2").disabled = true;
	    document.getElementById(prefix_component + "TIEPDON_GIATRI2").value="";
	    
	    document.getElementById(prefix_component + "TIEPDON_GIATRI3").disabled = true;
	    document.getElementById(prefix_component + "TIEPDON_GIATRI3").value="";
	    
	    document.getElementById(prefix_component + "TIEPDON_GIATRI4").disabled = true;
	    document.getElementById(prefix_component + "TIEPDON_GIATRI4").value="";
	    
	    document.getElementById(prefix_component + "TIEPDON_MOC1").disabled = true;
	    document.getElementById(prefix_component + "TIEPDON_MOC1").value="";
	    
	    document.getElementById(prefix_component + "TIEPDON_MOC2").disabled = true;
	    document.getElementById(prefix_component + "TIEPDON_MOC2").value="";
	    
	    document.getElementById(prefix_component + "TIEPDON_MOC3").disabled = true;
	    document.getElementById(prefix_component + "TIEPDON_MOC3").value="";
	}

	function enableTGBH() {
		document.getElementById(prefix_component + "TIEPDON_GIATRI1").disabled = false;
	    document.getElementById(prefix_component + "TIEPDON_GIATRI2").disabled = false;
	    document.getElementById(prefix_component + "TIEPDON_GIATRI3").disabled = false;
	    document.getElementById(prefix_component + "TIEPDON_GIATRI4").disabled = false;
	    document.getElementById(prefix_component + "TIEPDON_MOC1").disabled = false;
	    document.getElementById(prefix_component + "TIEPDON_MOC2").disabled = false;
	    document.getElementById(prefix_component + "TIEPDON_MOC3").disabled = false;
	}
  
}


