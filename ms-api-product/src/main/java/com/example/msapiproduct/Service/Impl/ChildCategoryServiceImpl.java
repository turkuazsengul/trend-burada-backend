package com.example.msapiproduct.Service.Impl;

import com.example.msapiproduct.Converter.ChildCategoryConverter;
import com.example.msapiproduct.Core.BaseResponse;
import com.example.msapiproduct.Core.BaseResponseCodeEnum;
import com.example.msapiproduct.Core.BaseResponseMessageEnum;
import com.example.msapiproduct.Model.CategoryDto;
import com.example.msapiproduct.Model.ChildCategoryDto;
import com.example.msapiproduct.Repository.ChildCategoryRepository;
import com.example.msapiproduct.Service.ChildCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChildCategoryServiceImpl implements ChildCategoryService {

    private final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private ChildCategoryRepository childCategoryRepository;

    @Autowired
    private ChildCategoryConverter childCategoryConverter;

    @Override
    public BaseResponse<ChildCategoryDto> getAllCategory() {
        BaseResponse<ChildCategoryDto> baseResponse = new BaseResponse<>();
        try{
            List<ChildCategoryDto> childCategoryDtoList = childCategoryConverter.convertToDtoList(childCategoryRepository.findAll());

            baseResponse.setResultCount(childCategoryDtoList.size());
            baseResponse.setReturnCode(BaseResponseCodeEnum.SUCCESS_CODE.getCode());
            baseResponse.setReturnMessage(BaseResponseMessageEnum.SUCCESS_MESSAGE.getMessage());
            baseResponse.setReturnData(childCategoryDtoList);

            logger.info("Success! get child category list from db  ==>  got category model: " + childCategoryDtoList);
        }catch (Exception e){
            logger.error("Get Child Category list from DB error: " + e.getMessage());
            baseResponse.setReturnCode(BaseResponseCodeEnum.UNSUCCESS_CODE.getCode());
            baseResponse.setReturnMessage(BaseResponseMessageEnum.UNSUCCESS_MESSAGE.getMessage());
        }

        return baseResponse;
    }

    @Override
    public BaseResponse<ChildCategoryDto> getCategoryById(Integer pkId) {
        return null;
    }

    @Override
    public BaseResponse<ChildCategoryDto> addCategory(ChildCategoryDto childCategoryDto) {
        return null;
    }
}
