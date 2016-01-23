package service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AffairMapper;
import domain.Affair;
import service.interfaces.IAffairService;
import utils.DateUtil;
import utils.StringUtil;
@Service
public class AffairService implements IAffairService{
	@Autowired 
	private AffairMapper affairDao;
	public List<Affair> getAll() {
		// TODO Auto-generated method stub
//		return affairMapper.
		return affairDao.selectAll();
	}
	@Override
	public void createOne(Affair affair) {
		// TODO Auto-generated method stub
		affair.setCreateTime(DateUtil.getNow());
		affairDao.insert(affair);
	}
	@Override
	public void startOne(int id, String how) {
		// TODO Auto-generated method stub
		Affair af=affairDao.selectByPrimaryKey(id);
		if(!StringUtil.checkValid(how)) how="尚未找到合适的方法";
		af.setHow(how);
		af.setStartTime(DateUtil.getNow());
		affairDao.updateByPrimaryKeySelective(af);
	}
	@Override
	public void completeOne(int id, String comment) {
		// TODO Auto-generated method stub
		Affair af=affairDao.selectByPrimaryKey(id);
		if(StringUtil.checkValid(comment)) comment="尚未分享评价";
		af.setComment(comment);
//		Date startDate=af.getStartTime();
//		Date endDate=new Date();
//		startDate.getTime()
	}
	@Override
	public void terminateOne(int id, String reason) {
		// TODO Auto-generated method stub
		
	}

}
