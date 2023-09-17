package com.example.msapiproduct.Service;

import com.example.msapiproduct.Core.BaseResponse;
import com.example.msapiproduct.Model.StockDto;

public interface StockService {
    BaseResponse<StockDto> getAllStockStatus();
    BaseResponse<StockDto> getStockStatusById(int id);
    BaseResponse<StockDto> deleteStockStatus(int id);
    BaseResponse<StockDto> addStockStatus(StockDto stockDto);
}
