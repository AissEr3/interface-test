package common;

import api.ApiObject;
import api.SetRestAssured;
import api.configure.ConfigureOptions;
import api.configure.InterfaceConfigure;
import api.configure.strategy.StrategyFactory;
import api.manage.login.LoginResponseInfo;
import api.manage.login.LoginResponseInfoManager;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.stat.RequestType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName BaseInterface
 * @Author AissEr
 * @Date 2022/10/30 22:27
 * @Version 1.0
 * @Description
 **/
public class BaseInterface implements InterfaceTest{
    protected RequestSpecification given;
    protected Response response;
    protected LoginResponseInfo loginInfo;
    private final String interfaceConfigureFilePath;
    private ApiObject apiObject;
    private InterfaceConfigure interfaceConfigure;

    public BaseInterface(String interfaceConfigureFilePath){
        this.interfaceConfigureFilePath = interfaceConfigureFilePath;
        apiObject = new ApiObject();
        loadFileAndConfigure();
    }

    private void defaultConfigureGiven(){
        given = SetRestAssured.startSet()
                .defaultContentType()
                .defaultHeaders()
                .defaultCookies()
                .endSet();
    }

    // 使用SetRestAssured来设置given
    protected void configureGiven(){
        if(loginInfo == null){
            defaultConfigureGiven();
        }
        else {
            given = SetRestAssured.startSet()
                    .contentType(apiObject.getContentType())
                    .headers(apiObject.getHeaders())
                    .cookies(apiObject.getCookies())
                    .endSet();
        }
    }

    protected void loadFileAndConfigure(){
        interfaceConfigure = new InterfaceConfigure(interfaceConfigureFilePath);
        interfaceConfigure.initConfigure();
        interfaceConfigure.configure(apiObject);
    }

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

    protected void addData(Map<String,?> data){
        RequestType requestType = apiObject.getRequestType();
        if(requestType == RequestType.GET || requestType == RequestType.DELETE){
            given.params(data);
        }
        else if(requestType == RequestType.POST || requestType == RequestType.PUT){
            given.body(data);
        }
    }

    @Override
    public Response request() {
        return request(null);
    }

    @Override
    public Response request(Map<String,?> data) {
        configureGiven();
        if(data != null && data.size() != 0){
            addData(data);
        }
        runInterface();
        return response;
    }

    public void specifyUser(String username,String password){
        if(loginInfo == null){
            loginInfo = new LoginResponseInfoManager(username, password);
        }
        else {
            loginInfo.changeLoginMessage(username, password);
        }
        ConfigureLogin.cookies(apiObject,loginInfo);
        ConfigureLogin.header(apiObject,loginInfo);
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

    public void addHeader(String headerName, Object headerValue){
        apiObject.getHeaders().put(headerName,headerValue);
    }

    public void addCookie(String cookieName, Object cookieValue){
        apiObject.getCookies().put(cookieName,cookieValue);
    }

    // 将登录信息放入header或cookies里
    protected static class ConfigureLogin{
        private static final String[] HEADER_OPTIONS = {"Authorization"};
        private static final String[] COOKIES_OPTIONS = {"Pycharm-3339b356","remember","Idea-80e4ad09"};

        public static void header(ApiObject apiObject,LoginResponseInfo loginInfo){
            Map<String,String> value = new HashMap<>();
            Map<String, String> login = loginInfo.getValue();
            for(String opt : HEADER_OPTIONS){
                if(opt.equals("Authorization")){
                    value.put(opt,login.get(LoginResponseInfoManager.TOKEN_NAME));
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
