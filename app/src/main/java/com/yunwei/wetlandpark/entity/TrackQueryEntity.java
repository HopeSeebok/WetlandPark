package com.yunwei.wetlandpark.entity;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.water.entity
 * @Description:
 * @date 2016/9/18 17:01
 */
public class TrackQueryEntity {

    private int PointID;

    private double X;

    private double Y;

    private double XS;

    private double YS;

    private double XM;

    private double YM;

    private double Z;

    private String CreateTime;

    private String CreateTimeSTR;

    private String PointState;

    private int FootID;

    public void setPointID(int PointID){
        this.PointID = PointID;
    }
    public int getPointID(){
        return this.PointID;
    }
    public void setX(double X){
        this.X = X;
    }
    public double getX(){
        return this.X;
    }
    public void setY(double Y){
        this.Y = Y;
    }
    public double getY(){
        return this.Y;
    }
    public void setXS(double XS){
        this.XS = XS;
    }
    public double getXS(){
        return this.XS;
    }
    public void setYS(double YS){
        this.YS = YS;
    }
    public double getYS(){
        return this.YS;
    }
    public void setXM(double XM){
        this.XM = XM;
    }
    public double getXM(){
        return this.XM;
    }
    public void setYM(double YM){
        this.YM = YM;
    }
    public double getYM(){
        return this.YM;
    }
    public void setZ(double Z){
        this.Z = Z;
    }
    public double getZ(){
        return this.Z;
    }
    public void setCreateTime(String CreateTime){
        this.CreateTime = CreateTime;
    }
    public String getCreateTime(){
        return this.CreateTime;
    }
    public void setCreateTimeSTR(String CreateTimeSTR){
        this.CreateTimeSTR = CreateTimeSTR;
    }
    public String getCreateTimeSTR(){
        return this.CreateTimeSTR;
    }
    public void setPointState(String PointState){
        this.PointState = PointState;
    }
    public String getPointState(){
        return this.PointState;
    }
    public void setFootID(int FootID){
        this.FootID = FootID;
    }
    public int getFootID(){
        return this.FootID;
    }

}
