package com.demo.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class StringUtil {
    /**
     * 姝ｅ垯琛ㄨ揪寮忥細楠岃瘉鏁板瓧
     */
    public static final String REGEX_NUMBER = "^[0-9]\\d*$";

    /**
     * 姝ｅ垯琛ㄨ揪寮忥細楠岃瘉鎵嬫満鍙�
     */
    public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$";

    /**
     * 姝ｅ垯琛ㄨ揪寮忥細楠岃瘉閭
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    /**
     * 姝ｅ垯琛ㄨ揪寮忥細楠岃瘉姹夊瓧
     */
    public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";

    /**
     * 姝ｅ垯琛ㄨ揪寮忥細楠岃瘉韬唤璇�
     */
    public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";

    /**
     * 姝ｅ垯琛ㄨ揪寮忥細楠岃瘉URL
     */
    public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

    /**
     * 姝ｅ垯琛ㄨ揪寮忥細楠岃瘉IP鍦板潃
     */
    public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";




    /**
     * 鏍￠獙鏄惁涓烘暟瀛�
     *
     * @param number
     * @return
     */
    public static boolean isNumber(String number){
        return number!=null&&!number.equals("")&&Pattern.matches(REGEX_NUMBER,number);
    }
    /**
     * 鏍￠獙鎵嬫満鍙�
     *
     * @param mobile
     * @return 鏍￠獙閫氳繃杩斿洖true锛屽惁鍒欒繑鍥瀎alse
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }

    /**
     * 鍒ゆ柇瀛楃涓叉槸鍚﹀惈鏈夋暟瀛�
     * @return
     */
    public static boolean hasNumber(String value){
        //銆愬惈鏈夋暟瀛椼�憈rue
        String regex = ".*[0-9].*";
        boolean result = value.matches(regex);
        return result;
    }
    /**
     * 鏍￠獙閭
     *
     * @param email
     * @return 鏍￠獙閫氳繃杩斿洖true锛屽惁鍒欒繑鍥瀎alse
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }

    /**
     * 鏍￠獙姹夊瓧
     *
     * @param chinese
     * @return 鏍￠獙閫氳繃杩斿洖true锛屽惁鍒欒繑鍥瀎alse
     */
    public static boolean isChinese(String chinese) {
        return Pattern.matches(REGEX_CHINESE, chinese);
    }

    /**
     * 鏍￠獙韬唤璇�
     *
     * @param idCard
     * @return 鏍￠獙閫氳繃杩斿洖true锛屽惁鍒欒繑鍥瀎alse
     */
    public static boolean isIDCard(String idCard) {
        return Pattern.matches(REGEX_ID_CARD, idCard);
    }

    /**
     * 鏍￠獙URL
     *
     * @param url
     * @return 鏍￠獙閫氳繃杩斿洖true锛屽惁鍒欒繑鍥瀎alse
     */
    public static boolean isUrl(String url) {
        return Pattern.matches(REGEX_URL, url);
    }

    /**
     * 鏍￠獙IP鍦板潃
     *
     * @param ipAddr
     * @return
     */
    public static boolean isIPAddr(String ipAddr) {
        return Pattern.matches(REGEX_IP_ADDR, ipAddr);
    }
    public static String getTime(){
        long time= System.currentTimeMillis()/1000;//鑾峰彇绯荤粺鏃堕棿鐨�10浣嶇殑鏃堕棿鎴�
        String str= String.valueOf(time);
        return str;
    }

    //瀛楃涓茶浆鏃堕棿鎴筹紙绮惧害鍒扮锛�
    public static String getTime(String timeString){
        String timeStamp = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy骞碝M鏈坉d鏃�");
        Date d;
        try{
            d = sdf.parse(timeString);
            long l = d.getTime()/1000;
            timeStamp = String.valueOf(l);
        } catch(ParseException e){
            e.printStackTrace();
        }
        return timeStamp;
    }

    //瀛楃涓茶浆鏃堕棿鎴筹紙绮惧害鍒扮锛�
    public static String getTime(String timeString, String pattern){
        String timeStamp = "";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date d;
        try{
            d = sdf.parse(timeString);
            long l = d.getTime()/1000;
            timeStamp = String.valueOf(l);
        } catch(ParseException e){
            e.printStackTrace();
        }
        return timeStamp;
    }

    //鏃堕棿鎴宠浆瀛楃涓诧紙绮惧害鍒扮锛�
    public static String getStrTime(String timeStamp){
        if (timeStamp==null)return "";
        String timeString = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy骞碝M鏈坉d鏃�");
        long l = Long.valueOf(timeStamp)*1000;
        timeString = sdf.format(new Date(l));
        return timeString;
    }

    //鏃堕棿鎴宠浆瀛楃涓诧紙绮惧害鍒扮锛�
    public static String getStrTime(String timeStamp, String pattern){
        if (timeStamp==null)return "";
        String timeString = null;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        long l = Long.valueOf(timeStamp)*1000;
        timeString = sdf.format(new Date(l));
        return timeString;
    }

    /**
     * 鍒ゆ柇涓や釜瀛楃涓叉槸鍚︾浉绛�
     * @param arg1
     * @param arg2
     * @return
     */
    public static boolean isEquals(String arg1, String arg2){
        boolean result = false;
        if (arg1==null&&arg2==null)
            result = true;
        else{
            if (arg1!=null&&arg2!=null){
                if (arg1.equals(arg2))
                    result = true;
            }
        }
        return result;
    }

    /**
     * 鍒ゆ柇瀛楃涓叉槸鍚︿笉涓虹┖
     * @param value
     * @return
     */
    public static boolean isNotEmpty(String value){
        return value!=null&&!value.equals("")&&!value.equals("null")&&!value.equals("NULL");
    }



}
