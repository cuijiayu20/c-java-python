package 对称;

import java.util.Arrays;

public class TestDesUtil {
    public static void main(String[] args) {
        String key = "这是秘钥";
        String data = "DOCT";
        DesUtil desUtil = new DesUtil(key);
        System.out.println("加密前的明文:" + data);
//加密后的byte型的密文


        byte[] bytes = desUtil.DesEncrypt(data.getBytes(), 1);
        System.out.println("加密后的密文:" + Arrays.toString(bytes));
//将加密的内容进行解密
        System.out.println("解密后的明文:" + new
                String(desUtil.DesEncrypt(bytes, 0)));
    }
}