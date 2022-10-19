package test.use.common.test;

import org.junit.AfterClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;


/**
 * @ClassName BaseTest
 * @Author AissEr
 * @Date 2022/10/18 17:57
 * @Version 1.0
 * @Description TODO
 **/
public class BaseTest {

    @BeforeAll
    public static void beforeClass(){
        System.out.println("before Class");
    }

    @BeforeEach
    public void beforeEach1(){
        System.out.println("each 1");
    }

    public BaseTest(){
        System.out.println("BaseTest Run");
    }


}
