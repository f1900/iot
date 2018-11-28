package com.mit.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="user"
    ,catalog="iot"
)
@JsonInclude(Include.NON_EMPTY)@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class User  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
     private String name;
     private String phone;
     @JsonIgnore
     private String password;
     private Integer sex;
     @JsonFormat(pattern = "yyyy-MM-dd")
     @DateTimeFormat(iso=ISO.DATE,pattern="yyyy-MM-dd")
     private Date birthday;
     private String head;
     private String country;
     @JsonIgnore
     private Set<UserProduct> userProducts = new HashSet<UserProduct>(0);


    // Constructors

    /** default constructor */
    public User() {
    }

	/** minimal constructor */
    public User(Integer id) {
        this.id = id;
    }
    
    /** full constructor */
    public User(Integer id, String name, String phone, String password, Integer sex, Date birthday, String head, String country, Set<UserProduct> userProducts) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.sex = sex;
        this.birthday = birthday;
        this.head = head;
        this.country = country;
        this.userProducts = userProducts;
    }

   
    // Property accessors
    @Id
    
    @Column(name="id", unique=true, nullable=false)

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    @Column(name="name" )

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="phone" )

    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    @Column(name="password" )

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Column(name="sex")

    public Integer getSex() {
        return this.sex;
    }
    
    public void setSex(Integer sex) {
        this.sex = sex;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="birthday", length=10)

    public Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    
    @Column(name="head" )

    public String getHead() {
        return this.head;
    }
    
    public void setHead(String head) {
        this.head = head;
    }
    
    @Column(name="country" )

    public String getCountry() {
        return this.country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="user")
    @JsonIgnore
    public Set<UserProduct> getUserProducts() {
        return this.userProducts;
    }
    
    public void setUserProducts(Set<UserProduct> userProducts) {
        this.userProducts = userProducts;
    }
}