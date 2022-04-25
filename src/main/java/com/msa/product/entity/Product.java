package com.msa.product.entity;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

	@Id
	@Column(name = "product_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String productName;
	
	@Column(nullable = false)
	private String address;
	
	@Column(nullable = false)
	private String detailAddress;
	
	@Column(nullable = false)
	private Long price;
	
	@CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
	
	@Builder
	public Product(String productName, String address, String detailAddress, Long price) {
		this.productName = productName;
		this.address = address;
		this.detailAddress = detailAddress;
		this.price = price;
	}
	
}
