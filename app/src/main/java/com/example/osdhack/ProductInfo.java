package com.example.osdhack;

class ProductInfo {
    public String pname;
    public int qty;
    public int price;
    public String productdescription;

    public ProductInfo(String pname,int qty,int price,String productdescription)
    {
        this.pname=pname;
        this.qty=qty;
        this.price=price;
        this.productdescription=productdescription;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getProductdescription() {
        return productdescription;
    }

    public void setProductdescription(String productdescription) {
        this.productdescription = productdescription;
    }
}
