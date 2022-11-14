package api.configure;

/**
 * 简易约定、管理配置文件可配置项信息
 * 所有选项均非必填
 */
public enum ConfigureOptions {

    /**
     * 基础信息配置项
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
     * 单独接口信息配置项
     */
    INTERFACE_INFO("interface-info",
            new String[]{"path","requestType","contentType","dataPlaceIn"}),

    TEST_DATA("default-test-data"),

    JSON_SCHEME("json-schema");


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
