package common;

import io.restassured.response.Response;

import java.util.Map;

/**
 *  “接口测试”接口，发送请求时可以有哪些参数
 *  同时规定响应信息由ResponseHandle类进行处理
 *  可以根据实际的项目自由改变该类
 */
public interface InterfaceTest {
    ResponseHandle request();

    <T> ResponseHandle request(T data);

}
