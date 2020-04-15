package service;


import dto.Device;
import error.DeviceNotFoundException;
import error.DuplicateAddressException;
import mapper.DeviceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class DeviceService {

  @Autowired
  private DeviceMapper deviceMapper;

  public List<Device> getList() {
    return deviceMapper.selectList();
  }

  public Device getById(int id) {
    return checkDeviceIsNull(deviceMapper.selectOneByDeviceId(id));
  }

  @Transactional
  public int create(double lat, double lon, String address) {
    Device device = deviceMapper.selectOneByAddress(address);
    if(device != null)
      throw new DuplicateAddressException();
    deviceMapper.insert(lat, lon, address);
    return deviceMapper.selectOneByAddress(address).getId();
  }

  @Transactional
  public void update(double lat, double lon, String addr, int id) {
    checkDeviceIsNull(deviceMapper.selectOneByDeviceId(id));
    deviceMapper.update(lat, lon, addr, id);
  }

  @Transactional
  public void delete(int id) {
    checkDeviceIsNull(deviceMapper.selectOneByDeviceId(id));
    deviceMapper.delete(id);
  }

  private Device checkDeviceIsNull(Device device) {
    if (device == null)
      throw new DeviceNotFoundException();
    return device;
  }

}
