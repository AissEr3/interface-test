package eladmin.test;

import common.*;
import api.configure.option.TestModuleOptions;
import common.test.InterfaceRun;
import common.test.RelevanceVariable;
import common.test.ResponseHandle;
import common.test.SetRestAssured;
import io.restassured.path.json.exception.JsonPathException;
import org.junit.jupiter.api.*;

import java.util.*;

/**
 * @ClassName SingleInterfaceTest
 * @Author AissEr
 * @Date 2022/10/18 17:57
 * @Version 1.0
 * @Description 对单接口请求参数测试
 **/
@DisplayName("单接口请求参数测试")
public class SingleInterfaceTest {
    private static Map<String,String> pathMap  = new HashMap<>();

    // 指定单接口文件基本路径
    private static final String BASE_PATH = "src/test/test-resource/data/single";
    private static final String ALL_SINGLE_INTERFACE_FILE_PATH
            = "src/test/test-resource/application/single-interface-file-path.yaml";

    @BeforeAll
    public static void initPathMap(){
        SetRestAssured.initGenernalConfigure();
        pathMap = (Map<String, String>) new YamlMapper(ALL_SINGLE_INTERFACE_FILE_PATH).getYamlMap();
    }

    @AfterAll
    public static void exitApplicationUser(){
        SetRestAssured.exitSettingUser();
    }

    /**
     * Junit5的动态测试
     * 该方法为：驱动测试方法，驱动核心代码的运行，调整好测试用例运行队列
     * @return
     */
    @TestFactory
    @DisplayName("单接口请求参数测试")
    Collection<DynamicContainer> single() {
        // 最终要返回给Junit5让其测试的“测试用例容器列表”
        ArrayList<DynamicContainer> result = new ArrayList<>(pathMap.size());
        // 所有单接口的配置文件路径，迭代取出
        pathMap.forEach((interfaceName,interfacePath) -> {
            // 读取单接口的配置文件，并配置好该但接口，等待运行
            InterfaceRun currentInterface = new InterfaceRun(BASE_PATH+interfacePath);
            // 设置该单接口测试用例列表，最终将列表放入测试用例容器中，即将所有用例放入容器
            List<DynamicTest> list = new ArrayList<>();
            currentInterface.getCurrentInterfaceTestData().forEach(data -> {
                String describe = (String) data.get(TestModuleOptions.DESCRIBE.getName());
                /*
                 *  将该条测试用例，加入测试用例容器；
                 *  runTest方法，就是测试用例执行方法
                 *  DynamicTest.dynamicTest（）方法是创建一条测试用例
                 */
                list.add(DynamicTest.dynamicTest(interfaceName+"："+describe,() -> runTest(data,currentInterface)));
            });
            // 这个接口的数据都变成了测试用例，并放在同一个测试用例容器里，将容器放入容器列表中
            result.add(DynamicContainer.dynamicContainer(interfaceName,list));
        });
        sortSingleTest(result);
        return result;
    }

    // 执行测试
    private static void runTest(Map<String,?> data,InterfaceRun currentInterface){
        // 1.发送请求
        ResponseHandle responseHandle = currentInterface.request(data.get(TestModuleOptions.DATA.getName()));

        // 2.检查是否需要关联数据
        Map<String,Object> relevance = (Map<String, Object>)
                data.get(TestModuleOptions.ADD_RELEVANCE_DATA.getName());
        if(relevance != null){
            Set<String> keys = relevance.keySet();
            for(String key : keys){
                try{
                    Object jsonObject = responseHandle.getJsonObject(key);
                    if(jsonObject != null){
                        RelevanceVariable.addRelevanceVariable((String) relevance.get(key),jsonObject);
                    }
                    else {
                        RelevanceVariable.addRelevanceVariable(key,
                                RelevanceVariable.replaceByRelevanceVariable((String) relevance.get(key)));
                    }
                } catch (JsonPathException e){
                        RelevanceVariable.addRelevanceVariable(key,
                                RelevanceVariable.replaceByRelevanceVariable((String) relevance.get(key)));
                }
            }
        }

        // 3.验证断言是否通过
        Object excepted = data.get(TestModuleOptions.EXCEPTED.getName());
        if(excepted != null){
            responseHandle.verifyExcepted((Map<String,?>) excepted);
        }
    }

    private static void sortSingleTest(ArrayList<DynamicContainer> dynamicContainers){
        // 排一次序，否则有可能是乱序的；编译器提示不准确，不能省略该代码！！！
        dynamicContainers.sort(
                (DynamicContainer d1, DynamicContainer d2) ->
                        d1.getDisplayName().compareTo(d2.getDisplayName())
        );
    }
}
