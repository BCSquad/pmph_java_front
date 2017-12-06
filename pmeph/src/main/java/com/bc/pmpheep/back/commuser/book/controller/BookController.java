package com.bc.pmpheep.back.commuser.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.commuser.book.bean.BookVO;
import com.bc.pmpheep.back.commuser.book.service.BookService;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;
import com.bc.pmpheep.back.util.RouteUtil;
import com.bc.pmpheep.back.util.StringUtil;
import com.bc.pmpheep.controller.bean.ResponseBean;
import com.bc.pmpheep.general.controller.BaseController;
import com.bc.pmpheep.service.exception.CheckedServiceException;

@Controller
@RequestMapping(value = "/books")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class BookController extends BaseController {

    @Autowired
    BookService                 bookService;
    // @Autowired
    // MaterialTypeService materialTypeService;
    // 当前业务类型
    private static final String BUSSINESS_TYPE = "图书管理";

    /**
     * 
     * 
     * 功能描述：初始化/条件查询书籍信息
     * 
     * @param pageSize 当页条数
     * @param pageNumber 当前页数
     * @param type 书籍类别
     * @param name 书籍名称/ISBN
     * @param isOnSale 是否上架
     * @param isNew 是否新书
     * @param isPromote 是否推荐
     * @param path 书籍类别根路径
     * @return
     * @throws Exception
     * 
     */
    // @LogDetail(businessType = BUSSINESS_TYPE, logRemark = "初始化/条件查询书籍信息")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(Integer pageSize, Integer pageNumber, BookVO bookVO) throws Exception {
        ModelAndView model = new ModelAndView();
        String pageUrl = "";
        PageParameter<BookVO> pageParameter = new PageParameter<>(pageNumber, pageSize);
        if (StringUtil.notEmpty(bookVO.getName())) {
            bookVO.setName(bookVO.getName().replaceAll(" ", ""));// 去除空格
        }
        pageParameter.setParameter(bookVO);
        try {
            PageResult<BookVO> page = bookService.listBookVO(pageParameter);
            List<BookVO> bookList = page.getRows();
            for (BookVO book : bookList) {
                book.setImageUrl(RouteUtil.DEFAULT_USER_AVATAR);
            }
            model.addObject("page", page);
            model.setViewName(pageUrl);
        } catch (CheckedServiceException e) {
            throw new CheckedServiceException(e.getBusiness(), e.getResult(), e.getMessage(),
                                              pageUrl);
        }
        return model;
    }

    /**
     * 
     * 
     * 功能描述：修改单个/多个书籍详情
     * 
     * @param ids 书籍id
     * @param type 书籍类型id
     * @param isOnSale 是否上架
     * @param isNew 是否新书
     * @param isPromote 是否推荐
     * @return 是否成功
     * @throws Exception
     * 
     */
    @ResponseBody
    // @LogDetail(businessType = BUSSINESS_TYPE, logRemark = "修改单个/多个书籍详情")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseBean update(Long[] ids, Long type, Boolean isOnSale, Boolean isNew,
    Boolean isPromote) throws Exception {
        return new ResponseBean(bookService.updateBookById(ids, type, isOnSale, isNew, isPromote));
    }

    /**
     * 
     * 
     * 功能描述：获取所有书籍类别
     * 
     * @param parentId 父级id
     * @return
     * 
     */
    // @ResponseBody
    // @LogDetail(businessType = BUSSINESS_TYPE, logRemark = "获取所有书籍类别")
    // @RequestMapping(value = "/list/materialType", method = RequestMethod.GET)
    // public ResponseBean materialType(Long parentId) {
    // return new ResponseBean(materialTypeService.listMaterialType(parentId));
    // }

    /**
     * 
     * 
     * 功能描述：根据书籍id删除书籍
     * 
     * @param id 书籍id
     * @return 是否成功
     * @throws Exception
     * 
     */
    @ResponseBody
    // @LogDetail(businessType = BUSSINESS_TYPE, logRemark = "删除书籍")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseBean delete(Long id) throws Exception {
        return new ResponseBean(bookService.deleteBookById(id));
    }

}
