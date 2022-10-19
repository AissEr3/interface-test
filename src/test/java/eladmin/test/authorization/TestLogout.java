package eladmin.test.authorization;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;

/**
 * @ClassName TestLogout
 * @Author AissEr
 * @Date 2022/10/16 22:07
 * @Version 1.0
 * @Description TODO
 **/
public class TestLogout {
    @Test
    void testRightLogout(){
        when().delete("http://localhost:8000/auth/logout").then().log().all();
    }
}
