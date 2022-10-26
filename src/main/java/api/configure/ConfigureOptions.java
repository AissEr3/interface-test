package api.configure;

/**
 * 简易约定、管理配置文件可配置项信息
 * 所有选项均非必填
 */
public enum ConfigureOptions {
    ROOT_OPTION("test-config",
             "options: base | default-loginMessage | default-headers | default-cookies"),

    BASE("base",
        "options: baseURI | port | contentType"),

    DEFAULT_LOGIN_MESSAGE("default-loginMessage",
            "options: username | password "),

    LOGIN_HEADERS("login-headers",
            "options: header1 | header2 | header3 | ......"),

    LOGIN_COOKIES("login-cookies",
            "options: cookie1 | cookie2 | cookie3 | ......");


    private String name;
    private String describe;

    ConfigureOptions(String name,String describe){
        this.name = name;
        this.describe = describe;
    }

    public String getName() {
        return name;
    }

    public String getDescribe() {
        return describe;
    }
}
