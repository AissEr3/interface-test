package util;

import org.yaml.snakeyaml.Yaml;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

/**
 * @ClassName ReadFileUtil
 * @Author AissEr
 * @Date 2022/10/11 22:55
 * @Version 1.0
 * @Description 自己学习类
 **/
public class ReadFileUtil {
    /**
     * 读取 ymal，最原始的读取方式，只用在了GeneralConfigure类（只配置一次），主要是学习读取Yaml文件的信息并转换为map
     * 其他地方读取yaml文件用Jackson，如InterfaceConfigure类实现的
     *
     * @param path 需要读取的文件路径
     * @return Map
     */
    public static Map readYamlToMap(String path) throws IOException {
        Yaml yml = new Yaml();
        FileReader reader = new FileReader(PathUtil.realPath(path));
        BufferedReader buffer = new BufferedReader(reader);
        Map<String,Object> map = yml.load(buffer);
        reader.close();
        buffer.close();
        return map;
    }
}
