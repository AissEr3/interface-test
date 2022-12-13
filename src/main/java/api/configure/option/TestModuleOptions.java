package api.configure.option;

/**
 * @ClassName TestModuleOptions
 * @Author AissEr
 * @Date 2022/12/11 19:15
 * @Version 1.0
 * @Description
 **/
public enum TestModuleOptions {
    TEST_MODULE("test-module",
            new String[]{"request-parameter-template","single-test-data"}),

    REQUEST_PARAMETER_TEMPLATE("request-parameter-template"),

    EACH_SETTING("each-before-setting"),

    SINGLE_TEST_DATA("single-test-data",
            new String[]{"describe","data","excepted"}),
        DESCRIBE("describe"),
        DATA("data"),
        EXCEPTED("excepted"),
        ADD_RELEVANCE_DATA("addRelevanceData");


    private String name;
    private String[] opts;

    TestModuleOptions(String name){
        this.name = name;
    }

    TestModuleOptions(String name, String[] opts){
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
