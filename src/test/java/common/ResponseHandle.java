package common;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.assertj.core.api.Assertions.*;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName ResponseHandle
 * @Author AissEr
 * @Date 2022/11/14 19:21
 * @Version 1.0
 * @Description 处理接口的响应数据类，封装一些方法方便对响应数据进行测试
 *              可自己增加方法
 **/
@Data
public class ResponseHandle {
    private Response response;
    private InterfaceRun runner;

    /**
     * 需要使用到对于运行类的一些属性（protected属性），所以需要指定
     * @param alreadyRunning 运行类的对象
     * @param response 与运行类对应的响应信息
     */
    public ResponseHandle(InterfaceRun alreadyRunning,Response response){
        setRunner(alreadyRunning);
        setResponse(response);
    }

    /**
     * 增加兼容性，完全等价RestAssured的then方法
     * 此类只做功能的扩展，因此不破坏原来的功能
     * @return
     */
    public ValidatableResponse then(){
        return response.then();
    }

    /**
     * 直接获取jsonpath进行断言
     * @return 响应信息的jsonpath
     */
    public JsonPath jsonPath(){
        return response.then().extract().jsonPath();
    }

    /**
     * 使用JsonPath获取值
     */
    public Object getJsonObject(String path){
        return jsonPath().getJsonObject(path);
    }

    /**
     * 验证JsonSchema
     */
    public ResponseHandle verifyJsonSchema(){
        verifyJsonSchema(runner.interfaceConfigure.getJsonSchema());
        return this;
    }

    public ResponseHandle verifyJsonSchema(String jsonScheme){
        response.then().assertThat()
                .body(matchesJsonSchema(jsonScheme));
        return this;
    }

    public ResponseHandle verifyExcepted(Map<String,?> excepted){
        Set<String> keys = excepted.keySet();
        for(String key : keys){
            if(!key.equals("statusCode")){
                assertThat((String) getJsonObject(key)).contains((String)excepted.get(key));
            }
            else {
                verifyStatusCode((Integer) excepted.get(key));
            }
        }
        return this;
    }

    private ResponseHandle verifyStatusCode(Integer code){
        response.then().statusCode(code);
        return this;
    }

}
