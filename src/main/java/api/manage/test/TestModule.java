package api.manage.test;

import api.configure.option.TestModuleOptions;
import util.MapUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName TestModule
 * @Author AissEr
 * @Date 2022/12/11 19:25
 * @Version 1.0
 * @Description 未实现使用
 **/
@Deprecated
public class TestModule {
    private final String TIMESTAMP = "{timestamp}";
    private final Map<String,?> TEMPLATE;
    private final Map<String,?> EACH_BEFORE_SETTING;
    private Map<String,?> testModuleData;

    public TestModule(Map<String,?> testModule){
        testModuleData = testModule;
        TEMPLATE = (Map<String, ?>) testModuleData.get(TestModuleOptions.REQUEST_PARAMETER_TEMPLATE.getName());
        EACH_BEFORE_SETTING =
                (Map<String, ?>) testModuleData.get(TestModuleOptions.EACH_SETTING.getName());
    }

    /**
     * 读取文集中的请求参数模板
     * @return 返回请求参数模板例子
     */
    public Map<String,?> getRequestParameterTemplate(){
        return (Map<String,?>) testModuleData.get(TestModuleOptions.REQUEST_PARAMETER_TEMPLATE.getName());
    }

    /**
     * @return 获取配置文件中设置的单接口测试数据
     */
    public List<Map<String,?>> getTestData(){
        return (List<Map<String,?>>) testModuleData.get(TestModuleOptions.SINGLE_TEST_DATA.getName());
    }

    private void setRealData(Map<String,Object> data){
        Map<String,Object> realData = new HashMap<>();
        realData.putAll(TEMPLATE);
        for(String key : EACH_BEFORE_SETTING.keySet()){
            String value = (String) EACH_BEFORE_SETTING.get(key);
            value = value.replace(TIMESTAMP,Long.toString(System.currentTimeMillis()));
            MapUtil.setMapByPoint(realData,key,value);
        }
        for(String key : data.keySet()){
            MapUtil.setMapByPoint(realData,key,data.get(key));
        }
        data.clear();
        data.putAll(realData);
    }

//    request-parameter-template:
//    {
//        page: 0
//        size: 10
//        sort: "id,desc"
//    }
//    each-before-setting:
//    page: "page{timestamp}"

}
