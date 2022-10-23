package eladmin.test.authorization;

import api.ApiObject;
import api.configure.FundamentalConfigure;
import org.junit.jupiter.api.Test;
import eladmin.base.BaseTest;

/**
 * @ClassName TestLogout
 * @Author AissEr
 * @Date 2022/10/16 22:07
 * @Version 1.0
 * @Description TODO
 **/
public class TestLogout extends BaseTest {

    @Test
    void test1(){
        ApiObject apiObject = new ApiObject();
        FundamentalConfigure base = FundamentalConfigure.getInstance();
        base.configure(apiObject);
        System.out.println(apiObject.toString());
    }

}
