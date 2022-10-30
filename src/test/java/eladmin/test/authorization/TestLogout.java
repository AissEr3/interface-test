package eladmin.test.authorization;

import api.ApiObject;
import api.configure.FundamentalConfigure;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.junit.jupiter.api.Test;
import base.BaseTest;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * @ClassName TestLogout
 * @Author AissEr
 * @Date 2022/10/16 22:07
 * @Version 1.0
 * @Description TODO
 **/
public class TestLogout extends BaseTest {

    @Test
    void test1() throws IOException {
        ObjectMapper mapper = new YAMLMapper();
        HashMap<String, Object> hashMap = mapper.readValue(new File("src/test/test-resource/data/login.yaml")
                , new HashMap<String, Object>().getClass());
        given.params(hashMap).get("/api/users").then().log().all();
    }

}
