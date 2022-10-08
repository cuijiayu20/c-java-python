package 古典;

/***
 * 古典算法 加密解密代码 java语言就是用类来组织程序
 */


public class Operation {
    //加密的方法 方法 面向对象语言把函数叫方法 完成一个独立的功能然后可以被反复调用
    public String encode(int key, String s) {
        char c;
        String temp = "";
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);//从下标0开始取出每一个字符
            if (c >= 'A' && c <= 'Z') {
                c = (char) ((c - 65 + key) % 26 + 65);
            } else if (c >= 'a' && c <= 'z') {
                c = (char) ((c - 97 + key) % 26 + 97);
            } else {
                System.out.println("请输入字母");
                return null;

            }
            temp = temp + c;
        }
        return temp;
    }
    public String decode(int key,String s){
        char c ;
        String temp = "";
        for(int i=0;i<s.length();i++){
            c = s.charAt(i);
            if(c>='A'&&c<='Z'){
                c =(char) ((c-65+key)%26+65);
            }else if(c>='a'&&c<='z'){
                c = (char)((c-97+key)%26+97);
            }else{
                System.out.println("请输入字母");
                return null;
            }
            temp=temp+c;
        }
        return temp;
    }
    public int operationKey(int key){
        while(key<0){
            key = key+26;
        }
        key = key%26;
        return key;
    }
}