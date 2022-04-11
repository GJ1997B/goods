package com.goods.business.converter;

import com.goods.business.mapper.CategoryMapper;
import com.goods.common.model.business.ProductCategory;
import com.goods.common.vo.business.ProductCategoryTreeNodeVO;
import com.goods.common.vo.business.ProductCategoryVO;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class CategoryConverter {

    /**
     * ProductCategory转ProductCategoryTreeNodeVO
     * @param productCategory
     * @return
     */
    public static List<ProductCategoryTreeNodeVO> CategoryToCategoryTreeNodeVO(List<ProductCategory> productCategory){
        List<ProductCategoryTreeNodeVO> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(productCategory)){
            for (ProductCategory category : productCategory) {
                ProductCategoryTreeNodeVO treeNodeVO = new ProductCategoryTreeNodeVO();
                BeanUtils.copyProperties(category,treeNodeVO);
                list.add(treeNodeVO);
            }
        }
        return list;
    }


     /**
     * ProductCategoryVO转ProductCategoryTreeNodeVO
     * @param productCategoryVO
     * @return*/

    /*public static List<ProductCategoryTreeNodeVO> categoryToCategoryTreeNodeVO(List<ProductCategoryVO> productCategoryVO){
        List<ProductCategoryTreeNodeVO> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(productCategoryVO)){
            for (ProductCategoryVO categoryVO : productCategoryVO) {
                ProductCategoryTreeNodeVO productCategoryTreeNodeVO = new ProductCategoryTreeNodeVO();
                BeanUtils.copyProperties(categoryVO,productCategoryTreeNodeVO);
                list.add(productCategoryTreeNodeVO);
            }
        }
        return list;
    }*/

     /**
     * ProductCategory转ProductCategoryVO
     * @param productCategory
     * @return*/

    public static List<ProductCategoryVO> categoryToProductCategoryVO(List<ProductCategory> productCategory){
        List<ProductCategoryVO> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(productCategory)){
            for (ProductCategory category : productCategory) {
                ProductCategoryVO productCategoryVO = new ProductCategoryVO();
                BeanUtils.copyProperties(category,productCategoryVO);
                list.add(productCategoryVO);
            }
        }
        return list;
    }

}
