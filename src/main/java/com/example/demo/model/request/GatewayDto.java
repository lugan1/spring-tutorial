package com.example.demo.model.request;

import lombok.Value;

import java.util.List;

@Value
public class GatewayDto {
    String type;
    String id;
    List<GatewayDeviceDto> devices;
}
