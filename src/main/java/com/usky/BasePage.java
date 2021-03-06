package com.usky;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.usky.comm.JsonUtil;
import com.usky.comm.Log;
import com.usky.comm.Utility;

/**
 * Servlet implementation class BasePage
 */
public class BasePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected static Log log = null;
	protected HashMap<String, String> mParam = null;
	protected HashMap<String, Object> mResponse = null;
	protected HashMap<String, Object> mResponseHeader = null;
	protected HashMap<String, Object> mResponseData = null;
	//protected static HashMap<String, BaseService> sv_list = null;
	protected static HashMap<String, HashMap<String, BaseService>> sv_list = null;
	protected BaseService sv = null;
	protected long startTime = 0;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BasePage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		//log = new Log("log/" + getClass().getSimpleName());
		log = new Log("log/" + getClass().getName());
		log.WriteLine(getClass().getSimpleName() + "服务启动");
		DefineService();
		//myParam = config.getInitParameter("MyParam");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("rawtypes")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		startTime = System.currentTimeMillis();
		//request.setCharacterEncoding("utf-8");
		//request.setCharacterEncoding("GBK");

		mParam = new LinkedHashMap<String, String>();
		mResponse = new LinkedHashMap<String, Object>();
		mResponseHeader = new LinkedHashMap<String, Object>();
		mResponseData = new LinkedHashMap<String, Object>();
		
		mResponse.put("responseHeader", mResponseHeader);
		mResponse.put("responseData", mResponseData);
		
		mResponseHeader.put("status", -1);
		mResponseHeader.put("msg", "未处理");
		
		mResponseHeader.put("RequestType", request.getMethod());
		mResponseHeader.put("log", log.GetFilename());
		
		Enumeration e = request.getParameterNames();
		while (e.hasMoreElements()){
			String p_key = (String)e.nextElement();
			if ("GET".equals(request.getMethod()))
				mParam.put(p_key, Utility.codeToString(request.getParameter(p_key)));
			else
				mParam.put(p_key, request.getParameter(p_key));
			//log.WriteLine(p_key + " = " + mParam.get(p_key));
		}
		mResponseHeader.put("params",mParam);
		
		CallService(request, response);
		
		OnResponse(response);
	}

	protected void DefineService() {
		
	}
	
	void CallService(HttpServletRequest request, HttpServletResponse response){
		//BaseService sv = null;
		try {
			if (mParam == null || mParam.size() == 0){
				Usage();
				return;
			}
			if (!mParam.containsKey("sv")){
				SetErrorMsg(-1, "没有设置sv参数");
				return;
			}
			
			sv = GetService(mParam.get("sv"));
			if (sv == null){
				SetErrorMsg(-1,"没有定义服务[" + mParam.get("sv") + "]");
				return;
			}
			
			try {
				sv = sv.clone();
			} catch (CloneNotSupportedException e) {
				SetErrorMsg(-1, e.getMessage());
				e.printStackTrace();
				return;
			}

			sv.request = request;
			sv.response = response;
			sv.log = log;
			sv.page = this;
			sv.mParam = mParam;
			sv.mResponseData = mResponseData;
			sv.mResponseHeader = mResponseHeader;
			sv.startTime = startTime;
			
			if (mParam.size() == 1 && sv.GetUsage().size() >= 1)
			{
			    sv.Usage();
			    return;
			}
			
			//检查服务参数
			Iterator<Entry<String, String[]>> iter_usage = sv.GetUsage().entrySet().iterator();
			while (iter_usage.hasNext()) {
				Entry<String, String[]> entry_usage = iter_usage.next();
				String key_usage = entry_usage.getKey();
				String[] value_usage = entry_usage.getValue();
				
				if (Utility.IsEmpty(key_usage))
					continue;
				
				if (value_usage == null || value_usage.length < 3) {
					SetErrorMsg(-1, "服务[" + sv.code + "]的参数[" + key_usage + "]定义错误");
					mResponseData.put("value_usage", value_usage);
					mResponseData.put("Usage", sv.GetUsage());
					return;
				}
				
				if ("Y".equals(value_usage[1]) && (!mParam.containsKey(key_usage) || mParam.get(key_usage) == null || Utility.IsEmpty(mParam.get(key_usage)))) {
					sv.Usage();
					SetErrorMsg(-1, "服务[" + sv.code + "]的参数[" + key_usage + "]必须输入且不能为空");
					mResponseData.put("condition", "Y".equals(value_usage[1]) && (!mParam.containsKey(key_usage)));
					mResponseData.put("equals", "Y".equals(value_usage[1]));
					mResponseData.put("value_usage[1]", value_usage[1]);
					mResponseData.put("value_usage", value_usage);
					mResponseData.put("Usage", sv.GetUsage());
					return;
				}
				
				if (!Utility.IsEmpty(value_usage[2]) && mParam.containsKey(key_usage) && mParam.get(key_usage) != null && !Utility.IsEmpty(mParam.get(key_usage))) {
					String[] sa = value_usage[2].split(",");
					boolean bFound = false;
					for (String s : sa){
						if (mParam.get(key_usage).equals(s)){
							bFound = true;
							break;
						}
					}
					if (!bFound){
						String msg = "参数[" + key_usage + "]只能是[" + sa[0] + "]";
						for (int i = 1; i < sa.length - 1; i++)
							msg += "、[" + sa[i] + "]";
						msg += "或[" + sa[sa.length - 1] + "]";
						sv.Usage();
						SetErrorMsg(-1, msg);
						return;
					}
				}
			}
		} catch (Exception e) {
			SetErrorMsg(-1, "服务初始时遇到致命错误：" + Utility.GetExceptionMsg(e));
			log.Write(e, "服务初始时遇到致命错误：");
			//e.printStackTrace();
			return;
		}
		
		SetErrorMsg(0, "");		
		try {
			if (!sv.DoWork()) {
				int status = Integer.parseInt(mResponseHeader.get("status").toString());
				String msg = mResponseHeader.get("msg").toString();
				if (status == 0 && Utility.IsEmpty(msg)) {
					SetErrorMsg(-1, "程序错误：服务调用失败，但是没有设置错误信息");
					//mResponseHeader.put("CallStack", (new Exception()).getStackTrace());
				}
				return;
			}
		} catch (Exception e) {
			SetErrorMsg(-1, "服务[" + sv.code + "]执行时遇到致命错误：" + Utility.GetExceptionMsg(e));
			log.Write(e, "服务[" + sv.code + "]执行时遇到致命错误：");
			mResponseHeader.put("CallStack", e.getStackTrace());
			//e.printStackTrace();
			return;
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	void Usage(){
		SetErrorMsg(0, "");
		if (sv_list == null)
			return;

		if (!Utility.HasField(sv_list, getClass().getName(), HashMap.class))
			return;
		
		HashMap<String, BaseService> hm_sv = (HashMap<String, BaseService>)sv_list.get(getClass().getName());
		
		HashMap<String, Object> usage = new LinkedHashMap<String, Object>();
		Iterator iter = hm_sv.entrySet().iterator();
		while (iter.hasNext()){
			Entry entry = (Entry)iter.next();
			String key = entry.getKey().toString();
			BaseService bs = (BaseService)entry.getValue();
			if (bs == null || !bs.bShow)
				continue;
			if (key.indexOf("_") < 0 && usage.containsKey(key))	//防止类似Test和Test_TestIt_Test123的重复
				key += " ";
			HashMap<String, Object> usage_child = usage;
			String[] sa = entry.getKey().toString().split("_");
			for (int i = 0; i < sa.length - 1; i++){
				if (Utility.IsEmpty(sa[i]))
					continue;
				if (usage.containsKey(sa[i]) && usage.get(sa[i]).getClass() != HashMap.class)
					sa[i] += " ";
				HashMap<String, Object> usage_now;
				if (!usage_child.containsKey(sa[i])){
					usage_now = new LinkedHashMap<String, Object>();
					usage_child.put(sa[i], usage_now);
				}
				else
					usage_now = (HashMap<String, Object>)usage_child.get(sa[i]);
				usage_child = usage_now;
			}
			usage_child.put(key, bs.GetDescribe());
		}
		mResponseData.put("sv", usage);
	}

	void AddService(String code, BaseService bs){
		AddService(code, bs, true);
	}
	
	synchronized void AddService(String code, BaseService bs, boolean bShow){
		if (sv_list == null)
			sv_list = new LinkedHashMap<String, HashMap<String, BaseService>>();
		
		HashMap<String, BaseService> hm_sv;
		if (Utility.HasField(sv_list, getClass().getName(), HashMap.class)) {
			hm_sv = (HashMap<String, BaseService>)sv_list.get(getClass().getName());
		}
		else {
			hm_sv = new LinkedHashMap<String, BaseService>();
			sv_list.put(getClass().getName(), hm_sv);
			//log.WriteLine("Add sv");
			log.WriteLine(sv_list.get(getClass().getName()).getClass().getName());
		}
		if (hm_sv.containsKey(code)){
			log.WriteLine(getClass().getSimpleName() + "服务[" + code + "]重复定义");
			return;
		}
		bs.code = code;
		bs.bShow = bShow;
		if (bs.init()) {
			bs.DefineParams();
			hm_sv.put(code, bs);
			log.WriteLine(code + "\t" + bs.GetDescribe());
		}
	}
	
	BaseService GetService(String sv){
		if (sv_list == null)
			return null;
		
		if (!Utility.HasField(sv_list, getClass().getName(), HashMap.class))
			return null;
		
		HashMap<String, BaseService> hm_sv = (HashMap<String, BaseService>)sv_list.get(getClass().getName());
		if (hm_sv.containsKey(sv))
			return hm_sv.get(sv);
		else
			return null;
	}
	
	String GetResponseText() throws Exception {
		mResponseHeader.put("time", 9753124680l);
		Gson gson = JsonUtil.getGson();
		//HashMap<String, Object> m = (HashMap<String, Object>)gson.fromJson(gson.toJson(mResponse), mResponse.getClass());
		String text = gson.toJson(mResponse);
		text = text.replaceFirst("9753124680", String.valueOf((new Date()).getTime() - startTime));
		//log.WriteLine(text);
		
		if (Utility.HasField(mParam, "callback"))
			text = mParam.get("callback") + "(" + text + ")";
		
		if (sv != null)
			return sv.GetResponseText(text);
		else 
			return text;
	}
	
	void OnResponse(HttpServletResponse response){
		try{
			response.setContentType("text/html");
			response.setCharacterEncoding("utf-8");
			response.getWriter().println(GetResponseText());
			response.getWriter().flush();
			response.getWriter().close();
			
			if (sv != null)
				sv.OnFinish();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	void SetErrorMsg(int status, String msg){
		mResponseHeader.put("status", status);
		mResponseHeader.put("msg", msg);
	}
}
