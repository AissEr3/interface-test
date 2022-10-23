package api.configure.strategy;

import api.configure.ConfigureOptions;
import api.configure.item.BaseInfoConfigure;

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
    private static Map<String,ConfigureStrategy> factory = new HashMap<>();

    static{
        factory.put(ConfigureOptions.BASE.getName(), new BaseInfoConfigure());
    }
}
