package eladmin.test.authorization;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.use.common.annotation.InterfaceTest;
import test.use.common.test.BaseTest;

import static io.restassured.RestAssured.*;

/**
 * @ClassName TestLogout
 * @Author AissEr
 * @Date 2022/10/16 22:07
 * @Version 1.0
 * @Description TODO
 **/
public class TestLogout extends BaseTest {

    @BeforeEach
    public void beforeEach2(){
        System.out.println("each 2");
    }

    @InterfaceTest
    void test1(){
        System.out.println("test1");
    }

    @InterfaceTest
    void test2(){
        System.out.println("test2");
    }

}
