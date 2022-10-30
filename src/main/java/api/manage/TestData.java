package api.manage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.HashMap;

public interface TestData<T> {
    // 避免每次配置都创建，浪费资源
    Class<? extends HashMap> CONVERT_TYPE = new HashMap<String,Object>().getClass();
    ObjectMapper MAPPER = new YAMLMapper();

    T getTestData();
}
