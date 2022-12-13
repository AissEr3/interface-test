package common;

import io.restassured.response.Response;

import java.util.Map;

/**
 *  “接口测试”接口，发送请求时可以有哪些参数
 *  同时规定响应信息由ResponseHandle类进行处理
 *  该接口为函数式接口，可以使用lambda表达式
 */
public interface InterfaceTest {

    /**
     * 如果没有数据，则填写为null
     * @param data 请求参数，可以为null
     * @param <T> 参数的类型
     * @return 返回ResponseHandle对象，将响应数据交给该类处理
     */
    <T> ResponseHandle request(T data);

    /**
     * 发送请求方法，可以通过lamdba表达式来快速的发送一些临时的请求
     * @param data 请求数据
     * @param request 实现的请求方法
     * @param <T> 请求数据的类型
     * @return 响应结果
     */
    public static <T> ResponseHandle sendRequest(T data,InterfaceTest request){
        return request.request(data);
    }
}
