package service.interfaces;

import java.util.List;

import domain.Affair;


public interface IAffairService {
	public List<Affair> getAll();
	public void createOne(Affair affair);
	public void startOne(int id,String how);
	public void completeOne(int id,String comment);
	public void terminateOne(int id,String reason);
}
