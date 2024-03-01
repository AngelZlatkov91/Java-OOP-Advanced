package org.BoxDeliver.Sender;

import org.BoxDeliver.common.ExceptionMessages;

public class BaseSender implements Sender{
    private String name;
    private String town;
    private String address;
    private String phone;

    public BaseSender(String name, String town, String address, String phone) {
        this.setName(name);
        this.setTown(town);
        this.setAddress(address);
        this.setPhone(phone);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.SENDER_NAME_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        if (town == null || town.trim().isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.SENDER_TOWN_CANNOT_BE_NULL_OR_EMPTY);
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
            throw new IllegalArgumentException(ExceptionMessages.SENDER_PHONE_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.phone = phone;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sender:").append(System.lineSeparator());
        sb.append("Name: ").append(getName()).append(System.lineSeparator());
        sb.append("Address: ").append(getAddress()).append(System.lineSeparator());
        sb.append("Town: ").append(getTown()).append(System.lineSeparator());
        sb.append("Phone: ").append(getPhone()).append(System.lineSeparator());
        return sb.toString().trim();
    }
}
