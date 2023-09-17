package com.example.msapiproduct.Service.Impl;

import com.example.msapiproduct.Converter.StockConverter;
import com.example.msapiproduct.Core.BaseResponse;
import com.example.msapiproduct.Core.BaseResponseCodeEnum;
import com.example.msapiproduct.Core.BaseResponseMessageEnum;
import com.example.msapiproduct.Entity.StockEntity;
import com.example.msapiproduct.Model.StockDto;
import com.example.msapiproduct.Repository.StockRepository;
import com.example.msapiproduct.Service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StockServiceImpl implements StockService {
    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private StockConverter stockConverter;

    @Autowired
    private StockRepository stockRepository;


    private BaseResponse<StockDto> baseResponse;

    @Override
    public BaseResponse<StockDto> getAllStockStatus() {
        baseResponse = new BaseResponse<>();
        try {
            List<StockEntity> stockEntity = stockRepository.findAll();
            List<StockDto> stockDtoResponse = stockConverter.convertToDtoList(stockEntity);

            baseResponse.setResultCount(stockDtoResponse.size());
            baseResponse.setReturnCode(BaseResponseCodeEnum.SUCCESS_CODE.getCode());
            baseResponse.setReturnMessage(BaseResponseMessageEnum.SUCCESS_MESSAGE.getMessage());
            baseResponse.setReturnData(stockDtoResponse);

            logger.info("Success! get stock-status list from db  ==>  got stock-status model: " + stockDtoResponse);
        } catch (Exception e) {
            baseResponse.setReturnCode(BaseResponseCodeEnum.UNSUCCESS_CODE.getCode());
            baseResponse.setReturnMessage(BaseResponseMessageEnum.UNSUCCESS_MESSAGE.getMessage());
        }
        return baseResponse;
    }

    @Override
    public BaseResponse<StockDto> getStockStatusById(int id) {
        baseResponse = new BaseResponse<>();
        try {
            StockEntity stockEntity = stockRepository.findByPkId(id);
            StockDto stockDtoResponse = stockConverter.convertToDto(stockEntity);

            baseResponse.setReturnCode(BaseResponseCodeEnum.SUCCESS_CODE.getCode());
            baseResponse.setReturnMessage(BaseResponseMessageEnum.SUCCESS_MESSAGE.getMessage());
            baseResponse.addList(stockDtoResponse);

            logger.info("Success! get stock-status from db  ==>  got stock-status model: " + stockDtoResponse);
        } catch (Exception e) {
            logger.error("get Stock-Status Process Fail: " + e);
            baseResponse.setReturnCode(BaseResponseCodeEnum.UNSUCCESS_CODE.getCode());
            baseResponse.setReturnMessage(BaseResponseMessageEnum.UNSUCCESS_MESSAGE.getMessage());
        }
        return baseResponse;
    }

    @Override
    public BaseResponse<StockDto> deleteStockStatus(int id) {
        baseResponse = new BaseResponse<>();
        try {
            stockRepository.deleteById(id);

            baseResponse.setReturnCode(BaseResponseCodeEnum.SUCCESS_CODE.getCode());
            baseResponse.setReturnMessage(BaseResponseMessageEnum.SUCCESS_MESSAGE.getMessage());

            logger.info("Success! delete stock-status on db");
        } catch (Exception e) {
            logger.error("delete Stock-Status Process Fail: " + e);
            baseResponse.setReturnCode(BaseResponseCodeEnum.UNSUCCESS_CODE.getCode());
            baseResponse.setReturnMessage(BaseResponseMessageEnum.UNSUCCESS_MESSAGE.getMessage());
        }
        return baseResponse;
    }

    @Override
    public BaseResponse<StockDto> addStockStatus(StockDto stockDto) {
        baseResponse = new BaseResponse<>();
        /**
         * save işlemi ile tek method içinde Add ve Edit işlemini ayırmak amacıyla.
         */
        boolean isEdit = stockDto.getPkId() != null;
        try {

            /**
             * Tüm stock isimleri büyük kaydedilsin.
             */
            stockDto.setStockStatusName(stockDto.getStockStatusName().toUpperCase());

            StockEntity stockEntity = stockConverter.convertToEntity(stockDto);
            StockEntity stockEntityResponse = stockRepository.save(stockEntity);
            StockDto stockDtoResponse = stockConverter.convertToDto(stockEntityResponse);

            baseResponse.setReturnCode(BaseResponseCodeEnum.SUCCESS_CODE.getCode());
            baseResponse.setReturnMessage(BaseResponseMessageEnum.SUCCESS_MESSAGE.getMessage());
            baseResponse.addList(stockDtoResponse);

            if(isEdit){
                logger.info("Success! edit stock-status from db  ==>  edited stock-status model: " + stockDtoResponse);
            }else{
                logger.info("Success! add stock-status from db  ==>  added stock-status model: " + stockDtoResponse);
            }

        } catch (Exception e) {
            if(isEdit){
                logger.error("edit Stock-Status Process Fail: " + e);
            }else{
                logger.error("add Stock-Status Process Fail: " + e);
            }

            baseResponse.setReturnCode(BaseResponseCodeEnum.UNSUCCESS_CODE.getCode());
            baseResponse.setReturnMessage(BaseResponseMessageEnum.UNSUCCESS_MESSAGE.getMessage());
        }
        return baseResponse;
    }
}
