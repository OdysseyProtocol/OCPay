package com.stormfives.ocpay.common;

/**
 * Created by lyc on 17/3/7.
 */
public enum HttpStatus {

    SUCCESS(true),
    FALSE(false);

    HttpStatus(Boolean key){
      this.key=key;
    }
    private Boolean key;

    public Boolean getKey() {
        return key;
    }

    public void setKey(Boolean key) {
        this.key = key;
    }


}
