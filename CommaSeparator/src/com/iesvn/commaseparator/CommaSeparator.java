/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.commaseparator;

/**
 *
 * @author HP
 */
import java.math.BigDecimal;
import java.text.NumberFormat;

public class CommaSeparator {

    public CommaSeparator() {
    }

    public static String formatDoubleWithSeprator(Double dNumber) {
        Double zero = new Double(0.0D);
        if (dNumber.equals(zero)) {
            return "";
        } else {
            NumberFormat nf = NumberFormat.getInstance();
            nf.setMaximumFractionDigits(2);
            String sNum = nf.format(dNumber);
            sNum = sNum.replace(".", "_");
            sNum = sNum.replaceAll(",", ".");
            sNum = sNum.replaceAll("_", ",");
            
            if (sNum.equals("-0") || sNum.equals("0")) {
            	sNum = "";
            }
            
            return sNum;
        }
    }
    
    public static String formatDoubleWithSeprator4Digits(Double dNumber) {
        Double zero = new Double(0.0D);
        if (dNumber.equals(zero)) {
            return "";
        } else {
            NumberFormat nf = NumberFormat.getInstance();
            nf.setMaximumFractionDigits(4);
            String sNum = nf.format(dNumber);
            sNum = sNum.replace(".", "_");
            sNum = sNum.replaceAll(",", ".");
            sNum = sNum.replaceAll("_", ",");
            
            if (sNum.equals("-0") || sNum.equals("0")) {
            	sNum = "";
            }
            
            return sNum;
        }
    }

    public static String formatDoubleWithSepratorHavingDecimal(Double dNumber) {
        Double zero = new Double(0.0D);
        if (dNumber.equals(zero)) {
            return "";
        }
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        String sNum = nf.format(dNumber);
        sNum = sNum.replace(".", "_");
        sNum = sNum.replaceAll(",", ".");
        sNum = sNum.replaceAll("_", ",");
        int len = sNum.split(",").length;
        if (len > 1) {
            String acc = sNum.split(",")[len - 1];
            if (acc.length() < 2) {
                sNum = (new StringBuilder(String.valueOf(sNum))).append("0").toString();
            }
        } else {
            sNum = (new StringBuilder(String.valueOf(sNum))).append(",00").toString();
        }
        
        if (sNum.equals("-0,00") || sNum.equals("0,00")) {
        	sNum = "";
        }
        
        return sNum;
    }
    
    public static String formatDoubleWithSepratorHavingDecimalNoZero(Double dNumber) {
        Double zero = new Double(0.0D);
        if (dNumber.equals(zero)) {
            return "";
        }
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        String sNum = nf.format(dNumber);
        sNum = sNum.replace(".", "_");
        sNum = sNum.replaceAll(",", ".");
        sNum = sNum.replaceAll("_", ",");
        
        if (sNum.equals("-0") || sNum.equals("0")) {
        	sNum = "";
        }

        return sNum;
    }

    public static String formatIntegerWithSepratorHavingDecimal(Integer dNumber) {
        if (dNumber.intValue() == 0) {
            return "";
        }
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        String sNum = nf.format(dNumber);
        sNum = sNum.replace(".", "_");
        sNum = sNum.replaceAll(",", ".");
        sNum = sNum.replaceAll("_", ",");
        int len = sNum.split(",").length;
        if (len > 1) {
            String acc = sNum.split(",")[len - 1];
            if (acc.length() < 2) {
                sNum = (new StringBuilder(String.valueOf(sNum))).append("0").toString();
            }
        } else {
            sNum = (new StringBuilder(String.valueOf(sNum))).append(",00").toString();
        }
        return sNum;
    }

    public static String formatLongWithSepratorHavingDecimal(Long dNumber) {
        if (dNumber.longValue() == 0L) {
            return "";
        }
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        String sNum = nf.format(dNumber);
        sNum = sNum.replace(".", "_");
        sNum = sNum.replaceAll(",", ".");
        sNum = sNum.replaceAll("_", ",");
        int len = sNum.split(",").length;
        if (len > 1) {
            String acc = sNum.split(",")[len - 1];
            if (acc.length() < 2) {
                sNum = (new StringBuilder(String.valueOf(sNum))).append("0").toString();
            }
        } else {
            sNum = (new StringBuilder(String.valueOf(sNum))).append(",00").toString();
        }
        return sNum;
    }

    public static String formatLongWithSepratorHavingDecimalNoZero(Long dNumber) {
        if (dNumber.longValue() == 0L) {
            return "";
        }
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        String sNum = nf.format(dNumber);
        sNum = sNum.replace(".", "_");
        sNum = sNum.replaceAll(",", ".");
        sNum = sNum.replaceAll("_", ",");
        
        return sNum;
    }

    public static String formatBigDecimalWithSepratorHavingDecimal(BigDecimal dNumber) {
        if (dNumber.equals(Integer.valueOf(0))) {
            return "";
        }
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        String sNum = nf.format(dNumber);
        sNum = sNum.replace(".", "_");
        sNum = sNum.replaceAll(",", ".");
        sNum = sNum.replaceAll("_", ",");
        int len = sNum.split(",").length;
        if (len > 1) {
            String acc = sNum.split(",")[len - 1];
            if (acc.length() < 2) {
                sNum = (new StringBuilder(String.valueOf(sNum))).append("0").toString();
            }
        } else {
            sNum = (new StringBuilder(String.valueOf(sNum))).append(",00").toString();
        }
        return sNum;
    }
    public static String formatBigDecimalWithSepratorHavingDecimalNoZero(BigDecimal dNumber) {
        if (dNumber.equals(Integer.valueOf(0))) {
            return "";
        }
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        String sNum = nf.format(dNumber);
        sNum = sNum.replace(".", "_");
        sNum = sNum.replaceAll(",", ".");
        sNum = sNum.replaceAll("_", ",");

        return sNum;
    }
    public static void main(String args[]) {
        System.out.println("1 :" + formatDoubleWithSeprator(Double.valueOf(0.0D)));
        System.out.println("2 : " +formatDoubleWithSepratorHavingDecimal(Double.valueOf(0.0D)));
        System.out.println("3 : " +formatDoubleWithSepratorHavingDecimal(new Double(123)));
        System.out.println("4 : " +formatDoubleWithSepratorHavingDecimalNoZero(new Double(123.0)));
        System.out.println("5 : " +formatDoubleWithSepratorHavingDecimalNoZero(new Double(123.07)));
        System.out.println("6 : " +formatDoubleWithSeprator4Digits(new Double(0.0006)));
        System.out.println("7 : " +formatDoubleWithSeprator4Digits(new Double(0.00006)));
        System.out.println("8 : " +formatDoubleWithSeprator4Digits(new Double(0.00608)));
        System.out.println("9 : " +formatDoubleWithSeprator4Digits(new Double(12)));
        System.out.println("10: " + formatIntegerWithSepratorHavingDecimal(Integer.valueOf(0x1e)));
        Long lo = new Long(0x0eL);
        System.out.println("11: " + formatLongWithSepratorHavingDecimal(lo));
        BigDecimal acc = new BigDecimal(44.19999999D);
        System.out.println("12: " + formatBigDecimalWithSepratorHavingDecimal(acc));        
    }
}
