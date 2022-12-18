package common.test;

import api.ApiObject;
import api.configure.AbstractConfigure;
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
 * @Description 将全局配置文件的信息加载出来，并根据全局文件的信息创建配置好的given
 **/
public class SetRestAssured {
    private static AbstractConfigure generalConfigure;
    private static ApiObject apiObject = ApiObject.builder().build();
    private RequestSpecification given;

    private SetRestAssured(){}

    /**
     * 对外提供的设置方法
     * @return
     */
    public static InterfaceSetter startSet(){
        return new SetRestAssured().settingRestAssured();
    }

    /**
     * 如果需要设置配置文件类，在这里设置
     * 具体如何设置，看GeneralConfigure类即可知道
     */
    public static void initGenernalConfigure(){
        generalConfigure = GeneralConfigure.getInstance();
        generalConfigure.initConfigure();
        generalConfigure.configure(apiObject);

        // 设置基础信息，只用配置一次
        if(apiObject.getBaseURI() != null){
            RestAssured.baseURI = apiObject.getBaseURI();
        }
        if(apiObject.getBasePath() != null){
            RestAssured.basePath = apiObject.getBasePath();
        }
        if(apiObject.getPort() != null){
            RestAssured.port = Integer.parseInt(apiObject.getPort());
        }
    }

    public static void exitSettingUser(){
        GeneralConfigure.exitCurrentUser();
    }

    /**
     * @return 返回配置项，类似于Builder模式
     */
    private InterfaceSetter settingRestAssured(){
        // 真正获取所有信息
        given = RestAssured.given();
        return new InterfaceSetter();
    }

    /**
     * 可以自己配置given的地方，类似于Builder模式
     */
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
            Map<String, Object> headers = apiObject.getHeaders();
            if(headers != null && headers.size() != 0){
                given.headers(headers);
            }
            return this;
        }

        // 设置header
        public InterfaceSetter headers(Map<String, Object> headers){
            if(headers != null && headers.size() != 0){
                given.headers(headers);
            }
            return this;
        }

        // 设置默认cookies
        public InterfaceSetter defaultCookies(){
            // 如果有cookie，配置cookie
            Map<String, Object> cookies = apiObject.getCookies();
            if(cookies != null && cookies.size() != 0){
                given.cookies(cookies);
            }
            return this;
        }

        // 设置cookies
        public InterfaceSetter cookies(Map<String, Object> cookies){
            if(cookies != null && cookies.size() != 0){
                given.cookies(cookies);
            }
            return this;
        }

        // 结束设置
        public RequestSpecification endSet(){
            return SetRestAssured.this.given;
        }
    }
}
