package eladmin.test.authorization;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import test.use.config.RsaProperties;
import test.use.manage.info.LoginResponseInfo;
import test.use.manage.info.LoginResponseInfoManager;

import java.util.Map;

import static io.restassured.RestAssured.*;

/**
 * @ClassName TestLogin
 * @Author AissEr
 * @Date 2022/10/16 20:51
 * @Version 1.0
 * @Description TODO
 **/

public class TestLogin {
    private static final String PUBLIC_KEY = RsaProperties.getPublicKey();

    @Test
    @Order(1)
    void testRightLogin() throws Exception{
        LoginResponseInfo info = new LoginResponseInfoManager("admin","123456");
        Map<String, String> loginInfo = info.getLoginInfo();
        System.out.println(loginInfo.toString());
        info.changeLoginResponseInfo("test","123456");
        loginInfo = info.getLoginInfo();
        System.out.println(loginInfo.toString());
    }


    @Test
    @Order(3)
    void testRightLogout(){
        when().delete("http://localhost:8000/auth/logout").then().log().all();
    }

    @Test
    @Order(2)
    void testRightCode(){
        when().get("http://localhost:8000/auth/code").then().log().all();
    }
}
