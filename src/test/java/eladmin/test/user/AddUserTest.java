package eladmin.test.user;

import common.BaseTest;
import common.InterfaceRun;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;

/**
 * @ClassName AddUserTest
 * @Author AissEr
 * @Date 2022/11/9 19:44
 * @Version 1.0
 * @Description TODO
 **/
public class AddUserTest extends BaseTest {
    @BeforeAll
    public static void setInterface(){
        currentInterface = new InterfaceRun(
                "src/test/test-resource/data/addUser.yaml");
    }

    public static List<Map<String,Object>> testData() {
        return currentInterface.getDefaultTestData();
    }

    @MethodSource("testData")
    @ParameterizedTest
    void addUserTest(Map<String,Object> data){
        currentInterface.request((Map<String, ?>) data.get("data")).then().log().all();
    }
}
