package com.example.demo.model.request;

import lombok.Value;

@Value
public class GatewayDeviceDto {
    String id;
    String name;
    String advertising;
    String scan_response;
}
