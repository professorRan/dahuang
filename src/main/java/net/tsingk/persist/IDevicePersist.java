package net.tsingk.persist;

import net.tsingk.pojo.Device;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by delin on 2018/6/22.
 */
@Service
public interface IDevicePersist {

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "hwId", column = "HW_ID"),
            @Result(property = "masterHwId", column = "MASTER_HWID"),
            @Result(property = "wifiSid", column = "WIFI_SID"),
            @Result(property = "deviceType", column = "DEVICE_TYPE"),
            @Result(property = "status", column = "STATUS"),
            @Result(property = "name", column = "NAME"),
            @Result(property = "regTime", column = "REG_TIME"),
            @Result(property = "lastRptTime", column = "LAST_RPT_TIME"),
            @Result(property = "user", column = "USER_ID",
                    one=@One(select = "net.tsingk.persist.IUserPersist.selectUserById")),
    })
    @Select("<script> SELECT * FROM HYP_DEVICE <where> <if test='hwID!=null'>HW_ID LIKE concat('%',#{hwID},'%')</if></where> LIMIT #{startPos},#{pageSize}  </script>")
    List<Device> getDeviceList(@Param("startPos") long startPos, @Param("pageSize") int pageSize, @Param("hwID") String hwId);


    @Select("<script> SELECT COUNT(*) FROM HYP_DEVICE<where><if test='hwID!=null'>HW_ID LIKE concat('%',#{hwID},'%')</if></where></script>")
    long getDeviceCount(@Param("hwID") String hwId);

    @Select("SELECT COUNT(*) FROM HYP_DEVICE WHERE MASTER_HWID=#{masterHwId}")
    long getSlaveDevicesCount(@Param("masterHwId") String masterHwId);

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "hwId", column = "HW_ID"),
            @Result(property = "masterHwId", column = "MASTER_HWID"),
            @Result(property = "wifiSid", column = "WIFI_SID"),
            @Result(property = "deviceType", column = "DEVICE_TYPE"),
            @Result(property = "status", column = "STATUS"),
            @Result(property = "name", column = "NAME"),
            @Result(property = "regTime", column = "REG_TIME"),
            @Result(property = "lastRptTime", column = "LAST_RPT_TIME"),
            @Result(property = "user", column = "USER_ID",
                    one=@One(select = "net.tsingk.persist.IUserPersist.selectUserById")),
    })
    @Select("SELECT * FROM HYP_DEVICE WHERE HW_ID=#{hwID}")
    Device getDeviceByHwId(@Param("hwID") String hwId);

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "hwId", column = "HW_ID"),
            @Result(property = "masterHwId", column = "MASTER_HWID"),
            @Result(property = "wifiSid", column = "WIFI_SID"),
            @Result(property = "deviceType", column = "DEVICE_TYPE"),
            @Result(property = "status", column = "STATUS"),
            @Result(property = "name", column = "NAME"),
            @Result(property = "regTime", column = "REG_TIME"),
            @Result(property = "lastRptTime", column = "LAST_RPT_TIME"),
            @Result(property = "user", column = "USER_ID",
                    one=@One(select = "net.tsingk.persist.IUserPersist.selectUserById")),
    })
    @Select("SELECT * FROM HYP_DEVICE WHERE MASTER_HWID=#{masterHwId} LIMIT #{startPos},#{pageSize}")
    List<Device> getPageDeviceSlaves(@Param("startPos") long startPos, @Param("pageSize") int pageSize, @Param("masterHwId") String masterHwId);


    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "hwId", column = "HW_ID"),
            @Result(property = "masterHwId", column = "MASTER_HWID"),
            @Result(property = "wifiSid", column = "WIFI_SID"),
            @Result(property = "deviceType", column = "DEVICE_TYPE"),
            @Result(property = "status", column = "STATUS"),
            @Result(property = "name", column = "NAME"),
            @Result(property = "regTime", column = "REG_TIME"),
            @Result(property = "lastRptTime", column = "LAST_RPT_TIME"),
            @Result(property = "user", column = "USER_ID",
                    one=@One(select = "net.tsingk.persist.IUserPersist.selectUserById")),
    })
    @Select("SELECT * FROM HYP_DEVICE WHERE MASTER_HWID=#{masterHwId}")
    List<Device> getDeviceSlaves(String masterHwId);



    @Update("UPDATE HYP_DEVICE SET HW_ID=#{hwId},MASTER_HWID=#{masterHwId},WIFI_SID=#{wifiSid},DEVICE_TYPE=#{deviceType}," +
            "STATUS=#{status},NAME=#{name},REG_TIME=#{regTime},LAST_RPT_TIME=#{lastRptTime} WHERE id=#{id}")
    void updateDeviceInfo(Device device);

    @Update("UPDATE HYP_DEVICE SET MASTER_HWID=#{masterHwId},USER_ID=#{user.id} WHERE id=#{id}")
    void updateDeviceOwner(Device device);

    @Update("<script> UPDATE HYP_DEVICE SET USER_ID=#{userId} WHERE id in " +
            "<foreach item='item' index='index' collection='devices' open='(' separator=',' close=')'> #{item.id} </foreach> </script>")
    void batchupdateDeviceOwner(@Param("devices") List<Device> devices, @Param("userId") long userId);


    @Update("Update HYP_DEVICE SET USER_ID = NULL where HW_ID=#{deviceId}")
    void resetDeviceOwner(@Param("deviceId") String devId);


    @Update("Update HYP_DEVICE SET USER_ID = NULL where HW_ID=#{masterHwId} or MASTER_HWID=#{masterHwId}")
    void resetMasterDeviceOwner(@Param("masterHwId") String masterId);
}
