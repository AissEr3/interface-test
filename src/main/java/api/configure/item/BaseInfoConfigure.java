package api.configure.item;

import api.ApiObject;
import api.configure.ConfigureOptions;
import api.configure.strategy.ConfigureStrategy;
import utils.ApiObjectUtil;

import java.util.Map;

/**
 * @ClassName BaseInfoConfigure
 * @Author AissEr
 * @Date 2022/10/23 20:10
 * @Version 1.0
 * @Description TODO
 **/
public class BaseInfoConfigure implements ConfigureStrategy<Map<String,String>>{
    public static final String name = ConfigureOptions.BASE.getName();

    @Override
    public void alterConfigureContent(ApiObject apiObject, Map<String,String> value) {
        for (String item : ApiObjectUtil.items) {
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
