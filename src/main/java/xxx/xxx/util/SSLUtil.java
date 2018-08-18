package xxx.xxx.util;

import java.util.logging.Logger;

import javax.net.ssl.SSLSession;
import javax.servlet.http.HttpServletRequest;

public class SSLUtil {

	private static Logger logger = Logger.getLogger(SSLUtil.class.getName());
	
	public static String getUser(HttpServletRequest request) {
		
		SSLSession sslContext = (SSLSession) request.getAttribute("org.eclipse.jetty.servlet.request.ssl_session");
		
		try {
			
			return sslContext.getPeerPrincipal().getName();
		}
		catch(Exception e) {
			
			logger.warning("Failure retrieving DN : " + e.getMessage());
		}
		
		return null;
	}
}
