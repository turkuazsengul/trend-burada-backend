package com.example.msapiproduct.Converter;

import com.example.msapiproduct.Core.AbstractBaseConverter;
import com.example.msapiproduct.Entity.StockEntity;
import com.example.msapiproduct.Model.StockDto;
import org.springframework.stereotype.Component;

@Component
public class StockConverter extends AbstractBaseConverter<StockDto, StockEntity> {
}
