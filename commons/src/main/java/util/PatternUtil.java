package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class PatternUtil {

    /**
     * 获取复合redisKey上面的id
     *
     * @param listName
     * @param key
     * @return
     */
    public static String getRedisListKeyNumber(String listName,String key){

        final String pattern = listName+"([1-9][0-9]+)$";

        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(key);

        return matcher.find()==true? matcher.group(1):null;

    }


    /**
     *获取list基本元素的类型名
     *
     * @param list
     * @return
     */
    public static String getListElementType(String list){
        final String patttern = "^java.util.List<(\\S+)>$";

        Pattern compile = Pattern.compile(patttern);
        Matcher matcher = compile.matcher(list);

        return matcher.find()==true? matcher.group(1):null;
    }


    /**
     * 获取map的key和value的类型名
     *
     * @param mapTypeName
     * @return
     */
    public static Map<String,String> getMapKeyAndValueType(String mapTypeName){
        final String pattern= "^java.util.Map<(\\S+), (\\S+)>$";

        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(mapTypeName);

        if (matcher.find()){
            Map<String,String> map = new HashMap<>();
            map.put("key",matcher.group(1));
            map.put("value",matcher.group(2));

            return map;
        }

        return null;
    }


    /**
     * 获取字符串中的整型数字
     * @param str
     * @return
     */
    public static List<Integer> getStringNum(String str){
        final String pattern = "\\d+";

        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(str);
        List<Integer> integers = new ArrayList<>();

        while (matcher.find()){
            integers.add(Integer.valueOf(matcher.group()));
        }

        return integers;

    }


    /**
     * 获取字符串中的long类型数据
     * @param str
     * @return
     */
    public static List<Long> getStringNumLong(String str){
        final String pattern= "\\d+";

        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(str);
        List<Long> longs = new ArrayList<>();

        while (matcher.find()){
            longs.add(Long.valueOf(matcher.group()));
        }

        return longs;
    }

}
