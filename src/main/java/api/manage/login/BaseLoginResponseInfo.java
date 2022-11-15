package api.manage.login;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName BaseLoginResponseInfo
 * @Author AissEr
 * @Date 2022/10/17 18:54
 * @Version 1.0
 * @Description 登录后会有很多信息时后续需要的，如token，username等
 *      使用
 **/
public abstract class BaseLoginResponseInfo implements LoginResponseInfo {
    protected Map<String,String> loginInfo;

    protected abstract void runLoginInterface();

    protected abstract void initResponseInfo();

    protected abstract void initDefaultInfo();

    protected abstract void setLoginMessage(String username, String password);

    /**
     * 使用“模板模式”
     * 调用的方法都是自定义的抽象类，需要子类去实现
     */
    public void initLoginResponseInfo(){
        loginInfo = new HashMap<>();
        runLoginInterface();
        initResponseInfo();
        initDefaultInfo();
    }

    /**
     * 实现接口
     * @param username username
     * @param password password
     */
    @Override
    public void changeLoginMessage(String username, String password) {
        loginInfo = null;
        setLoginMessage(username, password);
    }

    /**
     * 实现接口
     * @return loginResponseValue
     */
    @Override
    public Map<String, String> getValue() {
        if(loginInfo == null){
            initLoginResponseInfo();
        }
        return loginInfo;
    }
}
