import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Network network=new Network();

        int choose; //输入的选项
        Scanner scanner=new Scanner(System.in);

        boolean flag=false;

        while(true){

            System.out.println("1.初始化");
            System.out.println("2.显示当前各路由表信息");
            System.out.println("3.根据循环轮次更新路由表");
            System.out.println("4.更改网络拓扑结构");
            System.out.println("5.结束");

            choose=scanner.nextInt();

            switch (choose){
                case 1:
                    Function.fun1(network);
                    flag=true;

                    break;
                case 2:
                    if(!flag) {
                        System.out.println("未初始化，请先完成初始化！\n");
                        break;
                    }else {
                        Function.fun2(network);
                        break;
                    }

                case 3:
                    if(!flag) {
                        System.out.println("未初始化，请先完成初始化！\n");
                        break;
                    }else {
                        Function.fun3(network);
                        break;
                    }


                case 4:
                    if(!flag) {
                        System.out.println("未初始化，请先完成初始化！\n");
                        break;
                    }else {
                        Function.fun4(network);
                        break;
                    }

                case 5:
                    System.exit(0);
                    break;
            }

        }
    }
}

