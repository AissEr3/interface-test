package utils;

/**
 * @ClassName PathUtil
 * @Author AissEr
 * @Date 2022/10/11 23:20
 * @Version 1.0
 * @Description TODO
 **/
public class PathUtil {
    private static final String TEST_RESOURCE_PATH = "src/test/test_data/";

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


}
