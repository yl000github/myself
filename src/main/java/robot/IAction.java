package robot;
/**
 * 人的行为
 * 观察信息，思考应对方式，采取行动
 * @author Yang
 *
 */
public interface IAction {
	public void watch();
	public void think();
	public void action();
}
