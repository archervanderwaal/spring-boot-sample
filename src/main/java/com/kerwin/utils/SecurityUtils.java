package com.kerwin.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.SecureRandom;

/**
 * Created by Kerwin on 2016/4/21.
 * 加密 & 解密
 */
public class SecurityUtils {

    /**
     * MD5加密
     *
     * @param str 要加密的字符串
     * @return 加密获得字符串
     */
    public static String changeStr(String str) throws Exception {
        StringBuilder sb = new StringBuilder();
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] bs = md.digest(str.getBytes());
        for (byte b : bs) {
            int input = b & 0xff; // 1~0x01 8~0x08 10~0xa 15~0xf 16~0x10
            if (input < 16) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(input));
        }
        return sb.toString();
    }

    /**
     * MD5加密
     *
     * @param str 要加密的字符串
     * @return 加密获得字符串
     */
    public static String MD5(String str) {
        if (str == null) {
            return null;
        } else {
            try {
                MessageDigest e = MessageDigest.getInstance("MD5");
                e.update(str.getBytes("UTF-8"));
                byte[] digest = e.digest();
                StringBuilder hexString = new StringBuilder();

                for (byte aDigest : digest) {
                    String strTemp = Integer.toHexString(aDigest & 255 | -256).substring(6);
                    hexString.append(strTemp);
                }

                return hexString.toString();
            } catch (Exception var6) {
                var6.printStackTrace();
                return str;
            }
        }
    }

    /**
     * Base64加密
     *
     * @param str 要加密的字符串
     * @return 加密后的字符串
     * @throws UnsupportedEncodingException
     */
    public static String Base64Encode(String str) throws UnsupportedEncodingException {
        return (new BASE64Encoder()).encode(str.getBytes("UTF-8"));
    }

    /**
     * Base64解密
     *
     * @param str 要解密的Base64字符串
     * @return 解密后的字符串
     * @throws IOException
     */
    public static String Base64Decode(String str) throws IOException {
        return new String((new BASE64Decoder()).decodeBuffer(str), "UTF-8");
    }

    /**
     * ASE
     *
     * @param content  .
     * @param password .
     * @return .
     */
    public static String AesEncrypt(String content, String password) {
        try {
            KeyGenerator e = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes());
            e.init(128, secureRandom);
            SecretKey secretKey = e.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(1, key);
            return bytes2Hex(cipher.doFinal(content.getBytes("UTF-8")));
        } catch (Exception var8) {
            var8.printStackTrace();
            return null;
        }
    }

    /**
     * ASE
     *
     * @param encryptContent .
     * @param password       密码
     * @return 字符串
     */
    public static String AesDecrypt(String encryptContent, String password) {
        try {
            KeyGenerator e = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes());
            e.init(128, secureRandom);
            SecretKey secretKey = e.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(2, key);
            return new String(cipher.doFinal(hex2Bytes(encryptContent)));
        } catch (Exception var8) {
            var8.printStackTrace();
            return null;
        }
    }

    /**
     * Bytes to Hex
     *
     * @param srcBytes .
     * @return .
     */
    public static String bytes2Hex(byte[] srcBytes) {
        StringBuilder hexRetSB = new StringBuilder();

        for (byte b : srcBytes) {
            String hexString = Integer.toHexString(255 & b);
            hexRetSB.append(hexString.length() == 1 ? Integer.valueOf(0) : "").append(hexString);
        }

        return hexRetSB.toString();
    }

    /**
     * Hex to Bytes
     *
     * @param source .
     * @return .
     */
    public static byte[] hex2Bytes(String source) {
        byte[] sourceBytes = new byte[source.length() / 2];

        for (int i = 0; i < sourceBytes.length; ++i) {
            sourceBytes[i] = (byte) Integer.parseInt(source.substring(i * 2, i * 2 + 2), 16);
        }

        return sourceBytes;
    }

    /**
     * DES加密
     *
     * @param source .
     * @param desKey .
     * @return 字符串
     * @throws Exception
     */
    public static String desEncrypt(String source, String desKey) throws Exception {
        try {
            SecretKeyFactory e = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = e.generateSecret(new DESKeySpec(desKey.getBytes()));
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(1, securekey);
            byte[] destBytes = cipher.doFinal(source.getBytes());
            StringBuilder hexRetSB = new StringBuilder();

            for (byte b : destBytes) {
                String hexString = Integer.toHexString(255 & b);
                hexRetSB.append(hexString.length() == 1 ? Integer.valueOf(0) : "").append(hexString);
            }

            return hexRetSB.toString();
        } catch (Exception var12) {
            throw new Exception("DES加密发生错误", var12);
        }
    }

    /**
     * DES解密
     *
     * @param source .
     * @param desKey .
     * @return 字符串
     * @throws Exception
     */
    public static String desDecrypt(String source, String desKey) throws Exception {
        byte[] sourceBytes = new byte[source.length() / 2];

        for (int e = 0; e < sourceBytes.length; ++e) {
            sourceBytes[e] = (byte) Integer.parseInt(source.substring(e * 2, e * 2 + 2), 16);
        }

        try {
            SecretKeyFactory var8 = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = var8.generateSecret(new DESKeySpec(desKey.getBytes()));
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(2, securekey);
            byte[] destBytes = cipher.doFinal(sourceBytes);
            return new String(destBytes);
        } catch (Exception var7) {
            throw new Exception("DES解密发生错误", var7);
        }
    }

    /**
     * 3DES加密
     *
     * @param src     .
     * @param keybyte .
     * @return .
     * @throws Exception
     */
    public static byte[] threeDesEncrypt(byte[] src, byte[] keybyte) throws Exception {
        try {
            byte[] e = new byte[24];
            if (keybyte.length < e.length) {
                System.arraycopy(keybyte, 0, e, 0, keybyte.length);
            } else {
                System.arraycopy(keybyte, 0, e, 0, e.length);
            }

            SecretKeySpec deskey = new SecretKeySpec(e, "DESede");
            Cipher c1 = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            c1.init(1, deskey);
            return c1.doFinal(src);
        } catch (Exception var5) {
            throw new Exception("3DES加密发生错误", var5);
        }
    }

    /**
     * 3DES解密
     *
     * @param src     .
     * @param keybyte .
     * @return .
     * @throws Exception
     */
    public static byte[] threeDesDecrypt(byte[] src, byte[] keybyte) throws Exception {
        try {
            byte[] e = new byte[24];
            if (keybyte.length < e.length) {
                System.arraycopy(keybyte, 0, e, 0, keybyte.length);
            } else {
                System.arraycopy(keybyte, 0, e, 0, e.length);
            }

            SecretKeySpec deskey = new SecretKeySpec(e, "DESede");
            Cipher c1 = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            c1.init(2, deskey);
            return c1.doFinal(src);
        } catch (Exception var5) {
            throw new Exception("3DES解密发生错误", var5);
        }
    }

    /**
     * 3DES加密
     *
     * @param src .
     * @param key .
     * @return .
     * @throws Exception
     */
    public static String threeDesEncrypt(String src, String key) throws Exception {
        return bytes2Hex(threeDesEncrypt(src.getBytes(), key.getBytes()));
    }

    /**
     * 3DES解密
     *
     * @param src .
     * @param key .
     * @return .
     * @throws Exception
     */
    public static String threeDesDecrypt(String src, String key) throws Exception {
        return new String(threeDesDecrypt(hex2Bytes(src), key.getBytes()));
    }

//    /**
//     * 加密解密测试
//     *
//     * @param args .
//     * @throws Exception
//     */
//    public static void main(String[] args) throws Exception {
//        String str = "数据加密的基本过程就是对原来为明文的文件或数据按某种算法进行处理，使其成为不可读的一段代码，通常称为“密文”，使其只能在输入相应的密钥之后才能显示出本来内容，通过这样的途径来达到保护数据不被非法人窃取、阅读的目的。 该过程的逆过程为解密，即将该编码信息转化为其原来数据的过程。";
//        str = str + str;
//        str = str + str;
//        str = str + str;
//        String PWD = "SecurityUtil.PWD";
//        System.out.println("原文:[" + str.length() + "]" + str);
//        System.out.println("==MD5===============");
//        System.out.println(MD5(str));
//        System.out.println("==Base64============");
//        String strBase64 = Base64Encode(str);
//        System.out.println("加密:[" + strBase64.length() + "]" + strBase64);
//        System.out.println("解密:" + Base64Decode(strBase64));
//        System.out.println("==Aes============");
//        String strAes = AesEncrypt(str, PWD);
//        System.out.println("加密:[" + strAes.length() + "]" + strAes);
//        System.out.println("解密:" + AesDecrypt(strAes, PWD));
//        System.out.println("==Des==============");
//        String strDes = desEncrypt(str, PWD);
//        System.out.println("加密:[" + strDes.length() + "]" + strDes);
//        System.out.println("解密:" + desDecrypt(strDes, PWD));
//        System.out.println("==3Des==============");
//        String str3Des = threeDesEncrypt(str, PWD);
//        System.out.println("加密:[" + str3Des.length() + "]" + str3Des);
//        System.out.println("解密:" + threeDesDecrypt(str3Des, PWD));
//        long t1 = System.currentTimeMillis();
//
//        int i;
//        for (i = 0; i < 10000; ++i) {
//            MD5(str);
//        }
//
//        System.out.println("\nMD5:" + (System.currentTimeMillis() - t1));
//        t1 = System.currentTimeMillis();
//
//        for (i = 0; i < 10000; ++i) {
//            Base64Encode(str);
//        }
//
//        System.out.println("Base64:" + (System.currentTimeMillis() - t1));
//        t1 = System.currentTimeMillis();
//
//        for (i = 0; i < 10000; ++i) {
//            AesEncrypt(str, PWD);
//        }
//
//        System.out.println("Aes:" + (System.currentTimeMillis() - t1));
//        t1 = System.currentTimeMillis();
//
//        for (i = 0; i < 10000; ++i) {
//            desEncrypt(str, PWD);
//        }
//
//        System.out.println("Des:" + (System.currentTimeMillis() - t1));
//        t1 = System.currentTimeMillis();
//
//        for (i = 0; i < 10000; ++i) {
//            threeDesEncrypt(str, PWD);
//        }
//
//        System.out.println("3Des:" + (System.currentTimeMillis() - t1));
//        t1 = System.currentTimeMillis();
//
//        for (i = 0; i < 10000; ++i) {
//            Base64Decode(strBase64);
//        }
//
//        System.out.println("\nBase64:" + (System.currentTimeMillis() - t1));
//        t1 = System.currentTimeMillis();
//
//        for (i = 0; i < 10000; ++i) {
//            AesDecrypt(strAes, PWD);
//        }
//
//        System.out.println("Aes:" + (System.currentTimeMillis() - t1));
//        t1 = System.currentTimeMillis();
//
//        for (i = 0; i < 10000; ++i) {
//            desDecrypt(strDes, PWD);
//        }
//
//        System.out.println("Des:" + (System.currentTimeMillis() - t1));
//        t1 = System.currentTimeMillis();
//
//        for (i = 0; i < 10000; ++i) {
//            threeDesDecrypt(str3Des, PWD);
//        }
//
//        System.out.println("3Des:" + (System.currentTimeMillis() - t1));
//    }
}
