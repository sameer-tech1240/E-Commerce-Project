package com.quickCommerce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long sellerId;
    private String productName;
    private String category;
    private Double price;
    private Double discount;
    private Double discountedPrice;
    private Integer quantity;
    @Lob
    @Column(name = "front_image", columnDefinition = "LONGBLOB") // optional but good for MySQL
    private byte[] frontImage;

    @Lob
    private byte[] img1;

    @Lob
    private byte[] img2;

    @Lob
    private byte[] img3;

    @Lob
    private byte[] img4;

    @Lob
    private byte[] img5;
    public byte[] getImg1() {
		return img1;
	}

	public void setImg1(byte[] img1) {
		this.img1 = img1;
	}

	public byte[] getImg2() {
		return img2;
	}

	public void setImg2(byte[] img2) {
		this.img2 = img2;
	}

	public byte[] getImg3() {
		return img3;
	}

	public void setImg3(byte[] img3) {
		this.img3 = img3;
	}

	public byte[] getImg4() {
		return img4;
	}

	public void setImg4(byte[] img4) {
		this.img4 = img4;
	}

	public byte[] getImg5() {
		return img5;
	}

	public void setImg5(byte[] img5) {
		this.img5 = img5;
	}

	// Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(Double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }



    public byte[] getFrontImage() {
		return frontImage;
	}

	public void setFrontImage(byte[] frontImage) {
		this.frontImage = frontImage;
	}

	
}
