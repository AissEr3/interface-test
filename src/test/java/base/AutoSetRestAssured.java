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
 * @ClassName AutoSetRestAssured
 * @Author AissEr
 * @Date 2022/10/30 20:02
 * @Version 1.0
 * @Description 该类自动配置接口通用（每个测试接口都需要）的接口信息
 **/
public class AutoSetRestAssured {
    private static final GeneralConfigure fundamentalConfigure;
    private static ApiObject apiObject = new ApiObject();
    public static RequestSpecification given;

    static{
        settingFundamentalConfigure();
        fundamentalConfigure = FundamentalConfigure.getInstance();
        fundamentalConfigure.initConfigure();
        fundamentalConfigure.configure(apiObject);
        settingRestAssured();
    }

    /**
     * 如果需要设置该类，在这里设置
     */
    private static void settingFundamentalConfigure(){

    }

    // 设置token信息
    private static void settingRestAssured(){
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

        // 默认的contentType
        String defaultContentType = apiObject.getContentType();
        if(defaultContentType != null){
            given.contentType(defaultContentType);
        }

        // 如果有header，配置header
        Map<String, String> headers =  apiObject.getHeaders();
        if(headers != null && headers.size() != 0){
            given.headers(headers);
        }

        // 如果有cookies，如果有cookies
        Map<String, String> cookies =  apiObject.getHeaders();
        if(cookies != null && cookies.size() != 0){
            given.cookies(cookies);
        }
    }
}
