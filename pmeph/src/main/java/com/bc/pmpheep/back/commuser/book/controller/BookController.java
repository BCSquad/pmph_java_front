package com.bc.pmpheep.back.commuser.book.controller;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    @Qualifier("com.bc.pmpheep.back.commuser.book.service.BookServiceImpl")
    BookService bookService;

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
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(@RequestParam(value="pageSize",defaultValue="10")Integer pageSize, Integer pageNumber, BookVO bookVO,@RequestParam(value="type",required=true)Long type) throws Exception {
        ModelAndView model = new ModelAndView();
        String pageUrl = "commuser/booklist/bookList";
        Map<String, Object> user = getUserInfo();
        BigInteger uid = null;
		if (user != null && getUserInfo().get("id")!=null && getUserInfo().get("id").toString() != null) {
			uid = (BigInteger)getUserInfo().get("id");
		}
        bookVO.setLogUserId(uid);
        PageParameter<BookVO> pageParameter = new PageParameter<>(pageNumber, pageSize);
        //书籍的分类，将会查询该类及其所有子类的图书
        bookVO.setType(type);
        if (StringUtil.notEmpty(bookVO.getName())) {
            bookVO.setName(bookVO.getName().replaceAll(" ", ""));// 去除空格
        }
        pageParameter.setParameter(bookVO);
        try {
        	//获取某教材分类及其父类列表
        	List<Map<String,Object>> parentTypeList = bookService.queryParentTypeListByTypeId(type);
        	String materiaName = bookService.getMaterialTypeNameById(type);
        	PageResult<BookVO> page = bookService.listBookVO(pageParameter);
            List<BookVO> bookList = page.getRows();
            model.addObject("page", page);
            if (null == pageNumber) {
                pageNumber = 1;
            }
            if (bookList!=null && bookList.size()>0) {
            	model.addObject("listSize", bookList.size());
			}else{
				model.addObject("listSize", 0);
			}
            model.addObject("parentTypeList", parentTypeList);
            model.addObject("materiaName", materiaName);
            model.addObject("pageNumber", pageNumber);
            model.addObject("pageSize", pageSize);
            model.addObject("order", bookVO.getOrder());
            model.addObject("materialType", type);
            model.setViewName(pageUrl);
        } catch (CheckedServiceException e) {
            throw new CheckedServiceException(e.getBusiness(), e.getResult(), e.getMessage(),
                                              pageUrl);
        }
        return model;
    }

    @RequestMapping(value = "/promoteList", method = RequestMethod.GET)
    public ModelAndView promoteList(@RequestParam(value="pageSize",defaultValue="10")Integer pageSize, Integer pageNumber, BookVO bookVO,@RequestParam(value="type",required=true)Long type) throws Exception {
        ModelAndView model = new ModelAndView();
        String pageUrl = "commuser/booklist/promoteBookList";
        Map<String, Object> user = getUserInfo();
        BigInteger uid = null;
        if (user != null && getUserInfo().get("id")!=null && getUserInfo().get("id").toString() != null) {
            uid = (BigInteger)getUserInfo().get("id");
        }
        bookVO.setLogUserId(uid);
        PageParameter<BookVO> pageParameter = new PageParameter<>(pageNumber, pageSize);
        //书籍的分类，将会查询该类及其所有子类的图书
        bookVO.setType(type);
        if (StringUtil.notEmpty(bookVO.getName())) {
            bookVO.setName(bookVO.getName().replaceAll(" ", ""));// 去除空格
        }
        pageParameter.setParameter(bookVO);
        try {
            //获取某教材分类及其父类列表
//            List<Map<String,Object>> parentTypeList = bookService.queryParentTypeListByTypeId(type);
//            String materiaName = bookService.getMaterialTypeNameById(type);
            PageResult<BookVO> page = bookService.listPromoteBookVO(pageParameter);
            List<BookVO> bookList = page.getRows();
            model.addObject("page", page);
            if (null == pageNumber) {
                pageNumber = 1;
            }
            if (bookList!=null && bookList.size()>0) {
                model.addObject("listSize", bookList.size());
            }else{
                model.addObject("listSize", 0);
            }
//            model.addObject("parentTypeList", parentTypeList);
//            model.addObject("materiaName", materiaName);
            model.addObject("pageNumber", pageNumber);
            model.addObject("pageSize", pageSize);
            model.addObject("order", bookVO.getOrder());
            model.addObject("materialType", type);
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
