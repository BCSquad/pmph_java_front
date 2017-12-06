package com.bc.pmpheep.back.commuser.user.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bc.pmpheep.back.commuser.book.bean.BookVO;
import com.bc.pmpheep.back.commuser.user.bean.WriterUser;
import com.bc.pmpheep.back.commuser.user.bean.WriterUserCertification;
import com.bc.pmpheep.back.commuser.user.bean.WriterUserCertificationVO;
import com.bc.pmpheep.back.commuser.user.dao.WriterUserDao;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;
import com.bc.pmpheep.back.util.ObjectUtil;
import com.bc.pmpheep.back.util.PageParameterUitl;
import com.bc.pmpheep.back.util.StringUtil;
import com.bc.pmpheep.general.bean.FileType;
import com.bc.pmpheep.general.service.FileService;
import com.bc.pmpheep.service.exception.CheckedExceptionBusiness;
import com.bc.pmpheep.service.exception.CheckedExceptionResult;
import com.bc.pmpheep.service.exception.CheckedServiceException;

/**
 * 
 * 
 * 功能描述：普通用户业务实现
 * 
 * 
 * 
 * @author (作者) 曾庆峰
 * 
 * @since (该版本支持的JDK版本) ：JDK 1.6或以上
 * @version (版本) 1.0
 * @date (开发日期) 2017年11月27日
 * @modify (最后修改时间)
 * @修改人 ：曾庆峰
 * @审核人 ：
 *
 */
@Service("com.bc.pmpheep.back.commuser.user.service.WriterUserServiceImpl")
public class WriterUserServiceImpl implements WriterUserService {
	@Autowired
	private WriterUserDao writerUserDao;
	@Autowired
	private FileService fileService;

	@Override
	public WriterUser get(Long id) throws CheckedServiceException {
		if (ObjectUtil.isNull(id)) {
			throw new CheckedServiceException(CheckedExceptionBusiness.USER_MANAGEMENT,
					CheckedExceptionResult.NULL_PARAM, "用户ID为空时禁止查询");
		}
		return writerUserDao.get(id);
	}

	@Override
	public WriterUserCertificationVO showTeacherCertification(Long id) {
		if (ObjectUtil.isNull(id)) {
			throw new CheckedServiceException(CheckedExceptionBusiness.USER_MANAGEMENT,
					CheckedExceptionResult.NULL_PARAM, "id不能为空");
		}
		WriterUserCertificationVO writerUserCertificationVO = writerUserDao.showTeacherCertification(id);
		return writerUserCertificationVO;
	}

	@Override
	public WriterUserCertification updateTeacherCertification(WriterUserCertificationVO writerUserCertificationVO) 
			throws IOException {
		if (ObjectUtil.isNull(writerUserCertificationVO)) { // 获取的数据不能为空
			throw new CheckedServiceException(CheckedExceptionBusiness.USER_MANAGEMENT,
					CheckedExceptionResult.NULL_PARAM, "作家用户信息不能为空");
		}
		// 获取需要插入数据库的数据
		Long userId = writerUserCertificationVO.getUserId(); // 获取作家id
		Long orgId = writerUserCertificationVO.getOrgId(); // 获取对应学校id
		String handPhone = writerUserCertificationVO.getHandphone(); // 获取手机
		String idCard = writerUserCertificationVO.getIdcard(); // 获取身份证
		Integer progress = writerUserCertificationVO.getProgress(); // 认证进度
		MultipartFile cert = writerUserCertificationVO.getCert(); // 获取教师资格证
		// 把获取的数据添加进writerUserCertification
		WriterUserCertification writerUserCertification = new WriterUserCertification();
		writerUserCertification.setUserId(userId);
		writerUserCertification.setOrgId(orgId);
		writerUserCertification.setHandphone(handPhone);
		writerUserCertification.setIdcard(idCard);
		writerUserCertification.setProgress(progress);
		writerUserCertification.setCert(null);
		writerUserDao.addCertification(writerUserCertification);
		String mongoId = null;
        if (null == cert) {
        	
        } else {
            mongoId = fileService.save(cert, FileType.TEACHER_CERTIFICATION_PIC, writerUserCertificationVO.getId());
            if (null != mongoId) {
            	Long ids = writerUserCertification.getId();
            	writerUserCertification.setCert(mongoId);
            	writerUserDao.updateCertification(ids);
            }
        }
		return writerUserCertification;
	}

	@Override
	public Integer updateUserPassWord(Long id, String username) {
		if (ObjectUtil.isNull(id)) {
			throw new CheckedServiceException(CheckedExceptionBusiness.USER_MANAGEMENT,
                    CheckedExceptionResult.NULL_PARAM, "id不能为空");
		}
		if (StringUtil.isEmpty(username)) {
			throw new CheckedServiceException(CheckedExceptionBusiness.USER_MANAGEMENT,
                    CheckedExceptionResult.NULL_PARAM, "机构代码不能为空");
		}
		Integer writerUser = writerUserDao.updateUserPassWord(id, username);
		return writerUser;
	}

	@Override
	public PageResult<WriterUser> getOrg(PageParameter<WriterUser> pageParameter) {
		if(ObjectUtil.isNull(pageParameter.getParameter().getOrgId())){
			throw new CheckedServiceException(CheckedExceptionBusiness.USER_MANAGEMENT,
                    CheckedExceptionResult.NULL_PARAM, "机构id不能为空");
		}
		int total = writerUserDao.getOrgTotal(pageParameter);
        PageResult<WriterUser> pageResult = new PageResult<>();
        if (total > 0) {
            PageParameterUitl.CopyPageParameter(pageParameter, pageResult);
            pageResult.setRows(writerUserDao.getOrg(pageParameter));
        }
        pageResult.setTotal(total);
        return pageResult;
	}
}
