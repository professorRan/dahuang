
CREATE TABLE HYP_ADMIN_USER (
    ID               BIGINT      auto_increment NOT NULL,
    USER_NAME        VARCHAR(400),
    USER_PWD         VARCHAR(100),
    NICK_NAME        VARCHAR(40),
    primary key(ID)
);



CREATE TABLE HYP_USER (
    ID               BIGINT      auto_increment NOT NULL,
    NAME             VARCHAR(400),
    PHONE_NUMBER     VARCHAR(100),
    USER_PASSWORD    VARCHAR(100),
    OUTER_ID         VARCHAR(50),
    IM_USER_ID       VARCHAR(400),
    IM_USER_OUT_ID   VARCHAR(400),
    IM_USER_TOKEN    VARCHAR(400),
    OPEN_ID          VARCHAR(200),
    GENDER           smallint(1),
    POINTS           smallint(8),
    AVATAR1          VARCHAR(300),
    AVATAR2          VARCHAR(300),
    AVATAR3          VARCHAR(300),
    primary key(ID)
);




CREATE TABLE HYP_DEVICE (
    ID              BIGINT      auto_increment NOT NULL,
    HW_ID           VARCHAR(200),
    MASTER_HWID     VARCHAR(200),
    WIFI_SID        VARCHAR(200),
    USER_ID         VARCHAR(200),
    DEVICE_TYPE     SMALLINT(2),
    STATUS          SMALLINT(2),
    NAME            VARCHAR(200),
    REG_TIME        DATETIME,
    LAST_RPT_TIME   DATETIME,
    primary key(ID)
);


CREATE TABLE HYP_DEVICE_CONN_RECORD (
    ID              BIGINT      auto_increment NOT NULL,
    DEVICE_HWID     VARCHAR(200),
    CONN_FLAG       SMALLINT(1),
    CONN_DATETIME   DATETIME,
    primary key(ID)
);


CREATE TABLE HYP_PLANTS (
    ID                BIGINT      auto_increment NOT NULL,
    PLANT_NAME         VARCHAR(200),
    PLANT_NAME1        VARCHAR(200),
    PLANT_SUMMARY      VARCHAR(800),
    PLANT_TEMPERTURE   VARCHAR(1000),
    PLANT_DESC         VARCHAR(2000),
    PLANT_PLACE        VARCHAR(2000),
    PLANTING           VARCHAR(2000),
    LIKE_WORD          VARCHAR(2000),
    WORD               VARCHAR(100),
    PIC1               VARCHAR(200),
    PIC2               VARCHAR(200),
    PIC3               VARCHAR(200),
    PIC4               VARCHAR(200),
    PIC5               VARCHAR(200),
    T_RANGE_1          NUMERIC(9,2),
    T_RANGE_2          NUMERIC(9,2),
    H_RANGE_1          NUMERIC(9,2),
    H_RANGE_2          NUMERIC(9,2),
    O_RANGE_1          NUMERIC(9,2),
    O_RANGE_2          NUMERIC(9,2),
    S_RANGE_1          NUMERIC(9,2),
    S_RANGE_2          NUMERIC(9,2),
    primary key(ID)
);
CREATE TABLE HYP_ADS_IMAGES (
  id                    int(10)    auto_increment NOT NULL,
  CONTEXT_PATH          varchar(1000) ,
  IMAGE_WIDTH           varchar(100) ,
  IMAGE_HEIGHT          varchar(100) ,
  VIEW_COUNT            varchar(100) ,
  VIEW_LINK             varchar(1000) ,
  ADS_TYPE              int(10) ,
  ADS_LINK              varchar(1000) ,
  PRIMARY KEY (id)
) ;



