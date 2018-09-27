package com.sda;

import java.util.*;

public class Application {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<Integer, String>() {{
            put(2, "dwa");
            put(3, "trzy");
            put(4, "cztery");
            put(5, null);
            put(6, "");
        }};
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            Integer key = entry.getKey();
            Optional<String> value = Optional.ofNullable(entry.getValue());
            //2: 3
            //3: 4
            //4: 6
            //5: 0
            //6: 0
        }
    }
}
