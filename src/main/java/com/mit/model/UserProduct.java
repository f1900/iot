package com.mit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * UserProduct entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="user_product"
    ,catalog="iot"
)
@JsonIgnoreProperties(value = { "hibernateLazyInitializer","handler","fieldHandler"})
public class UserProduct  implements java.io.Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields    
     private Integer id;
     @JsonIgnore
     private User user;
//     @JsonIgnore
     private Product product;


    // Constructors

    /** default constructor */
    public UserProduct() {
    }

    
    /** full constructor */
    public UserProduct(User user, Product product) {
        this.user = user;
        this.product = product;
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
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="user", nullable=false)

    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="product", nullable=false)

    public Product getProduct() {
        return this.product;
    }
    
    public void setProduct(Product product) {
        this.product = product;
    }
   








}