package common;

import io.restassured.response.Response;

import java.util.Map;

public interface InterfaceTest {
    ResponseHandle request();

    ResponseHandle request(Object data);

    ResponseHandle request(Map<String,?> data);
}
