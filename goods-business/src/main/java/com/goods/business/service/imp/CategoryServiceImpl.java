package com.goods.business.service.imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.goods.business.converter.CategoryConverter;
import com.goods.business.mapper.CategoryMapper;
import com.goods.business.service.CategoryService;
import com.goods.common.model.business.ProductCategory;
import com.goods.common.response.ResponseBean;
import com.goods.common.utils.CategoryTreeBuilder;
import com.goods.common.vo.business.ProductCategoryTreeNodeVO;
import com.goods.common.vo.business.ProductCategoryVO;
import com.goods.common.vo.system.PageVO;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public PageVO<ProductCategoryTreeNodeVO> categoryTree(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);//设置分页
        Example example = new Example(ProductCategory.class);//要分页的类
        List<ProductCategory> productCategories = categoryMapper.selectByExample(example);//通过条件查询
        //转换treeNodeVo
        List<ProductCategoryTreeNodeVO> treeNodeVOS = CategoryConverter.CategoryToCategoryTreeNodeVO(productCategories);
        //树形结构
        List<ProductCategoryTreeNodeVO> build = CategoryTreeBuilder.build(treeNodeVOS);
        //分页
        PageInfo<ProductCategoryTreeNodeVO> info = new PageInfo<>(build);
        return new PageVO<>(info.getTotal(),build);
    }

    /*@Override
    public PageVO<ProductCategoryTreeNodeVO> categoryTree(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);//设置分页
        List<ProductCategory> productCategoryList = categoryMapper.selectAll();
        List<ProductCategoryVO> productCategoryVOS = CategoryConverter.categoryToProductCategoryVO(productCategoryList);
        List<ProductCategoryTreeNodeVO> productCategoryTreeNodeVOS = CategoryConverter.categoryToCategoryTreeNodeVO(productCategoryVOS);
        List<ProductCategoryTreeNodeVO> build = CategoryTreeBuilder.build(productCategoryTreeNodeVOS);
        return new PageVO<>(build.size(),build);
    }*/

    @Override
    public List<ProductCategoryTreeNodeVO> getParentCategoryTree() {

        //创建list集合
        List<ProductCategoryTreeNodeVO> treeNodeVOList = new ArrayList<>();
        //查询所有
        List<ProductCategory> productCategories = categoryMapper.selectAll();
        
        //条件查询
       /* Example example = new Example(ProductCategory.class);
        example.createCriteria().andEqualTo("pid",0);
        categoryMapper.selectByExample()*/

        Example example = new Example(ProductCategory.class);
        example.createCriteria().andEqualTo("pid",0);
        List<ProductCategory> list = categoryMapper.selectByExample(example);
        List<ProductCategoryTreeNodeVO> productCategoryTreeNodeVOS = CategoryConverter.CategoryToCategoryTreeNodeVO(list);
        for (ProductCategoryTreeNodeVO productCategoryTreeNodeVO : productCategoryTreeNodeVOS) {
            ProductCategoryTreeNodeVO productCategoryTreeNodeVO1 = new ProductCategoryTreeNodeVO();
            productCategoryTreeNodeVO1.setName("222");
            ArrayList<ProductCategoryTreeNodeVO> productCategoryTreeNodeVOS1 = new ArrayList<>();
            productCategoryTreeNodeVOS1.add(productCategoryTreeNodeVO1);
            productCategoryTreeNodeVO.setChildren(productCategoryTreeNodeVOS1);
        }

       /* List<ProductCategory> productCategories = categoryMapper.selectAll();
        for (ProductCategory productCategory : productCategories) {
            List<ProductCategory> arrayList = new ArrayList<>();
            for (ProductCategory productCategory1 : list) {
                if (productCategory.getPid()==productCategory1.getId()){
                    arrayList.add(productCategory);
                }
                productCategory1.set
            }
        }*/

        return productCategoryTreeNodeVOS;
    }
}
