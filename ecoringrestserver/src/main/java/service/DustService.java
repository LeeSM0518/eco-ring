package service;

import dto.Device;
import dto.DeviceAndDust;
import dto.Dust;
import error.DeviceNotFoundException;
import mapper.DeviceMapper;
import mapper.DustMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class DustService {

  @Autowired
  private DustMapper dustMapper;
  @Autowired
  private DeviceMapper deviceMapper;

  private static final String VERY_BAD = "매우 나쁨";
  private static final String BAD = "나쁨";
  private static final String USUALLY = "보통";
  private static final String GOOD = "좋음";


  public List<DeviceAndDust> getList() {
    return dustMapper.selectList();
  }

  @Transactional
  public void create(int density, int deviceId) {
    Device device = deviceMapper.selectOneByDeviceId(deviceId);
    if (device == null)
      throw new DeviceNotFoundException();
    String severity;
    if (density > 150)
      severity = VERY_BAD;
    else if (density > 80)
      severity = BAD;
    else if (density > 30)
      severity = USUALLY;
    else
      severity = GOOD;
    dustMapper.create(density, severity, deviceId);
  }

}
