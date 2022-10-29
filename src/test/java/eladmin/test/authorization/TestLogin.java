package eladmin.test.authorization;

import api.ApiObject;
import api.configure.FundamentalConfigure;
import api.configure.InterfaceConfigure;
import io.restassured.http.ContentType;
import org.junit.Ignore;
import org.junit.jupiter.api.*;

import api.manage.login.LoginJSON;
import api.manage.login.LoginResponseInfo;
import api.manage.login.LoginResponseInfoManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import static io.restassured.RestAssured.*;

/**
 * @ClassName TestLogin
 * @Author AissEr
 * @Date 2022/10/16 20:51
 * @Version 1.0
 * @Description TODO
 **/
@Disabled
public class TestLogin{

    @Test
    @Order(1)
    void testRightLogin() throws Exception{
        LoginJSON loginJSON = new LoginJSON("admin","123456");

        given().
        contentType(ContentType.JSON).
                body(loginJSON).
        when().
                post("http://localhost:8000/auth/login").
        then().
                log().all();
    }

    @Test
    @Order(3)
    @Ignore
    void testRightLogout(){
        LoginResponseInfo info = new LoginResponseInfoManager("admin","123456");
        Map<String, String> loginInfo = info.getValue();
        given()
                .headers("Authorization",loginInfo.get("ELADMIN-TOKEN"))
                .cookies(loginInfo)
        .when().delete("http://localhost:8000/auth/logout").then().log().all();
    }

    @Test
    @Order(2)
    void testCode(){
        when().get("http://localhost:8000/auth/code").then().log().all();
    }

    @Test
    @Order(2)
    void testRightInfo(){
        LoginResponseInfo info = new LoginResponseInfoManager("admin","123456");
        Map<String, String> loginInfo = info.getValue();
        given()
                .headers("Authorization",loginInfo.get("ELADMIN-TOKEN"))
                .cookies(loginInfo).log().all()
        .when()
                .get("http://localhost:8000/auth/info")
        .then()
                .log().all();
    }

    @Test
    public void test001() throws IOException {
        ApiObject apiObject = new ApiObject();

        FundamentalConfigure fundamentalConfigure = FundamentalConfigure.getInstance();
        fundamentalConfigure.initConfigure();
        fundamentalConfigure.configure(apiObject);
        InterfaceConfigure interfaceConfigure = new InterfaceConfigure("test:data/login.yaml");
        interfaceConfigure.initConfigure();
        interfaceConfigure.configure(apiObject);
        System.out.println(apiObject.toString());

        ArrayList<Map<String, Object>> testData = interfaceConfigure.getTestData();

        Map<String, String> loginInfo = fundamentalConfigure.getLoginInfo().getValue();
        given()
                .headers("Authorization",loginInfo.get("ELADMIN-TOKEN"))
                .cookies(loginInfo)
                .queryParams(testData.get(0)).log().all()
        .when().get("http://localhost:8000/api/users").then().log().all();
    }
}
