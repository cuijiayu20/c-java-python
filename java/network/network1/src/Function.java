import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Function {

    public static void fun1(Network network) {

        network.initNetwork();

        if(network.getRouterList().size()==network.getRouterNum())
            System.out.println("初始化成功!!!");

        fun2(network);


    }

    public static void fun2(Network network) {
        for(int i=0;i<network.getRouterList().size();i++) {
            System.out.println(network.getRouterList().get(i));
        }
    }

    public static void updateTable(Network network) throws IOException, ClassNotFoundException {
        List<Router> routerList=network.getRouterList();
        for(int i=0;i<routerList.size();i++) {

            Router router1=routerList.get(i);
            //Map<String,Information> map1=routerList.get(i).getInformation();
            //System.out.println(router1);

            List<Router> nearRouter=routerList.get(i).getNearRouter();

            for(int j=0;j<nearRouter.size();j++) {
                Router router=nearRouter.get(j);
                //Router router2=new Router();
                //router2=router;

                //如果没有信息，什么也不做
                if(router.getInformation()==null || router.getInformation().size()==0 || router1==router){
                    break;
                }

                //对router2的复制，避免直接修改路由器router2的路由表
                ByteArrayOutputStream baos=new ByteArrayOutputStream();
                ObjectOutputStream oos=new ObjectOutputStream(baos);
                oos.writeObject(router);
                oos.close();

                byte[] bs=baos.toByteArray();
                ByteArrayInputStream bais=new ByteArrayInputStream(bs);
                ObjectInputStream ois=new ObjectInputStream(bais);
                Router router2= (Router) ois.readObject();
                ois.close();

                //System.out.println(router2);

                for(Information information:router2.getInformation().values()) {
                    //距离小于16时加1
                    if(information.getDistance()<16) {
                        information.setDistance(information.getDistance()+1);
                    }
                    information.setNextJumpRouter(router2.getRouterName());

                }
                //System.out.println(router2);

                Information information=null;
                for(String s:router2.getInformation().keySet()) {

                    //原路由表中不包括目的网络N,则添加到路由表中
                    if(!router1.getInformation().containsKey(s)) {
                        information=new Information(s,router2.getInformation().get(s).getDistance(),router2.getRouterName());
                        router1.getInformation().put(s, information);
                    }else {
                        //如果下一跳相同，替换原路由表中的项目，
                        if(router1.getInformation().get(s).getNextJumpRouter().equals(router2.getRouterName())){
                            router1.getInformation().put(s,router2.getInformation().get(s));//直接替换
                        }else {
                            //若收到的项目中的距离小于路由表中的距离，则进行更新，
                            if(router2.getInformation().get(s).getDistance()<router1.getInformation().get(s).getDistance()){
                                router1.getInformation().put(s,router2.getInformation().get(s));
                            }
                        }
                    }
                }

                //System.out.println(router1);
            }
        }
    }

    public static void fun3(Network network) throws IOException, ClassNotFoundException {

        System.out.print("请输入循环轮次:");
        Scanner in = new Scanner(System.in);
        int sum = in.nextInt();
        //in.close();
        for(int a = 0; a < sum; a++){

            System.out.println("---循环轮次"+(a+1)+"---");
            updateTable(network);

            for(int i=0;i<network.getRouterNum();i++) {
                System.out.println(network.getRouterList().get(i));
            }

        }
    }


    public static void fun4_1(Network network) {

        System.out.println("请输入要加入的网络名称：");
        String net;
        Scanner in=new Scanner(System.in);
        net=in.nextLine();

        System.out.println("请输入与该网络相邻的路由器名称：");
        String str=in.nextLine();
        String routers[]=str.split(" ");

        for(int i=0;i<routers.length;i++) {
            for(int j=0;j<network.getRouterList().size();j++) {
                Router router=network.getRouterList().get(j);
                if(router.getRouterName().equals(routers[i])) {
                    router.getInformation().put(net,new Information(net,1,"null"));
                }
            }
        }

        for(int i=0;i<network.getRouterNum();i++) {
            System.out.println(network.getRouterList().get(i));
        }

    }

    public static void fun4_2(Network network) {

        System.out.println("请输入要退出的网络名称：");
        String net;
        Scanner in=new Scanner(System.in);
        net=in.nextLine();

        for(int i=0;i<network.getRouterList().size();i++) {
            Router router=network.getRouterList().get(i);
            if(router.getInformation().containsKey(net)) {
                router.getInformation().remove(net);
            }
        }

        for(int i=0;i<network.getRouterNum();i++) {
            System.out.println(network.getRouterList().get(i));
        }

    }

    public static void fun4_3(Network network) throws ClassNotFoundException, IOException {
        System.out.println("请输入发生故障的路由器名称：");
        String rt;
        Scanner in=new Scanner(System.in);
        rt=in.nextLine();

        Iterator<Router> it1=network.getRouterList().iterator();

        while(it1.hasNext()) {

            Router router=(Router)it1.next();
            if(router.getRouterName().equals(rt)) {
                //得到其(C)相邻路由器
                List<Router> nearRouter=router.getNearRouter();
                for(int i=0;i<nearRouter.size();i++) {
                    Router r1=nearRouter.get(i);

                    //从相邻路由器(例如D)的相邻路由表中删除C
                    for(int j=0;j<r1.getNearRouter().size();j++) {
                        if(r1.getNearRouter().get(j).getRouterName().equals(rt)) {
                            r1.getNearRouter().remove(j);
                        }
                    }
                }
                //清空Map集合
                router.getInformation().clear();
                //将此路由器从List集合删除
                it1.remove();
                network.setRouterNum(network.getRouterNum()-1);
            }
        }

        Iterator<Router> it=network.getRouterList().iterator();

        while(it.hasNext()) {

            Router router=(Router)it.next();
            //删除下一跳为rt的表项
            Iterator<String> its=router.getInformation().keySet().iterator();
            while(its.hasNext()) {
                String str=(String)its.next();
                if(router.getInformation().get(str).getNextJumpRouter().equals(rt)) {
                    its.remove();
                }
            }
        }

		/*Iterator<Information> its=router.getInformation().values().iterator();

		while(its.hasNext()) {
			Information infor=(Information)its.next();
			if(infor.getNextJumpRouter().equals(rt)) {
				its.remove();

			}
		}
		*/
		/*for(int i=0;i<network.getRouterList().size();i++) {
		if(network.getRouterList().get(i).getRouterName().equals(rt)) {
			network.getRouterList().remove(i);
			network.setRouterNum(network.getRouterNum()-1);
		}
	}*/

		/*for(int i=0;i<network.getRouterList().size();i++) {
			Router router=network.getRouterList().get(i);

			for(String str:router.getInformation().keySet()) {
				if(router.getInformation().get(str).getNextJumpRouter().equals(rt)) {

					router.getInformation().remove(str);

				}
			}
		}*/

        for(int i=0;i<network.getRouterList().size();i++) {
            System.out.println(network.getRouterList().get(i));
        }

		/*
		updateTable(network);

		for(int i=0;i<network.getRouterNum();i++) {
			System.out.println(network.getRouterList().get(i));
		}*/

    }

    public static void fun4(Network network) throws ClassNotFoundException, IOException {

        int choose; //输入的选项
        Scanner scanner=new Scanner(System.in);

        System.out.println("1.网络加入");
        System.out.println("2.网络退出");
        System.out.println("3.路由器故障");
        System.out.println("4.取消");

        System.out.println("请选择：");
        choose=scanner.nextInt();

        switch(choose) {
            case 1:
                fun4_1(network);
                break;
            case 2:
                fun4_2(network);
                break;
            case 3:
                fun4_3(network);
                break;
            case 4:
                break;

        }
    }
}

