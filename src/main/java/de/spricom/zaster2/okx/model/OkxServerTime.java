package de.spricom.zaster2.okx.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OkxServerTime {

    private String ts;

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    @Override
    public String toString() {
        return "OkxServerTime{" +
                "ts='" + ts + '\'' +
                '}';
    }
}
