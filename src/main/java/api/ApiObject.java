package api;

import json.JsonBean;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import utils.stat.RequestType;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ApiObject
 * @Author AissEr
 * @Date 2022/10/20 21:12
 * @Version 1.0
 * @Description TODO
 **/
@Data
@ToString
public class ApiObject {
    private String baseURI;

    private String port;

    private String contentType;

    private JsonBean body;// post

    private Map<String, String> query;// get

    private String basePath;

    private String path;

    private RequestType requestType;

    private Map<String, String> headers;

    private Map<String, String> cookies;
}
