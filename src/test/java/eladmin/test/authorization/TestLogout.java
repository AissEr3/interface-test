package eladmin.test.authorization;


import common.BaseInterface;
import eladmin.api.usermanage.QueryUserInterface;

import common.BaseTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.kohsuke.rngom.parse.host.Base;

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
    static BaseInterface queryUserInterface = new BaseInterface("src/test/test-resource/data/addUser.yaml");

    @MethodSource("testData")
    @ParameterizedTest
    void test1(Map<String, Object> data){
        queryUserInterface.request(data).then().log().all();
    }

    public static List<Map<String, Object>> testData(){
        return queryUserInterface.getDefaultTestData();
    }
}
