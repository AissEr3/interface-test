package eladmin.test.authorization;

import api.ApiObject;
import api.manage.ConfigureManager;
import api.test.TestRunner;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Ignore;
import org.junit.jupiter.api.*;

import api.manage.login.LoginJSON;
import api.manage.login.LoginResponseInfo;
import api.manage.login.LoginResponseInfoManager;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.List;
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

        ConfigureManager configureManager = new ConfigureManager(apiObject,"test:data/login.yaml");
        configureManager.initConfigureClass();
        configureManager.setConfigureInfo();

        List<Map<String, Object>> testData = configureManager.getTestData();

        Map<String, String> loginInfo = configureManager.getLoginInfo().getValue();
        given()
                .headers("Authorization",loginInfo.get("ELADMIN-TOKEN"))
                .cookies(loginInfo)
                .queryParams(testData.get(0))
        .when().get("http://localhost:8000/api/users").then().log().all();
    }


}
