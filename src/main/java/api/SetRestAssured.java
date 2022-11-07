package api;

import api.ApiObject;
import api.configure.FundamentalConfigure;
import api.configure.GeneralConfigure;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

/**
 * @ClassName SetRestAssured
 * @Author AissEr
 * @Date 2022/10/30 20:02
 * @Version 1.0
 * @Description
 **/
public class SetRestAssured {
    private static GeneralConfigure fundamentalConfigure;
    private static ApiObject apiObject = new ApiObject();
    public RequestSpecification given;

    private SetRestAssured(){}

    public static InterfaceSetter startSet(){
        return new SetRestAssured().settingRestAssured();
    }

    /**
     * 如果需要设置该类，在这里设置
     */
    public static void initFundamentalConfigure(){
        fundamentalConfigure = FundamentalConfigure.getInstance();
        fundamentalConfigure.initConfigure();
        fundamentalConfigure.configure(apiObject);
    }

    private InterfaceSetter settingRestAssured(){
        // 设置基础信息
        if(apiObject.getBaseURI() != null){
            RestAssured.baseURI = apiObject.getBaseURI();
        }
        if(apiObject.getBasePath() != null){
            RestAssured.basePath = apiObject.getBasePath();
        }
        if(apiObject.getPort() != null){
            RestAssured.port = Integer.parseInt(apiObject.getPort());
        }

        // 真正获取所有信息
        given = RestAssured.given();
        return new InterfaceSetter();
    }

    public class InterfaceSetter {
        // 默认的contentType，内容为配置文件的内容
        public InterfaceSetter defaultContentType(){
            String defaultContentType = apiObject.getContentType();
            if(defaultContentType != null && !defaultContentType.equals("")){
                given.contentType(defaultContentType);
            }
            return this;
        }

        // 设置contentType
        public InterfaceSetter contentType(String contentType){
            if(contentType != null && !contentType.equals("")){
                given.contentType(contentType);
            }
            return this;
        }

        // 设置contentType
        public InterfaceSetter contentType(ContentType contentType){
            if(contentType != null){
                given.contentType(contentType);
            }
            return this;
        }

        // 设置配置文件中默认的header
        public InterfaceSetter defaultHeaders(){
            // 如果有header，配置header
            Map<String, String> headers = apiObject.getHeaders();
            if(headers != null && headers.size() != 0){
                given.headers(headers);
            }
            return this;
        }

        // 设置header
        public InterfaceSetter headers(Map<String, String> headers){
            if(headers != null && headers.size() != 0){
                given.headers(headers);
            }
            return this;
        }

        // 设置默认cookies
        public InterfaceSetter defaultCookies(){
            // 如果有cookie，配置cookie
            Map<String, String> cookies = apiObject.getCookies();
            if(cookies != null && cookies.size() != 0){
                given.cookies(cookies);
            }
            return this;
        }

        public InterfaceSetter cookies(Map<String, String> cookies){
            if(cookies != null && cookies.size() != 0){
                given.headers(cookies);
            }
            return this;
        }

        public RequestSpecification endSet(){
            return SetRestAssured.this.given;
        }
    }
}
