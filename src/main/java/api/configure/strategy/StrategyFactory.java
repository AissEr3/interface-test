package api.configure.strategy;

import api.configure.ConfigureOptions;
import api.configure.item.BaseInfoConfigure;
import api.configure.item.CookiesInfoConfigure;
import api.configure.item.HeadersInfoConfigure;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName StrategyFactory
 * @Author AissEr
 * @Date 2022/10/23 20:36
 * @Version 1.0
 * @Description TODO
 **/
public class StrategyFactory {
    private static Map<ConfigureOptions,ConfigureStrategy> factory = new HashMap<>();

    static{
        factory.put(ConfigureOptions.BASE, new BaseInfoConfigure());
        factory.put(ConfigureOptions.LOGIN_HEADERS, new HeadersInfoConfigure());
        factory.put(ConfigureOptions.LOGIN_COOKIES, new CookiesInfoConfigure());
    }

    public static ConfigureStrategy createStrategy(ConfigureOptions option){
        return factory.get(option);
    }

}
