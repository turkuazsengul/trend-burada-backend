package com.example.msapiproduct.Service.Impl;

import com.example.msapiproduct.Converter.ProductConverter;
import com.example.msapiproduct.Core.BaseResponse;
import com.example.msapiproduct.Core.BaseResponseCodeEnum;
import com.example.msapiproduct.Core.BaseResponseMessageEnum;
import com.example.msapiproduct.Entity.ProductEntity;
import com.example.msapiproduct.FeignClient.Model.OrderDto;
import com.example.msapiproduct.Integration.OrderIntegration;
import com.example.msapiproduct.Model.ProductDto;
import com.example.msapiproduct.Repository.ProductRepository;
import com.example.msapiproduct.Service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {
    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductConverter productConverter;

    @Autowired
    private OrderIntegration orderIntegration;

    @Override
    public BaseResponse<ProductDto> addProduct(ProductDto productDto) {
        BaseResponse<ProductDto> baseResponse = new BaseResponse<>();
        try{
            ProductEntity productEntity = productRepository.save(productConverter.convertToEntity(productDto));
            ProductDto productDtoResponse = productConverter.convertToDto(productEntity);

            baseResponse.setReturnCode(BaseResponseCodeEnum.SUCCESS_CODE.getCode());
            baseResponse.setReturnMessage(BaseResponseMessageEnum.SUCCESS_MESSAGE.getMessage());
            baseResponse.addList(productDtoResponse);

            logger.info("Success! add product to db  ==>  added product model: " + productDtoResponse);
        }catch (Exception e){
            logger.error("Add Product Process Fail: " + e);
            baseResponse.setReturnCode(BaseResponseCodeEnum.UNSUCCESS_CODE.getCode());
            baseResponse.setReturnMessage(BaseResponseMessageEnum.UNSUCCESS_MESSAGE.getMessage());
        }

        return baseResponse;
    }

    @Override
    public BaseResponse<ProductDto> gelAllProduct() {
        BaseResponse<ProductDto> baseResponse = new BaseResponse<>();
        try{
            List<ProductEntity> productEntity = productRepository.findAll();
            List<ProductDto> productDtoResponse = productConverter.convertToDtoList(productEntity);

            baseResponse.setResultCount(productDtoResponse.size());
            baseResponse.setReturnCode(BaseResponseCodeEnum.SUCCESS_CODE.getCode());
            baseResponse.setReturnMessage(BaseResponseMessageEnum.SUCCESS_MESSAGE.getMessage());
            baseResponse.setReturnData(productDtoResponse);

            logger.info("Success! get product list  ==>  got product model: " + productDtoResponse);
        }catch (Exception e){
            baseResponse.setReturnCode(BaseResponseCodeEnum.UNSUCCESS_CODE.getCode());
            baseResponse.setReturnMessage(BaseResponseMessageEnum.UNSUCCESS_MESSAGE.getMessage());
        }
        return baseResponse;
    }

    @Override
    public BaseResponse<ProductDto> deleteProduct(int productId) {
        BaseResponse<ProductDto> baseResponse = new BaseResponse<>();
        try{
            productRepository.deleteById(productId);
            baseResponse.setReturnCode(BaseResponseCodeEnum.SUCCESS_CODE.getCode());
            baseResponse.setReturnMessage(BaseResponseMessageEnum.SUCCESS_MESSAGE.getMessage());

            logger.info("Success! Delete product on db");
        }catch (Exception e){
            logger.error("Delete Product Process Fail: " + e);
            baseResponse.setReturnCode(BaseResponseCodeEnum.UNSUCCESS_CODE.getCode());
            baseResponse.setReturnMessage(BaseResponseMessageEnum.UNSUCCESS_MESSAGE.getMessage());
        }
        return baseResponse;
    }

    @Override
    public String addProduct2Order(ProductDto productDto, int userId) {
        String response;
        OrderDto orderDto = orderIntegration.addOrder(productDto,userId);
        response = orderDto != null ? "Siparişiniz sepete başarıyla eklendi":"Bir hata oluştu";

        return response;
    }
}
