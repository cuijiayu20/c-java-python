package com.HomeWork4;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class ClotheDB {
    private static Map<String, Clothe> clothes=new LinkedHashMap<String, Clothe>();
    static{
        clothes.put("1",new Clothe("1","衣服"));
        clothes.put("2",new Clothe("2","裤子"));
        clothes.put("3",new Clothe("3","衬衫"));
        clothes.put("4",new Clothe("4","内衣"));
        clothes.put("5",new Clothe("5","帽子"));
    }
    //获取所有衣服
    public static Collection<Clothe> getAll(){
        return clothes.values();
    }
    public static Clothe getClothe (String id) {
        return clothes.get(id);
    }
}
