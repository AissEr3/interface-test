package api.configure;

import api.ApiObject;
import api.configure.strategy.StrategyFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import util.PathUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static api.configure.ConfigureOptions.*;

/**
 * @ClassName InterfaceConfigure
 * @Author AissEr
 * @Date 2022/10/26 20:54
 * @Version 1.0
 * @Description 该类将具体的接口配置文件信息，配置到APIObject中
 **/
public class InterfaceConfigure extends AbstractConfigure {
    // 使用到的静态资源
    private  static Class<? extends HashMap> CONVERT_TYPE = new HashMap<String,Object>().getClass();
    private  static ObjectMapper MAPPER = new YAMLMapper();
    // 管理文件
    private File configureFile;

    public InterfaceConfigure(){}

    public InterfaceConfigure(String filePath){
        setFile(filePath);
    }

    public InterfaceConfigure(File configureFile){
        this.configureFile = configureFile;
    }

    /**
     * 配置ApiObject对象
     * @param object
     */
    @Override
    public void configure(ApiObject object) {
        Map<String,Object> resultMap = (Map<String, Object>) applicationMap.get(INTERFACE_INFO.getName());
        if(resultMap != null) {
            StrategyFactory.createStrategy(INTERFACE_INFO).alterConfigureContent(object, resultMap);
        }
    }

    /**
     *  初始化配置文件信息，使用Jackson将Yaml文件反序列化为Map
     */
    @Override
    protected void initApplicationMap() {
        try{
            // MAPPER是静态资源
            applicationMap = MAPPER.readValue(configureFile, CONVERT_TYPE);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * @return 获取配置文件中设置的单接口测试数据
     */
    public List<Map<String,Object>> getTestData(){
        return (List<Map<String,Object>>) applicationMap.get(TEST_DATA.getName());
    }

    /**
     * @return 获取配置文件中设置的JsonSchema
     */
    public String getJsonSchema(){
        return (String) applicationMap.get(JSON_SCHEME.getName());
    }

    public void setFile(String filePath){
        configureFile = new File(PathUtil.realPath(filePath));
    }

    public void setFile(File file){
        configureFile = file;
    }
}
