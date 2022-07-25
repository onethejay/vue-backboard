package com.example.vuebackboard.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class StreamTest {

    @DisplayName("1. 스트림 생성")
    @Test
    void test_1(){
        List<Map<String, Object>> mapList = new ArrayList<>();

        mapList.stream();        //List Collection의 .stream() 메서드를 통해 스트림을 생성한다.
    }

    @DisplayName("2. 스트림 중간연산 - filter")
    @Test
    void test_2(){
        List<Map<String, Object>> mapList = new ArrayList<>();
        Map<String, Object> amountMap;

        amountMap = new HashMap<>();
        amountMap.put("currencyCd", "KRW");
        amountMap.put("amount", 120000);
        mapList.add(amountMap);

        amountMap = new HashMap<>();
        amountMap.put("currencyCd", "USD");
        amountMap.put("amount", 100);
        mapList.add(amountMap);

        amountMap = new HashMap<>();
        amountMap.put("currencyCd", "CNY");
        amountMap.put("amount", 240000);
        mapList.add(amountMap);

        int krwAmount = 0;

        for (Map<String, Object> amountMapp : mapList) {
            if ("KRW".equals(amountMapp.get("currencyCd"))) {
                krwAmount = (int) amountMapp.get("amount");
            }
        }

        assertThat(krwAmount).isEqualTo(120000);

        Map<String, Object> krwMap = mapList.stream()
                .filter(m -> "KRW".equals(m.get("currencyCd")))     //mapList에서 currencyCd가 "KRW"인 객체가 필터링  (Stream)
                .findFirst()                                        //필터링된 Stream에서 객체를 찾아 꺼낸다. - 최종연산 (Optional<Map<String, Object>>)
                .get();                                             //Optional 객체에서 데이터를 꺼내 krwMap에 할당한다.

        int streamKrwAmount = (int) krwMap.get("amount");

        assertThat(krwAmount).isEqualTo(120000);
        assertThat(streamKrwAmount).isEqualTo(krwAmount);
    }

    @DisplayName("3. 스트림 중간연산 - map")
    @Test
    void test_3(){
        List<Map<String, Object>> mapList = new ArrayList<>();
        Map<String, Object> amountMap;

        amountMap = new HashMap<>();
        amountMap.put("currencyCd", "KRW");
        amountMap.put("amount", 120000);
        mapList.add(amountMap);

        amountMap = new HashMap<>();
        amountMap.put("currencyCd", "USD");
        amountMap.put("amount", 100);
        mapList.add(amountMap);

        amountMap = new HashMap<>();
        amountMap.put("currencyCd", "CNY");
        amountMap.put("amount", 240000);
        mapList.add(amountMap);

        Map<String, Object> krwMap = mapList.stream()
                .filter(m -> "KRW".equals(m.get("currencyCd")))     //mapList에서 currencyCd가 "KRW"인 객체가 필터링  (Stream)
                .findFirst()                                        //필터링된 Stream에서 객체를 찾아 꺼낸다. - 최종연산 (Optional<Map<String, Object>>)
                .get();                                             //Optional 객체에서 데이터를 꺼내 krwMap에 할당한다.
        int streamKrwAmount = (int) krwMap.get("amount");
    }
}
