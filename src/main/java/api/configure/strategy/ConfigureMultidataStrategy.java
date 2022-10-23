package api.configure.strategy;

import api.ApiObject;

public interface ConfigureMultidataStrategy<T> extends ConfigureStrategy<T>{

    void addConfigureContent(ApiObject apiObject, T value);

    void deleteConfigureContent(ApiObject apiObject, T value);

}
