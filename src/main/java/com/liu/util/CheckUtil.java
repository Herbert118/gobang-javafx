package com.liu.util;

import com.sun.scenario.effect.impl.state.AccessHelper;

public class CheckUtil {
    public static boolean checkNotBlank(String string){
        if (string!=null){
            if(!string.isEmpty()){
                return true;
            }
        }
        return false;
    }
    public static boolean checkName(String name){
        if(name == null){
            return false;
        }
        else{
            return true;
        }
    }

    public static boolean checkPassword(String password){
        if(password == null){
            return false;
        }
        else{
            //必须包含字母和数字, 且不小于6位
            return password.matches("^(?=.*?[0-9])(?=.*?[a-z]).{6,}$");
        }
    }
    public static boolean checkId(String id){
        if(id == null){
            return false;
        }
        else{
            return true;
        }
    }

    public static boolean checkEmail(String email){
        if(email == null){
            return false;
        }
        return email.matches("^[0-9a-zA-Z_]*@[0-9a-zA-Z_]*.com$");
    }

    public static boolean checkAuthority(String authority) {
        if("user".equals(authority)||"admin".equals(authority)){
            return true;
        }
        return false;
    }
}
