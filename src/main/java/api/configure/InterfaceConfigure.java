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
import java.util.Map;

import static api.configure.ConfigureOptions.*;

/**
 * @ClassName InterfaceConfigure
 * @Author AissEr
 * @Date 2022/10/26 20:54
 * @Version 1.0
 * @Description TODO
 **/
@Data
public class InterfaceConfigure extends GeneralConfigure{
    // 避免每次配置都创建mapper，浪费资源
    private static final ObjectMapper mapper = new YAMLMapper();

    private String configureFilePath;

    public InterfaceConfigure(){}

    public InterfaceConfigure(String path){
        configureFilePath = path;
    }

    @Override
    public void configure(ApiObject object) {
        Map<String,Object> resultMap = (Map<String, Object>) applicationMap.get(INTERFACE_INFO.getName());
        StrategyFactory.createStrategy(INTERFACE_INFO).alterConfigureContent(object,resultMap);
    }

    @Override
    protected void initApplicationMap() {
        try{
            applicationMap = mapper.readValue(new File(PathUtil.realPath(configureFilePath)),
                    new HashMap<String,Object>().getClass());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Map<String,Object>> getTestData(){
        return (ArrayList<Map<String,Object>>) applicationMap.get(TEST_DATA.getName());
    }

}
