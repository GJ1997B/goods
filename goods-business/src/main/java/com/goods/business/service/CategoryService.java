package com.goods.business.service;

import com.goods.common.model.business.ProductCategory;
import com.goods.common.response.ResponseBean;
import com.goods.common.vo.business.ProductCategoryTreeNodeVO;
import com.goods.common.vo.business.ProductCategoryVO;
import com.goods.common.vo.system.PageVO;

import java.util.List;

public interface CategoryService {

    /**
     * 类别列表展示
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageVO<ProductCategoryTreeNodeVO> categoryTree(Integer pageNum, Integer pageSize);

    /**
     * 添加-获取父分类
     * @return
     */
    List<ProductCategoryTreeNodeVO> getParentCategoryTree();
}
