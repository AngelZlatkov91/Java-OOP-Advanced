package org.BoxDeliver.Deliver;

import org.BoxDeliver.common.ExceptionMessages;

public class BaseDeliver implements Deliver{
    private String name;
    private String town;
    private String postCode;
    private String address;
    private String phone;

    public BaseDeliver(String name, String town,String postCode, String address, String phone) {
        this.setName(name);
        this.setTown(town);
        this.setPostCode(postCode);
        this.setAddress(address);
        this.setPhone(phone);
    }

    @Override
    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        if (postCode == null || postCode.trim().isEmpty()){
            throw new IllegalArgumentException(ExceptionMessages.DELIVER_POST_CODE_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.postCode = postCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.DELIVER_NAME_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        if (town == null || town.trim().isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.DELIVER_TOWN_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.town = town;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address == null) {
            address = "";
        }
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.DELIVER_PHONE_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.phone = phone;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Deliver:").append(System.lineSeparator());
        sb.append("Name: ").append(getName()).append(System.lineSeparator());
        sb.append("Address: ").append(getAddress()).append(System.lineSeparator());
        sb.append("Town: ").append(getTown()).append(": Post Code - ").append(getPostCode()).append(System.lineSeparator());
        sb.append("Phone: ").append(getPhone()).append(System.lineSeparator());
        return sb.toString().trim();
    }
}
