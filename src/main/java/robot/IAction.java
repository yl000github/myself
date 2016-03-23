package robot;
/**
 * 人的行为
 * 观察信息，思考应对方式，采取行动
 * @author Yang
 *
 */
public interface IAction {
	public void watch() throws Exception;
	public void think() throws Exception;
	public void action() throws Exception;
}
