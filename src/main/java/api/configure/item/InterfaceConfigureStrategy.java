package api.configure.item;

import api.ApiObject;
import api.configure.option.ConfigureOptions;
import api.configure.strategy.ConfigureStrategy;
import util.ApiObjectUtil;

import java.util.Map;

/**
 * @ClassName InterfaceConfigureStrategy
 * @Author AissEr
 * @Date 2022/10/27 8:52
 * @Version 1.0
 * @Description 修改具体运行接口配置信息的策略
 **/
public class InterfaceConfigureStrategy implements ConfigureStrategy<Map<String,Object>> {
    public static final String name = ConfigureOptions.INTERFACE_INFO.getName();

    @Override
    public void alterConfigureContent(ApiObject apiObject, Map<String, Object> value) {
        for (String item : ConfigureOptions.INTERFACE_INFO.getOptions()) {
            Object obj = value.get(item);
            if(obj != null){
                try {
                    ApiObjectUtil.setApiObject(apiObject,item,obj);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
