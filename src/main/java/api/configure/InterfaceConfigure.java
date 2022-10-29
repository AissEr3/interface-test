package api.configure;

import api.ApiObject;
import api.configure.strategy.StrategyFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import lombok.Data;
import utils.PathUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static api.configure.ConfigureOptions.*;

/**
 * @ClassName InterfaceConfigure
 * @Author AissEr
 * @Date 2022/10/26 20:54
 * @Version 1.0
 * @Description TODO
 **/
public class InterfaceConfigure extends GeneralConfigure{
    // 避免每次配置都创建，浪费资源
    public static final Class<? extends HashMap> PARAMETERS_TYPE = new HashMap<String,Object>().getClass();
    private static final ObjectMapper MAPPER = new YAMLMapper();

    private File configureFile;

    public InterfaceConfigure(){}

    public InterfaceConfigure(String filePath){
        setFile(filePath);
    }

    public InterfaceConfigure(File configureFile){
        this.configureFile = configureFile;
    }

    @Override
    public void configure(ApiObject object) {
        Map<String,Object> resultMap = (Map<String, Object>) applicationMap.get(INTERFACE_INFO.getName());
        StrategyFactory.createStrategy(INTERFACE_INFO).alterConfigureContent(object,resultMap);
    }

    @Override
    protected void initApplicationMap() {
        try{
            applicationMap = MAPPER.readValue(configureFile, PARAMETERS_TYPE);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public List<Map<String,Object>> getTestData(){
        return (List<Map<String,Object>>) applicationMap.get(TEST_DATA.getName());
    }

    public void setFile(String filePath){
        configureFile = new File(PathUtil.realPath(filePath));
    }

    public void setFile(File file){
        configureFile = file;
    }
}
