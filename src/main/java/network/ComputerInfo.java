package network;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Formatter;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

public class ComputerInfo {
	public String getComputerName() throws UnknownHostException {
		InetAddress s = InetAddress.getLocalHost();
		return s.getHostName();
	}

	public String getIPAddress() throws Exception {
		InetAddress address = InetAddress.getLocalHost();
		NetworkInterface ni = NetworkInterface.getByInetAddress(address);
		ni.getInetAddresses().nextElement().getAddress();
		String sIP = address.getHostAddress();
		return sIP;
	}

	public String getMacAddress() throws Exception {
		InetAddress address = InetAddress.getLocalHost();
		NetworkInterface ni = NetworkInterface.getByInetAddress(address);
		ni.getInetAddresses().nextElement().getAddress();
		byte[] mac = ni.getHardwareAddress();
		String sMAC = "";
		Formatter formatter = new Formatter();
		for (int i = 0; i < mac.length; i++) {
			sMAC = formatter.format(Locale.getDefault(), "%02X%s", mac[i], (i < mac.length - 1) ? "-" : "").toString();
		}
		formatter.close();
		return sMAC;

	}

	public String getUserName() {
		Map<String, String> map = System.getenv();
		String userName = map.get("USERNAME");// 获取用户名
		// String computerName = map.get("COMPUTERNAME");// 获取计算机名
		// String userDomain = map.get("USERDOMAIN");// 获取计算机域名
		return userName;
	}

	public String getOsName() {
		Properties props1 = System.getProperties();
		return props1.getProperty("os.name");
	}

	public String getOsVersion() {
		Properties props1 = System.getProperties();
		return props1.getProperty("os.version");
	}
	public static void main(String[] args) throws Exception {
		ComputerInfo c=new ComputerInfo();
		System.out.println(c.getComputerName());
		System.out.println(c.getOsName());
		System.out.println(c.getOsVersion());
		System.out.println(c.getUserName());
		System.out.println(c.getIPAddress());
		System.out.println(c.getMacAddress());
	}
}
