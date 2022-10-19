package test.use.common.control;

import lombok.Data;
import test.use.utils.MapUtil;
import test.use.utils.YamlUtil;

import java.io.IOException;
import java.util.Map;

/**
 * @ClassName YamlControl
 * @Author AissEr
 * @Date 2022/10/18 17:10
 * @Version 1.0
 * @Description TODO
 **/
@Data
public class YamlControl {
    private String name;
    private Map<String,Object> yamlMap;

    public YamlControl(){

    }

    public YamlControl(String yamlPath) throws IOException {
        changeYaml(yamlPath);
    }

    public YamlControl(String yamlName, String yamlPath) throws IOException {
        changeYaml(yamlName, yamlPath);
    }

    public YamlControl changeYaml(String yamlPath) throws IOException{
        yamlMap = YamlUtil.getReadAbleYaml(yamlPath);
        // 不设置默认以文件名为yaml的名字
        name = yamlPath.substring(yamlPath.lastIndexOf('/'),yamlPath.lastIndexOf(".yaml"));
        return this;
    }

    public YamlControl changeYaml(String yamlName, String yamlPath) throws IOException{
        yamlMap = YamlUtil.getReadAbleYaml(yamlPath);
        name = yamlName;
        return this;
    }

    // 可以通过.来读取
    public Object read(String targetKey){
        if(yamlMap == null || yamlMap.isEmpty()){
            return null;
        }
        return MapUtil.readMapByPoint(yamlMap,targetKey);
    }
}
