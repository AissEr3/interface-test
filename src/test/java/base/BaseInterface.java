package base;

import api.ApiObject;
import api.configure.InterfaceConfigure;
import io.restassured.specification.RequestSpecification;

import java.util.List;
import java.util.Map;

/**
 * @ClassName BaseInterface
 * @Author AissEr
 * @Date 2022/10/30 22:27
 * @Version 1.0
 * @Description TODO
 **/
public class BaseInterface {
    protected RequestSpecification given;
    protected String interfaceConfigureFilePath;
    protected ApiObject apiObject;
    protected InterfaceConfigure interfaceConfigure;

    public BaseInterface(){

    }

    protected void loadFileAndConfigure(){
        interfaceConfigure = new InterfaceConfigure(interfaceConfigureFilePath);
        interfaceConfigure.initConfigure();
        interfaceConfigure.configure(apiObject);
    }

    protected void configureGiven(){

    }

    public List<Map<String, Object>> getDefaultTestData(){
        return interfaceConfigure.getTestData();
    }
}
