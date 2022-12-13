package api.configure.item;

import api.ApiObject;
import api.configure.option.ConfigureOptions;
import api.configure.strategy.ConfigureStrategy;
import util.ApiObjectUtil;

import java.util.Map;

/**
 * @ClassName BaseConfigureStrategy
 * @Author AissEr
 * @Date 2022/10/23 20:10
 * @Version 1.0
 * @Description 修改基础配置信息的策略
 **/
public class BaseConfigureStrategy implements ConfigureStrategy<Map<String,String>>{
    public static final String name = ConfigureOptions.BASE.getName();

    @Override
    public void alterConfigureContent(ApiObject apiObject, Map<String,String> value) {
        for (String item : ConfigureOptions.BASE.getOptions()) {
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
