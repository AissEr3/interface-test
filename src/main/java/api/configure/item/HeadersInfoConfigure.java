package api.configure.item;

import api.ApiObject;
import api.configure.ConfigureOptions;
import api.configure.FundamentalConfigure;
import api.configure.strategy.ConfigureStrategy;

import java.util.Map;

/**
 * @ClassName HeadersInfoConfigure
 * @Author AissEr
 * @Date 2022/10/24 11:33
 * @Version 1.0
 * @Description TODO
 **/
public class HeadersInfoConfigure implements ConfigureStrategy<Map<String,String>> {
    public static final String name = ConfigureOptions.LOGIN_HEADERS.getName();
    private static Map<String, String> loginInfo = FundamentalConfigure.getInstance().getLoginInfo().getValue();
    private static final String TOKEN_NAME = "ELADMIN-TOKEN";

    @Override
    public void alterConfigureContent(ApiObject apiObject, Map<String, String> value) {
        Map<String, String> headers = apiObject.getHeaders();
        for(String key : value.keySet()){
            String val = loginInfo.get(value.get(key));
            if(val == null){
                val = value.get(key);
            }
            headers.put(key,val);
        }
    }

}
