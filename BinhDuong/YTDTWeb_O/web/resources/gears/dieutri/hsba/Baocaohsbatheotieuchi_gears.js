function init() {
	
    if (window.google && google.gears) {
    	
		try {
			setAttrForCombobox(prefix_component + 'DM_BENH_ICD_MA_1','DM_BENH_ICD__1_span', 'DM_BENH_ICD__1',"getDmBenhIcd()","","","");
	          
			setAttrForCombobox(prefix_component + 'DT_DM_CLS_BANG_GIA_MA_1','DT_DM_CLS_BANG_GIA_span1', 'DT_DM_CLS_BANG_GIA__1',"getDmPhauThuatThuThuatCls()","","","");   

	    } catch (e) {
	    
	     alert("init():" + e);
	     
	    }
    }

}