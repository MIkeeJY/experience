package cn.org.cfpamf.data.exception.e;

import android.content.Context;

import com.orhanobut.logger.Logger;

import cn.org.cfpamf.data.constant.Constants;
import cn.org.cfpamf.data.okHttp.AbstractBaseOkHttp;
import cn.org.cfpamf.data.util.FileUtils;

public class PrintLogUtil {
	/**
	 * 日志名称
	 */
	private static String mtsLog = "MTSLog";

	/**
	 * 打印日志
	 * 
	 * @param
	 */
	private static void printLogToSdCard(Context context, PrintLog printLog) {
		String fileName = mtsLog + printLog.getRequestTime() + ".txt";
		String messageLog = printLog.toString();
		boolean b = FileUtils.writeFile(Constants.APP_BASE_PATH + fileName, messageLog);
		if (b) {
			Logger.e("zzy", "日志打印成功");
		} else {
			Logger.e("zzy", "日志打印失败");
		}
		// 清除日志对象
		fileName = null;
		printLog = null;
		messageLog = null;
		// StatService.reportError(context, messageLog);
		// Logger.e(messageLog.toString());
	}

	public static void createPrintLogToSdCard(Context context, AbstractBaseOkHttp abstractBaseOkHttp) {
		PrintLog printLog = new PrintLog();
		printLog.setHeaders(abstractBaseOkHttp.getRequest().toString());
		printLog.setResponseTime(abstractBaseOkHttp.getResponseTime());
		printLog.setRequestTime(abstractBaseOkHttp.getRequestTime());
		printLog.setRequestBody(abstractBaseOkHttp.getRequestJson() == null ? "" : abstractBaseOkHttp.getRequestJson());
		printLog.setResponseBody(abstractBaseOkHttp.getErrorMessage());
		printLogToSdCard(context, printLog);
	}
}
