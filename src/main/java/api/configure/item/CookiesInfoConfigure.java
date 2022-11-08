package api.configure.item;

import api.ApiObject;
import api.configure.ConfigureOptions;
import api.configure.FundamentalConfigure;
import api.configure.strategy.ConfigureStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName CookiesInfoConfigure
 * @Author AissEr
 * @Date 2022/10/26 18:42
 * @Version 1.0
 * @Description TODO
 **/
public class CookiesInfoConfigure implements ConfigureStrategy<Map<String, Object>> {
    public static final String name = ConfigureOptions.LOGIN_COOKIES.getName();
    private static Map<String, String> loginInfo = FundamentalConfigure.getInstance().getDefaultLoginInfo().getValue();

    @Override
    public void alterConfigureContent(ApiObject apiObject, Map<String, Object> value) {
        Map<String, Object> cookies = new HashMap<>();
        for(String key : value.keySet()){
            Object val = loginInfo.get(value.get(key));
            if(val == null){
                val = value.get(key);
            }
            cookies.put(key,val);
        }
        apiObject.setCookies(cookies);
    }
}
