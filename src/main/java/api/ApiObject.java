package api;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import util.stat.RequestType;


import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ApiObject
 * @Author AissEr
 * @Date 2022/10/20 21:12
 * @Version 1.0
 * @Description 指定每个接口可以配置的信息
 **/
@Data
@ToString
@Builder
public class ApiObject {
    private String baseURI;

    private String port;

    private String contentType;

//    private Map<String, Object> data;

    private String basePath;

    private String path;

    private String dataPlaceIn;

    private String requestType;

    private Map<String, Object> headers = new HashMap<>();

    private Map<String, Object> cookies = new HashMap<>();

    public RequestType getRequestType(){
        for(RequestType type : RequestType.values()){
            if(type.getTypeName().equals(requestType)){
                return type;
            }
        }
        return null;
    }
}
