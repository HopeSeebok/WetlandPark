package com.yunwei.wetlandpark.greedao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "TRACK_POINTS_TABLE".
 */
public class TrackPointsTable implements java.io.Serializable {

    private Long id;
    private Double distance;
    private String cacheID;
    private String points;

    public TrackPointsTable() {
    }

    public TrackPointsTable(Long id) {
        this.id = id;
    }

    public TrackPointsTable(Long id, Double distance, String cacheID, String points) {
        this.id = id;
        this.distance = distance;
        this.cacheID = cacheID;
        this.points = points;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getCacheID() {
        return cacheID;
    }

    public void setCacheID(String cacheID) {
        this.cacheID = cacheID;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

}
