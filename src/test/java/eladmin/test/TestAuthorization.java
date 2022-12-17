package eladmin.test;

import common.test.*;
import org.junit.jupiter.api.*;

import api.manage.login.LoginJSON;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.*;

/**
 * @ClassName TestAuthorization
 * @Author AissEr
 * @Date 2022/10/16 20:51
 * @Version 1.0
 * @Description TODO
 **/
@DisplayName("登录、登出授权测试")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestAuthorization {
    private static InterfaceTest loginInterface;
    private static InterfaceTest getCodeInterface;

    @BeforeAll
    public static void initAllAuthorizationInterface(){
        // 创建获取验证码的接口
        getCodeInterface = data -> new ResponseHandle(
                when().get("http://localhost:8000/auth/code")
        );

        // 设置登录接口
        loginInterface = data -> new ResponseHandle(
                given().contentType("application/json").body(data)
                        .when().post("http://localhost:8000/auth/login")
        );
    }

    @Test
    @Order(1)
    @DisplayName("获取验证码测试")
    void testCode(){
        String uuid = (String) getCodeInterface.request(null).getJsonObject("uuid");
        assertThat(uuid).contains("code-key");
    }


    @Order(2)
    @DisplayName("登录成功测试")
    @ParameterizedTest
    @CsvFileSource(resources = "/data/authorization/rightLogin.csv",numLinesToSkip = 1)
    void testRightLogin(String username,String password) {
        // 测试代码
        LoginJSON loginJSON = new LoginJSON(username,password);
        ResponseHandle loginHandle = loginInterface.request(loginJSON);
        loginHandle.verifyStatusCode(200);
        assertThat((String) loginHandle.getJsonObject("user.user.username")).isEqualTo(username);

        // 测试通过将token放入全局变量库中，一个用户名对应一个token
        String token = (String) loginHandle.getJsonObject("token");
        RelevanceVariable.addRelevanceVariable(username,token);
    }

    @Order(3)
    @DisplayName("登录失败测试")
    @ParameterizedTest
    @CsvFileSource(resources = "/data/authorization/errorLogin.csv",numLinesToSkip = 1)
    void testErrorLogin(String username,String password,String code,String uuid,String message){
        LoginJSON loginJSON = new LoginJSON(username,password);
        loginJSON.setCode(code);
        loginJSON.setUuid(uuid);
        ResponseHandle loginHandle = loginInterface.request(loginJSON);
        loginHandle.verifyStatusCode(400);
        assertThat(loginHandle.getJsonObject("message")).isEqualTo(message);
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("已经测完登录")
    class AlreadyLoginTest{
        private static String token;

        @BeforeAll
        public static void setToken(){
            token = (String) RelevanceVariable.getRelevanceVariable("admin");
        }

        @Test
        @Order(2)
        @DisplayName("测试查询登录信息接口")
        void testRightInfo(){
            ResponseHandle userInfoInterface = InterfaceTest.sendRequest(null, data -> {
                return new ResponseHandle(given().headers("Authorization", token)
                        .when().get("http://localhost:8000/auth/info"));
            });
            userInfoInterface.verifyStatusCode(200);
            assertThat(userInfoInterface.getJsonObject("user.username")).isEqualTo("admin");
        }

        @Order(3)
        @RepeatedTest(2)
        @DisplayName("退出所有成功登录的用户")
        void testRightLogout(){
            InterfaceTest.sendRequest(null, (data) -> {
                return new ResponseHandle(given().header("Authorization", token).when()
                        .delete("http://localhost:8000/auth/logout"));
            }).verifyStatusCode(200);
        }

        @AfterEach
        public void changeToken(){
            String testToken = (String) RelevanceVariable.getRelevanceVariable("test");
            if(token.equals(testToken)){
                token = (String) RelevanceVariable.getRelevanceVariable("admin");
            }
            else {
                token = testToken;
            }
        }

    }

}
