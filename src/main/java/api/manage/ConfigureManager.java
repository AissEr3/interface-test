package api.manage;

import api.ApiObject;
import api.configure.Configure;
import api.configure.FundamentalConfigure;
import api.configure.InterfaceConfigure;
import api.manage.login.LoginResponseInfo;
import lombok.Data;
import lombok.ToString;
import org.junit.jupiter.params.provider.Arguments;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ConfigureManager
 * @Author AissEr
 * @Date 2022/10/27 20:31
 * @Version 1.0
 * @Description 待测试
 **/
@Data
public class ConfigureManager{
    private ApiObject apiObject;
    private static final FundamentalConfigure fundamentalConfigure = FundamentalConfigure.getInstance();
    private InterfaceConfigure interfaceConfigure;

    public ConfigureManager(){

    }

    public ConfigureManager(ApiObject apiObject){
        this.apiObject = apiObject;
    }

    public ConfigureManager(ApiObject apiObject,String InterfaceConfPath){
        this(apiObject);
        setInterfaceConfigure(InterfaceConfPath);
    }

    public void setInterfaceConfigure(String path){
        interfaceConfigure = new InterfaceConfigure(path);
    }

    public void initConfigure(){
        fundamentalConfigure.initConfigure();
        interfaceConfigure.initConfigure();
    }

    public void configure(){
        fundamentalConfigure.configure(apiObject);
        interfaceConfigure.configure(apiObject);
    }

    public LoginResponseInfo getLoginInfo(){
        return fundamentalConfigure.getLoginInfo();
    }

    public ArrayList<Map<String, Object>> getTestData(){
        return interfaceConfigure.getTestData();
    }

    public List<Arguments> getJunit5TestCases(){
        List<Map<String, Object>> testData = getTestData();
        List<Arguments> testCase = new ArrayList<>();
        for(Map<String,Object> data : testData){
            testCase.add(Arguments.arguments(data));
        }
        return testCase;
    }
}
