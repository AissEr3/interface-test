package eladmin.test.authorization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import org.junit.Ignore;
import org.junit.jupiter.api.*;

import api.manage.LoginJSON;
import api.manage.info.LoginResponseInfo;
import api.manage.info.LoginResponseInfoManager;
import utils.PathUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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

//    @Test
//    @Order(3)
//    void testRightLogout(){
//        LoginResponseInfo info = new LoginResponseInfoManager("admin","123456");
//        Map<String, String> loginInfo = info.getValue();
//        given()
//                .headers("Authorization",loginInfo.get("ELADMIN-TOKEN"))
//                .cookies(loginInfo)
//        .when().delete("http://localhost:8000/auth/logout").then().log().all();
//    }

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
    @Ignore
    public void test001() throws IOException {
        ObjectMapper mapper = new YAMLMapper();
        HashMap<String,Object> hashMap = mapper.readValue(new File(PathUtil.realPath("test:data/login.yaml")), new HashMap<String,Object>().getClass());
        ArrayList testData = (ArrayList)hashMap.get("testData");

        LoginResponseInfo info = new LoginResponseInfoManager("admin","123456");
        Map<String, String> loginInfo = info.getValue();
        given()
                .headers("Authorization",loginInfo.get("ELADMIN-TOKEN"))
                .cookies(loginInfo)
                .queryParams((Map<String, String>) testData.get(0)).log().all()
        .when().get("http://localhost:8000/api/users").then().log().all();
    }
}
