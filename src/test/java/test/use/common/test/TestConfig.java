package test.use.common.test;

import lombok.Data;
import test.use.utils.RequestType;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName TestConfig
 * @Author AissEr
 * @Date 2022/10/19 21:08
 * @Version 1.0
 * @Description 对应configure注解可以配置的信息内容
 **/
@Data
public class TestConfig {
    private String baseURI;

    private String port;

    private String basePath;

    private RequestType type;

    private Map<String,String> headers;

    private Map<String,String> cookies;

    public void setHeaders(){

    }
}
