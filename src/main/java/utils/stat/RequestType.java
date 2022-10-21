package utils.stat;

public enum RequestType {
    GET("get"),
    POST("post"),
    PUT("put"),
    DELETE("delete");

    String typeName;

   RequestType(String typeName){
        this.typeName = typeName;
    }

    public static RequestType getType(String typeName){
       for(RequestType type : RequestType.values()){
           if(type.typeName.equals(typeName)){
               return type;
           }
       }
       return null;
    }

    public String getTypeName(){
       return typeName;
    }

    @Override
    public String toString() {
        return typeName;
    }
}
