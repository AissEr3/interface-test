package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.yaml.snakeyaml.Yaml;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @ClassName ReadFileUtil
 * @Author AissEr
 * @Date 2022/10/11 22:55
 * @Version 1.0
 * @Description TODO
 **/
public class ReadFileUtil {
    /**
     * 读取 ymal
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
