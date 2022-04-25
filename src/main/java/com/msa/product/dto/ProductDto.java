package com.msa.product.dto;

import javax.validation.Valid;

import com.msa.product.entity.Product;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ProductDto {
	
	@Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SaveReq {
    	
    	@Valid
        private String productName;
    	
    	@Valid
    	private String address;
    	
    	@Valid
        private String detailAddress;
    	
    	@Valid
    	private long price;

        @Builder
        public SaveReq(String productName, String address, String detailAddress, long price) {
            this.productName = productName;
            this.address = address;
            this.detailAddress = detailAddress;
            this.price = price;
        }

        public Product toEntity() {
            return Product.builder()
                    .productName(this.productName)
                    .address(this.address)
                    .detailAddress(this.detailAddress)
                    .price(this.price)
                    .build();
        }
	}
	
	@Getter
	public static class Res {
		private Long productId;
		private String productName;
		private String address;
		private String detailAddress;
		private Long price;
		
		public Res(Product product) {
			this.productId = product.getId();
			this.productName = product.getProductName();
			this.address = product.getAddress();
			this.detailAddress = product.getDetailAddress();
			this.price = product.getPrice();
		}
	}
}
