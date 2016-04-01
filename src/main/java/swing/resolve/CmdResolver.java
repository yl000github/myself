package swing.resolve;

import java.io.IOException;

public class CmdResolver {
	public static void runCmd(String path1) {
		Runtime r = Runtime.getRuntime();
		String path = "cmd.exe /C start "+path1;
		Process pro = null;
		try {
			pro = r.exec(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// pro.destroy();
	}
	public static void runFrontCmd() {
		runCmd("C:\\Users\\Administrator\\Desktop\\omp\\front.bat");
	}

}
