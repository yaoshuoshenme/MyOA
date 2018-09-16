package com.edu.shiro;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.hash.SimpleHash;

import java.security.Key;

public class ShiroEncryUtil {

    private static final String salt = "today";

    //Md5摘要
    public static String md5(String pass){
        SimpleHash md5 = new SimpleHash("MD5", pass, salt, 128);
        return md5.toString();
    }

    //Base64编码
    public static String ToBase64(byte[] data){
        return Base64.encodeToString(data);
    }

    //Base64解码
    public static String Base64To(String data){
        return Base64.decodeToString(data.getBytes());
    }

    //秘钥
    public static Key createKey(){
        AesCipherService aesCipherService = new AesCipherService();
        aesCipherService.setKeySize(256);
        return aesCipherService.generateNewKey();
    }

    //AES加密
    public static String aesEncode(Key key, String data){
        AesCipherService aesCipherService = new AesCipherService();
        return aesCipherService.encrypt(data.getBytes(),key.getEncoded()).toBase64();
    }

    //AES解密
    public static String aesDecode(Key key, String data){
        AesCipherService aesCipherService = new AesCipherService();
        return new String(aesCipherService.decrypt(Base64.decode(data),key.getEncoded()).getBytes());

    }

}
