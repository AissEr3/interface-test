package utils;

/**
 * @ClassName PathUtil
 * @Author AissEr
 * @Date 2022/10/11 23:20
 * @Version 1.0
 * @Description TODO
 **/
public class PathUtil {
    private static final String TEST_RESOURCE_PATH = "src/test/test-resource/";

    private static final String CLASS_RESOURCE_PATH = "src/main/resources";

    /**
     * 直接读取在resource下的文件
     * 在路径前加上`resource:`即可
     *
     * @param path
     * @return
     */
    public static String realPath(String path){
        String realPath = path;
        int index = path.indexOf(':');
        String prefix = path.substring(0,index);
        if(prefix.equals("test")) {
            realPath = TEST_RESOURCE_PATH + path.substring(index + 1);
        }
        else if(prefix.equals("resource")){
            realPath = CLASS_RESOURCE_PATH + path.substring(index + 1);
        }
        return realPath;
    }


    /**
     * 用'.'连接所有字符串；即所有字符串连接，以'.'间隔字符串
     * @param keys 需要连接的key
     * @return 返回连接后的字符串
     */
    public static String connectionByPoint(String ... keys){
        StringBuilder result = new StringBuilder();
        for(String key : keys){
            result.append(key);
            result.append('.');
        }
        return result.substring(0,result.length()-1);
    }


}
