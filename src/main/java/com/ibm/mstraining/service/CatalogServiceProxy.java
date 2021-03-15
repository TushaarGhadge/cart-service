package com.ibm.mstraining.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ibm.mstraining.model.Product;

@FeignClient(name="catalaog-service")
public interface CatalogServiceProxy {
	
	@GetMapping("/catalog-service/product/{productId}")
	public Product getProduct(@PathVariable long productId);
	
}
