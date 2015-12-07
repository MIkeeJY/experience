package cn.org.cfpamf.data.exception.e;


import cn.org.cfpamf.data.util.StringUtils;

/**
 * 项目名称：groupBackstage 类描述： 创建人：zzy 创建时间：2015/10/16 14:55 修改人：Administrator
 * 修改时间：2015/10/16 14:55 修改备注：
 */
public class PrintLog {

	private String requestBody;

	private String headers;

	private String requestTime;

	private String responseTime;

	private String responseBody;

	public String getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}

	public String getHeaders() {
		return headers;
	}

	public void setHeaders(String headers) {
		this.headers = headers;
	}

	public String getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}

	public String getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(String responseTime) {
		this.responseTime = responseTime;
	}

	public String getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("请求实体: ").append(headers).append("\n").append("开始时间: ").append(requestTime).append("\n").append("结束时间: ").append(responseTime).append("\n").append("返回信息: ")
				.append(responseBody).toString();
		if (!StringUtils.isEmpty(requestBody)) {
			stringBuilder.append("请求参数: ").append(requestBody).append("\n");
		}
		return stringBuilder.toString();
	}

}
