package com.a508.wms.productdetail.repository;

import com.a508.wms.productdetail.domain.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {

    List<ProductDetail> findByBusinessId(Long BusinessId);

    Optional<ProductDetail> findByBusinessIdAndBarcode(Long BusinessId, Long Barcode);
}