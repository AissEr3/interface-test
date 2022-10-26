package api.configure;

import api.ApiObject;
import api.configure.strategy.StrategyFactory;
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
 * @Description 该类是配置测试接口的基础信息，所有接口都需要的配置信息
 **/
public class FundamentalConfigure extends GeneralConfigure{
    // 配置文件的默认路径
    private static final String DEFAULT_APPLICATION_FILE_PATH = "test:application/application-test.yaml";
    // 默认的登录信息
    private static final String DEFAULT_USERNAME = "admin";
    // 默认的登录密码
    private static final String DEFAULT_PASSWORD = "123456";

    // 指定配置文件的根名称
    private static final String ROOT_NAME = ROOT_OPTION.getName();
    // 指定要配置的配置项有哪些
    private static final ConfigureOptions[] DEFAULT_OPTIONS = {BASE, LOGIN_HEADERS, LOGIN_COOKIES};

    // 下面是开放的配置信息，使用者可以进行修改
    public static String APPLICATION_FILE_PATH = DEFAULT_APPLICATION_FILE_PATH;
    public static String USERNAME = DEFAULT_USERNAME;
    public static String PASSWORD = DEFAULT_PASSWORD;
    public static ConfigureOptions[] OPTIONS = DEFAULT_OPTIONS;

    // 单例模式的实例，配置全局配置信息，所有只有一个实例；
    // 必然会使用到该类，所以直接在最开始就加载资源
    private static FundamentalConfigure instance = new FundamentalConfigure();

    public FundamentalConfigure(){

    }

    public static FundamentalConfigure getInstance(){
        return instance;
    }

    /**
     * 执行配置操作，将配置文件的信息读读取到指定的接口对象
     *
     * @param apiObject 指定配置的是哪一个测试接口对象
     */
    @Override
    public void configure(ApiObject apiObject){
        for(ConfigureOptions opt : OPTIONS){
            String targetKey = PathUtil.connectionByPoint(ROOT_NAME, opt.getName());
            Map<String,Object> resultMap = readApplicationByPoint(targetKey);
            StrategyFactory.createStrategy(opt).alterConfigureContent(apiObject,resultMap);
        }
    }

    /**
     *  初始化配置文件信息，将配置文件转换为一个Map，方便操作
     */
    @Override
    protected void initApplicationMap(){
        try {
            applicationMap = ReadFileUtil.readYamlToMap(APPLICATION_FILE_PATH);
        }catch (IOException e){
           e.printStackTrace();
        }
    }

    @Override
    protected void initLoginMessage() {
        if(applicationMap != null) {
            String targetKey = PathUtil.connectionByPoint(ROOT_NAME,
                    DEFAULT_LOGIN_MESSAGE.getName());
            if (targetKey != null) {
                Map<String, Object> resultMap = readApplicationByPoint(targetKey);
                loginInfo.changeLoginMessage((String) resultMap.get("username"), (String) resultMap.get("password"));
            } else {
                loginInfo.changeLoginMessage(USERNAME, PASSWORD);
            }
            // 初始化loginInfo
            loginInfo.getValue();
        }
    }

}
