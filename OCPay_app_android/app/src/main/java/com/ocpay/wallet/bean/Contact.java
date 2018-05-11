package com.ocpay.wallet.bean;

import java.io.Serializable;

/**
 * Created by y on 2018/5/10.
 */

public class Contact implements Serializable {

    String firstName;
    String familyName;
    String walletAddress;
    String phoneName;
    String note;
    String email;


    public Contact(String firstName, String familyName, String walletAddress) {
        this.firstName = firstName;
        this.familyName = familyName;
        this.walletAddress = walletAddress;
    }

    public Contact(String firstName, String walletAddress) {
        this.firstName = firstName;
        this.walletAddress = walletAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getWalletAddress() {
        return walletAddress;
    }

    public String getPhoneName() {
        return phoneName;
    }

    public String getNote() {
        return note;
    }

    public String getEmail() {
        return email;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
