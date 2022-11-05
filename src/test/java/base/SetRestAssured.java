package base;

import api.ApiObject;
import api.configure.FundamentalConfigure;
import api.configure.GeneralConfigure;
import api.configure.InterfaceConfigure;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import lombok.Data;
import org.kohsuke.rngom.parse.host.Base;

import java.io.File;
import java.util.Map;

/**
 * @ClassName SetRestAssured
 * @Author AissEr
 * @Date 2022/10/30 20:02
 * @Version 1.0
 * @Description 该类自动配置接口通用（每个测试接口都需要）的接口信息
 **/
public class SetRestAssured {
    private static final GeneralConfigure fundamentalConfigure;
    private static ApiObject apiObject = new ApiObject();
    public RequestSpecification given;

    static{
        settingFundamentalConfigure();
        fundamentalConfigure = FundamentalConfigure.getInstance();
        fundamentalConfigure.initConfigure();
        fundamentalConfigure.configure(apiObject);
    }

    /**
     * 如果需要设置该类，在这里设置
     */
    private static void settingFundamentalConfigure(){

    }

    public InterfaceBuilder settingRestAssured(){
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
        return new InterfaceBuilder();
    }

    private class InterfaceBuilder{
        public InterfaceBuilder setContentType(){
            // 默认的contentType
            String defaultContentType = apiObject.getContentType();
            if(defaultContentType != null){
                given.contentType(defaultContentType);
            }
            return this;
        }

        public InterfaceBuilder setHeaders(){
            // 如果有header，配置header
            Map<String, String> headers =  apiObject.getHeaders();
            if(headers != null && headers.size() != 0){
                given.headers(headers);
            }
            return this;
        }

        public InterfaceBuilder setCookies(){
            // 如果有header，配置header
            Map<String, String> cookies =  apiObject.getCookies();
            if(cookies != null && cookies.size() != 0){
                given.headers(cookies);
            }
            return this;
        }

        public RequestSpecification build(){
            return given;
        }
    }

}
