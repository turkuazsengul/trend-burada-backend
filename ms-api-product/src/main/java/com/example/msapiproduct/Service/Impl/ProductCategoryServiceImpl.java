package com.example.msapiproduct.Service.Impl;

import com.example.msapiproduct.Converter.ProductCategoryConverter;
import com.example.msapiproduct.Core.BaseResponse;
import com.example.msapiproduct.Core.BaseResponseCodeEnum;
import com.example.msapiproduct.Core.BaseResponseMessageEnum;
import com.example.msapiproduct.Entity.ProductCategoryEntity;
import com.example.msapiproduct.Model.*;
import com.example.msapiproduct.Repository.ProductCategoryRepository;
import com.example.msapiproduct.Service.ProductCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private ProductCategoryConverter productCategoryConverter;

    @Override
    public BaseResponse<ProductCategoryDto> getAllProductCategory() {
        BaseResponse<ProductCategoryDto> baseResponse = new BaseResponse<>();
        try{
            List<ProductCategoryDto> productCategoryDtoList = productCategoryConverter.convertToDtoList(productCategoryRepository.findAll());

            baseResponse.setResultCount(productCategoryDtoList.size());
            baseResponse.setReturnCode(BaseResponseCodeEnum.SUCCESS_CODE.getCode());
            baseResponse.setReturnMessage(BaseResponseMessageEnum.SUCCESS_MESSAGE.getMessage());
            baseResponse.setReturnData(productCategoryDtoList);

        }catch (Exception e){
            logger.error("Get Category list from DB error: " + e.getMessage());
            baseResponse.setReturnCode(BaseResponseCodeEnum.UNSUCCESS_CODE.getCode());
            baseResponse.setReturnMessage(BaseResponseMessageEnum.UNSUCCESS_MESSAGE.getMessage());
        }

        return baseResponse;
    }

    @Override
    public BaseResponse<ProductCategoryDto> getProductCategoryById(Integer pkId) {
        BaseResponse<ProductCategoryDto> baseResponse = new BaseResponse<>();
        try{
            ProductCategoryDto productCategoryDto = productCategoryConverter.convertToDto(productCategoryRepository.findByPkId(pkId));

            baseResponse.setReturnCode(BaseResponseCodeEnum.SUCCESS_CODE.getCode());
            baseResponse.setReturnMessage(BaseResponseMessageEnum.SUCCESS_MESSAGE.getMessage());
            baseResponse.addList(productCategoryDto);

        }catch (Exception e){
            logger.error("Get Category list from DB error: " + e.getMessage());
            baseResponse.setReturnCode(BaseResponseCodeEnum.UNSUCCESS_CODE.getCode());
            baseResponse.setReturnMessage(BaseResponseMessageEnum.UNSUCCESS_MESSAGE.getMessage());
        }

        return baseResponse;
    }

    @Override
    public BaseResponse<ProductCategoryDto> addProductCategory(ProductCategoryDto productCategoryDto) {
        BaseResponse<ProductCategoryDto> baseResponse = new BaseResponse<>();
        try{
            ProductCategoryEntity productCategoryEntity = productCategoryRepository.save(productCategoryConverter.convertToEntity(productCategoryDto));
            ProductCategoryDto productCategoryDto1 = productCategoryConverter.convertToDto(productCategoryEntity);

            baseResponse.setReturnCode(BaseResponseCodeEnum.SUCCESS_CODE.getCode());
            baseResponse.setReturnMessage(BaseResponseMessageEnum.SUCCESS_MESSAGE.getMessage());
            baseResponse.addList(productCategoryDto1);

        }catch (Exception e){
            logger.error("Category saving error: " + e.getMessage());
            baseResponse.setReturnCode(BaseResponseCodeEnum.UNSUCCESS_CODE.getCode());
            baseResponse.setReturnMessage(BaseResponseMessageEnum.UNSUCCESS_MESSAGE.getMessage());
        }

        return baseResponse;
    }

    @Override
    public BaseResponse<ProductCategoryDropDownItem> getProductCategoryDropDownItem() {
        BaseResponse<ProductCategoryDropDownItem> baseResponse = new BaseResponse<>();
        ProductCategoryDropDownItem productCategoryDropDownItem = new ProductCategoryDropDownItem();

        List<ProductCategoryDropDownItem> productCategoryDropDownItemList = new ArrayList<>();
        try{
            List<ProductCategoryDto> productCategoryDtoList = productCategoryConverter.convertToDtoList(productCategoryRepository.findAll());
            baseResponse.setResultCount(productCategoryDtoList.size());
            baseResponse.setReturnCode(BaseResponseCodeEnum.SUCCESS_CODE.getCode());
            baseResponse.setReturnMessage(BaseResponseMessageEnum.SUCCESS_MESSAGE.getMessage());
            baseResponse.setReturnData(productCategoryDropDownItemList);

        }catch (Exception e){
            logger.error("Get Category list from DB error: " + e.getMessage());
            baseResponse.setReturnCode(BaseResponseCodeEnum.UNSUCCESS_CODE.getCode());
            baseResponse.setReturnMessage(BaseResponseMessageEnum.UNSUCCESS_MESSAGE.getMessage());
        }

        return baseResponse;
    }
}
