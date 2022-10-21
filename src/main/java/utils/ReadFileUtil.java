package utils;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import json.JsonBean;
import json.authorization.LoginJSON;
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

    /**
     * 读取文件并将其转换为指定的bean，使用Jackson进行转
     * 目前一支可转换文件类型：yaml、json
     *
     * @param path
     * @param clazz
     * @return
     * @throws IOException
     */
    public static Object readFileToBean(String path, Class clazz) throws IOException {
        FileReader reader = new FileReader(PathUtil.realPath(path));
        BufferedReader buffer = new BufferedReader(reader);
        YAMLMapper yamlMapper = new YAMLMapper();
        Object o = yamlMapper.readValue(buffer, clazz);
        reader.close();
        buffer.close();
        return o;
    }
}
