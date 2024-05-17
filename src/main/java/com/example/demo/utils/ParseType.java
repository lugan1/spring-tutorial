package com.example.demo.utils;

/**
 * ParseType 열거형은 아모밴드에서 게이트웨이로 전달되는 Raw 데이터를 파싱하는데 사용됩니다.
 * 각 열거형 값은 파싱할 데이터의 시작과 끝 인덱스를 나타냅니다.
 *
 * <p>아래는 각 열거형 값이 나타내는 데이터를 설명합니다:
 * <ul>
 *   <li>OXYGEN: 산소 수치</li>
 *   <li>PULSE: 맥박 수치</li>
 *   <li>BLOOD_PRESSURE_MAX: 최대 혈압</li>
 *   <li>BLOOD_PRESSURE_MIN: 최소 혈압</li>
 *   <li>BODY_TEMPERATURE: 체온</li>
 *   <li>STEP: 걸음 수</li>
 *   <li>BATTERY: 배터리 수치</li>
 * </ul>
 *
 * <p>각 열거형 값의 {@code parse} 메서드는 Raw 데이터 문자열에서 해당 데이터를 추출하고 정수로 변환합니다.
 * 만약 Raw 데이터 문자열이 규격에 맞지 않으면 {@code IllegalArgumentException}을 던집니다.
 *
 * @author lugan1
 */
public enum ParseType {
    OXYGEN(36, 38),
    PULSE(38, 40),
    BLOOD_PRESSURE_MAX(28, 30),
    BLOOD_PRESSURE_MIN(30, 32),
    BODY_TEMPERATURE(32, 36),
    STEP(24, 28),
    BATTERY(40, 42);

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
        if(advertising == null || advertising.length() < end) {
            throw new IllegalArgumentException("Advertising 데이터가 아모밴드의 규격에 맞지 않습니다.");
        }

        return Integer.parseInt(advertising.substring(start, end), 16);
    }
}
