package api.manage.login;

import io.restassured.response.Response;
import util.MapUtil;

import java.util.Map;

import static io.restassured.RestAssured.*;

/**
 * @ClassName LoginResponseInfoManage
 * @Author AissEr
 * @Date 2022/10/17 20:59
 * @Version 1.0
 * @Description TODO
 **/
public class LoginResponseInfoManage extends BaseLoginResponseInfo {
    public static final String TOKEN_NAME = "ELADMIN-TOKEN";
    private LoginJSON loginJSON;
    private Response response;

    public LoginResponseInfoManage(){
    }

    public LoginResponseInfoManage(LoginJSON loginJSON){
        setLoginJSON(loginJSON);
    }

    public LoginResponseInfoManage(String username, String password){
        setLoginJSON(username,password);
    }

    public void setLoginJSON(LoginJSON loginJSON){
        this.loginJSON = loginJSON;
    }

    public LoginJSON getLoginJSON(){
        return loginJSON;
    }

    public void setLoginJSON(String username, String password){
        loginJSON = new LoginJSON(username,password);
    }

    @Override
    public void exitThisUser(){
        if(loginJSON != null){
            given()
                .headers("Authorization",loginInfo.get(TOKEN_NAME))
                .cookies(loginInfo)
            .when()
                .delete("http://localhost:8000/auth/logout");
        }
    }

    // 重写修改登录信息的方法，增加功能，如果是同一用户，不进行修改
    @Override
    public void changeLoginMessage(String username, String password) {
        if(loginJSON == null || !loginJSON.getUsername().equals(username)){
            super.changeLoginMessage(username, password);
        }
    }

    @Override
    protected void setLoginMessage(String username, String password){
        setLoginJSON(username,password);
    }

    @Override
    protected void runLoginInterface() {
        if(loginJSON.isRas()){
            response = given()
                    .contentType("application/json")
                    .body(loginJSON)
                    .when()
                    .post("http://localhost:8000/auth/login");
        }
    }

    @Override
    protected void initResponseInfo() {
        Map map = response.then().extract().jsonPath().getMap("");
        loginInfo.put(TOKEN_NAME, (String) MapUtil.readMapByPoint(map,"token"));
        loginInfo.put("username", (String) MapUtil.readMapByPoint(map,"user.user.username"));
        loginInfo.put("password",(String) MapUtil.readMapByPoint(map,"user.user.password"));
    }

    @Override
    protected void initDefaultInfo() {
        loginInfo.put("remember","true");
        loginInfo.put("Pycharm-3339b356","61093f87-8779-4b40-b995-8d9cba97bb1e");
        loginInfo.put("Idea-80e4ad09","bd43ce8f-6a5b-43c8-b470-8c24ed2b3704");
    }
}
