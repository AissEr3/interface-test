package test.use.utils;

public enum RequestType {
    GET("get"),
    POST("post"),
    PUT("put"),
    DELETE("delete");

    String typeName;

   RequestType(String typeName){
        this.typeName = typeName;
    }
}
