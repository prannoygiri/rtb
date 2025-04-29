package com.rtb.search.entity;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Data
@Document(indexName = "rtb")
public class AdRequest {
    private String id;
    private List<Imp> imp;
    private App app;
    private Device device;
    private User user;
    private Integer at;
    private List<String> bcat;
    private List<String> badv;


    @Data
    public static class Imp {
        private String id;
        private Banner banner;
        private Integer instl;
        private String tagid;
        private double bidfloor;
    }

    @Data
    public static class Banner {
        private Integer w;
        private Integer h;
        private Integer pos;
        private List<Integer> btype;
        private List<Integer> battr;
        private List<Integer> api;
    }

    @Data
    public static class App {
        private String id;
        private String name;
        private List<String> cat;
        private String ver;
        private String bundle;
        private Publisher publisher;
        private String storeurl;
    }

    @Data
    public static class Publisher {
        private String id;
        private String name;
        private String domain;
    }

    @Data
    public static class Device {
        private Integer dnt;
        private String ua;
        private String ip;
        private Geo geo;
        private String dpidsha1;
        private String dpidmd5;
        private String carrier;
        private String language;
        private String make;
        private String model;
        private String os;
        private String osv;
        private Integer js;
        private Integer connectiontype;
        private Integer devicetype;
    }

    @Data
    public static class Geo {
        private String country;
        private Double lat;
        private Double lon;
        private String city;
        private String metro;
        private String region;
        private String zip;
    }

    @Data
    public static class User {
        private String id;
        private String yob;
        private String gender;
    }
}