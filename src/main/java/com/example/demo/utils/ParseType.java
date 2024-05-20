package com.example.demo.utils;

import lombok.Value;

import java.util.Objects;


public enum ParseType {
    OXYGEN(36, 38),
    PULSE(38, 40),
    BLOOD_PRESSURE_MAX(28, 30),
    BLOOD_PRESSURE_MIN(30, 32),
    BODY_TEMPERATURE(32, 36),
    STEP(24, 28),
    BATTERY(40, 42);

    private static final int ADVERTISING_LENGTH = 60;
    private final int start;
    private final int end;

    ParseType(int start, int end){
        this.start = start;
        this.end = end;
    }


    /**
     * 규격 문서는 현재 없기 때문에, 생활치료센터, 정신응급 프로젝트의 파싱 로직을 그대로 사용
     * @link <a href="https://github.com/softnet-devlop/mental_api">정신응급 프로젝트</a>
     * @param advertising 아모밴드 -> 게이트웨이에서 전달되는 Raw 데이터
     * @return int 파싱된 데이터
     * @throws IllegalArgumentException advertising 데이터가 규격에 맞지 않을 경우
     * */
    public int parse(String advertising) throws IllegalArgumentException {
        if(advertising != null && advertising.length() == ADVERTISING_LENGTH) {
            throw new IllegalArgumentException("Advertising 데이터가 아모밴드의 규격에 맞지 않습니다.");
        }

        String substring = Objects.requireNonNull(advertising).substring(start, end);
        return Integer.parseInt(substring, 16);
    }
}
