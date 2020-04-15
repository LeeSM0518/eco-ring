package mapper;

import dto.DeviceAndDust;
import dto.Dust;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DustMapper {

  @Select("select de.id, de.latitude, de.longitude, de.address, du.density, du.measuredDate, du.severity\n" +
      "from device de, dust du " +
      "where de.id = du.measuredDeviceId and " +
      "du.measuredDate in ( " +
      "  select max(measuredDate)" +
      "  from dust" +
      "  group by measureddeviceid" +
      ")")
  public List<DeviceAndDust> selectList();

  @Insert("insert into dust (density, measuredDate, severity, measuredDeviceId) " +
      "values (#{density}, now(), #{severity}, #{measuredDeviceId})")
  public void create(@Param("density") int density, @Param("severity") String severity,
                     @Param("measuredDeviceId") int deviceId);

}
