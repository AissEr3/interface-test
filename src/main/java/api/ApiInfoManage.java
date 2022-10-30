package api;

import api.ApiObject;
import api.configure.FundamentalConfigure;
import api.configure.InterfaceConfigure;
import lombok.Data;

import java.io.File;


/**
 * @ClassName ApiInfoManage
 * @Author AissEr
 * @Date 2022/10/19 20:10
 * @Version 1.0
 * @Description todo
 **/
@Data
public class ApiInfoManage {
    private static ApiObject apiObject = new ApiObject();
    private static FundamentalConfigure fundamentalConfigure;
    private InterfaceConfigure interfaceConfigure;

    public ApiInfoManage(String interfaceConfPath){
        interfaceConfigure = new InterfaceConfigure(interfaceConfPath);
    }

    public ApiInfoManage(File interfaceFile){
        interfaceConfigure = new InterfaceConfigure(interfaceFile);
    }

    public void loadFundamentalConfigure(){
        fundamentalConfigure = FundamentalConfigure.getInstance();
        fundamentalConfigure.initConfigure();
        fundamentalConfigure.configure(apiObject);
    }

    public void loadInterfaceConfigure(){
        interfaceConfigure.initConfigure();
        interfaceConfigure.configure(apiObject);
    }

    public void setInterfaceConfigure(String interfaceConfPath){
        interfaceConfigure.setFile(interfaceConfPath);
    }

    public void setInterfaceConfigure(File interfaceFile){
        interfaceConfigure.setFile(interfaceFile);
    }

}
