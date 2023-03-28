package com.example.study.user;

import java.text.DecimalFormat;
import java.util.*;

public class ParkingPrice {//유저 고유데이터에 표시할 주차요금 합계 계산 클래스
	boolean[] parking = new boolean[10000]; //차량 입차 여부
    int[] parkingTime = new int[10000]; //차량 총 누적 주차 시간
    Map<Integer, Integer> map = new HashMap<>();

    public String solution(int[] fees, String[] records) {

        // 1. records에서 입 출차기록 읽어서 반영
        /*
         * IN -> 차량번호에 맞는 parking true로 변경
         *      입차시간 분 단위로 변경해서 map에 저장 <치량번호, 입차시간(분)>
         * 
         * OUT -> 차량번호에 맞는 parking false로 변경
         *      map에서 입차시간(분) 찾아오고, 출차시간(분)으로 총 누적시간 계산
         *      parkingTime 배열에 누적시간 저장
         * 
         *      만약에 여러번 입-출차 반복시 누적시간 더해서 저장하는것주의
         * 
         * 마지막에 parking 배열 체크해서, 출차하지 않은애들 일괄 처리
         */


        // 1. 차량별 누적 주차시간 계산
        for(String r : records) {

            StringTokenizer st = new StringTokenizer(r,":| ");
            int hr = Integer.parseInt( st.nextToken() );
            int mn = Integer.parseInt( st.nextToken() );
            int car = Integer.parseInt( st.nextToken() );
            String cmd = st.nextToken();

            int time = hr*60+mn;//입차시간 분

            if("IN".equals(cmd)) { // IN 입차
                parking[car] = true; // 차량 입차 여부
                map.put(car, time); //맵에 차량번호랑 입차시간 넣음
            }
            else { // OUT 출차
                parking[car]= false; // 차량 출차
                int inTime = map.get(car); //맵에서 입차시간 가져오기
                parkingTime[car] += time - inTime; // 총 누적 시간
            }


        } // for records

        for(int i=0;i<parking.length;i++) {
            if(parking[i]==true) { //출차 내역이 없으면
                int inTime = map.get(i); //맵에서 입차시간 가져오기
                parkingTime[i] += (23*60+59)-inTime; // 출차 강제계산

            }
        }


        // 2. 누적 주차시간으로 요금 계산
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < parkingTime.length; i++) {
            if(parkingTime[i] == 0) continue;
            int result =0;
            result = cal(fees[0], fees[1], fees[2], fees[3], parkingTime[i]);

            list.add(result);
        }

        int[] answer = list.stream().mapToInt(Integer::intValue).toArray();
        
        
        
        int priceresult =0;
        for(int i=0;i<answer.length;i++) {
        	priceresult+=answer[i];
        }
        
        DecimalFormat df = new DecimalFormat("###,###");
		String value_str = df.format(priceresult);
		return value_str;
    }
    
    //콤마 찍기
    public String comma(int value) {
		DecimalFormat df = new DecimalFormat("###,###");
		String value_str = df.format(value);
		return value_str;
	}//comma()
    
    //주차요금 계산
    public int cal(int nMinute,int nCost,int exMinute, int exCost ,int useMinute) {
        int cost =0;

        int calMinute =(int)Math.ceil((double)(useMinute-nMinute)/exMinute);

        if(useMinute>nMinute) {
            cost = nCost+(calMinute)*exCost;
        }
        else {
            cost = nCost;
        }

        return cost;
    }//cal()
}//class ParkingPrice
