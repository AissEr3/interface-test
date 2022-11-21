package common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import lombok.Data;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName YamlMapper
 * @Author AissEr
 * @Date 2022/11/18 16:32
 * @Version 1.0
 * @Description TODO
 **/
public class YamlMapper {
    // 使用到的静态资源
    private  static Class<? extends HashMap> CONVERT_TYPE = new HashMap<String,Object>().getClass();
    private  static ObjectMapper MAPPER = new YAMLMapper();

    private Map<String,?> yaml;

    public YamlMapper(String filePath){
        setYaml(filePath);
    }

    public YamlMapper(File file){
        setYaml(file);
    }

    public Map<String, ?> getYamlMap() {
        return yaml;
    }

    public void setYaml(String filePath) {
        setYaml(new File(filePath));
    }

    public void setYaml(File file) {
        try{
            // MAPPER是静态资源
            yaml = MAPPER.readValue(file, CONVERT_TYPE);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
