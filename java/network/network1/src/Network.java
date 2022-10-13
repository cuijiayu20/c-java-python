import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Network {

    private int routerNum;				//路由器个数
    private List<Router> routerList;	//路由器集合

    public int getRouterNum() {
        return routerNum;
    }

    public void setRouterNum(int routerNum) {
        this.routerNum = routerNum;
    }


    public List<Router> getRouterList() {
        return routerList;
    }

    public void setRouterList(List<Router> routerList) {
        this.routerList = routerList;
    }

    //初始化路由表
    public void initRouter(){
        routerNum=6;

        this.routerList= new ArrayList<Router>();
        Map<String,Information> informationA=new HashMap<>();
        informationA.put("N1",new Information("N1",1,"null"));
        informationA.put("N2",new Information("N2",1,"null"));
        informationA.put("N3",new Information("N3",1,"null"));
        this.routerList.add(new Router("A",informationA));

        Map<String,Information> informationB=new HashMap<>();
        informationB.put("N3",new Information("N3",1,"null"));
        informationB.put("N4",new Information("N4",1,"null"));
        this.routerList.add(new Router("B",informationB));

        Map<String,Information> informationC=new HashMap<>();
        informationC.put("N4",new Information("N4",1,"null"));
        informationC.put("N5",new Information("N5",1,"null"));
        this.routerList.add(new Router("C",informationC));

        Map<String,Information> informationD=new HashMap<>();
        informationD.put("N2",new Information("N2",1,"null"));
        informationD.put("N6",new Information("N6",1,"null"));
        this.routerList.add(new Router("D",informationD));

        Map<String,Information> informationE=new HashMap<>();
        informationE.put("N1",new Information("N1",1,"null"));
        informationE.put("N6",new Information("N6",1,"null"));
        this.routerList.add(new Router("E",informationE));

        Map<String,Information> informationF=new HashMap<>();
        informationF.put("N5",new Information("N5",1,"null"));
        informationF.put("N6",new Information("N6",1,"null"));
        this.routerList.add(new Router("F",informationF));

    }

    //初始化相邻路由
    public void initNearRouter() {
        /*观察初始路由表项，可以发现相邻的路由表具有一个表项交集，
         * 因此可以根据是否有交集，判断他们是否相邻
         */
        for(int i=0;i<this.routerNum;i++) {

            //得到路由表(例如A)的关键字存放在Set集合中
            Set<String> set=this.routerList.get(i).getInformation().keySet();
            List<Router> nearRouter=new ArrayList<Router>();

            //遍历其他路由表
            for(int j=0;j<this.routerNum;j++) {
                if(i!=j) {

                    /*若此路由表(例如B)中有关键字为s的表项，则B与A相邻
                     * 将B加入A的相邻路由器集合中；
                     */
                    for(String s:set) {
                        if(this.routerList.get(j).getInformation().containsKey(s))
                            nearRouter.add(this.routerList.get(j));
                    }
                }
            }
            this.routerList.get(i).setNearRouter(nearRouter);
        }
    }

    //输出路由器相邻路由器
	/*for(int i=0;i<this.routerNum;i++) {
		List<String> nearRouter=new ArrayList<String>();
		nearRouter=this.routerList.get(i).getNearRouter();

		for(int j=0;j<nearRouter.size();j++)
			System.out.println(nearRouter.get(j));
			System.out.println("\n");

	}*/

    //初始化网络拓扑
    public void initNetwork() {
        initRouter();
        initNearRouter();

    }

}

