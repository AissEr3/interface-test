package api.configure.item;

import api.ApiObject;
import api.configure.option.ConfigureOptions;
import api.configure.GeneralConfigure;
import api.configure.strategy.ConfigureStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName HeadersConfigureStrategy
 * @Author AissEr
 * @Date 2022/10/24 11:33
 * @Version 1.0
 * @Description 修改Header配置信息的策略
 **/
public class HeadersConfigureStrategy implements ConfigureStrategy<Map<String,Object>> {
    public static final String name = ConfigureOptions.LOGIN_HEADERS.getName();
    private static Map<String, String> loginInfo = GeneralConfigure.getInstance().getDefaultLoginInfo().getValue();

    @Override
    public void alterConfigureContent(ApiObject apiObject, Map<String, Object> value) {
        Map<String, Object> headers = new HashMap<>();
        for(String key : value.keySet()){
            Object val = loginInfo.get(value.get(key));
            if(val == null){
                val = value.get(key);
            }
            headers.put(key,val);
        }
        apiObject.setHeaders(headers);
    }

}
