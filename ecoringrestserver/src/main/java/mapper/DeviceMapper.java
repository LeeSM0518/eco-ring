package mapper;

import dto.Device;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface DeviceMapper {

  @Select("select * from device")
  List<Device> selectList();

  @Select("select * from device where id=#{deviceId}")
  Device selectOneByDeviceId(@Param("deviceId") int id);

  @Select("select * from device where address=#{address}")
  Device selectOneByAddress(@Param("address") String address);

  @Insert("insert into device (latitude, longitude, address, createdDate) " +
      "values (#{latitude}, #{longitude}, #{address}, now())")
  void insert(@Param("latitude") double latitude,
              @Param("longitude") double longitude,
              @Param("address") String address);

  @Update("update device set latitude=#{latitude}, longitude=#{longitude}, address=#{address} " +
      "where id=#{deviceId}")
  void update(@Param("latitude") double latitude, @Param("longitude") double longitude,
              @Param("address") String address, @Param("deviceId") int id);

  @Delete("delete from device where id=#{deviceId}")
  void delete(@Param("deviceId") int id);

}
