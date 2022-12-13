package common;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName RelevanceVariable
 * @Author AissEr
 * @Date 2022/12/11 21:10
 * @Version 1.0
 * @Description 实现关联操作，如果
 **/
public class RelevanceVariable {
    private static final Pattern REGULAR = Pattern.compile("\\$\\{((\\w+)|(\\w+\\[\\d+\\]))\\}{1}");
    public static Map<String, Object> relevanceData = new HashMap<>();

    public static void addRelevanceVariable(String variableName, Object variableVale) {
        relevanceData.put(variableName, variableVale);
    }

    public static Object getRelevanceVariable(String variableName) {
        return relevanceData.get(variableName);
    }

    // 根据字符串替换
    public static String replaceByRelevanceVariable(String target) {
        Matcher matcher = REGULAR.matcher(target);
        List<String> matcherList = new ArrayList<>();
        String result = target;
        while(matcher.find()){
            matcherList.add(matcher.group());
        }
        for(String replaced : matcherList){
            String name = replaced.substring(2, replaced.length()-1);
            String replacement = "";
            // 如果是从数组找，例如ids[1]
            if(name.contains("[")){
                int i = name.indexOf('[');
                int j = name.indexOf(']');
                String realName = name.substring(0,i);
                String index = name.substring(i+1,j);
                List relevanceVariable = (List) getRelevanceVariable(realName);
                // 将其转换为字符串，但要注意是否为数字类型
                Object o = relevanceVariable.get(Integer.parseInt(index));
                if(o instanceof Number){
                    replacement = String.valueOf(o);
                }else {
                    replacement = (String) o;
                }
            }
            // 否则直接匹配
            else {
                replacement = (String) getRelevanceVariable(name);
            }
            result = result.replace(replaced, replacement);
        }
        return result;
    }

    // 根据map替换
    public static void replaceByRelevanceVariable(Map<String,Object> map){
        Set<String> keys = map.keySet();
        for(String key : keys){
            Object o = map.get(key);
            // 使用关联必须是字符串类型
            if(o instanceof String){
                String value = replaceByRelevanceVariable((String) o);
                map.replace(key,value);
            }
        }
    }
}