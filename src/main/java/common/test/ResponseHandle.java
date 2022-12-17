package common.test;

import io.restassured.path.json.JsonPath;
import io.restassured.path.json.exception.JsonPathException;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import lombok.Data;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
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

    /**
     * @param response 与运行类对应的响应信息
     */
    public ResponseHandle(Response response){
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

    public ResponseHandle verifyStatusCode(Integer code){
        response.then().statusCode(code);
        return this;
    }

    public ResponseHandle verifyExcepted(Map<String,?> excepted){
        Set<String> keys = excepted.keySet();
        RelevanceVariable.replaceByRelevanceVariable((Map<String, Object>) excepted);
        for(String key : keys){
            if(key.equals("statusCode")){
                verifyStatusCode((Integer) excepted.get(key));
                continue;
            }
            try{
                Object jsonObject = getJsonObject(key);
                if(jsonObject != null){
                    assertByType(excepted.get(key),jsonObject);
                }
            }catch (JsonPathException e){
                fail("no response data");
            }
        }
        return this;
    }

    private void assertByType(Object excepted, Object actual){
        if(actual instanceof String){
            if(excepted instanceof Number){
                assertThat((String) actual).contains(String.valueOf(excepted));
            }
            else {
                assertThat((String) actual).contains((String) excepted);
            }
        }
        else if(actual instanceof Number){
            if(excepted instanceof String){
                assertThat(String.valueOf(actual)).isEqualTo(excepted);
            }
            else {
                assertThat(actual).isEqualTo(excepted);
            }
        }
        else if(actual instanceof List){
            assertThat((List) actual).contains(excepted);
        }
        else {
            fail("not fount assert method, default to fail");
        }
    }
}
