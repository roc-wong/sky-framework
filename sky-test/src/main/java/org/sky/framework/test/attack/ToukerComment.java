package org.sky.framework.test.attack;


import java.io.IOException;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.cookie.CookieSpec;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
/*import org.apache.http.cookie.CookieSpecFactory;
import org.apache.http.impl.cookie.BrowserCompatSpec;
import org.apache.http.params.HttpParams;*/
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.tags.InputTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class ToukerComment {

	private static String _b_ = "618fbefe-77bf-4e7a-a4c8-38a90dc3a8ef3536B9A3";
	private static String _d_ = "803D09DC0C9AB99A,sky8";
	private static String _e_ = "{\"id\":\"803D09DC0C9AB99A\",\"_e_\":\"14181A0540805E82A6422869F6367667\",\"_sign_\":\"37A8F960\"}";
	private static String ssid = "3mRgqsvmnsA=";
	private static String _s_ = "{\"id\":\"803D09DC0C9AB99A\",\"_e_\":\"3FA9B996C6323C694F436ADC40BD968F\",\"_sign_\":\"FD309CF7\"}";
	
	private static String Hm_lpvt_20cdf1964c65350054c8b3052f2ea5a6 = "1434692156";
	private static String Hm_lpvt_61c64f7e87efbb1d5f7138ad13962177 = "1434691468";
	private static String Hm_lvt_20cdf1964c65350054c8b3052f2ea5a6	= "1434093030,1434424718,1434590136,1434675454";
	private static String Hm_lvt_61c64f7e87efbb1d5f7138ad13962177	= "1434424718,1434511301,1434589237,1434675454";
	private static String tls__Session	= "uf5e62vcjdm5pv2g6tcf3sfql2";
	
	static HttpClient client = new HttpClient();

	public static void main(String[] args) throws Exception {

		login();

//		String article = getPostHash(2);

//		System.out.println(article);

//		Comment comment = createComment(article);

//		postComment(2, comment);
		
		
	}

	private static String createCookie() {
		// TODO Auto-generated method stub
		StringBuilder cookieBuilder = new StringBuilder();
		cookieBuilder.append("_b_=").append(_b_).append(";");
		cookieBuilder.append("_e_=").append(_e_).append(";");
		cookieBuilder.append("_d_=").append(_d_).append(";");
		cookieBuilder.append("ssid=").append(ssid).append(";");
		cookieBuilder.append("_s_=").append(_s_).append(";");
		cookieBuilder.append("Hm_lpvt_20cdf1964c65350054c8b3052f2ea5a6=").append(Hm_lpvt_20cdf1964c65350054c8b3052f2ea5a6).append(";");
		cookieBuilder.append("Hm_lpvt_61c64f7e87efbb1d5f7138ad13962177=").append(Hm_lpvt_61c64f7e87efbb1d5f7138ad13962177).append(";");
		cookieBuilder.append("Hm_lvt_20cdf1964c65350054c8b3052f2ea5a6=").append(Hm_lvt_20cdf1964c65350054c8b3052f2ea5a6).append(";");
		cookieBuilder.append("Hm_lvt_61c64f7e87efbb1d5f7138ad13962177=").append(Hm_lvt_61c64f7e87efbb1d5f7138ad13962177).append(";");
		cookieBuilder.append("tls__Session=").append(tls__Session).append(";");
		return null;
	}

	private static void postComment(Integer articleId, Comment comment) throws Exception{
		PostMethod post = new PostMethod("http://bbs.touker.com/article/ajax/save_comment/");
		post.setRequestHeader("Host", "bbs.touker.com");
		post.setRequestHeader("Origin", "http://bbs.touker.com");
		post.setRequestHeader("Referer", "http://bbs.touker.com/article/" + articleId);
		post.setRequestHeader("Connection", " Keep-Alive");
		
		String cookie = createCookie();
		post.setRequestHeader("Cookie", cookie);
		
		NameValuePair post_hash = new NameValuePair("post_hash", comment.getPost_hash());
		NameValuePair article_id = new NameValuePair("article_id", comment.getArticle_id().toString());
		NameValuePair message = new NameValuePair("message", "测试循环");
		NameValuePair _post_type = new NameValuePair("_post_type", comment.get_post_type());
		post.setRequestBody(new NameValuePair[] { post_hash, article_id, message, _post_type});
		
		int status = client.executeMethod(post);
		System.out.println(post.getResponseBodyAsString());
		post.releaseConnection();
	}

	private static Comment createComment(String article) throws ParserException {
		Parser parser = new Parser(article);

		// 过滤 <frame >标签的 filter，用来提取 frame 标签里的 src 属性所表示的链接
		NodeFilter frameFilter = new NodeFilter() {
			public boolean accept(Node node) {
				if (node.getText().startsWith("html")) {
					return true;
				} else {
					return false;
				}
			}
		};
		// OrFilter 接受<a>标签或<frame>标签，注意NodeClassFilter()可用来过滤一类标签，linkTag对应<标签>
		OrFilter inputFilter = new OrFilter(
				new NodeClassFilter(InputTag.class), frameFilter);

		NodeList list = parser.extractAllNodesThatMatch(inputFilter);
		Comment comment = new Comment();;
		for (int i = 0; i < list.size(); i++) {
			Node tag = list.elementAt(i);
			if (tag instanceof InputTag) {
				InputTag link = (InputTag) tag;
				String name = link.getAttribute("name");
				String value = link.getAttribute("value");
				if("post_hash".equals(name)){
					comment.setPost_hash(value);
				}else if("article_id".equals(name)){
					comment.setArticle_id(Integer.valueOf(value));
				}
			}
		}
		System.out.println("获取到发布评论的票据" + comment);
		return comment;
	}

	private static String getPostHash(Integer articleId) throws Exception {
		GetMethod getMethod = new GetMethod("http://bbs.touker.com/article/" + articleId);
		String cookie = "_b_=" + _b_ + ";_e_=" + _e_ + ";_d_=" + _d_ + ";ssid="
				+ ssid + ";";

		// getMethod.setRequestHeader("Referer","http://kyxk.net/wForum/disparticle.php?boardName=LifeScience&ID=2114");
		// getMethod.setRequestHeader("Accept-Encoding"," gzip, deflate");
		getMethod.setRequestHeader("Host", "bbs.touker.com");
		getMethod.setRequestHeader("Connection", " Keep-Alive");
		getMethod.setRequestHeader("Cookie", cookie);

		getMethod.getParams().setParameter(
				HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		int status = client.executeMethod(getMethod);
		System.out.println("获取postHash请求： = " + status);
		String body = getMethod.getResponseBodyAsString();
		
		Cookie[] cc = client.getState().getCookies();
		System.out.print("getPostHash 获取到的cookie");
		for (int i = 0; i < cc.length; i++) {
			System.out.println(cc[i]);
		}
		
		CookieSpec cookiespec = CookiePolicy.getDefaultSpec();
		Cookie[] cookies = cookiespec.match(".touker.com", 80, "/",
				true, client.getState().getCookies());

		for (int i = 0; i < cookies.length; i++) {
			String coo = cookies[i].getName();
			String cooVal = cookies[i].getValue();
			System.out.println(coo + "===" + cooVal);
			switch (coo) {
			case "Hm_lpvt_20cdf1964c65350054c8b3052f2ea5a6":
				Hm_lpvt_20cdf1964c65350054c8b3052f2ea5a6 = cooVal;
				System.out.println(Hm_lpvt_20cdf1964c65350054c8b3052f2ea5a6 + "---->Hm_lpvt_20cdf1964c65350054c8b3052f2ea5a6=刷新==" + Hm_lpvt_20cdf1964c65350054c8b3052f2ea5a6);
				break;
			case "Hm_lpvt_61c64f7e87efbb1d5f7138ad13962177":
				Hm_lpvt_61c64f7e87efbb1d5f7138ad13962177 = cooVal;
				System.out.println(Hm_lpvt_20cdf1964c65350054c8b3052f2ea5a6 + "---->Hm_lpvt_61c64f7e87efbb1d5f7138ad13962177=刷新==" + Hm_lpvt_61c64f7e87efbb1d5f7138ad13962177);
				break;
			default:
				break;
			}
		}
		
		
		getMethod.releaseConnection();
		return body;
	}

	private static void login() throws IOException, HttpException {
		// 模拟登录页面 login.jsp->main.jsp
		PostMethod post = new PostMethod(
				"https://account.touker.com/login/login.do");
		
		post.setRequestHeader("Host", "bbs.touker.com");
		
		NameValuePair name = new NameValuePair("logonId", "18551208215");
		NameValuePair pass = new NameValuePair("pwd", "123456");
		post.setRequestBody(new NameValuePair[] { name, pass });
		int status = client.executeMethod(post);
		System.out.println(post.getResponseBodyAsString());
		post.releaseConnection();
		// 查看 cookie 信息
		Cookie[] cc = client.getState().getCookies();
		System.out.print("login 获取到的cookie");
		for (int i = 0; i < cc.length; i++) {
			System.out.println(cc[i]);
		}
		
	/*	CookieSpecFactory csf = new CookieSpecFactory() {
		    public CookieSpec newInstance(HttpParams params) {
		        return new BrowserCompatSpec() {   
		            @Override
		            public void validate(Cookie cookie, CookieOrigin origin)
		            throws MalformedCookieException {
		                // Oh, I am easy
		            }
		        };
		    }
		};

		DefaultHttpClient httpclient = new DefaultHttpClient();
		httpclient.getCookieSpecs().register("easy", csf);
		httpclient.getParams().setParameter(
		     ClientPNames.COOKIE_POLICY, "easy");
		*/
		
		CookieSpec cookiespec = CookiePolicy.getDefaultSpec();
		Cookie[] cookies = cookiespec.match(".touker.com", 80, "/",
				true, client.getState().getCookies());
		if (cookies.length == 0) {
			System.out.println("None");
		} else {

			for (int i = 0; i < cookies.length; i++) {
				String coo = cookies[i].getName();
				String cooVal = cookies[i].getValue();
				System.out.println(coo + "===" + cooVal);
				switch (coo) {
				case "_b_":
					_b_ = cooVal;
					break;
				case "_e_":
					_e_ = cooVal;
					break;
				case "ssid":
					ssid = cooVal;
					break;
				case "_d_":
					_d_ = cooVal;
					break;
				case "_s_":
					_s_ = cooVal;
					break;
				default:
					break;
				}
			}
		}
	}

	static class Comment {
		private String post_hash;
		private Integer article_id;
		private String message;
		private String _post_type = "ajax";

		public String getPost_hash() {
			return post_hash;
		}

		public void setPost_hash(String post_hash) {
			this.post_hash = post_hash;
		}

		public Integer getArticle_id() {
			return article_id;
		}

		public void setArticle_id(Integer article_id) {
			this.article_id = article_id;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public String get_post_type() {
			return _post_type;
		}

		public void set_post_type(String _post_type) {
			this._post_type = _post_type;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Comment [post_hash=").append(post_hash)
					.append(", article_id=").append(article_id)
					.append(", message=").append(message)
					.append(", _post_type=").append(_post_type).append("]");
			return builder.toString();
		}
		
		
	}

}
