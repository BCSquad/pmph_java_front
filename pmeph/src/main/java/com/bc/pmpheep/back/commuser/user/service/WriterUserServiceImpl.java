package com.bc.pmpheep.back.commuser.user.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.commuser.user.bean.Org;
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
import com.mongodb.gridfs.GridFSDBFile;

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
	public WriterUserCertificationVO showTeacherCertification(Long userId) {
		if (ObjectUtil.isNull(userId)) {
			throw new CheckedServiceException(CheckedExceptionBusiness.USER_MANAGEMENT,
					CheckedExceptionResult.NULL_PARAM, "userId不能为空");
		}
		WriterUserCertificationVO writerUserCertificationVO = writerUserDao.showTeacherCertification(userId);
		if (ObjectUtil.isNull(writerUserCertificationVO)) {
			writerUserCertificationVO = new WriterUserCertificationVO();
		}
		List<Org> orgList = writerUserDao.getOrgList();
		writerUserCertificationVO.setOrgList(orgList);
		String cert = writerUserCertificationVO.getCert();
		if (StringUtil.notEmpty(cert)) {
			GridFSDBFile getFile = fileService.get(cert);
			String getFileName = getFile.getFilename();
			writerUserCertificationVO.setCertName(getFileName);
		}
		return writerUserCertificationVO;
	}

	@Override
	public WriterUserCertification updateTeacherCertification(WriterUserCertification writerUserCertification, 
			String realName) throws IOException {
		if (ObjectUtil.isNull(writerUserCertification)) { // 获取的数据不能为空
			throw new CheckedServiceException(CheckedExceptionBusiness.USER_MANAGEMENT,
					CheckedExceptionResult.NULL_PARAM, "作家用户信息不能为空");
		}
		// 获取需要插入数据库的数据
		Long id = writerUserCertification.getId();
		Long userId = writerUserCertification.getUserId(); // 获取作家id
		Long orgId = writerUserCertification.getOrgId(); // 获取对应学校id
		String handPhone = writerUserCertification.getHandphone(); // 获取手机
		String idCard = writerUserCertification.getIdcard(); // 获取身份证
		Integer progress = writerUserCertification.getProgress(); // 认证进度
		String cert = writerUserCertification.getCert(); // 获取教师资格证
		// 把获取的数据添加进writerUserCertification
		WriterUserCertification writerUserCertifications = new WriterUserCertification();
		writerUserCertifications.setId(id);
		writerUserCertifications.setUserId(userId);
		writerUserCertifications.setOrgId(orgId);
		writerUserCertifications.setHandphone(handPhone);
		writerUserCertifications.setIdcard(idCard);
		writerUserCertifications.setProgress(progress);
		writerUserCertifications.setCert(cert);
		WriterUser writerUser = new WriterUser();
		writerUser.setIdcard(idCard);
		writerUser.setHandphone(handPhone);
		writerUser.setOrgId(orgId);
		writerUser.setRealname(realName);
		writerUser.setId(userId);
		if (ObjectUtil.isNull(id)) { //id为空就增加否则修改
			writerUserDao.addCertification(writerUserCertifications);
			writerUserDao.updateWriterUser(writerUser);
			File migCert = new File(cert);
			String parent = migCert.getParent(); // 获取文件路径
			String migCertName = migCert.getName(); // 获取文件名
			String nameEnd = migCertName.substring(migCertName.lastIndexOf(".")+1); // 获取文件后缀
			String mongoId = null;
	        if (migCert.exists()) {
	        	writerUserCertification.setCert(null);
	        } else {
	        	File newMigCert = new File(parent+"教师资格证"+nameEnd);
	        	migCert.renameTo(newMigCert);
	            mongoId = fileService.saveLocalFile(migCert, FileType.TEACHER_CERTIFICATION_PIC, writerUserCertifications.getId());
	            if (null != mongoId) {
	            	writerUserCertifications.setCert(mongoId);
	            	writerUserDao.updateCertification(writerUserCertifications);
	            	Date date = new Date();
	            	writerUser.setCert(mongoId);
	            	writerUser.setAuthTime(date);
	            	writerUserDao.updateWriterUser(writerUser);
	            }
	        }
		} else {
			writerUserDao.updateCertification(writerUserCertifications);
			writerUserDao.updateWriterUser(writerUser);
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
