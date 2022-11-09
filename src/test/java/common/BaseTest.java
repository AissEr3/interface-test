package common;


import org.junit.jupiter.api.BeforeAll;

import java.util.List;
import java.util.Map;

/**
 * @ClassName BaseTest
 * @Author AissEr
 * @Date 2022/10/18 17:57
 * @Version 1.0
 * @Description TODO
 **/
public abstract class BaseTest {
    protected static String path;
    protected static InterfaceRun currentInterface;

    @BeforeAll
    public static void beforeAll(){
        SetRestAssured.initFundamentalConfigure();
    }
}
