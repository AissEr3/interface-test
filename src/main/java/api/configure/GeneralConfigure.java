package api.configure;

import manage.info.LoginResponseInfoManager;

import java.util.Map;

/**
 * @ClassName GeneralConfigure
 * @Author AissEr
 * @Date 2022/10/21 10:41
 * @Version 1.0
 * @Description
 *   该类是所有配置类都需要有的东西，但该类并不对ApiObject直接配置信息；
 *   很多信息都需要登录后才可以获取，例如header、cookies
 **/
public abstract class GeneralConfigure implements Configure {
    protected LoginResponseInfoManager loginInfo;

    // 记录配置信息的map
    protected Map<String,String> applicationMap;

    public GeneralConfigure(){
        initDefaultLoginMessage();
        loginInfo.getLoginInfo();
    }

    //
    protected abstract void initDefaultLoginMessage();

    // 设置读取配置信息的方法
    protected abstract void initApplicationMap();
}
