import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Greed {

    public static void main(String[] args) {
        int[] money = {1, 2, 5, 10, 20, 50, 100};
        int[] count = {10, 2, 5, 6, 2, 1, 5};
        int num = 999;
        Map map = getBest(num,money,count);
        System.out.println(map);
    }

    public static Map getBest(int num, int[] money, int[] counts) {
        Map<Integer, Integer> map = new LinkedHashMap<>();
        for (int i = money.length - 1;i >= 0; i--) {
            if ((num / money[i]) >= counts[i]) {
                map.put(money[i], counts[i]);
                num = num - counts[i]*money[i];
            }else {
                if ((num / money[i]) != 0){
                    map.put(money[i], num / money[i]);
                    num = num % money[i];
                }
            }
        }
        return map;
    }
}
