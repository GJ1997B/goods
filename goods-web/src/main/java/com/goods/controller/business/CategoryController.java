package com.goods.controller.business;

import com.goods.business.service.CategoryService;
import com.goods.common.model.business.ProductCategory;
import com.goods.common.response.ResponseBean;
import com.goods.common.vo.business.ProductCategoryTreeNodeVO;
import com.goods.common.vo.business.ProductCategoryVO;
import com.goods.common.vo.system.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/business/productCategory")
@CrossOrigin
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    //类别列表展示
    //http://www.localhost:8989/business/productCategory/categoryTree?pageNum=1&pageSize=55
    @GetMapping("/categoryTree")
    public ResponseBean<PageVO<ProductCategoryTreeNodeVO>> categoryTree(@RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize) {

        PageVO<ProductCategoryTreeNodeVO> treeList = categoryService.categoryTree(pageNum,pageSize);
        return ResponseBean.success(treeList);
    }

    //添加分类

    //添加-获取父分类
    //http://www.localhost:8989/business/productCategory/getParentCategoryTree
    @GetMapping("getParentCategoryTree")
    public ResponseBean<List<ProductCategoryTreeNodeVO>> getParentCategoryTree(){
        return ResponseBean.success(categoryService.getParentCategoryTree());
    }
}
