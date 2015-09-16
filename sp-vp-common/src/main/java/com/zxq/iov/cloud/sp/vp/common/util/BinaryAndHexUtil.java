package com.zxq.iov.cloud.sp.vp.common.util;

import java.io.*;

/**
 * 二进制，16进制转换工具类
 */
public class BinaryAndHexUtil {

    /**
     * 二进制字节数组转换为16进制字符串
     *
     * @param bArray
     *            字节数组
     * @return 16进制字符串
     */
    public static final String bytesToHexString(byte[] bArray,boolean isUpperCase) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2) {
                sb.append(0);
            }
            sb.append(isUpperCase?sTemp.toUpperCase():sTemp.toLowerCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制ASCII码字符串写入文件
     *
     * @param str
     *            16进制字符串
     * @param filePath
     *            文件路径
     * @throws java.io.IOException
     *             IO异常
     */
    public static void write(String str, String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(str);
        fw.flush();
    }

    /**
     * 将字节数组写入文件
     * @param bytes 字节数组
     * @param filePath 文件路径
     * @throws java.io.IOException IO异常
     */
    public static void write(byte[] bytes,String filePath) throws IOException{
        File file = new File(filePath);
        FileOutputStream stream = new FileOutputStream(file);
        stream.write(bytes);
        stream.flush();
        stream.close();
    }

    /**
     * 读取文件中的字节数组
     * @param filePath 文件路径
     * @return 字节数组
     */
    public static byte[] readBytes(String filePath){
        try {
            FileInputStream fis = new FileInputStream(new File(filePath));
            int byteLength = fis.available();
            byte[] bytes = new byte[byteLength];
            fis.read(bytes);
            return bytes;
        } catch (Exception e) {
            throw new RuntimeException("读文件出错!", e);
        }

    }

    /**
     * 读取文件中的16进制ASCII码字符串
     *
     * @param filePath
     *            文件路径
     * @return 16进制ASCII码字符串
     */
    public static String readString(String filePath) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(filePath)));
            StringBuffer value = new StringBuffer("");
            String data = null;
            while ((data = br.readLine()) != null) {
                value.append(data);
            }
            return value.toString();
        } catch (Exception e) {
            throw new RuntimeException("读文件出错!");
        }
    }

    /**
     * 将16进制ASCII字符串转换为二进制字节数组
     *
     * @param hex
     *            16进制ASCII字符串
     * @return 二进制字节数组
     */
    public static byte[] hexStringToByte(String hex) {

        char[] data = hex.toCharArray();

        int len = data.length;

        if ((len & 0x01) != 0) {
            throw new RuntimeException("Odd number of characters.");
        }

        byte[] out = new byte[len >> 1];

        // two characters form the hex value.
        for (int i = 0, j = 0; j < len; i++) {
            int f = toDigit(data[j], j) << 4;
            j++;
            f = f | toDigit(data[j], j);
            j++;
            out[i] = (byte) (f & 0xFF);
        }

        return out;
    }

    /**
     * 将十六进制字符转换成一个整数
     *
     * @param ch
     *            十六进制char
     * @param index
     *            十六进制字符在字符数组中的位置
     * @return 一个整数
     * @throws RuntimeException
     *             当ch不是一个合法的十六进制字符时，抛出运行时异常
     */
    protected static int toDigit(char ch, int index) {
        int digit = Character.digit(ch, 16);
        if (digit == -1) {
            throw new RuntimeException("Illegal hexadecimal character " + ch
                    + " at index " + index);
        }
        return digit;
    }

    private static byte toByte(char c) {
        byte b = (byte) "0123456789ABCDEF".indexOf(c);
        return b;
    }

    /**
     * 读取InputStream中的ASCII字符串
     * @param inputStream 输入流
     * @return ASCII字符串
     */
    public static String read(InputStream inputStream){
        try{
            int i = -1;
            StringBuffer sb = new StringBuffer();
            while ((i = inputStream.read())!=-1) {
                sb.append((char)i);
            }
            return sb.toString();
        }catch(Exception e){
            throw new RuntimeException("读取InputStream出错",e);
        }

    }

}
