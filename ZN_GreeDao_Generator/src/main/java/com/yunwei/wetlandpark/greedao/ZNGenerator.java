package com.yunwei.wetlandpark.greedao;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

/**
 * @Package: com.yunwei.zaina.greedao
 * @Description:数据库结构创建
 * @author: Aaron
 * @date: 2016-05-31
 * @Time: 10:50
 * @version: V1.0
 */
public class ZNGenerator {
    /**
     * 默认输出的包名
     */
    private static final String defaultJavaPackage = "com.yunwei.wetlandpark.greedao";
    /**
     * 默认输入目录
     */
    private static final String defaultOutDir = "././app/src/main/java";

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(2, defaultJavaPackage);

//        addNote(schema);
//        addLogin(schema);
//
//        addWaterMeterPlan(schema);
//        addWaterMeter(schema);

//        addMapLayer(schema);

        addTrackTemporary(schema);
        addTrackPoint(schema);
        addTrackPointsTable(schema);
//
//        /* 设施信息表单*/
        addDevice(schema);
//
//        /* 巡查表*/
        addCheckPlanTable(schema);
//        /* 维护设施表*/
        addMaintainDeviceTable(schema);
//
//        //任务
        addTaskListAndDetailTable(schema);
        addTaskPointListTable(schema);
        addPlanToInspect(schema);
        /*Trouble表*/
        addTroubleTable(schema);
        addTroubleShooterTable(schema);
        /*签到表*/
        addSignInTable(schema);

        new DaoGenerator().generateAll(schema, defaultOutDir);
    }

    /**
     * 创建表
     *
     * @param schema
     */
    private static void addNote(Schema schema) {
        Entity note = schema.addEntity("Note");
        note.addIdProperty();
        note.addStringProperty("text").notNull();
        note.addStringProperty("comment");
        note.addDateProperty("date");
    }

    /**
     * 创建登录数据表
     *
     * @param schema
     */
    private static void addLogin(Schema schema) {
        Entity entity = schema.addEntity("login");
        entity.addIdProperty().autoincrement();
        entity.addIntProperty("userId");
        entity.addStringProperty("realName");
        entity.addStringProperty("userTel");
        entity.addStringProperty("unitName");
        entity.addStringProperty("unitAddr");
    }


    /**
     * 地图图层缓存
     *
     * @param schema
     * @desc 按图层等级分表[1-19]
     */
    private static void addMapLayer(Schema schema) {
        for (int i = 1; i <= 19; i++) {
            Entity entity = schema.addEntity("MapLayer" + i);
            entity.addIntProperty("layerType");
            entity.addIntProperty("level");
            entity.addIntProperty("col");
            entity.addIntProperty("row");
            entity.addByteArrayProperty("layer");
        }
    }

    /**
     * 足迹采集临时表
     *
     * @param schema
     */
    private static void addTrackTemporary(Schema schema) {
        Entity entity = schema.addEntity("Track");
        entity.implementsSerializable();
        entity.addIdProperty().primaryKey();
        entity.addLongProperty("tId");
        entity.addLongProperty("startTime");
        entity.addLongProperty("endTime");
        entity.addDoubleProperty("distance");
        entity.addIntProperty("state");
        entity.addStringProperty("remark");
        entity.addStringProperty("resume");
        entity.addStringProperty("pause");
        entity.addIntProperty("revint1");
        entity.addIntProperty("revint2");
        entity.addIntProperty("revint3");
        entity.addStringProperty("revstr1");
        entity.addStringProperty("revstr2");
        entity.addStringProperty("revstr3");
    }

    /**
     * 足迹pointsList缓存表
     *
     * @param schema
     */
    private static void addTrackPointsTable(Schema schema) {
        Entity entity = schema.addEntity("TrackPointsTable");
        entity.implementsSerializable();
        entity.addIdProperty().primaryKey();
        entity.addDoubleProperty("distance");
        entity.addStringProperty("cacheID");
        entity.addStringProperty("points");
    }


    /**
     * 足迹点记录
     *
     * @param schema
     */
    private static void addTrackPoint(Schema schema) {
        Entity entity = schema.addEntity("TrackPoint");
        entity.implementsSerializable();
        entity.addIdProperty().primaryKey();
        entity.addLongProperty("pId");
        entity.addLongProperty("time");
        entity.addDoubleProperty("lat");
        entity.addDoubleProperty("lng");
        entity.addDoubleProperty("x");//墨卡托X
        entity.addDoubleProperty("y");//墨卡托Y
        entity.addFloatProperty("speed");//两点间的速度
        entity.addFloatProperty("gpsSpeed");//GPS的速度
        entity.addDoubleProperty("distance");//两点间的距离
        entity.addStringProperty("provider");//定位方式
        entity.addStringProperty("locationMode");//定位方式
        entity.addStringProperty("address");//地址
        entity.addIntProperty("revint1");
        entity.addIntProperty("revint2");
        entity.addIntProperty("revint3");
        entity.addStringProperty("revstr1");
        entity.addStringProperty("revstr2");
        entity.addStringProperty("revstr3");
    }

    private static void addDevice(Schema schema) {
        Entity entity = schema.addEntity("DeviceTable");
        entity.implementsSerializable();
        entity.addIdProperty().primaryKey();
        /* 设施基本信息*/
        entity.addStringProperty("deviceID");
        entity.addStringProperty("RFID");
        entity.addStringProperty("deviceCode");
        entity.addStringProperty("deviceAddress");
        entity.addIntProperty("deviceTypeCode");
        entity.addStringProperty("deviceTypeName");
        entity.addDoubleProperty("lng");
        entity.addDoubleProperty("lat");
        entity.addLongProperty("createTime");//设施创建时间
        entity.addStringProperty("imagesUrl");/* 图片*/
        entity.addIntProperty("localTag");/* 本地标记 未提交 0 / 提交 1 */
        /* 用户信息*/
        entity.addStringProperty("userID");//上报人员的ID
        entity.addStringProperty("userName");//上报人员名
        entity.addIntProperty("unitID");//上报人员的单位 ID
        entity.addStringProperty("contact");/* 联系人*/
        entity.addStringProperty("phone"); /* 联系电话*/
        /*其它字段（请根据项目在下面添加）*/
        entity.addStringProperty("startTime");
        entity.addStringProperty("stopTime");
        entity.addStringProperty("useDepartment");
        entity.addStringProperty("ext");

    }

    private static void addCheckPlanTable(Schema schema) {
        Entity entity = schema.addEntity("CheckPlanTable");
        entity.implementsSerializable();
        entity.addIdProperty().primaryKey();
        /*与设施相关字段*/
        entity.addStringProperty("planID");/* 计划ID (移动端未保留唯一ID)*/
        entity.addStringProperty("date");/*填报日期*/
        entity.addStringProperty("startAt");
        entity.addStringProperty("endAt");
        entity.addStringProperty("firstSecondRoomTemp");
        entity.addStringProperty("firstSecondRoomHum");
        entity.addStringProperty("thirdRoomTemp");
        entity.addStringProperty("thirdRoomHum");
        entity.addStringProperty("firefightingSystem");
        entity.addStringProperty("usbRoom");
        entity.addStringProperty("airConditioner");
        entity.addStringProperty("compensationMeter");
        entity.addStringProperty("distributionRoom");
        entity.addStringProperty("coreRouter");
        entity.addStringProperty("accessSystem");
        entity.addStringProperty("note");/*备注*/
        entity.addStringProperty("checkedPointCount");/*巡检了多少个点*/
        entity.addStringProperty("checkedUser");/*巡检的用户*/
    }

    private static void addMaintainDeviceTable(Schema schema) {
        Entity entity = schema.addEntity("MaintainDeviceTable");
        entity.implementsSerializable();
        entity.addIdProperty().primaryKey(); /* Id主键*/
        /*与设施相关字段*/
        entity.addStringProperty("deviceID");/* 设施ID (移动端未保留唯一ID)*/
        entity.addStringProperty("deviceCode");/* 设置编号*/
        entity.addStringProperty("deviceAddress");/* 设置地址*/
        entity.addIntProperty("deviceTypeCode");
        entity.addStringProperty("deviceTypeName");
        /*与用户相关字段*/
        entity.addStringProperty("userName");/* 任务人*/
        entity.addStringProperty("userID");/* 任务人ID*/
        /*维护字段*/
        entity.addStringProperty("hdType");/* 隐患类型*/
        entity.addStringProperty("hdDesc");/* 隐患描述*/
        entity.addDoubleProperty("lng");
        entity.addDoubleProperty("lat");
        entity.addStringProperty("workAddress");/* 当前工作定位地址*/
        entity.addLongProperty("saveTime");/* 时间*/
        entity.addStringProperty("note");/* 备注*/
        entity.addStringProperty("reportType");/* 上报方式 RfID/none */
        entity.addStringProperty("imagesUrl");/* 图片*/
        entity.addIntProperty("localTag");/* 本地标记 未提交 0 / 提交 1 */
        entity.addStringProperty("missionID");/* 跟任务关联时的 任务ID*/
        entity.addStringProperty("missionPointID");/* 跟任务关联时的 任务点ID*/
        /*其它字段（请根据项目在下面添加）*/

    }


    private static void addCustomerOrder(Schema schema) {
        Entity customer = schema.addEntity("Customer");
        customer.addIdProperty();
        customer.addStringProperty("name").notNull();
        Entity order = schema.addEntity("Order");
        order.setTableName("ORDERS"); // "ORDER" is a reserved keyword
        order.addIdProperty();
        Property orderDate = order.addDateProperty("date").getProperty();
        Property customerId = order.addLongProperty("customerId").notNull().getProperty();
        order.addToOne(customer, customerId);

        ToMany customerToOrders = customer.addToMany(order, customerId);
        customerToOrders.setName("orders");
        customerToOrders.orderAsc(orderDate);
    }

    /**
     * 任务列表和详情
     * @param schema
     */
    private static void addTaskListAndDetailTable(Schema schema) {
        Entity entity = schema.addEntity("TaskListAndDetailEntity");
        entity.addIdProperty().primaryKey(); /* Id主键*/
        entity.addIntProperty("TaskId");
        entity.addIntProperty("TaskType");
        entity.addIntProperty("TaskFormType");
        entity.addIntProperty("TaskMold");
        entity.addIntProperty("TaskStatus");
        entity.addIntProperty("TaskLevel");
        entity.addIntProperty("Expires");
        entity.addStringProperty("UserId");
        entity.addStringProperty("TaskNote");
        entity.addStringProperty("OrderPeople");
        entity.addStringProperty("Excutor");
        entity.addStringProperty("TaskRemark");
        entity.addStringProperty("TaskDeviceNos");
//        entity.addStringProperty("OrderTime");
//        entity.addStringProperty("ReceiptTime");
        entity.addStringProperty("ProcessingTime");
        entity.addStringProperty("AuditTime");
        entity.addStringProperty("FinishTime");
        entity.addStringProperty("EndTime");
        entity.addBooleanProperty("IsHistory");
        entity.addStringProperty("HistoryTime");
        entity.addIntProperty("revint1");
        entity.addIntProperty("revint2");
        entity.addIntProperty("revint3");
        entity.addStringProperty("revstr1");
        entity.addStringProperty("revstr2");
        entity.addStringProperty("revstr3");
    }

    /**
     * 任务点列表
     * @param schema
     */
    private static void addTaskPointListTable(Schema schema) {
        Entity entity = schema.addEntity("TaskPointListEntity");
        entity.addIdProperty().primaryKey(); /* Id主键*/
        entity.addIntProperty("TaskId");
        entity.addStringProperty("TaskPointId");
        entity.addIntProperty("TaskPointKind");
        entity.addIntProperty("TaskPointStatus");
        entity.addStringProperty("UserId");
        entity.addStringProperty("TaskPointNo");
        entity.addStringProperty("TaskPointType");
        entity.addStringProperty("TaskPointAddr");
        entity.addDoubleProperty("Lng");
        entity.addDoubleProperty("Lat");
        entity.addStringProperty("Imgs");
        entity.addBooleanProperty("IsCompleted");
        entity.addIntProperty("revint1");
        entity.addIntProperty("revint2");
        entity.addIntProperty("revint3");
        entity.addStringProperty("revstr1");
        entity.addStringProperty("revstr2");
        entity.addStringProperty("revstr3");
    }

    /**
     * 计划
     * @param schema
     */
    private static void addPlanToInspect(Schema schema) {
        Entity entity = schema.addEntity("PlanToInspectEntity");
        entity.addIdProperty().primaryKey(); /* Id主键*/
        entity.addStringProperty("PlanId");
        entity.addStringProperty("PlanName");
        entity.addStringProperty("UserId");
        entity.addStringProperty("PlanRule");
        entity.addStringProperty("StartTime");
        entity.addStringProperty("EndTime");
        entity.addStringProperty("Executor");
        entity.addStringProperty("Note");
        entity.addStringProperty("Codes");
        entity.addStringProperty("Rfids");
        entity.addIntProperty("revint1");
        entity.addIntProperty("revint2");
        entity.addIntProperty("revint3");
        entity.addStringProperty("revstr1");
        entity.addStringProperty("revstr2");
        entity.addStringProperty("revstr3");
    }

    private static void addTroubleTable(Schema schema){
        Entity entity = schema.addEntity("TroubleTable");
        entity.implementsSerializable();
        entity.addIdProperty().primaryKey(); /* Id主键*/
        /*与用户相关字段*/
        entity.addStringProperty("userName");/* 任务人*/
        entity.addStringProperty("userID");/* 任务人ID*/
        /*Trouble 字段*/
        entity.addStringProperty("troubleType");/* 隐患类型*/
        entity.addStringProperty("troubleDesc");/* 隐患描述*/
        entity.addDoubleProperty("lng");
        entity.addDoubleProperty("lat");
        entity.addStringProperty("troubleAddress");/* 当前工作定位地址*/
        entity.addLongProperty("time");/* 时间*/
        entity.addStringProperty("note");/* 备注*/
        entity.addStringProperty("imagesUrl");/* 图片*/
        entity.addIntProperty("localTag");/* 本地标记 未提交 0 / 提交 1 */
        entity.addIntProperty("emergencyLevel");/*紧急程度*/
        entity.addStringProperty("missionID");/* 跟任务关联时的 任务ID*/
        entity.addStringProperty("missionPointID");/* 跟任务关联时的 任务点ID*/
        /*其它字段（请根据项目在下面添加）*/
    }

    private static void addTroubleShooterTable(Schema schema){
        Entity entity = schema.addEntity("TroubleShooterTable");
        entity.implementsSerializable();
        entity.addIdProperty().primaryKey(); /* Id主键*/
        /*与用户相关字段*/
        entity.addStringProperty("userName");/* 任务人*/
        entity.addStringProperty("userID");/* 任务人ID*/
        /*TroubleShooter 字段*/
        entity.addStringProperty("troubleID");
        entity.addStringProperty("missionDetailID");
        entity.addStringProperty("missionID");
        entity.addDoubleProperty("lng");
        entity.addDoubleProperty("lat");
        entity.addLongProperty("time");/* 时间*/
        entity.addStringProperty("note");/* 备注*/
        entity.addStringProperty("imagesUrl");/* 图片*/
        entity.addIntProperty("localTag");/* 本地标记 未提交 0 / 提交 1 */
        /*其它字段（请根据项目在下面添加）*/
    }

    private static void addSignInTable(Schema schema){
        Entity entity = schema.addEntity("SignInTable");
        entity.implementsSerializable();
        entity.addIdProperty().primaryKey(); /* Id主键*/
        /*与用户相关字段*/
        entity.addStringProperty("userName");/* 任务人*/
        entity.addStringProperty("userID");/* 任务人ID*/
        /*SignInTable 字段*/
        entity.addStringProperty("code");
        entity.addStringProperty("address");
        entity.addDoubleProperty("lng");
        entity.addDoubleProperty("lat");
        entity.addLongProperty("time");/* 时间*/
        entity.addStringProperty("note");/*签到详情*/
        /*其它字段（请根据项目在下面添加）*/
        entity.addStringProperty("test");
    }
}
