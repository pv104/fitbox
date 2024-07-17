package com.a508.wms.service;

import com.a508.wms.domain.Product;
import com.a508.wms.domain.ProductDetail;
import com.a508.wms.dto.ProductDetailResponse;
import com.a508.wms.dto.ProductInfos;
import com.a508.wms.dto.ProductRequest;
import com.a508.wms.repository.ProductDetailRepository;
import com.a508.wms.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;
    private final ProductDetailRepository productDetailRepository;

    public ProductService(ProductRepository productRepository,
        ProductDetailRepository productDetailRepository) {
        this.productRepository = productRepository;
        this.productDetailRepository = productDetailRepository;
    }


    /**
     * DB에서 가져온 Product의 데이터를 Stream을 통해 Dto로 전환하여 반환해주는 기능
     * @return List<ProductInfos> Product의 데이터를 가진 DTO들의 List
     */
    public List<ProductInfos> findAll(){
        final List<Product> products=productRepository.findAll();
        return products.stream().map(product -> ProductInfos.builder()
            .comment(product.getComment())
            .quantity(product.getProductQuantity())
            .productDetail(getProductDetail(product.getProductDetail()))
            .build())
            .toList();
    }

    /**
     * 인자로 들어온 id에 해당하는 Product를 반환하는 기능
     * id에 해당 하는 데이터가 없다면 예외 발생.
     * @param id : Product의 id
     * @return ProductInfos: Product의 데이터를 가진 DTO
     */
    public ProductInfos findById(long id){
        Product product=productRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        return ProductInfos.builder()
            .comment(product.getComment())
            .quantity(product.getProductQuantity())
            .productDetail(getProductDetail(product.getProductDetail()))
            .build();
    }

    /**
     * 상품 상세 id에 해당하는 상품들을 가져오는 기능
     * @param productDetailId: 상품 상세 id (PK)
     * @return 상품 상세 id에 해당하는 상품들의 리스트
     */
    public List<ProductInfos> findByProductDetailId(long productDetailId){
        final List<Product> products=productRepository.findByProductDetailId(productDetailId);
        return getProductInfos(products);
    };

    /**
     * 사업체 id에 해당하는 상품들을 가져오는 기능
     * @param businessId: 사업체 id (PK)
     * @return 사업체 id에 해당하는 상품들의 리스트
     */
    public List<ProductInfos> findByBusinessId(long businessId){
        final List<Product> products=productRepository.findByBusinessId(businessId);
        return getProductInfos(products);
    }

    /**
     * 창고 id에 해당하는 상품들을 가져오는 기능
     * @param warehouseId:창고 id (PK)
     * @return 창고 id에 해당하는 상품들의 리스트
     */
    public List<ProductInfos> findByWarehouseId(long warehouseId){
        final List<Product> products=productRepository.findByWarehouseId(warehouseId);
        return getProductInfos(products);
    }

    /**
     * List<Product>를 List<ProductInfos>로 변환해주는 기능
     * @param products:product들의 데이터 리스트
     * @return
     */

    public List<ProductInfos> getProductInfos(List<Product> products){
        return products.stream().map(product -> com.a508.wms.dto.ProductInfos.builder()
                .comment(product.getComment())
                .quantity(product.getProductQuantity())
                .productDetail(getProductDetail(product.getProductDetail()))
                .build())
            .toList();
    }

    /**
     *
     * @param productDetail : ProductDetail의 데이터를 Dto로 변환해주는 기능
     * @return ProductDetailResponse : ProductDetail의 데이터를 가진 DTO
     */
    public ProductDetailResponse getProductDetail(ProductDetail productDetail){
        return ProductDetailResponse.builder()
            .barcode(productDetail.getBarcode())
            .name(productDetail.getName())
            .size(productDetail.getSize())
            .unit(productDetail.getUnit())
            .originalPrice(productDetail.getOriginalPrice())
            .sellingPrice(productDetail.getSellingPrice())
            .build();
    }


    /**
     * ProductDetail값을 통해 Product를 저장하는 기능
     * @param request: Product 데이터
     */
    public void save(ProductRequest request){
        ProductDetail productDetail=productDetailRepository.findById(request.getProductDetailId())
            .orElseThrow(()->new IllegalArgumentException("Invalid ProductDetail Id"));

        Product product=new Product(productDetail,request.getProductQuantity(),
            request.getExpirationDate(), request.getComment());

        productRepository.save(product);
    }
}
