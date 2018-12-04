package net.tsingk.persist;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * Created by delin on 2018/6/29.
 */
public class DeviceProvider {
    private final String TABLE = "HYP_DEVICE";

    public String  deviceCount(final String hwID){
        return new SQL() {
            {
                SELECT("COUNT(*)").FROM(TABLE);
                if (hwID == null) {

                } else {
                    WHERE("HW_ID LIKE concat('%',#{hwID},'%')");
                }
            }
        }.toString();
    }
}
