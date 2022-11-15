package common;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import lombok.Data;

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
    public JsonPath getJsonPath(){
        return response.then().extract().jsonPath();
    }

    /**
     * 验证JsonSchema
     */
    public void verifyJsonSchema(){
        verifyJsonSchema(runner.interfaceConfigure.getJsonSchema());
    }

    public void verifyJsonSchema(String jsonScheme){
        response.then().assertThat()
                .body(matchesJsonSchema(jsonScheme));
    }
}
