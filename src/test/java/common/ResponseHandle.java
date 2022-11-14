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
 * @Description TODO
 **/
@Data
public class ResponseHandle {
    private Response response;
    private InterfaceRun runner;

    public ResponseHandle(InterfaceRun alreadyRunning,Response response){
        setRunner(alreadyRunning);
        setResponse(response);
    }

    // 增加兼容性，完全匹配RestAssured，该类只做功能的扩展
    public ValidatableResponse then(){
        return response.then();
    }

    public JsonPath getJsonPath(){
        return response.then().extract().jsonPath();
    }

    public void verifyJsonScheme(){
        verifyJsonScheme(runner.interfaceConfigure.getJsonSchema());
    }

    public void verifyJsonScheme(String jsonScheme){
        response.then().assertThat()
                .body(matchesJsonSchema(jsonScheme));
    }
}
