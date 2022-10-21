package manage.info;

import java.util.Map;

public interface LoginResponseInfo {
    void changeLoginResponseInfo(String username, String password);

    Map<String,String> getLoginInfo();
}
