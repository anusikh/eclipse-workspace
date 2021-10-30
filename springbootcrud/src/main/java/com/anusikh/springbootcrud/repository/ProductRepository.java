package com.anusikh.springbootcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anusikh.springbootcrud.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	Product findByName(String name);
}
