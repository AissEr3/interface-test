package api.configure;

import api.ApiObject;
import api.configure.option.ConfigureOptions;
import api.configure.strategy.StrategyFactory;
import api.manage.login.LoginResponseInfoManage;
import common.YamlMapper;
import util.MapUtil;
import util.PathUtil;

import java.util.Map;
import static api.configure.option.ConfigureOptions.*;

/**
 * @ClassName GeneralConfigure
 * @Author AissEr
 * @Date 2022/10/20 22:14
 * @Version 1.0
 * @Description 该类是配置测试接口的基础信息，所有接口都需要的配置信息
 **/
public class GeneralConfigure extends AbstractConfigure {
    // 配置文件的默认路径
    private static final String DEFAULT_APPLICATION_FILE_PATH
            = "src/test/test-resource/application/application-test-admin.yaml";
    // 默认的登录信息
    private static final String DEFAULT_USERNAME = "admin";
    // 默认的登录密码
    private static final String DEFAULT_PASSWORD = "123456";

    // 指定配置文件的根名称
    private static final String ROOT_NAME = ROOT_OPTION.getName();
    // 指定要配置的配置项有哪些
    private static final ConfigureOptions[] DEFAULT_OPTIONS = {BASE, LOGIN_HEADERS, LOGIN_COOKIES};

    // 下面是开放的配置信息，使用者可以进行修改
    public static String applicationFilePath = DEFAULT_APPLICATION_FILE_PATH;
    public static String username = DEFAULT_USERNAME;
    public static String password = DEFAULT_PASSWORD;
    public static ConfigureOptions[] options = DEFAULT_OPTIONS;

    // 单例模式的实例，配置全局配置信息，所有只有一个实例；
    // 必然会使用到该类，所以直接在最开始就加载资源
    private static GeneralConfigure instance = new GeneralConfigure();

    private GeneralConfigure(){
    }

    public static GeneralConfigure getInstance(){
        return instance;
    }

    /**
     * 执行配置操作，将配置文件的信息读读取到指定的接口对象
     *
     * @param apiObject 指定配置的是哪一个测试接口对象
     */
    @Override
    public void configure(ApiObject apiObject){
        if(applicationMap != null){
            for(ConfigureOptions opt : options){
                String targetKey = PathUtil.connectionByPoint(ROOT_NAME, opt.getName());
                Map<String,Object> resultMap = (Map<String, Object>) MapUtil.readMapByPoint(applicationMap, targetKey);
                if(resultMap != null){
                    StrategyFactory.createStrategy(opt).alterConfigureContent(apiObject,resultMap);
                }
            }
        }
    }

    /**
     *  初始化配置文件信息，将配置文件转换为一个Map，方便操作
     */
    @Override
    protected void initApplicationMap(){
        applicationMap = new YamlMapper(applicationFilePath).getYamlMap();
    }

    /**
     * 该配置类需要重置登录信息，因此需要重写登录方法
     */
    @Override
    protected void initLoginMessage() {
        defaultLoginInfo = new LoginResponseInfoManage();
        if(applicationMap != null) {
            String targetKey = PathUtil.connectionByPoint(ROOT_NAME,
                    DEFAULT_LOGIN_MESSAGE.getName());
            if (targetKey != null) {
                Map<String, Object> resultMap = (Map<String, Object>) MapUtil.readMapByPoint(applicationMap, targetKey);
                defaultLoginInfo.changeLoginMessage((String) resultMap.get("username"), (String) resultMap.get("password"));
            } else {
                defaultLoginInfo.changeLoginMessage(username, password);
            }
            // 初始化loginInfo
            defaultLoginInfo.getValue();
        }
    }

    public static void exitCurrentUser(){
        defaultLoginInfo.exitThisUser();
    }
}
