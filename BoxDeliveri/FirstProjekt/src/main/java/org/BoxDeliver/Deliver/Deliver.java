package org.BoxDeliver.Deliver;

public interface Deliver {
    String getName();
    String getTown();
    String getAddress();
    String getPhone();
    String getPostCode();
    void setName(String name);
    void setAddress(String address);
    void setTown(String town);
    void setPhone(String phone);
}
