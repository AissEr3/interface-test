package api.configure.strategy;

import api.ApiObject;

import java.util.Map;

/**
 * 策略模式
 * 设计配置文件的信息，不同策略配置不同的信息
 */
public interface ConfigureStrategy<T> {

    void alterConfigureContent(ApiObject apiObject, T value);

}
