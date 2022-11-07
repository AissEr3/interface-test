package base;

import io.restassured.response.Response;

import java.util.Map;

public interface InterfaceTest<T> {
    Response request(T data);
}
