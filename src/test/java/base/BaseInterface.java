package base;

import api.ApiObject;
import api.configure.InterfaceConfigure;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;
import java.util.Map;

/**
 * @ClassName BaseInterface
 * @Author AissEr
 * @Date 2022/10/30 22:27
 * @Version 1.0
 * @Description 基础接口测试的特征和行为
 **/
public abstract class BaseInterface<T> implements InterfaceTest<T>{
    protected RequestSpecification given;
    protected String interfaceConfigureFilePath;
    protected Response response;
    private ApiObject apiObject;
    private InterfaceConfigure interfaceConfigure;

    public BaseInterface(String interfaceConfigureFilePath){
        this.interfaceConfigureFilePath = interfaceConfigureFilePath;
        apiObject = new ApiObject();
        loadFileAndConfigure();
    }

    // 使用SetRestAssured来设置given
    protected abstract void configureGiven();

    protected void runInterface(){
        // java switch新特性（jdk14及以上）
        String path = apiObject.getPath();
        response = switch (apiObject.getRequestType()){
            case GET -> given.get(path);
            case POST -> given.post(path);
            case PUT ->  given.put(path);
            case DELETE -> given.delete(path);
        };
    }

    protected void loadFileAndConfigure(){
        interfaceConfigure = new InterfaceConfigure(interfaceConfigureFilePath);
        interfaceConfigure.initConfigure();
        interfaceConfigure.configure(apiObject);
    }

    @Override
    public Response request(T data) {
        configureGiven();
        runInterface();
        return response;
    }

    public List<Map<String, Object>> getDefaultTestData(){
        return interfaceConfigure.getTestData();
    }

    public Response getResponse() {
        return response;
    }

    public JsonPath getJsonPath() {
        return response.then().extract().jsonPath();
    }
}
