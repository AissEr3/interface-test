package eladmin.test.user;

import common.InterfaceRun;
import common.RelevanceVariable;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;

/**
 * @ClassName TestUserTest
 * @Author AissEr
 * @Date 2022/11/9 19:44
 * @Version 1.0
 * @Description TODO
 **/
@Disabled
public class TestUserTest {
    protected static InterfaceRun currentInterface;

    @BeforeAll
    public static void setInterface(){
        currentInterface = new InterfaceRun(
                "src/test/test-resource/data/single/user/updateUser.yaml");
    }

    public static List<Map<String,?>> testData() {
        return currentInterface.getSingleTestData();
    }
    
    @MethodSource("testData")
    @ParameterizedTest
    void addUserTest(Map<String,Object> data){
        currentInterface.request(data.get("data")).then().log().all();
        System.out.println(RelevanceVariable.relevanceData);
    }
}
