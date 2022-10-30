package base;


import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

/**
 * @ClassName BaseTest
 * @Author AissEr
 * @Date 2022/10/18 17:57
 * @Version 1.0
 * @Description TODO
 **/
public class BaseTest {
    protected static final RequestSpecification given = AutoSetRestAssured.given;

    @BeforeAll
    public static void beforeClass(){

    }

}
