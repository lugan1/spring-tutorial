package com.example.demo.entity;

import javax.persistence.*;

@Entity
public class BloodPressure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "max_value", nullable = false)
    int maxValue; /*수축 혈압*/

    @Column(name = "min_value", nullable = false)
    int minValue; /*이완 혈압*/

    @Column(name = "measurement_date", nullable = false)
    String measurementDate; /*측정 일시*/
}
