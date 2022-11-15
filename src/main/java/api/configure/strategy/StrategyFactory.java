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
 * @Description 创建静态工厂，这里策略并不需要多个对象，一个即可
 **/
public class StrategyFactory {
    private static Map<ConfigureOptions,ConfigureStrategy> factory = new HashMap<>();
    // 此处策略都不用重复创建，因此只用创建一个即可
    private static final BaseConfigureStrategy BASE_CONFIGURE_STRATEGY = new BaseConfigureStrategy();
    private static final InterfaceConfigureStrategy INTERFACE_CONFIGURE_STRATEGY = new InterfaceConfigureStrategy();
    private static final HeadersConfigureStrategy HEADERS_CONFIGURE_STRATEGY = new HeadersConfigureStrategy();
    private static final CookiesConfigureStrategy COOKIES_CONFIGURE_STRATEGY = new CookiesConfigureStrategy();

    static{
        factory.put(ConfigureOptions.BASE, BASE_CONFIGURE_STRATEGY);
        factory.put(ConfigureOptions.LOGIN_HEADERS, HEADERS_CONFIGURE_STRATEGY);
        factory.put(ConfigureOptions.LOGIN_COOKIES, COOKIES_CONFIGURE_STRATEGY);
        factory.put(ConfigureOptions.INTERFACE_INFO, INTERFACE_CONFIGURE_STRATEGY);
    }

    public static ConfigureStrategy createStrategy(ConfigureOptions option){
        return factory.get(option);
    }

}
