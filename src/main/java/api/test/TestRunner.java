package api.test;

import api.ApiObject;
import api.manage.ConfigureManager;
import api.manage.RestAssuredManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.util.List;
import java.util.Map;


/**
 * @ClassName TestRunner
 * @Author AissEr
 * @Date 2022/10/19 20:10
 * @Version 1.0
 * @Description 未实现
 **/
@Deprecated
public class TestRunner {
    private ApiObject apiObject;
    private static ConfigureManager configureManager;
    private static RestAssuredManager restAssuredManager;

    public TestRunner(ApiObject apiObject){
        this.apiObject = apiObject;
        configureManager = new ConfigureManager(apiObject);
        restAssuredManager = new RestAssuredManager(apiObject);
    }

    public TestRunner(ApiObject apiObject,String interfaceConfPath){
        this(apiObject);
        configureManager.setInterfaceConfigure(interfaceConfPath);
    }

    public void initTestRunner(){
        configureManager.initConfigureClass();
        configureManager.setConfigureInfo();
        restAssuredManager.initBaseInformation();
        restAssuredManager.getHaveBeenSetGiven();
    }

    public Response runThisInterfaceTest(){
        List<Map<String, Object>> testData = configureManager.getTestData();
        restAssuredManager.setTestData(testData.get(0));
        return restAssuredManager.run();
    }

    public void changeInterfaceConfFile(String fileName){
        configureManager.setInterfaceConfigure(fileName);
        configureManager.reloadInterfaceConfigure();
    }

    public void changeInterfaceConfFile(File interfaceFile){
        configureManager.setInterfaceConfigure(interfaceFile);
        configureManager.reloadInterfaceConfigure();
    }

    public List<Map<String, Object>> getTestData(){
        return configureManager.getTestData();
    }

}
