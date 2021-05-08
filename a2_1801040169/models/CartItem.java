package hanu.a2_1801040169.models;

import java.io.Serializable;
import java.util.Objects;

public class CartItem implements Serializable {
    private long id;
    private String thumbnail;
    private String name;
    private double price;

    private long count;
    private double sumPrice;

    private long productId;

    public CartItem(long id, String name, double price, long count, double sumPrice) {
        this.name = name;
        this.price = price;
        this.count = count;
        this.sumPrice = sumPrice;
    }

    public CartItem(String thumbnail, String name, double price, long count, double sumPrice, long productId) {
        this.name = name;
        this.price = price;
        this.thumbnail = thumbnail;
        this.count = count;
        this.sumPrice = sumPrice;
        this.productId = productId;
    }

    public CartItem(long id, String thumbnail, String name, double price, long count, double sumPrice, long productId) {
        this.id = id;
        this.thumbnail = thumbnail;
        this.name = name;
        this.price = price;
        this.count = count;
        this.sumPrice = sumPrice;
        this.productId = productId;
    }

//    public CartItem(String thumbnail, String name, double unitPrice, long id) {
//    }

//    public CartItem(long id, String thumbnail, String name, double price, int count, double sumPrice, long productId) {
//        this(id, thumbnail, name, price, count, sumPrice, productId);
//    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
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

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public double getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(double sumPrice) {
        this.sumPrice = sumPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return id == cartItem.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", thumbnail='" + thumbnail + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", sumPrice=" + sumPrice +
                ", productId=" + productId +
                '}';
    }
}
