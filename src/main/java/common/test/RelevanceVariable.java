package common.test;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName RelevanceVariable
 * @Author AissEr
 * @Date 2022/12/11 21:10
 * @Version 1.0
 * @Description 实现关联操作
 **/
public class RelevanceVariable {
    /**
     *  默认的使用关联的正则表达式，
     *  如 ${name}，找出关联数据集中，名称为name的数据
     *  如 ${ids[1]}，找出存入关联数据集中，名称为ids的列表，取出列表中第二个元素
     */
    public static final Pattern DEFAULT_REGULAR = Pattern.compile("\\$\\{((\\w+)|(\\w+\\[\\d+\\]))\\}{1}");
    // 可以修改匹配规则（默认规则，${key}），指定对应的匹配表达式即可，但key名称的规则仍只有以上两种
    public static Pattern REGULAR = DEFAULT_REGULAR;
    // 存放关联数据的地方
    private static Map<String, Object> relevanceData = new HashMap<>();

    public static void addRelevanceVariable(String variableName, Object variableVale) {
        relevanceData.put(variableName, variableVale);
    }

    public static Object getRelevanceVariable(String variableName) {
        return relevanceData.get(variableName);
    }

    public static void clear(){
        relevanceData.clear();
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
                replacement = objToString(o);
            }
            // 否则直接匹配
            else {
                replacement = objToString(getRelevanceVariable(name));
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

    private static String objToString(Object obj){
        if(obj instanceof Number){
            return String.valueOf(obj);
        }else {
            return (String) obj;
        }
    }
}