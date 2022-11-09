package api.configure.item;

import api.ApiObject;
import api.configure.ConfigureOptions;
import api.configure.strategy.ConfigureStrategy;
import utils.ApiObjectUtil;

import java.util.Map;

/**
 * @ClassName InterfaceConfigureStrategy
 * @Author AissEr
 * @Date 2022/10/27 8:52
 * @Version 1.0
 * @Description TODO
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
