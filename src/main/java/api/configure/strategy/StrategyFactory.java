package api.configure.strategy;

import api.configure.option.ConfigureOptions;
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

    public static ConfigureStrategy createStrategy(ConfigureOptions option){
        if(option == ConfigureOptions.BASE){
            return new BaseConfigureStrategy();
        }
        else if(option == ConfigureOptions.LOGIN_HEADERS){
            return new HeadersConfigureStrategy();
        }
        else if(option == ConfigureOptions.LOGIN_COOKIES){
            return new CookiesConfigureStrategy();
        }
        else if(option == ConfigureOptions.INTERFACE_INFO){
            return new InterfaceConfigureStrategy();
        }
        return null;
    }

}
