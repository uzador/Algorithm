package com.luxoft.solutions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Solution1 {
    public static void solution(String S) {
        Map<String, List<String>> phoneList = Arrays.asList(S.split("\\n")).stream()
                .map(elem -> elem.split(","))
                .collect(Collectors.groupingBy(phone -> phone[1], Collectors.mapping(phone -> phone[0], Collectors.toList())));

        Map<String, String[]> timeList = new HashMap<>();
        phoneList.forEach((k, v) -> {
            timeList.put(
                    k,
                    v.stream().map(val -> val.split(":")).reduce((a, b) -> {
                        int a1 = Integer.parseInt(a[0]);
                        int a2 = Integer.parseInt(a[1]);
                        int a3 = Integer.parseInt(a[2]);
                        int b1 = Integer.parseInt(b[0]);
                        int b2 = Integer.parseInt(b[1]);
                        int b3 = Integer.parseInt(b[2]);
                        a[0] = new StringBuilder().append(a1 + b1).toString();
                        a[1] = new StringBuilder().append(a2 + b2).toString();
                        a[2] = new StringBuilder().append(a3 + b3).toString();

                        return a;
                    }).get()
            );
        });

//        timeList.forEach((k, v) -> {
//            System.out.print("k: " + k);
//            System.out.println(" v: " + Arrays.toString(v));
//        });

        Map<String, Integer> timeInSecList = timeList.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, v -> Integer.parseInt(v.getValue()[2]) + Integer.parseInt(v.getValue()[1]) * 60 + Integer.parseInt(v.getValue()[0]) * 360));

        System.out.println(timeInSecList);

        Integer max = timeInSecList.entrySet().stream()
                .map(Map.Entry::getValue)
                .max(Integer::max).get();

        System.out.println("Max: " + max);

        System.out.println(timeInSecList);
        String phone = null;
        for (Map.Entry<String, Integer> entry : timeInSecList.entrySet()) {
            if (entry.getValue() == max) {
                if (phone == null) {
                    phone = entry.getKey();
                } else {
                    if (phone.compareTo(entry.getKey()) > 0) {
                        phone = entry.getKey();
                    }
                }
            }
        }

        System.out.println("phone: " + phone);
    }

    public static void main(String[] args) {
        String str = "00:01:07,400-234-090\n" +
                "00:05:01,701-080-080\n" +
                "00:05:00,400-234-090\n" +
                "00:06:07,400-234-091";
        Solution1.solution(str);
    }
}