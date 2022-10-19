package test.use.utils;

/**
 * @ClassName PathUtil
 * @Author AissEr
 * @Date 2022/10/11 23:20
 * @Version 1.0
 * @Description TODO
 **/
public class PathUtil {
    private static final String RESOURCE_PATH = "src/test/test_data/";

    /**
     * 直接读取在resource下的文件
     * 在路径前加上`resource:`即可
     *
     * @param path
     * @return
     */
    public static String realPath(String path){
        String realPath = path;
        if(path.startsWith("resource:")) {
            realPath = RESOURCE_PATH + path.substring(path.indexOf(':') + 1);
        }
        return realPath;
    }


}
