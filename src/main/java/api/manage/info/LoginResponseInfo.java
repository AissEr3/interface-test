package api.manage.info;

import java.util.Map;

/**
 * 登录操作后会有很多的测试必要信息进行存储，例如：
 * 1. cookie
 * 2. token
 * 3. header 里的 Authorization 权限管理
 * ......
 */
public interface LoginResponseInfo {
    // 修改登录信息。如果从未设置过，将空设置为指定信息；如果修改登录信息一致，则不修改
    void changeLoginMessage(String username, String password);

    Map<String,String> getLoginInfo();
}
