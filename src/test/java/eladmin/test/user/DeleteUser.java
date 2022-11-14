package eladmin.test.user;

import common.BaseTest;
import common.InterfaceRun;
import common.SetRestAssured;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;

/**
 * @ClassName DeleteUser
 * @Author AissEr
 * @Date 2022/11/9 20:16
 * @Version 1.0
 * @Description TODO
 **/
public class DeleteUser extends BaseTest {
    @BeforeAll
    public static void setInterface(){
        currentInterface = new InterfaceRun(
                "src/test/test-resource/data/deleteUser.yaml");
    }

    public static List<Map<String,Object>> testData() {
        return currentInterface.getDefaultTestData();
    }

    @MethodSource("testData")
    @ParameterizedTest
    void queryUserTest(Map<String,Object> data){
        currentInterface.request(data.get("data")).then().log().all();
    }
}
