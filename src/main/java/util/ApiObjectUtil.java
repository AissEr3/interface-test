package util;

import api.ApiObject;

import java.lang.reflect.Field;

/**
 * @ClassName ApiObjectUtil
 * @Author AissEr
 * @Date 2022/10/23 18:54
 * @Version 1.0
 * @Description TODO
 **/
public class ApiObjectUtil {
    public static final String[] items = getConfigurationItem();

    /**
     * 通过反射获取ApiObject所有的属性名称，方便配置使用
     * @return APIObjectFieldsName
     */
    private static String[] getConfigurationItem(){
        Field[] fields = ApiObject.class.getDeclaredFields();
        String[] items = new String[fields.length];
        for(int i = 0; i < fields.length; i++){
            items[i] = fields[i].getName();
        }
        return items;
    }

    /**
     * 通过反射配置apiObject类，主要为具体配置策略服务
     * @param apiObject
     * @param fieldName
     * @param obj
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static void setApiObject(ApiObject apiObject,String fieldName,Object obj) throws NoSuchFieldException, IllegalAccessException {
        Field field = ApiObject.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(apiObject,obj);
    }

}
