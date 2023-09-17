package com.example.msapiproduct.Service.Impl;

import com.example.msapiproduct.Converter.CategoryConverter;
import com.example.msapiproduct.Core.BaseResponse;
import com.example.msapiproduct.Core.BaseResponseCodeEnum;
import com.example.msapiproduct.Core.BaseResponseMessageEnum;
import com.example.msapiproduct.Entity.CategoryEntity;
import com.example.msapiproduct.Model.CategoryDto;
import com.example.msapiproduct.Repository.CategoryRepository;
import com.example.msapiproduct.Service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryServiceImpl implements CategoryService {

    private final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryConverter categoryConverter;


    @Override
    public BaseResponse<CategoryDto> getAllCategory() {
        BaseResponse<CategoryDto> baseResponse = new BaseResponse<>();
        try{
            List<CategoryDto> categoryDtoList = categoryConverter.convertToDtoList(categoryRepository.findAll());

            baseResponse.setResultCount(categoryDtoList.size());
            baseResponse.setReturnCode(BaseResponseCodeEnum.SUCCESS_CODE.getCode());
            baseResponse.setReturnMessage(BaseResponseMessageEnum.SUCCESS_MESSAGE.getMessage());
            baseResponse.setReturnData(categoryDtoList);

            logger.info("Success! get category list from db  ==>  got category model: " + categoryDtoList);
        }catch (Exception e){
            logger.error("Get Category list from DB error: " + e.getMessage());
            baseResponse.setReturnCode(BaseResponseCodeEnum.UNSUCCESS_CODE.getCode());
            baseResponse.setReturnMessage(BaseResponseMessageEnum.UNSUCCESS_MESSAGE.getMessage());
        }

        return baseResponse;
    }

    @Override
    public BaseResponse<CategoryDto> getCategoryById(Integer pkId) {
        BaseResponse<CategoryDto> baseResponse = new BaseResponse<>();
        try{
            CategoryDto categoryDto = categoryConverter.convertToDto(categoryRepository.findByPkId(pkId));

            baseResponse.setReturnCode(BaseResponseCodeEnum.SUCCESS_CODE.getCode());
            baseResponse.setReturnMessage(BaseResponseMessageEnum.SUCCESS_MESSAGE.getMessage());
            baseResponse.addList(categoryDto);

            logger.info("Success! get category from db  ==>  got category model: " + categoryDto);
        }catch (Exception e){
            logger.error("Get Category list from DB error: " + e.getMessage());
            baseResponse.setReturnCode(BaseResponseCodeEnum.UNSUCCESS_CODE.getCode());
            baseResponse.setReturnMessage(BaseResponseMessageEnum.UNSUCCESS_MESSAGE.getMessage());
        }

        return baseResponse;
    }

    @Override
    public BaseResponse<CategoryDto> addCategory(CategoryDto categoryDto) {
        BaseResponse<CategoryDto> baseResponse = new BaseResponse<>();
        try{
            CategoryEntity categoryEntity = categoryRepository.save(categoryConverter.convertToEntity(categoryDto));
            CategoryDto categoryDto1 = categoryConverter.convertToDto(categoryEntity);

            baseResponse.setReturnCode(BaseResponseCodeEnum.SUCCESS_CODE.getCode());
            baseResponse.setReturnMessage(BaseResponseMessageEnum.SUCCESS_MESSAGE.getMessage());
            baseResponse.addList(categoryDto1);

            logger.info("Success! add category list to db  ==>  added category model: " + categoryDto1);
        }catch (Exception e){
            logger.error("Category saving error: " + e.getMessage());
            baseResponse.setReturnCode(BaseResponseCodeEnum.UNSUCCESS_CODE.getCode());
            baseResponse.setReturnMessage(BaseResponseMessageEnum.UNSUCCESS_MESSAGE.getMessage());
        }

        return baseResponse;
    }
}
