package com.msa.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.msa.product.dto.ProductDto;
import com.msa.product.entity.Product;
import com.msa.product.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Transactional(readOnly = true)
	public List<Product> findAllProduct() {
	    return productRepository.findAll();
	}
	
	public void create(ProductDto.SaveReq dto) {
		productRepository.save(dto.toEntity());
	}
	
	public void delete(long productId) {
		productRepository.deleteById(productId);
	}
}
