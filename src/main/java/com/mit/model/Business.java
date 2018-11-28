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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * Business entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="business"
    ,catalog="iot"
)
public class Business  implements java.io.Serializable {


    // Fields    
     private Integer id;
     private String name;
     private String country;
     @JsonIgnore
     private Set<Product> products = new HashSet<Product>(0);


    // Constructors

    /** default constructor */
    public Business() {
    }

	/** minimal constructor */
    public Business(Integer id) {
        this.id = id;
    }
    
    /** full constructor */
    public Business(Integer id, String name, String country, Set<Product> products) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.products = products;
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
    
    @Column(name="name", length=100)

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="country", length=100)

    public String getCountry() {
        return this.country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="business")

    public Set<Product> getProducts() {
        return this.products;
    }
    
    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}