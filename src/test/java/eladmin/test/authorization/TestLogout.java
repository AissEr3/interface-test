package eladmin.test.authorization;


import eladmin.api.usermanage.QueryUserInterface;

import base.BaseTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @ClassName TestLogout
 * @Author AissEr
 * @Date 2022/10/16 22:07
 * @Version 1.0
 * @Description TODO
 **/
public class TestLogout extends BaseTest {
    static QueryUserInterface queryUserInterface = new QueryUserInterface("src/test/test-resource/data/login.yaml");

    @MethodSource("testData")
    @ParameterizedTest
    void test1(Map<String, Object> data){
        queryUserInterface.request(data).then().log().all();
    }

    public static List<Map<String, Object>> testData(){
        return queryUserInterface.getDefaultTestData();
    }
}
