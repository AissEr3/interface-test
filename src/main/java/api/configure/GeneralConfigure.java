package api.configure;

import api.manage.info.LoginResponseInfo;
import api.manage.info.LoginResponseInfoManager;
import utils.MapUtil;

import java.util.Map;

/**
 * @ClassName GeneralConfigure
 * @Author AissEr
 * @Date 2022/10/21 10:41
 * @Version 1.0
 * @Description
 *   该类是所有配置类都需要有的东西，但该类并不对ApiObject直接配置信息；
 *   很多信息都需要登录后才可以获取，例如header、cookies，在配置header和cookies时从登录信息中取值
 **/
public abstract class GeneralConfigure implements Configure {
    protected static LoginResponseInfo loginInfo = new LoginResponseInfoManager();

    // 记录配置信息的map
    protected Map<String,Object> applicationMap;

    public GeneralConfigure(){
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

    // 通过‘.’的方式方便获取嵌套Map
    protected Map<String,Object> readApplicationByPoint(String targetKey){
        if(applicationMap != null){
            return (Map<String, Object>) MapUtil.readMapByPoint(applicationMap, targetKey);
        }
        return null;
    }

    public LoginResponseInfo getLoginInfo(){
        return loginInfo;
    }
}
