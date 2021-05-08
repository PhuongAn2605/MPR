package edu.hanu.myfriendswithdb.models;

import java.io.Serializable;
import java.util.Objects;

public class Friend {
    private long id;
    private String name;
    private String phoneNo;
    private String email;

    public Friend(String name, String phoneNo, String email) {
        this.name = name;
        this.phoneNo = phoneNo;
        this.email = email;
    }

    public Friend(long id, String name, String phoneNo, String email) {
        this.id = id;
        this.name = name;
        this.phoneNo = phoneNo;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "name='" + name + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Friend friend = (Friend) o;
        return Objects.equals(name, friend.name) &&
                Objects.equals(phoneNo, friend.phoneNo) &&
                Objects.equals(email, friend.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phoneNo, email);
    }
}
