package api.manage;

import api.ApiObject;

import api.configure.FundamentalConfigure;
import api.configure.InterfaceConfigure;
import api.manage.login.LoginResponseInfo;
import lombok.Data;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ConfigureManager
 * @Author AissEr
 * @Date 2022/10/27 20:31
 * @Version 1.0
 * @Description
 **/
public class ConfigureManager{
    private ApiObject apiObject;
    private static final FundamentalConfigure fundamentalConfigure = FundamentalConfigure.getInstance();
    private InterfaceConfigure interfaceConfigure;

    public ConfigureManager(){

    }

    public ConfigureManager(ApiObject apiObject){
        this.apiObject = apiObject;
    }

    public ConfigureManager(ApiObject apiObject,String interfaceConfPath){
        this(apiObject);
        setInterfaceConfigure(interfaceConfPath);
    }

    public ConfigureManager(ApiObject apiObject,File interfaceFile){
        this(apiObject);
        setInterfaceConfigure(interfaceFile);
    }

    public void setInterfaceConfigure(String path){
        interfaceConfigure = new InterfaceConfigure(path);
    }

    public void setInterfaceConfigure(File interfaceFile){
        interfaceConfigure = new InterfaceConfigure(interfaceFile);
    }

    public void reloadInterfaceConfigure(){
        interfaceConfigure.initConfigure();
        interfaceConfigure.configure(apiObject);
    }

    public void initConfigureClass(){
        fundamentalConfigure.initConfigure();
        interfaceConfigure.initConfigure();
    }

    public void setConfigureInfo(){
        fundamentalConfigure.configure(apiObject);
        interfaceConfigure.configure(apiObject);
    }

    public LoginResponseInfo getLoginInfo(){
        return fundamentalConfigure.getLoginInfo();
    }

    public List<Map<String, Object>> getTestData(){
        return interfaceConfigure.getTestData();
    }

    public ApiObject getApiObject() {
        return apiObject;
    }

    public void setApiObject(ApiObject apiObject) {
        this.apiObject = apiObject;
    }
}
