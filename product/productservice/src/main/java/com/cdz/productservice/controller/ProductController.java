package com.cdz.productservice.controller;

import com.cdz.common.urlparam.OrderFrom;
import com.cdz.common.utils.ResultUtil;
import com.cdz.common.vo.ProductItemVo;
import com.cdz.common.vo.ProductVo;
import com.cdz.common.vo.ResultVo;
import com.cdz.common.domain.ProductCategory;
import com.cdz.common.domain.ProductInfo;
import com.cdz.productservice.service.ProductCategoryService;
import com.cdz.productservice.service.ProductInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author chendezhi
 * @date 2018/4/20 10:22
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    private static ProductItemVo productInfo2ProductItemVo(ProductInfo productInfo) {
        ProductItemVo productItemVo = new ProductItemVo();
        BeanUtils.copyProperties(productInfo, productItemVo);
        return productItemVo;
    }

    /**
     * 通过productid list来获取对应的所有product详情
     *  提供外部服务
     * @param productIds
     * @return
     */
    @PostMapping("/productIds")
    public List<ProductInfo> findByProductIdList(@RequestBody List<String> productIds){
        List<ProductInfo> products = productInfoService.findByProductIdIn(productIds);
        return products;
    }


    /**
     * 扣库存
     *  提供外部服务
     * @param orderItems
     */
    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<OrderFrom.OrderItem> orderItems){

        productInfoService.decreaseStock(orderItems);
    }


    @GetMapping("")
    public ResultVo findAll() throws InterruptedException {

        //查询所有的 商品信息

        List<ProductInfo> productInfoList = productInfoService.findAllUp();

        //查询所有商品分类信息

        List<ProductCategory> productCategoryList = productCategoryService.findAll();
        //将 将商品信息 对应封装

        Map<Integer, List<ProductInfo>> groupByCategoryType = productInfoList.stream()
                .collect(Collectors
                        .groupingBy(ProductInfo::getCategoryType));
        //拼接页面展示信息

        List<ProductVo> productVos = productCategoryList.stream().map(productCategory -> {
            ProductVo productVo = new ProductVo();
            BeanUtils.copyProperties(productCategory, productVo);
            if (groupByCategoryType.containsKey(productCategory.getCategoryType())){
                List<ProductItemVo> productItemVos = groupByCategoryType
                        .get(productCategory.getCategoryType())
                        .stream()
                        .map(ProductController::productInfo2ProductItemVo)
                        .collect(Collectors.toList());
                productVo.setProductItemVoList(productItemVos);
                return productVo;
            }
            return null;
        }).filter(Objects::nonNull)
          .collect(Collectors.toList());

//        Thread.sleep(600);
        return ResultUtil.success(productVos);
    }
}
