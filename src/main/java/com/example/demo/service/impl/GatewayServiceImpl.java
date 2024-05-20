package com.example.demo.service.impl;

import com.example.demo.model.request.GatewayDto;
import com.example.demo.service.GatewayService;
import com.example.demo.utils.ParseType;

import java.util.Objects;

public class GatewayServiceImpl implements GatewayService {


    @Override
    public void saveAdvertisement(GatewayDto gatewayDto) {
        gatewayDto.getDevices()
                .stream()
                .filter(device -> Objects.equals(device.getName(), "BDTB"))
                .forEach(device -> {
                    String advertising = device.getAdvertising();
                    String macAddress = device.getId();
                });

    }
}
