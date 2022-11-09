package api.configure.strategy;

import api.configure.ConfigureOptions;
import api.configure.item.BaseConfigureStrategy;
import api.configure.item.CookiesConfigureStrategy;
import api.configure.item.HeadersConfigureStrategy;
import api.configure.item.InterfaceConfigureStrategy;

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
    // 此处策略都不用重复创建，因此只用创建一个即可
    private static final BaseConfigureStrategy BaseInfoConfigure = new BaseConfigureStrategy();
    private static final InterfaceConfigureStrategy INTERFACE_CONFIGURE_STRATEGY = new InterfaceConfigureStrategy();
    private static final HeadersConfigureStrategy HeadersInfoConfigure = new HeadersConfigureStrategy();
    private static final CookiesConfigureStrategy CookiesInfoConfigure = new CookiesConfigureStrategy();

    static{
        factory.put(ConfigureOptions.BASE, BaseInfoConfigure);
        factory.put(ConfigureOptions.LOGIN_HEADERS, HeadersInfoConfigure);
        factory.put(ConfigureOptions.LOGIN_COOKIES, CookiesInfoConfigure);
        factory.put(ConfigureOptions.INTERFACE_INFO, INTERFACE_CONFIGURE_STRATEGY);
    }

    public static ConfigureStrategy createStrategy(ConfigureOptions option){
        return factory.get(option);
    }

}
