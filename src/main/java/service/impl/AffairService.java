package service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AffairMapper;
import domain.Affair;
import service.interfaces.IAffairService;
import utils.DateUtil;
import utils.StringUtil;
@Service
public class AffairService implements IAffairService{
	public final static String STATUS_CREATE="0";
	public final static String STATUS_START="1";
	public final static String STATUS_COMPLETE="2";
	public final static String STATUS_TERMINATE="3";
	public final static String[] types=new String[]{
		"编程","人际","游戏","旅游","其他"	
	};
	@Autowired 
	private AffairMapper affairDao;
	public List<Affair> getAll() {
		return affairDao.selectAll();
	}
	@Override
	public boolean createOne(Affair affair) {
		affair.setCreateTime(DateUtil.getNow());
		if(!StringUtil.checkValid(affair.getWhy())){
			affair.setWhy("原因还是很重要的");
		}
		affair.setStatus(STATUS_CREATE);
		affairDao.insert(affair);
		return true;
	}
	@Override
	public boolean startOne(int id, String how) {
		Affair af=affairDao.selectByPrimaryKey(id);
		af.setStatus(STATUS_START);
		if(!StringUtil.checkValid(how)) how="尚未找到合适的方法";
		af.setHow(how);
		af.setStartTime(DateUtil.getNow());
		return affairDao.updateByPrimaryKeySelective(af)>0;
	}
	@Override
	public boolean completeOne(int id, String comment) {
		Affair af=affairDao.selectByPrimaryKey(id);
		af.setStatus(STATUS_COMPLETE);
		if(StringUtil.checkValid(comment)) comment="尚未分享评价";
		af.setComment(comment);
		Date startDate=af.getStartTime();
		Date endDate=new Date();
		String duration=DateUtil.diffString(startDate, endDate);
		af.setDuration(duration);
		return affairDao.updateByPrimaryKeySelective(af)>0;
	}
	@Override
	public boolean terminateOne(int id, String reason) {
		Affair af=affairDao.selectByPrimaryKey(id);
		af.setStatus(STATUS_TERMINATE);
		if(StringUtil.checkValid(reason)) reason="太忙了，都忘了留下reason了";
		af.setComment(reason);
		Date startDate=af.getStartTime();
		Date endDate=new Date();
		String duration=DateUtil.diffString(startDate, endDate);
		af.setDuration(duration);
		return affairDao.updateByPrimaryKeySelective(af)>0;
	}
	@Override
	public Map<String, String> getTypes() {
		Map<String, String> m=new HashMap<>();
		for (int i = 0; i < types.length; i++) {
			m.put(String.valueOf(i), types[i]);
		}
		return m;
	}

}
