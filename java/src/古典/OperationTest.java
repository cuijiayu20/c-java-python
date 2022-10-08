package 古典;

import 古典.Operation;

import java.util.Scanner;

public class OperationTest {
    public static void main(String[] args) {
        int operation;
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入数字1.加密 2.解密:");
        Operation operation1 = new Operation();
        operation = scanner.nextInt();
        switch (operation) {
            case 1:
                System.out.print("请输入加密秘钥:");
                int key = scanner.nextInt();
                key = operation1.operationKey(key);
                System.out.print("请输入明文:");
                String str = scanner.next();
                String message = operation1.encode(key, str);
                if (message != null)
                    System.out.println("加密成功:" + message);
                else
                    System.out.println("加密失败");
                break;
            case 2:System.out.print("请输入解密秘钥:");
                   int key1 = scanner.nextInt();
                    key1 = operation1.operationKey(key1);
                    System.out.print("请输入密文:");
                    String str1 = scanner.next();
                    key1 = operation1.operationKey(-key1);
                    String message1 = operation1.decode(key1,str1);
                    if(message1!=null){
                        System.out.println("解密成功:"+message1);
                    }else
                        System.out.println("解密失败");
                    break;
        }
    }
}
