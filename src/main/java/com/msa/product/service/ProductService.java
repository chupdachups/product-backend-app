package com.msa.product.service;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.msa.product.dto.ProductDto;
import com.msa.product.entity.Product;
import com.msa.product.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Transactional
@Slf4j
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private WebClient webClient;
	
	@Transactional(readOnly = true)
	public List<Product> findAllProduct() {
	    return productRepository.findAll();
	}
	
	public Mono<String> create(ProductDto.SaveReq dto) {
		return this.sendProductDataToReserve(productRepository.save(dto.toEntity()));
	}
	
	public void delete(long productId) {
		productRepository.deleteById(productId);
	}
	
	@SuppressWarnings("unchecked")
	private Mono<String> sendProductDataToReserve(Product product) {
		JSONObject jsonReq = new JSONObject();
		jsonReq.put("id", product.getId());
		jsonReq.put("productName", product.getProductName());
		jsonReq.put("price", product.getPrice());
		
		return this.webClient.post()
				.uri("/product")
				.accept(MediaType.APPLICATION_JSON)
				.bodyValue(jsonReq)
				.retrieve()
				.bodyToMono(String.class);
		
//		Span nextSpan = this.tracer.nextSpan().name("client");
		
//		return Mono.just(nextSpan)
//				.doOnNext(span -> this.tracer.withSpan(span.start()))
//				.flatMap(span -> {
//					log.info("<ACCEPTANCE_TEST> <TRACE:{}> Hello from consumer", this.tracer.currentSpan().context().traceId());
//					return this.webClient.post().uri("/product").accept(MediaType.APPLICATION_JSON).bodyValue(jsonReq).retrieve().bodyToMono(String.class);
//				})
//				.doFinally(signalType -> nextSpan.end());
		
//		return Mono.just(nextSpan)
//				.doOnNext(span -> this.tracer.withSpan(span.start()))
//				.flatMap(span -> {
//					log.info("<ACCEPTANCE_TEST> <TRACE:{}> Hello from consumer", this.tracer.currentSpan().context().traceId());
//					return this.webClient.get().retrieve().bodyToMono(String.class);
//				})
//				.doFinally(signalType -> nextSpan.end());
	}
	
	
}
