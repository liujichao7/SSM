package com.itheima.ssm.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptPasswordEncoderUtils {
    private static BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
    public static String encoderPassword(String password){
        return bcryptPasswordEncoder.encode(password);
    }

    //测试
    public static void main(String[] args) {
        String s = encoderPassword("123");
        System.out.println(s);
    }

}
