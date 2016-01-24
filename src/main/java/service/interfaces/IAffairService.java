package service.interfaces;

import java.util.List;
import java.util.Map;

import domain.Affair;


public interface IAffairService {
	public List<Affair> getAll();
	public boolean createOne(Affair affair);
	public boolean startOne(int id,String how);
	public boolean completeOne(int id,String comment);
	public boolean terminateOne(int id,String reason);
	public Map<String,String> getTypes();
}
