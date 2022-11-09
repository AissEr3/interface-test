package common;

import io.restassured.response.Response;

import java.util.Map;

public interface InterfaceTest {
    Response request();

    Response request(Object data);

    Response request(Map<String,?> data);
}
