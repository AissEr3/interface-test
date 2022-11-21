package common;

public enum TestDataOptions {
    DESCRIBE("describe"),
    DATA("data"),
    EXCEPTED("excepted");

    String keyName;

    TestDataOptions(String keyName){
        this.keyName = keyName;
    }

    public String keyName(){
        return keyName;
    }
}
