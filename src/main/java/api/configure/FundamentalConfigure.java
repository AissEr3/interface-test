package api.configure;

import api.ApiObject;
import utils.ApiObjectUtil;
import utils.PathUtil;
import utils.ReadFileUtil;

import java.io.IOException;
import java.util.Map;
import static api.configure.ConfigureOptions.*;

/**
 * @ClassName FundamentalConfigure
 * @Author AissEr
 * @Date 2022/10/20 22:14
 * @Version 1.0
 * @Description TODO
 **/
public class FundamentalConfigure extends GeneralConfigure{
    private static final String DEFAULT_APPLICATION_FILE_PATH = "test:application/application-test.yaml";
    private static final String DEFAULT_USERNAME = "admin";
    private static final String DEFAULT_PASSWORD = "123456";

    private static final String ROOT_NAME = ROOT_OPTION.getName();
    private static final ConfigureOptions[] DEFAULT_OPTIONS = {BASE,DEFAULT_HEADERS,DEFAULT_COOKIES};

    public static String APPLICATION_FILE_PATH = DEFAULT_APPLICATION_FILE_PATH;
    public static String USERNAME = DEFAULT_USERNAME;
    public static String PASSWORD = DEFAULT_PASSWORD;
    public static ConfigureOptions[] OPTIONS = DEFAULT_OPTIONS;


    private static FundamentalConfigure instance = new FundamentalConfigure();

    private FundamentalConfigure(){

    }

    public static FundamentalConfigure getInstance(){
        return instance;
    }

    // 功能未完全实现，等实现实现了两个设计模式再次重写
    @Override
    public void configure(ApiObject apiObject){
        for(ConfigureOptions conf : OPTIONS){
            String targetKey = PathUtil.connectionByPoint(ROOT_NAME, conf.getName());
            Map<String,Object> resultMap = readApplicationByPoint(targetKey);
//            ApiObjectUtil.autoSetStringVariable(apiObject,resultMap);
        }
    }

    @Override
    protected void initApplicationMap(){
        try {
            applicationMap = ReadFileUtil.readYamlToMap(APPLICATION_FILE_PATH);
        }catch (IOException e){
           e.printStackTrace();
        }
    }

    @Override
    protected void initDefaultLoginMessage() {
        if(applicationMap != null){
            String targetKey = PathUtil.connectionByPoint(ROOT_NAME,
                    DEFAULT_LOGIN_MESSAGE.getName());
            Map<String,Object> resultMap = readApplicationByPoint(targetKey);
            loginInfo.changeLoginMessage((String) resultMap.get("username"), (String) resultMap.get("password"));
        }
        else {
            loginInfo.changeLoginMessage(USERNAME,PASSWORD);
        }
    }

//    private void initBase(ApiObject apiObject){
//        String targetKey = PathUtil.connectionByPoint(ROOT_NAME, BASE.getName());
//        Map<String,String> resultMap = readApplicationByPoint(targetKey);
//        autoInit(apiObject,resultMap);
//    }
//
//    private void initDefaultHeaders(ApiObject apiObject){
//        String targetKey = PathUtil.connectionByPoint(ROOT_NAME, DEFAULT_HEADERS.getName());
//        Map<String,String> resultMap = readApplicationByPoint(targetKey);
//        autoInit(apiObject,resultMap);
//    }
//
//    private void initDefaultCookies(ApiObject apiObject){
//        String targetKey = PathUtil.connectionByPoint(ROOT_NAME, DEFAULT_COOKIES.getName());
//        Map<String,String> resultMap = readApplicationByPoint(targetKey);
//        autoInit(apiObject,resultMap);
//    }

}
