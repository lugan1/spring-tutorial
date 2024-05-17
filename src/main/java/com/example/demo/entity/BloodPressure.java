package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.persistence.*;



@Entity
public class BloodPressure {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "max_value", nullable = false)
    int maxValue; /*수축 혈압*/

    @Column(name = "min_value", nullable = false)
    int minValue; /*이완 혈압*/

    @Column(name = "measurement_date", nullable = false)
    String measurementDate; /*측정 일시*/
}
