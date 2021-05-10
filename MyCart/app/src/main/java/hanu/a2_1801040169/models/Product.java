package hanu.a2_1801040169.models;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

public class Product implements Serializable {
    private long id;
    private String thumbnail;
    private String name;
    private String category;
    private double price;

    private long count;
    private double sumPrice;

    //    private long productId;
    public Product() {
    }

    public Product(long id, String thumbnail, String name, String category, double price) {
        this.id = id;
        this.thumbnail = thumbnail;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public Product(long id,String name, String category, double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }


    public Product(String thumbnail, String name, double price, long count) {
        this.name = name;
        this.price = price;
        this.thumbnail = thumbnail;
        this.count = count;
        this.sumPrice = getSumPrice();
    }

    public Product(long id, String thumbnail, String name, String category, double price, long count) {
        this.id = id;
        this.thumbnail = thumbnail;
        this.name = name;
        this.category = category;
        this.price = price;
        this.count = count;
        this.sumPrice = getSumPrice();

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
        this.sumPrice = getSumPrice();
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
        this.sumPrice = getSumPrice();
    }

    public double getSumPrice() {
        return price * getCount();
    }

//    public double setSumPrice() {
//        return price * count;
//    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String formatPrice(double price) {
//        DecimalFormat formatter = new DecimalFormat("###,###,###");
//        return formatter.format(price);
        Locale lc = new Locale("nv","VN");
        NumberFormat nf = NumberFormat.getInstance(lc);
        String price_str = nf.format(price);
        return price_str.replace(",", ".");

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product Product = (Product) o;
        return id == Product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", thumbnail='" + thumbnail + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", sumPrice=" + sumPrice +
                '}';
    }
}
