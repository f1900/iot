package com.mit.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * Product entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="product"
    ,catalog="iot"
)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"}) 
public class Product  implements java.io.Serializable {

    // Fields    
     private Integer id;
     private Integer version;
     @JsonIgnore
     private Business business;
     private String name;
     private String face;
     @JsonIgnore
     private Set<UserProduct> userProducts = new HashSet<UserProduct>(0);


    // Constructors

    /** default constructor */
    public Product() {
    }

	/** minimal constructor */
    public Product(Integer id) {
        this.id = id;
    }
    
    /** full constructor */
    public Product(Integer id, Business business, String name, String face, Set<UserProduct> userProducts) {
        this.id = id;
        this.business = business;
        this.name = name;
        this.face = face;
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
    @Version
    @Column(name="version")

    public Integer getVersion() {
        return this.version;
    }
    
    public void setVersion(Integer version) {
        this.version = version;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="business")

    public Business getBusiness() {
        return this.business;
    }
    
    public void setBusiness(Business business) {
        this.business = business;
    }
    
    @Column(name="name", length=100)

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="face", length=100)

    public String getFace() {
        return this.face;
    }
    
    public void setFace(String face) {
        this.face = face;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="product")
@JsonIgnore
    public Set<UserProduct> getUserProducts() {
        return this.userProducts;
    }
    
    public void setUserProducts(Set<UserProduct> userProducts) {
        this.userProducts = userProducts;
    }
   








}