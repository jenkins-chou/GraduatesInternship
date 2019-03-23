package com.jenking.graduatesinternship.utils;

import android.content.Context;

import com.google.gson.Gson;
import com.jenking.graduatesinternship.models.impl.UserModel;

public class AccountTool {
    public static final String usertype_student = "student";
    public static final String usertype_teacher = "teacher";
    //判断用户是否登录
    public final static boolean isLogin(Context context){
        if (context==null)return false;
        UserModel userModel = new Gson().fromJson((String) SPUtils.get(context,SPUtils.FILE_USER,SPUtils.user_object,""),UserModel.class);
        if (userModel!=null&&StringUtil.isNotEmpty(userModel.getId())){
            return true;
        }else{
            return false;
        }
    }

    //保存用户信息
    public final static void saveUser(Context context,Object userObject){
        if (context==null)return;
        String userModelJson = new Gson().toJson(userObject);
        SPUtils.put(context,SPUtils.FILE_USER,SPUtils.user_object,userModelJson);
    }

    //获取用户类型
    public final static String getUserType(Context context){
        if (context==null)return null;
        if (!isLogin(context))return null;
        UserModel userModel = new Gson().fromJson((String) SPUtils.get(context,SPUtils.FILE_USER,SPUtils.user_object,""),UserModel.class);
        return userModel.getType();
    }


    //获取登录用户的全部信息
    public final static UserModel getLoginUser(Context context){
        if (context==null)return null;
        if (!isLogin(context))return null;
        UserModel userModel = new Gson().fromJson((String) SPUtils.get(context,SPUtils.FILE_USER,SPUtils.user_object,""),UserModel.class);
        return userModel;
    }

    public static boolean isCompleteUserInfo(Context context){
        boolean result = false;
        if (context==null)return false;
        if (!isLogin(context))return false;
        UserModel userModel = new Gson().fromJson((String) SPUtils.get(context,SPUtils.FILE_USER,SPUtils.user_object,""),UserModel.class);
        if (userModel!=null){
            if (StringUtil.isNotEmpty(userModel.getRealname())
                    &&StringUtil.isNotEmpty(userModel.getSchool_id())
                    &&StringUtil.isNotEmpty(userModel.getCollege_id())
                    &&StringUtil.isNotEmpty(userModel.getClass_id())){
                result = true;
            }
        }
        return result;
    }

    //退出登录
    public final static void logout(Context context){
        if (context==null)return;
        SPUtils.clear(context,SPUtils.FILE_USER);
    }
}
