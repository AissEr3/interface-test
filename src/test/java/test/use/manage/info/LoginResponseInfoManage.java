package test.use.manage.info;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LoginResponseInfoManage
 * @Author AissEr
 * @Date 2022/10/17 18:54
 * @Version 1.0
 * @Description 登录后会有很多信息时后续需要的，如token，username等
 **/
public abstract class LoginResponseInfoManage implements LoginResponseInfo {
    protected Map<String,String> loginInfo;

    protected abstract void setLoginResponseInfo();

    protected abstract void addDefaultInfo();

    protected abstract void runLoginInterface();

    protected abstract void setLoginMessage(String username, String password);

    public void initLoginResponseInfo(){
        loginInfo = new HashMap<>();
        runLoginInterface();
        setLoginResponseInfo();
        addDefaultInfo();
    }

    @Override
    public void changeLoginResponseInfo(String username, String password) {
        loginInfo = null;
        setLoginMessage(username, password);
    }

    @Override
    public Map<String, String> getLoginInfo() {
        if(loginInfo == null){
            initLoginResponseInfo();
        }
        return loginInfo;
    }
}
