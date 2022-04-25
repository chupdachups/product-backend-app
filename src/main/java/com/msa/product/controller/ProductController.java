package com.msa.product.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.msa.product.dto.ProductDto;
import com.msa.product.service.ProductService;

@RestController
@RequestMapping("products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public List<ProductDto.Res> getProducts() {
	    return productService.findAllProduct().stream()
	    		.map(m -> new ProductDto.Res(m))
	    		.collect(Collectors.toList());
	}
// response를 조금더 유연하게 하기 위해 result로 감싸 response를 추가하기 용이하다.	
//	@RequestMapping(method = RequestMethod.GET)
//	@ResponseStatus(value = HttpStatus.OK)
//	public Result getProducts() {
//		List<Product> products = productService.findAllProduct();
//		List<ProductDto.Res> collect = products.stream()
//				.map(m -> new ProductDto.Res(m)).collect(Collectors.toList());
//	    return new Result(collect.size(), collect);
//	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<?> saveProduct(@RequestBody @Valid final ProductDto.SaveReq dto) {
		productService.create(dto);
		return ResponseEntity.ok(true);
	}
	
	@RequestMapping(value = "/{productId}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<?> deleteProduct(@PathVariable("productId") long productId) {
		productService.delete(productId);
		return ResponseEntity.ok(true);
	}

}
