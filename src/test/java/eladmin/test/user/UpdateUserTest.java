package eladmin.test.user;

import api.ApiObject;
import common.BaseTest;
import common.InterfaceRun;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;

/**
 * @ClassName UpdateUserTest
 * @Author AissEr
 * @Date 2022/11/9 19:10
 * @Version 1.0
 * @Description TODO
 **/
public class UpdateUserTest extends BaseTest {
    @BeforeAll
    public static void setInterface(){
        currentInterface = new InterfaceRun(
                "src/test/test-resource/data/updateUser.yaml");
    }

    public static List<Map<String,Object>> testData() {
        return currentInterface.getDefaultTestData();
    }

    @MethodSource("testData")
    @ParameterizedTest
    void updateUserTest(Map<String,Object> data){
        currentInterface.request((Map<String, ?>) data.get("data")).then().log().all();
    }
}
