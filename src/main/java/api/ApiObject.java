package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.ToString;
import utils.stat.RequestType;


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

    private ObjectMapper body;// post

    private Map<String, String> query = new HashMap<>();// get

    private String basePath;

    private String path;

    private RequestType requestType;

    private Map<String, String> headers = new HashMap<>();

    private Map<String, String> cookies = new HashMap<>();
}
