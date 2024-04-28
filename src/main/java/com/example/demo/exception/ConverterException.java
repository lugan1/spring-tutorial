package com.example.demo.exception;

import com.example.demo.enumeration.ErrorCode;

public class ConverterException extends BusinessException {
    public ConverterException() {
        super(ErrorCode.CONVERTER_ERROR);
    }
}
