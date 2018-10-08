package com.lfj.nettyserver.utils;

/**
 * Created by lenovo on 2018/9/30.
 */
public class XorCodeUtils {

    public static String calculateXorCode(String content) {
        content = change(content);
        String[] b = content.split(" ");
        int a = 0;
        for (int i = 0; i < b.length; i++) {
            a = a ^ Integer.parseInt(b[i], 16);
        }
        if(a<10){
            StringBuffer sb = new StringBuffer();
            sb.append("0");
            sb.append(a);
            return sb.toString();
        }
        String hexStr = Integer.toHexString(a);
//        if(hexStr.length() == 1){
//            return "0" + hexStr;
//        }
        return hexStr;
    }


    public static String change(String content) {
        String str = "";
        for (int i = 0; i < content.length(); i++) {
            if (i % 2 == 0) {
                str += " " + content.substring(i, i + 1);
            } else {
                str += content.substring(i, i + 1);
            }
        }
        return str.trim();
    }


    public static void main(String[] args){
        String code = calculateXorCode("44493230313830393237303030303031058d0e423cf3");
        System.out.println(code);
    }

}
