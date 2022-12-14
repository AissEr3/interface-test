package common;

import api.ApiObject;
import api.configure.option.ConfigureOptions;
import api.configure.InterfaceConfigure;
import api.configure.option.TestModuleOptions;
import api.configure.strategy.StrategyFactory;
import api.manage.login.LoginResponseInfo;
import api.manage.login.LoginResponseInfoManage;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName InterfaceRun
 * @Author AissEr
 * @Date 2022/10/30 22:27
 * @Version 1.0
 * @Description 根据总配置文件信息，创建可运行的接口
 *              该类是接口，发送请求的类
 *              自动配置运行的接口信息，根据apiObject指定的信息来对指定接口发送请求
 *              是接口测试的核心类
 **/
public class InterfaceRun implements InterfaceTest{
    // 是否指定配置文件都会使用到的信息
    protected RequestSpecification given;
    protected LoginResponseInfo loginInfo;
    private ApiObject apiObject;

    // 如果指定配置文件才使用到的属性；
    // 文件路径即存储文件路径，也是判断是否使用配置文件的标志
    private final String INTERFACE_CONFIGURE_FILE_PATH;
    protected InterfaceConfigure interfaceConfigure;

    /**
     *  在运行前保证设置全局变量的信息已经初始化，且在运行时只用初始化一次
     */
    static{
        SetRestAssured.initFundamentalConfigure();
    }

    /**
     * 使用自定义接口信息运行接口
     * @param apiObject 配置的信息
     */
    public InterfaceRun(ApiObject apiObject){
        INTERFACE_CONFIGURE_FILE_PATH = null;
        this.apiObject = apiObject;
    }

    /**
     * 使用配置文件的信息运行接口
     * @param interfaceConfigureFilePath 配置文件的路径
     */
    public InterfaceRun(String interfaceConfigureFilePath){
        this.INTERFACE_CONFIGURE_FILE_PATH = interfaceConfigureFilePath;
        apiObject = ApiObject.builder().build();
        interfaceConfigure = new InterfaceConfigure(INTERFACE_CONFIGURE_FILE_PATH);
        loadFileAndApiObjectConfigure();
    }

    public InterfaceRun(File file){
        this.INTERFACE_CONFIGURE_FILE_PATH = "has";
        apiObject = ApiObject.builder().build();
        interfaceConfigure = new InterfaceConfigure(file);
        loadFileAndApiObjectConfigure();
    }

    /**
     * 加载指定的配置文件信息，并将配置文件的信息配置到属性ApiObject里
     */
    private void loadFileAndApiObjectConfigure(){
        interfaceConfigure.initConfigure();
        interfaceConfigure.configure(apiObject);
    }

    /**
     * 获取配置文件中的测试数据，此测试数据是用来测试单接口的
     * @return 单接口测试数据
     */
    public List<Map<String, ?>> getSingleTestData(){
        if(INTERFACE_CONFIGURE_FILE_PATH != null){
            return interfaceConfigure.getTestData();
        }
        return null;
    }

    /**
     * @param data 接口请求需要的参数，可以为null——null代表为空
     * @param <T> 如果数据在body里，可以任意类型；如果数据在其他地方，只能使用Map<String,?>
     */
    @Override
    public <T> ResponseHandle request(T data){
        configureGiven();
        if(data != null){
            addData(data);
        }
        return new ResponseHandle(runInterface());
    }

    /**
     * 使用SetRestAssured来设置given
     * 将APIObject的信息，通过SetRestAssured类配置好，并得到配置好的given
     */
    private void configureGiven(){
        if(loginInfo == null || INTERFACE_CONFIGURE_FILE_PATH == null){
            given = SetRestAssured.startSet().defaultContentType().defaultHeaders()
                    .defaultCookies().endSet();
            // 如果自己指定ContentType
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

    /**
     * 添加数据方法，将数据放在正确的地方
     * @param data 接口需要的数据
     * 缺少自定义异常
     */
    private<T> void addData(T data){
        String dataPlace = apiObject.getDataPlaceIn();
        if(dataPlace.equals("body") && data instanceof String){
            given.body(RelevanceVariable.replaceByRelevanceVariable((String) data));
        }
        // params可以匹配除body方法以外的所有方法
        else if(data instanceof Map){
            RelevanceVariable.replaceByRelevanceVariable((Map<String,Object>) data);
            given.params((Map<String,?>) data);
        }
        else {
            throw new RuntimeException("data type must be map");
        }
    }

    /**
     * 运行接口，根据指定的请求类型运行接口，没有指定默认发送get请求
     * @return 返回对应的响应处理类
     */
    private Response runInterface(){
        String path = RelevanceVariable.replaceByRelevanceVariable(apiObject.getPath());
        // java switch新特性（jdk14及以上）
        Response response = switch (apiObject.getRequestType()){
            case GET -> given.get(path);
            case POST -> given.post(path);
            case PUT ->  given.put(path);
            case DELETE -> given.delete(path);
            default -> given.get(path);
        };
        return response;
    }

    /**
     * 如果在运行时需要设置接口，可以调用该方法
     * @return 返回开放的接口信息设置类
     */
    public SettingRun setInterface(){
        return new SettingRun();
    }

    /**
     * 内部类，允许用户在使用时修改部分信息
     * 通过内部类来管理，这需要高度依赖运行类，所有使用内部类
     */
    public class SettingRun{
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

        public void changeToDefaultUser(){
            loginInfo = null;
        }

        public void addHeader(String headerName, Object headerValue){
            apiObject.getHeaders().put(headerName,headerValue);
        }

        public void addCookie(String cookieName, Object cookieValue){
            apiObject.getCookies().put(cookieName,cookieValue);
        }

        // 如果修改了用户，将登录信息放入header或cookies里
        // 自定义，针对该项目设置的
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
}
