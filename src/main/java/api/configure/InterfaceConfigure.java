package api.configure;

import api.ApiObject;
import api.configure.strategy.StrategyFactory;
import api.manage.TestData;
import utils.PathUtil;

import java.io.File;
import java.io.IOException;
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
public class InterfaceConfigure extends GeneralConfigure implements TestData<List<Map<String,Object>>> {
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
        if(resultMap != null) {
            StrategyFactory.createStrategy(INTERFACE_INFO).alterConfigureContent(object, resultMap);
        }
    }

    @Override
    protected void initApplicationMap() {
        try{
            applicationMap = MAPPER.readValue(configureFile, CONVERT_TYPE);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Map<String,Object>> getTestData(){
        return (List<Map<String,Object>>) applicationMap.get(DEFAULT_TEST_DATA.getName());
    }

    public void setFile(String filePath){
        configureFile = new File(PathUtil.realPath(filePath));
    }

    public void setFile(File file){
        configureFile = file;
    }
}
