package base;


import api.SetRestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

/**
 * @ClassName BaseTest
 * @Author AissEr
 * @Date 2022/10/18 17:57
 * @Version 1.0
 * @Description TODO
 **/
public class BaseTest {

    @BeforeAll
    public static void beforeAll(){
        SetRestAssured.initFundamentalConfigure();
    }

}
