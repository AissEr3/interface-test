package api.configure;

import api.ApiObject;
import api.configure.option.ConfigureOptions;
import api.configure.option.TestModuleOptions;
import api.configure.strategy.StrategyFactory;
import api.manage.login.LoginResponseInfoManage;
import common.YamlMapper;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @ClassName InterfaceConfigure
 * @Author AissEr
 * @Date 2022/10/26 20:54
 * @Version 1.0
 * @Description 该类将具体的接口配置文件信息，配置到APIObject中
 **/
public class InterfaceConfigure extends AbstractConfigure {
    // 管理文件
    private File configureFile;

    public InterfaceConfigure(){}

    public InterfaceConfigure(String filePath){
        configureFile = new File(filePath);
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
        Map<String,Object> resultMap = (Map<String, Object>) applicationMap.get(ConfigureOptions.INTERFACE_INFO.getName());
        if(resultMap != null) {
            StrategyFactory.createStrategy(ConfigureOptions.INTERFACE_INFO).alterConfigureContent(object, resultMap);
        }
    }

    /**
     *  初始化配置文件信息，使用Jackson将Yaml文件反序列化为Map
     */
    @Override
    protected void initApplicationMap() {
        applicationMap = new YamlMapper(configureFile).getYamlMap();
    }

    /**
     * @return 获取配置文件中设置的单接口测试数据
     */
    public List<Map<String,?>> getTestData(){
        return (List<Map<String,?>>) applicationMap.get(TestModuleOptions.SINGLE_TEST_DATA.getName());
    }
}
