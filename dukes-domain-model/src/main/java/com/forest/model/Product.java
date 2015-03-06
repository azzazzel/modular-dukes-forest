package com.forest.model;

import java.io.Serializable;

public class Product implements Serializable {

	private static final long serialVersionUID = -9109112921000514199L;
	protected Integer id;
	protected String name;
	protected double price;
	protected String description;
	private String img;
	private byte[] imgSrc;
	private Category category;

	public Product() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String simg) {
		this.img = simg;
	}

	public byte[] getImgSrc() {
		return imgSrc;
	}

	public void setImgSrc(byte[] imgSrc) {
		this.imgSrc = imgSrc;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Product)) {
			return false;
		}
		Product other = (Product) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.forest.entity.Product[id=" + id + "]";
	}

}