package eladmin.test;


import common.InterfaceRun;
import common.ResponseHandle;
import common.TestDataOptions;
import common.YamlMapper;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @ClassName SingleInterfaceTest
 * @Author AissEr
 * @Date 2022/10/18 17:57
 * @Version 1.0
 * @Description 对单接口进行测试
 **/

public class SingleInterfaceTest {
    private static Map<String,String> pathMap  = new HashMap<>();

    // 指定单接口文件基本路径
    private static final String BASE_PATH = "src/test/test-resource/data/single";
    private static final String ALL_SINGLE_INTERFACE_FILE_PATH
            = "src/test/test-resource/application/single-interface-file-path.yaml";

    @BeforeAll
    public static void beforeAll(){
        pathMap = (Map<String, String>) new YamlMapper(ALL_SINGLE_INTERFACE_FILE_PATH).getYamlMap();
    }

    /**
     * Junit5的动态测试，解决了困扰我1个月的问题
     * 该方法为：驱动测试方法，驱动核心代码的运行，调整好测试用例运行队列
     * @return
     */
    @TestFactory
    @DisplayName("单接口测试")
    Collection<DynamicContainer> single() {
        // 最终要返回给Junit5让其测试的“测试用例容器列表”
        ArrayList<DynamicContainer> result = new ArrayList<>(pathMap.size());
        // 所有单接口的配置文件，迭代取出
        pathMap.forEach((interfaceName,interfacePath) -> {
            // 读取单接口的配置文件，并配置好该但接口，等待运行
            InterfaceRun currentInterface = new InterfaceRun(BASE_PATH+interfacePath);
            // 设置该单接口测试用例列表，最终将列表放入测试用例容器中，即将所有用例放入容器
            List<DynamicTest> list = new ArrayList<>();
            // 获取配置文件的测试数据（每条测试用例），迭代取出
            currentInterface.getDefaultTestData().forEach(data -> {
                String describe = (String) data.get(TestDataOptions.DESCRIBE.keyName());
                /*
                 *  将该条测试用例，加入测试用例容器；
                 *  runTest方法，就是测试用例执行方法
                 *  DynamicTest.dynamicTest（）方法是创建一条测试用例
                 */
                list.add(DynamicTest.dynamicTest(interfaceName+":"+describe,() -> runTest(data,currentInterface)));
            });
            // 这个接口的数据都变成了测试用例，并放在同一个测试用例容器里，将容器放入容器列表中
            // 测试用例容器列表是给Junit5，让他帮我们测试的
            result.add(DynamicContainer.dynamicContainer(interfaceName,list));
        });
        // 将测试用例排序，因为有些用例需要顺序执行
        sortSingle(result);
        // 返回所有单接口的测试用例容器
        return result;
    }

    private void runTest(Map<String,Object> data,InterfaceRun currentInterface){
        ResponseHandle responseHandle = currentInterface.request(data.get(TestDataOptions.DATA.keyName()));
        Object excepted = data.get(TestDataOptions.EXCEPTED.keyName());
        if(excepted != null){
            responseHandle.verifyExcepted((Map<String,?>)excepted);
        }
    }

    private void sortSingle(ArrayList<DynamicContainer> dynamicContainers){
        // 排一次序，否则有可能是乱序的；编译器提示不准确，不能省略该代码！！！
        dynamicContainers.sort(
                (DynamicContainer d1, DynamicContainer d2) ->
                        d1.getDisplayName().compareTo(d2.getDisplayName())
        );
    }

}
