package api.configure;

import api.ApiObject;

/**
 * Configure 接口，是所有Configure类需要实现的接口
 * Configure类 ---> 配置ApiObject的类
 * 只有一个执行操作，configure方法
 */
public interface Configure {
    void configure(ApiObject object);
}
