package com.mit.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.mit.dao.ProductDao;
import com.mit.dao.UserDao;
import com.mit.dao.UserProductDao;
import com.mit.model.Product;
import com.mit.model.User;
import com.mit.model.UserProduct;
import com.mit.util.FileUp;

@Service
public class UserServ {
//	@Autowired
//	BussinessDao bd;
	@Autowired
	UserDao ud;
	@Autowired
	ProductDao pd;
	@Autowired
	UserProductDao upd;
	@Autowired
	FileUp fu;
	@PersistenceContext
	private EntityManager em;
//	@Cacheable("user") //标注该方法查询的结果进入缓存，再次访问时直接读取缓存中的数据
	public User getUser(String phone,String password){
//		ExampleMatcher em=ExampleMatcher.matching().withMatcher(propertyPath, genericPropertyMatcher);
		User u=new User();
		u.setPhone(phone);
		u.setPassword(password);
		ExampleMatcher em=ExampleMatcher.matching();
		Example<User> example = Example.of(u,em);
		Optional<User> o=ud.findOne(example);
		return o.isPresent()?o.get():null;
	}
	public User getUser2(String phone,String password){
//		ExampleMatcher em=ExampleMatcher.matching().withMatcher(propertyPath, genericPropertyMatcher);
		User u=new User();
		u.setPhone(phone);
		u.setPassword(password);

		Optional<User> o=ud.findOne(new Specification<User>(){
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return null;
			}});
		return o.isPresent()?o.get():null;
	}
	public int reg(String phone,String password){
		User u=new User();
		u.setPhone(phone);
		u.setPassword(password);
		try {
			u=ud.save(u);
		} catch (DataIntegrityViolationException e) {
			System.out.println(e.getMessage());
			return 1;
		}
		return u.getId();
	}
//	@CacheEvict(value={"user"},allEntries=true)
	public int modify(User user){
		Optional<User> one=ud.findById(user.getId());

		User u=one.get();
		try {
			u.setName(user.getName());
			u.setCountry(user.getCountry());
			u.setBirthday(user.getBirthday());
			u.setSex(user.getSex());
			ud.save(u);
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
		return 0;
	}
	public int modify(int id,String oldPassword,String newPassword){
		User u=new User();
		u.setId(id);
		u.setPassword(oldPassword);
		Example<User> example=Example.of(u);
		Optional<User> o=ud.findOne(example);
		if(!o.isPresent()){
			return 1;
		}
		u=o.get();
		u.setPassword(newPassword);
		ud.save(u);
		return 0;
	}
	public int modify(int id,String phone){
		Optional<User> o=ud.findById(id);
		if(!o.isPresent()){
			return 1;
		}
		User user=o.get();
		user.setPhone(phone);
		try {
			ud.save(user);
			
		} catch (Exception e) {
			return 1;
		}
		return 0;
	}
	public String modify(HttpServletRequest request){
		
		List<FileItem> fileItems=new ArrayList<FileItem>();
		Map<String, String> map=fu.field(request,fileItems);
		
		if(fileItems.size()==0||map.get("id")==null){
			System.out.println(fileItems.size());
			System.out.println(map.get("id"));
			return null;
		}

		String fileName=fileItems.get(0).getName();
		String fileExt=fileName.substring(fileName.lastIndexOf(".")!=-1?fileName.lastIndexOf("."):fileName.length());

		String url=fu.up(fileItems.get(0),"/head",map.get("id")+fileExt);
		if(url!=null){
			Optional<User> o=ud.findById(Integer.parseInt(map.get("id")));
			User user=o.get();
			user.setHead(url);
			ud.save(user);
			return url;
		}else {
			return null;
		}
	}
	
	public List<Product> all(){
		return pd.findAll();
	}

	public Set<UserProduct> my(int id){
		Optional<User> op=ud.findById(id);
		User user=op.get();
		Set<UserProduct> s=user.getUserProducts();
		return s;
		
	}

	public int add(int userId,int productId){
		User user=ud.getOne(userId);
		Product product=pd.getOne(productId);
		try {
			UserProduct up=upd.save(new UserProduct(user, product));
			
			return up.getId();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public void del(int id){
//		User user=new User(userId);
//		Product product=new Product(productId);
//		UserProduct up=new UserProduct(user, product);
		upd.deleteById(id);;
	}
}
