package api.configure;

import api.manage.login.LoginResponseInfo;
import api.manage.login.LoginResponseInfoManage;

import java.util.Map;

/**
 * @ClassName AbstractConfigure
 * @Author AissEr
 * @Date 2022/10/21 10:41
 * @Version 1.0
 * @Description
 *   该类是所有配置类都需要有的东西，但该类并不对ApiObject直接配置信息；
 *   很多信息都需要登录后才可以获取，例如header、cookies，在配置header和cookies时从登录信息中取值
 **/
public abstract class AbstractConfigure implements Configure {
    protected static final LoginResponseInfo DEFAULT_LOGIN_INFO = new LoginResponseInfoManage();

    // 记录配置信息的map
    protected Map<String,Object> applicationMap;

    public void initConfigure(){
        initApplicationMap();
        initLoginMessage();
    }

    /**
     * 设置读取配置信息的方法
     */
    protected abstract void initApplicationMap();

    /**
     * 设置默认的登录信息
     *
     * ******让子类去重写，但又不是每个子类必须都要重写的方法*****
     */
    protected void initLoginMessage(){

    }

    public LoginResponseInfo getDefaultLoginInfo(){
        return DEFAULT_LOGIN_INFO;
    }
}
