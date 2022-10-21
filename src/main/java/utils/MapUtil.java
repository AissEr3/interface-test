package utils;

import java.util.Map;

/**
 * @ClassName MapUtil
 * @Author AissEr
 * @Date 2022/10/17 19:03
 * @Version 1.0
 * @Description TODO
 **/
public class MapUtil {

    /**
     * 可以用'.'的方式读取map，例如 ras.public_key；目前功能为读取嵌套的map
     * 仅支持读取嵌套Map；即value类型为map
     * 不支持读取value是列表或数组，再读取列表或数组里的值；例如：ras.keys[1] 这种不支持
     * 但可以将数组或列表读取出来，如 ras.keys，如果keys存储的个列表，返回的是这个列表
     *
     * @param targetKey 要读取的目标值
     * @param map json或yaml转换成的map文件，或者多层嵌套的map,key类型必须为String
     * @return 读取的对象
     */
    public static Object readMapByPoint(Map<String,Object> map, String targetKey){
        String[] keys = targetKey.split("\\.");
        Map endMap = map;
        for(int i = 0; i < keys.length-1; i++){
            endMap = (Map) endMap.get(keys[i]);
        }
        return endMap.get(keys[keys.length-1]);
    }

//    private static Object getBy(String newKey){
//
//        return null;
//    }
//
//    private enum Selection{
//        STRING,LIST
//    }
}
