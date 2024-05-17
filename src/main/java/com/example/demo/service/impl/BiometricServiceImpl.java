package com.example.demo.service.impl;

import com.example.demo.service.BiometricService;
import com.example.demo.utils.ParseType;

public class BiometricServiceImpl implements BiometricService {

    @Override
    public void saveBiometricData(String advertising) {

        int step = ParseType.STEP.parse(advertising);
        int bloodPressureMax = ParseType.BLOOD_PRESSURE_MAX.parse(advertising);
    }
}
