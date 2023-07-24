package com.example.restauouma;

import java.util.Date;

public class productData {

    private Integer id;
    private String productId;

    private String productName;

    private String status;

    private Integer Qty;

    private Double price;
    private Double priceTtc;

    private Double tvaTaxe;
    private String type;
    private Date date;
    private String image;

    public productData(Integer id, String productId, String productName, String status,
                       Integer Qty, Double price, Double priceTtc,
                       Double tvaTaxe, String type, Date date, String image){
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.status = status;
        this.price = price;
        this.priceTtc = priceTtc;
        this.Qty = Qty;
        this.tvaTaxe = tvaTaxe;
        this.image = image;
        this.date = date;
        this.type = type;
    }
    public productData(Integer id, String productId, String productName, Double price, String image, Date date){
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.image = image;
        this.date = date;
    }

    public Integer getId(){
        return id;
    }
    public String getType(){
        return type;
    }
    public Integer getQty(){
        return Qty;
    }
    public String getProductId(){
        return productId;
    }
    public String getStatus(){
        return status;
    }
    public String getProductName(){
        return productName;
    }
    public Double getPrice(){
        return price;
    }
    public Double getPriceTtc(){
        return priceTtc;
    }
    public Double getTvaTaxe(){
        return tvaTaxe;
    }
    public String getImage(){
        return image;
    }
    public Date getDate(){
        return date;
    }


}
