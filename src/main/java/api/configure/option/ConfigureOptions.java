package api.configure.option;

/**
 * 简易约定、管理配置文件可配置项信息
 * 所有选项均非必填
 * 相当于一个静态资源类，且在运行时不可能有任何改变，因此使用枚举类
 */
public enum ConfigureOptions {

    /**
     * -----------基础信息配置项-----------
     */
    ROOT_OPTION("test-config",
             new String[] {"common","basePath","default-loginMessage","login-headers","login-cookies"}),

    BASE("base",
        new String[]{"baseURI","port","contentType"}),

    DEFAULT_LOGIN_MESSAGE("default-loginMessage",
            new String[]{"username","password"}),

    LOGIN_HEADERS("login-headers"),

    LOGIN_COOKIES("login-cookies"),

    /**
     * -----------单独接口信息配置项-----------
     */
    INTERFACE_INFO("interface-info",
            new String[]{"apiName","path","requestType","contentType","dataPlaceIn"});


    private String name;
    private String[] opts;

    ConfigureOptions(String name){
        this.name = name;
    }

    ConfigureOptions(String name,String[] opts){
        this(name);
        this.opts = opts;
    }

    public String getName() {
        return name;
    }

    public String[] getOptions() {
        return opts;
    }
}
