package com.example.msapiproduct.Service.Impl;

import com.example.msapiproduct.Converter.SubCategoryConverter;
import com.example.msapiproduct.Core.BaseResponse;
import com.example.msapiproduct.Core.BaseResponseCodeEnum;
import com.example.msapiproduct.Core.BaseResponseMessageEnum;
import com.example.msapiproduct.Entity.SubCategoryEntity;
import com.example.msapiproduct.Model.SubCategoryDto;
import com.example.msapiproduct.Repository.SubCategoryRepository;
import com.example.msapiproduct.Service.SubCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SubCategoryServiceImpl implements SubCategoryService {
    private final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private SubCategoryConverter subCategoryConverter;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Override
    public BaseResponse<SubCategoryDto> getAllSubCategory() {
        BaseResponse<SubCategoryDto> baseResponse = new BaseResponse<>();
        try{
            List<SubCategoryDto> subCategoryDtoList = subCategoryConverter.convertToDtoList(subCategoryRepository.findAll());

            baseResponse.setResultCount(subCategoryDtoList.size());
            baseResponse.setReturnCode(BaseResponseCodeEnum.SUCCESS_CODE.getCode());
            baseResponse.setReturnMessage(BaseResponseMessageEnum.SUCCESS_MESSAGE.getMessage());
            baseResponse.setReturnData(subCategoryDtoList);

            logger.info("Success! get sub-category list from db  ==>  got sub-category model: " + subCategoryDtoList);
        }catch (Exception e){
            logger.error("Get Category list from DB error: " + e.getMessage());
            baseResponse.setReturnCode(BaseResponseCodeEnum.UNSUCCESS_CODE.getCode());
            baseResponse.setReturnMessage(BaseResponseMessageEnum.UNSUCCESS_MESSAGE.getMessage());
        }

        return baseResponse;
    }

    @Override
    public BaseResponse<SubCategoryDto> getSubCategoryById(Integer pkId) {
        BaseResponse<SubCategoryDto> baseResponse = new BaseResponse<>();
        try{
            SubCategoryDto subCategoryDto = subCategoryConverter.convertToDto(subCategoryRepository.findByPkId(pkId));

            baseResponse.setReturnCode(BaseResponseCodeEnum.SUCCESS_CODE.getCode());
            baseResponse.setReturnMessage(BaseResponseMessageEnum.SUCCESS_MESSAGE.getMessage());
            baseResponse.addList(subCategoryDto);

            logger.info("Success! get sub-category from db  ==>  got sub-category model: " + subCategoryDto);
        }catch (Exception e){
            logger.error("Get Category list from DB error: " + e.getMessage());
            baseResponse.setReturnCode(BaseResponseCodeEnum.UNSUCCESS_CODE.getCode());
            baseResponse.setReturnMessage(BaseResponseMessageEnum.UNSUCCESS_MESSAGE.getMessage());
        }

        return baseResponse;
    }

    @Override
    public BaseResponse<SubCategoryDto> addSubCategory(SubCategoryDto subCategoryDto) {
        BaseResponse<SubCategoryDto> baseResponse = new BaseResponse<>();
        try{
            SubCategoryEntity subCategoryEntity = subCategoryRepository.save(subCategoryConverter.convertToEntity(subCategoryDto));
            SubCategoryDto subCategoryDto1 = subCategoryConverter.convertToDto(subCategoryEntity);

            baseResponse.setReturnCode(BaseResponseCodeEnum.SUCCESS_CODE.getCode());
            baseResponse.setReturnMessage(BaseResponseMessageEnum.SUCCESS_MESSAGE.getMessage());
            baseResponse.addList(subCategoryDto1);

            logger.info("Success! add sub-category from db  ==>  added sub-category model: " + subCategoryDto);
        }catch (Exception e){
            logger.error("Category saving error: " + e.getMessage());
            baseResponse.setReturnCode(BaseResponseCodeEnum.UNSUCCESS_CODE.getCode());
            baseResponse.setReturnMessage(BaseResponseMessageEnum.UNSUCCESS_MESSAGE.getMessage());
        }

        return baseResponse;
    }
}
