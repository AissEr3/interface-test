package common;

import api.ApiObject;
import api.configure.ConfigureOptions;
import api.configure.InterfaceConfigure;
import api.configure.strategy.StrategyFactory;
import api.manage.login.LoginResponseInfo;
import api.manage.login.LoginResponseInfoManage;
import com.sun.istack.NotNull;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.stat.RequestType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName InterfaceRun
 * @Author AissEr
 * @Date 2022/10/30 22:27
 * @Version 1.0
 * @Description 根据总配置文件信息，创建可运行的接口
 **/
public class InterfaceRun implements InterfaceTest{
    // 是否指定配置文件都会使用到的信息
    protected RequestSpecification given;
    protected Response response;
    protected LoginResponseInfo loginInfo;
    private ApiObject apiObject;

    // 如果指定配置文件才使用到的属性；
    // 文件路径即存储文件路径，也是判断是否使用配置文件的标志
    private final String INTERFACE_CONFIGURE_FILE_PATH;
    private InterfaceConfigure interfaceConfigure;

    public InterfaceRun(ApiObject apiObject){
        INTERFACE_CONFIGURE_FILE_PATH = null;
        this.apiObject = apiObject;
    }

    public InterfaceRun(String interfaceConfigureFilePath){
        this.INTERFACE_CONFIGURE_FILE_PATH = interfaceConfigureFilePath;
        apiObject = ApiObject.builder().build();
        loadFileAndApiObjectConfigure();
    }

    private void loadFileAndApiObjectConfigure(){
        interfaceConfigure = new InterfaceConfigure(INTERFACE_CONFIGURE_FILE_PATH);
        interfaceConfigure.initConfigure();
        interfaceConfigure.configure(apiObject);
    }

    public List<Map<String, Object>> getDefaultTestData(){
        if(INTERFACE_CONFIGURE_FILE_PATH != null){
            return interfaceConfigure.getTestData();
        }
        return null;
    }

    // 使用SetRestAssured来设置given
    private void configureGiven(){
        if(loginInfo == null || INTERFACE_CONFIGURE_FILE_PATH == null){
            given = SetRestAssured.startSet()
                    .defaultContentType()
                    .defaultHeaders()
                    .defaultCookies()
                    .endSet();
            // 如果自己指定了
            if(apiObject.getContentType() != null && !apiObject.getContentType().equals("")){
                given.contentType(apiObject.getContentType());
            }
        }
        else{
            given = SetRestAssured.startSet()
                    .contentType(apiObject.getContentType())
                    .headers(apiObject.getHeaders())
                    .cookies(apiObject.getCookies())
                    .endSet();
        }
    }

    private void addData(Map<String,?> data){
        String dataPlace = apiObject.getDataPlaceIn();
        if(dataPlace.equals("body")){
            given.body(data);
        }
        else if(dataPlace == null || dataPlace.equals("") || dataPlace.equals("query")){
            given.params(data);
        }
    }

    private void runInterface(){
        // java switch新特性（jdk14及以上）
        String path = apiObject.getPath();
        response = switch (apiObject.getRequestType()){
            case GET -> given.get(path);
            case POST -> given.post(path);
            case PUT ->  given.put(path);
            case DELETE -> given.delete(path);
        };
    }

    @Override
    public Response request() {
        configureGiven();
        runInterface();
        return response;
    }

    @Override
    public Response request(Object data){
        configureGiven();
        given.body(data);
        runInterface();
        return response;
    }

    @Override
    public Response request(Map<String,?> data) {
        configureGiven();
        addData(data);
        runInterface();
        return response;
    }

    public void specifyUser(String username,String password){
        if(loginInfo == null){
            loginInfo = new LoginResponseInfoManage(username, password);
        }
        else {
            loginInfo.changeLoginMessage(username, password);
        }
        ConfigureLogin.cookies(apiObject,loginInfo);
        ConfigureLogin.header(apiObject,loginInfo);
    }

    public void changeDefaultUser(){
        loginInfo = null;
    }

    public Response getResponse() {
        return response;
    }

    public void addHeader(String headerName, Object headerValue){
        apiObject.getHeaders().put(headerName,headerValue);
    }

    public void addCookie(String cookieName, Object cookieValue){
        apiObject.getCookies().put(cookieName,cookieValue);
    }

    // 将登录信息放入header或cookies里
    private static class ConfigureLogin{
        private static final String[] HEADER_OPTIONS = {"Authorization"};
        private static final String[] COOKIES_OPTIONS = {"Pycharm-3339b356","remember","Idea-80e4ad09"};

        public static void header(ApiObject apiObject,LoginResponseInfo loginInfo){
            Map<String,String> value = new HashMap<>();
            Map<String, String> login = loginInfo.getValue();
            for(String opt : HEADER_OPTIONS){
                if(opt.equals("Authorization")){
                    value.put(opt,login.get(LoginResponseInfoManage.TOKEN_NAME));
                }
                else {
                    value.put(opt,login.get(opt));
                }
            }
            StrategyFactory.createStrategy(ConfigureOptions.LOGIN_HEADERS).alterConfigureContent(apiObject,value);
        }

        public static void cookies(ApiObject apiObject,LoginResponseInfo loginInfo){
            Map<String,String> value = new HashMap<>();
            Map<String, String> login = loginInfo.getValue();
            for(String opt : COOKIES_OPTIONS){
                value.put(opt,login.get(opt));
            }
            StrategyFactory.createStrategy(ConfigureOptions.LOGIN_COOKIES).alterConfigureContent(apiObject,value);
        }
    }
}
