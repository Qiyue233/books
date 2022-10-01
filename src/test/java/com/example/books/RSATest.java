package com.example.books;

import com.example.books.util.RSAUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.KeyPair;
import java.util.Base64;
import java.util.Random;

@SpringBootTest
public class RSATest {
    @Test
    public void Test() throws Exception {
        String content = "123456";
        KeyPair keyPair=RSAUtil.getKeyPair();
        // 公钥216
        String publicKey;
        //私钥844
        String privateKey;
        publicKey= Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
        privateKey= Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
        String t = RSAUtil.publicEncrpyt(content, publicKey);
        System.out.println("公钥加密后"+t);

        String s1 = RSAUtil.privateDecrypt(t, privateKey);
        System.out.println("解密后"+s1 );


        //公钥加密-->私钥签名-->公钥验签-->私钥解密
       /* String s = publicEncrpyt(content, publicKey);
        System.out.println("公钥加密后"+s);

        //签名     Authorization
        String sign = sign(s, privateKey);
        System.out.println("私钥签名后："+sign);

        //验签
        boolean verify = verify(s, sign, publicKey);
        System.out.println("用公钥验签后"+verify);

        String s1 = privateDecrypt(s, privateKey);
        System.out.println("解密后"+s1 );*/


        /*String s = privateEncrpyt(content, privateKey);
        System.out.println("公钥加密后" + s);

        String sign = sign(s, privateKey);
        System.out.println("私钥签名后：" + sign);

        boolean verify = verify(s, sign, publicKey);
        System.out.println("用公钥验签后" + verify);

        String s1 = publicDecrypt(s, publicKey);
        System.out.println("解密后" + s1);*/
    }

    @Test
    public void random(){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<8;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        System.out.println(sb.toString());

    }

}
