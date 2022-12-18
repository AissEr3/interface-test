package eladmin.test;

import common.YamlMapper;
import common.test.InterfaceRun;
import common.test.ResponseHandle;
import common.test.SetRestAssured;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @ClassName NotAuthorizationTest
 * @Author AissEr
 * @Date 2022/12/17 12:02
 * @Version 1.0
 * @Description TODO
 **/
@DisplayName("发送越权请求")
public class NotAuthorizationTest {
    private static Map<String,Object> pathMap  = new HashMap<>();

    // 指定单接口文件基本路径
    private static final String BASE_PATH = "src/test/test-resource/data/single";
    private static final String ALL_SINGLE_INTERFACE_FILE_PATH
            = "src/test/test-resource/data/notAuthorization/notAuthorizationData.yaml";

    @BeforeAll
    public static void initPathMap(){
        SetRestAssured.initGenernalConfigure();
        pathMap = (Map<String,Object>) new YamlMapper(ALL_SINGLE_INTERFACE_FILE_PATH).getYamlMap();
    }

    @AfterAll
    public static void exitApplicationUser(){
        SetRestAssured.exitSettingUser();
    }

    /**
     * 测试无权限用户能否发送越权请求
     */
    @DisplayName("发送越权请求")
    @ParameterizedTest
    @MethodSource("notAuthorizationData")
    void notAuthorizationTest(Map<String,Object> map) {
        Set<String> strings = map.keySet();
        for(String name : strings){
            Map<String,Object> realMap = (Map<String, Object>) map.get(name);
            String interfacePath = (String) realMap.get("url");
            InterfaceRun currentInterface = new InterfaceRun(BASE_PATH+interfacePath);
            currentInterface.setInterface().specifyUser("test","123456");
            ResponseHandle responseHandle = currentInterface.request(realMap.get("data"));
            responseHandle.verifyStatusCode(400);
            assertThat(responseHandle.getJsonObject("message")).isEqualTo("不允许访问");
            currentInterface.setInterface().logoutCurrentUser();
        }
    }

    static Collection<Map<String,Object>> notAuthorizationData(){
        return (List<Map<String,Object>>) pathMap.get("data");
    }
}
