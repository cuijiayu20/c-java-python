import java.io.Serializable;
import java.util.List;
import java.util.Map;

//路由器
public class Router implements Serializable {

    private String routerName;	//路由器地址
    private Map<String,Information> information;//路由器中路由表信息
    private List<Router> nearRouter;	//相邻路由器


    public Router() {
    }


    public Router(String routerName, Map<String, Information> information) {
        this.routerName = routerName;
        this.information = information;
    }

    public String getRouterName() {
        return routerName;
    }

    public Map<String, Information> getInformation() {
        return information;
    }

    public void setRouterName(String routerName) {
        this.routerName = routerName;
    }

    public void setInformation(Map<String, Information> information) {
        this.information = information;
    }

    public void setNearRouter(List<Router> nearRouter) {
        this.nearRouter=nearRouter;
    }

    public List<Router> getNearRouter(){
        return nearRouter;
    }

    /*return "路由表名称:"+routerName+"\n"+
	"目的网络"+"  "+"跳数"+"  "+"下一跳路由器\n"+
	information.values()+"\n";
	"Router{\n"+
     */

    @Override
    public String toString() {

        return 	"routerName:    "+routerName+
                "\ninformation:\n"+information.values()+
                "\n";

    }
}


