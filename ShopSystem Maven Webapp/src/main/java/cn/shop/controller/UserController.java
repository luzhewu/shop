package cn.shop.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.shop.pojo.User;
import cn.shop.service.UserService;
import cn.shop.tools.Constants;
import cn.shop.tools.PageSupport;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;

@Controller
public class UserController extends BaseController {
	private Logger logger = Logger.getLogger(UserController.class);
	@Resource
	private UserService userService;

	/**
	 * 进入登录页面
	 * @return
	 */
	@RequestMapping(value = "login.html")
	public String login() {
		logger.debug("come to login------->");
		return "login";
	}

	/**
	 * 用户登录
	 * @param userCode
	 * @param password
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "dologin.html", method = RequestMethod.POST)
	public String doLogin(@RequestParam String userCode,
			@RequestParam String password, HttpSession session,
			HttpServletRequest request) {

		// 调用service层方法，进行用户匹配
		User user = userService.login(userCode, password);
		if (user != null) {// 登陆成功
			// 放入session
			session.setAttribute(Constants.USER_SESSION, user);
			// 页面跳转（welcome.jsp）
			return "redirect:/welcome.html";
		} else {// 登陆失败
			// 页面跳转（login.jsp）带出提示信息--转发
			request.setAttribute("error", "用户名或密码错误，请重新输入");
			return "login";
		}
	}

	/**
	 * 登陆成功，跳转页面，并检查session中登陆USER是否存在，不存在返回登陆页面
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "welcome.html")
	public String welcome(HttpSession session) {
		if (session.getAttribute(Constants.USER_SESSION) == null) {
			return "login";
		}
		return "welcome";
	}

	/**
	 * 用户注销操作
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "logout.html")
	public String logout(HttpSession session) {
		// 清除session
		session.removeAttribute(Constants.USER_SESSION);
		return "redirect:/login.html";
	}

	/**
	 * 查询用户列表
	 * @param userName  查询条件
	 * @param model		模型对象
	 * @param pageIndex 查第几页
	 * @return
	 */
	@RequestMapping(value = "user/userlist.html")
	public String userlist(String userName, Model model, String pageIndex) {
		// 查询用户列表
		List<User> userList = null;
		if (userName == null) {
			userName = "";
		}
		// 当前页码
		int currentPageNo = 1;
		if (pageIndex == null) {
			currentPageNo = 1;
		} else {
			currentPageNo = Integer.parseInt(pageIndex);
		}
		// 总记录数
		int totalCount = userService.getCount(userName);
		// 页容量
		int pageSize = 3;
		// 总页数
		PageSupport ps = new PageSupport();
		ps.setPageSize(pageSize);
		ps.setTotalCount(totalCount);
		ps.setCurrentPageNo(currentPageNo);
		int totalPageCount = ps.getTotalPageCount();
		// 控制首页和末页
		if (currentPageNo <= 1) {
			currentPageNo = 1;
		}
		if (currentPageNo > totalPageCount) {
			currentPageNo = totalPageCount;
		}

		userList = userService.getuserList(userName, currentPageNo, pageSize);
		model.addAttribute("userList", userList);
		model.addAttribute("userName", userName);
		model.addAttribute("currentPageNo", currentPageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("totalCount", totalCount);
		return "userlist";
	}

	/**
	 * 用户登陆失效进入的页面
	 * @return
	 */
	@RequestMapping(value = "401.html")
	public String to_401() {
		return "401";
	}

	/**
	 * 跳转到增加用户页面
	 */
	@RequestMapping(value = "/user/useradd.html", method = RequestMethod.GET)
	public String addUser(@ModelAttribute("user") User user) {
		return "useradd";
	}

	/**
	 * 保存增加用户
	 */
	@RequestMapping(value = "/user/useraddsave.html")
	public String addUserSave(HttpSession session, User user) {
		user.setCreatedBy(((User) session.getAttribute(Constants.USER_SESSION))
				.getId());
		user.setCreationDate(new Date());
		if (userService.add(user)) {
			return "redirect:/user/userlist.html";
		} else {
			return "useradd";
		}
	}

	/**
	 * 验证用户编码是否存在
	 */
	@RequestMapping(value = "/user/ucisexist.html")
	@ResponseBody
	public String userCodeIsExist(@RequestParam("userCode") String userCode) {
		Map<String, String> map = new HashMap<String, String>();
		logger.debug("进入userCodeIsExist-----userCode:" + userCode);
		if (StringUtils.isNullOrEmpty(userCode)) {
			map.put("userCode", "exist");
		} else {
			if (userService.selectUserCodeExist(userCode) != null) {
				map.put("userCode", "exist");
			} else {
				map.put("userCode", "notexist");
			}
		}
		logger.debug("JSONArray.toJSONString(map)--------->:"
				+ JSONArray.toJSONString(map));
		return JSONArray.toJSONString(map);
	}

	@RequestMapping(value = "/user/userview.html", method = RequestMethod.GET)
	public String getUserById(@RequestParam String uid, Model model) {
		User user = userService.getUserById(uid);
		model.addAttribute(user);
		return "userview";
	}

	@RequestMapping(value = "/user/userview.html", method = RequestMethod.POST)
	@ResponseBody
	public Object getUserById(@RequestParam String uid) {
		String cjson = "";
		if (StringUtils.isNullOrEmpty(uid)) {
			return "nodata";
		} else {
			try {
				User user = userService.getUserById(uid);
				cjson = JSON.toJSONString(user);
			} catch (Exception e) {
				e.printStackTrace();
				return "failed";
			}
		}
		return cjson;
	}

	/**
	 * 
	 * @param uid
	 * @return
	 */
	@RequestMapping(value = "/user/userview2", method = RequestMethod.GET)
	@ResponseBody
	public Object getUserById2(@RequestParam String uid) {
		User user = new User();
		try {
			user = userService.getUserById(uid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
}
