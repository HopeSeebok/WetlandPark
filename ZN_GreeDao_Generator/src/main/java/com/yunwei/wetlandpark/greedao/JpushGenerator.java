package com.yunwei.wetlandpark.greedao;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * @Package: com.yunwei.zaina.greedao
 * @Description:数据库结构创建
 * @author: Aaron
 * @date: 2016-05-31
 * @Time: 10:50
 * @version: V1.0
 */
public class JpushGenerator {
    /**
     * 默认输出的包名
     */
    private static final String defaultJavaPackage = "com.yunwei.cmcc.jpush.greendaonew";
    /**
     * 默认输入目录
     */
    private static final String defaultOutDir = "././jpush_SDK/src/";

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, defaultJavaPackage);

//        addTaskEntity(schema);
//        addAuditEntity(schema);
//        addAuditorEntity(schema);
//        addExecutorEntity(schema);
//        addImagesEntity(schema);
        addItemsEntity(schema);
//        addWorkTaskPointsEntity(schema);
//        addOwerEntity(schema);
//        addLocationEntity(schema);
        new DaoGenerator().generateAll(schema, defaultOutDir);
    }


    private static void addTaskEntity(Schema schema) {
        Entity entity = schema.addEntity("TaskEntity");
        entity.addIdProperty().primaryKey();
        entity.addStringProperty("UserId");
        entity.addStringProperty("TaskId");
        entity.addStringProperty("TaskType");
        entity.addStringProperty("TaskDesc");
        entity.addStringProperty("Remark");
        entity.addStringProperty("OrderTime");
        entity.addStringProperty("TaskUrgent");
        entity.addStringProperty("TaskFrom");
        entity.addStringProperty("TaskStatus");
        entity.addIntProperty("IsHistory");
        entity.addStringProperty("HistoryTime");
        entity.addStringProperty("none1");
        entity.addStringProperty("none2");
    }

    private static void addItemsEntity(Schema schema) {
        Entity entity = schema.addEntity("ItemsEntity");
        entity.addIdProperty().primaryKey();
        entity.addStringProperty("UserId");
        entity.addStringProperty("TaskId");
        entity.addStringProperty("No");
        entity.addStringProperty("TaskDesc");
        entity.addStringProperty("Remark");
        entity.addStringProperty("BackReason");
        entity.addStringProperty("TaskUrgent");
        entity.addStringProperty("TaskType");
        entity.addStringProperty("WorkTaskType");
        entity.addStringProperty("OrderTime");
        entity.addStringProperty("ReceiveTime");
        entity.addStringProperty("BackTime");
        entity.addStringProperty("RecycleTime");
        entity.addStringProperty("DealTime");
        entity.addStringProperty("FinishTime");
        entity.addIntProperty("Expries");
        entity.addIntProperty("IsHistory");
        entity.addStringProperty("HistoryTime");
        entity.addIntProperty("TaskStatus");
        entity.addStringProperty("User");
        entity.addStringProperty("Handler");
    }

    private static void addOwerEntity(Schema schema) {
        Entity entity = schema.addEntity("OwerEntity");
        entity.addIdProperty().primaryKey();
        entity.addStringProperty("UserId");
        entity.addStringProperty("TaskId");
        entity.addStringProperty("Id");
        entity.addStringProperty("Name");
        entity.addStringProperty("none1");
        entity.addStringProperty("none2");
    }

    private static void addExecutorEntity(Schema schema) {
        Entity entity = schema.addEntity("ExecutorEntity");
        entity.addIdProperty().primaryKey();
        entity.addStringProperty("UserId");
        entity.addStringProperty("TaskId");
        entity.addStringProperty("Id");
        entity.addStringProperty("Name");
        entity.addStringProperty("none1");
        entity.addStringProperty("none2");
    }

    private static void addAuditEntity(Schema schema) {
        Entity entity = schema.addEntity("AuditEntity");
        entity.addIdProperty().primaryKey();
        entity.addStringProperty("UserId");
        entity.addStringProperty("TaskId");
        entity.addBooleanProperty("IsAudit");
        entity.addStringProperty("AuditTime");
        entity.addStringProperty("none1");
        entity.addStringProperty("none2");
    }

    private static void addAuditorEntity(Schema schema) {
        Entity entity = schema.addEntity("AuditorEntity");
        entity.addIdProperty().primaryKey();
        entity.addStringProperty("UserId");
        entity.addStringProperty("TaskId");
        entity.addStringProperty("Id");
        entity.addStringProperty("Name");
        entity.addStringProperty("none1");
        entity.addStringProperty("none2");
    }

    private static void addWorkTaskPointsEntity(Schema schema) {
        Entity entity = schema.addEntity("WorkTaskPointsEntity");
        entity.addIdProperty().primaryKey();
        entity.addStringProperty("UserId");
        entity.addStringProperty("TaskId");
        entity.addStringProperty("TaskPointId");
        entity.addStringProperty("DeviceType");
        entity.addStringProperty("DeviceCode");
        entity.addStringProperty("WorkTaskPointId");
        entity.addStringProperty("No");
        entity.addStringProperty("RFID");
        entity.addStringProperty("XhsDeviceType");
        entity.addStringProperty("State");
        entity.addStringProperty("hdtype");
        entity.addStringProperty("hddesc");
        entity.addStringProperty("IsComplete");
        entity.addStringProperty("IsGiveback");
        entity.addStringProperty("Addr");
    }

    private static void addLocationEntity(Schema schema) {
        Entity entity = schema.addEntity("LocationEntity");
        entity.addIdProperty().primaryKey();
        entity.addStringProperty("UserId");
        entity.addStringProperty("TaskId");
        entity.addDoubleProperty("Lng");
        entity.addDoubleProperty("Lat");
        entity.addStringProperty("Name");
        entity.addStringProperty("UpdateTime");
        entity.addStringProperty("none1");
        entity.addStringProperty("none2");
    }

//    private static void addImagesEntity(Schema schema) {
//        Entity entity = schema.addEntity("ImagesEntity");
//        entity.addIdProperty().primaryKey();
//        entity.addStringProperty("UserId");
//        entity.addStringProperty("TaskId");
//        entity.addIntProperty("Index");
//        entity.addStringProperty("Uri");
//        entity.addStringProperty("Note");
//        entity.addStringProperty("none1");
//        entity.addStringProperty("none2");
//    }


    private static void addImagesEntity(Schema schema) {
        Entity entity = schema.addEntity("ImagesEntity");
        entity.addIdProperty().primaryKey();
        entity.addStringProperty("UserId");
        entity.addStringProperty("TaskId");
        entity.addIntProperty("Index");
        entity.addStringProperty("Uri");
        entity.addStringProperty("Note");
        entity.addStringProperty("none1");
        entity.addStringProperty("none2");
    }



}
