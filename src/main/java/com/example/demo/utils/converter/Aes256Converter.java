/*
package com.example.demo.utils.converter;


import com.example.demo.exception.ConverterException;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.util.StringUtils;

@Converter
public class Aes256Converter implements AttributeConverter<String, String> {

  private final Aes256Utils aes256Utils;

  public Aes256Converter() {
    this.aes256Utils = new Aes256Utils();
  }

  @Override
  public String convertToDatabaseColumn(String attribute) {
    if (!StringUtils.hasText(attribute)) {
      return attribute;
    }
    try {
      return aes256Utils.encrypt(attribute);
    } catch (Exception e) {
      throw new ConverterException();
    }
  }

  @Override
  public String convertToEntityAttribute(String dbData) {
    if (dbData != null) {
      try {
        return aes256Utils.decrypt(dbData);
      } catch (Exception e) {
        throw new ConverterException();
      }
    }
    else return null;
  }
}
*/
