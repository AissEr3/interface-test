package utils;

import api.ApiObject;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @ClassName ApiObjectUtil
 * @Author AissEr
 * @Date 2022/10/23 18:54
 * @Version 1.0
 * @Description TODO
 **/
public class ApiObjectUtil {
    public static final String[] items = getConfigurationItem();

    private static String[] getConfigurationItem(){
        Field[] fields = ApiObject.class.getDeclaredFields();
        String[] items = new String[fields.length];
        for(int i = 0; i < fields.length; i++){
            items[i] = fields[i].getName();
        }
        return items;
    }

    public static void setApiObject(ApiObject apiObject,String fieldName,Object obj) throws NoSuchFieldException, IllegalAccessException {
        Field field = ApiObject.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(apiObject,obj);
    }

}
