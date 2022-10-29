package api.manage;

import api.ApiObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Data;
import utils.stat.RequestType;

import java.util.Map;

/**
 * @ClassName RestAssuredManager
 * @Author AissEr
 * @Date 2022/10/29 20:13
 * @Version 1.0
 * @Description 未实现
 **/
@Deprecated
public class RestAssuredManager {
    private ApiObject apiObject;
    private ApiObject lastObject;
    private static RequestSpecification given = RestAssured.given();

    public RestAssuredManager(){

    }

    public RestAssuredManager(ApiObject apiObject){
        this.apiObject = apiObject;
    }

    public void initBaseInformation(){
        setBaseURI();
        setPort();
        setBasePath();
        setHeaders();
        setCookies();
    }

    public RequestSpecification getHaveBeenSetGiven(){
        if(lastObject == null
                || apiObject.getContentType() != null
                && !apiObject.getContentType().equals(lastObject.getContentType())){
            given.contentType(apiObject.getContentType());
        }
        if(lastObject == null
                || apiObject.getRequestType() != null
                && apiObject.getRequestType() != lastObject.getRequestType()){
            given.contentType(apiObject.getContentType());
        }
        lastObject = apiObject;
        return given;
    }

    public Response run(){
        RequestType requestType = apiObject.getRequestType();
        Response response = null;
        if(requestType == RequestType.GET){
            response = given.when().get(apiObject.getPath());
        }
        else if(requestType == RequestType.POST){
            response = given.when().post(apiObject.getPath());
        }
        else if(requestType == RequestType.PUT){
            response = given.when().put(apiObject.getPath());
        }
        else if(requestType == RequestType.DELETE){
            response = given.when().delete(apiObject.getPath());
        }
        return response;
    }

    public void setTestData(Map<String,Object> testData){
        RequestType requestType = apiObject.getRequestType();
        if(requestType == RequestType.GET || requestType == RequestType.DELETE){
            given.queryParams(testData);
        }
        else {
            given.body(testData);
        }
    }

    private void setBaseURI(){
        String basePath = apiObject.getBasePath();
        if (basePath != null){
            RestAssured.basePath = basePath;
        }
    }

    private void setPort(){
        String port = apiObject.getPort();
        if (port != null){
            RestAssured.port = Integer.parseInt(port);
        }
    }

    private void setBasePath(){
        String basePath = apiObject.getBasePath();
        if(basePath != null){
            RestAssured.basePath = basePath;
        }
    }

    private void setHeaders(){
        Map<String, String> headers = apiObject.getHeaders();
        if(headers != null || headers.size() != 0){
            given.headers(headers);
        }
    }

    private void setCookies(){
        Map<String, String> cookies = apiObject.getCookies();
        if(cookies != null || cookies.size() != 0){
            given.cookies(cookies);
        }
    }

}
