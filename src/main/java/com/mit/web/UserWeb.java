package com.mit.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mit.model.R;
import com.mit.model.User;
import com.mit.service.UserServ;


@RestController@RequestMapping("/")
public class UserWeb {

	@Autowired
	UserServ us;

	@RequestMapping("/login")
	public R login(@RequestParam(required=true) String phone,@RequestParam(required=true) String password,HttpSession session){
		User user=us.getUser(phone, password);
		int s=0;
		if (user!=null) {
			session.setAttribute("id",user.getId());
		}else {
			s=1;
		}
		return new R(s,user);
	}
	@RequestMapping("/code")
	public R code(@RequestParam(required=true) String phone) {
		return new R();
	}

	@RequestMapping(value={"/register","/reset"})@ResponseBody
	public R register(@RequestParam(required=true) String phone,@RequestParam(required=true) String password,@RequestParam(required=true) String code)  {
		int id=us.reg(phone, password);
		int s=0;
		if(id==0){
			s=1;
		}
		return new R(s,id);
	}

	@RequestMapping("/modify/info")@ResponseBody
	public R info(User u){
		return new R(us.modify(u)); 
	}

	@RequestMapping("/modify/password")@ResponseBody
	public R newPass(int id,String oldPassword,String newPassword){
		return new R(us.modify(id, oldPassword, newPassword),null);
	}

	@RequestMapping("/modify/phone")
	public R phone(int id,String phone,String code){
		return new R(us.modify(id, phone));
	}

	@RequestMapping("/modify/head")@ResponseBody
	public R head(HttpServletRequest request){
		String s=us.modify(request);
		if(s==null)
			return new R(1,null);
		return new R(us.modify(request));
	}
	@RequestMapping("/product/all")@ResponseBody
	public R all(){
		return new R(us.all());
	}
	@RequestMapping("/product/my")@ResponseBody
	public R my(int id){
		return new R(us.my(id));
	}
	@RequestMapping("/product/add")@ResponseBody
	public R add(int userId,int productId){
		us.add(userId, productId);
		return new R();
	}
	@RequestMapping("/product/del")@ResponseBody
	public R del(int id){
		try {
			us.del(id);
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			return new R(1);
		}
		return new R();
	}
}
