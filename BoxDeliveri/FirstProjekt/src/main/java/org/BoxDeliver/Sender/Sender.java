package org.BoxDeliver.Sender;

public interface Sender {
   String getName();
   String getTown();
   String getAddress();
   String getPhone();
   void setName(String name);
   void setAddress(String address);
   void setTown(String town);
   void setPhone(String phone);

}
