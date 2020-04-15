package controller;

import command.CreateDeviceRequest;
import command.CreateDeviceResponse;
import command.UpdateDeviceRequest;
import dto.Device;
import error.DeviceNotFoundException;
import error.DuplicateAddressException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.DeviceService;

import java.util.List;

@RestController
public class DeviceController {

  @Autowired
  private DeviceService service;

  @GetMapping("/devices")
  public List<Device> list() {
    return service.getList();
  }

  @GetMapping("/devices/{id}")
  public Device device(@PathVariable int id) {
    try {
      return service.getById(id);
    } catch (DeviceNotFoundException e) {
      throw new DeviceNotFoundException();
    }
  }

  @PutMapping("/devices/{id}")
  public void update(@PathVariable int id, @RequestBody UpdateDeviceRequest request) {
    try {
      service.update(
          request.getLatitude(),
          request.getLongitude(),
          request.getAddress(),
          request.getId());
    } catch (DeviceNotFoundException e) {
      throw new DeviceNotFoundException();
    }
  }

  @DeleteMapping("/devices/{id}")
  public void delete(@PathVariable int id) {
    try {
      service.delete(id);
    } catch (DeviceNotFoundException e) {
      throw new DeviceNotFoundException();
    }
  }

  @PostMapping("/devices")
  public CreateDeviceResponse create(@RequestBody CreateDeviceRequest request) {
    try {
      return new CreateDeviceResponse(
          service.create(
              request.getLatitude(),
              request.getLongitude(),
              request.getAddress()));
    } catch (DuplicateAddressException e) {
      throw new DuplicateAddressException();
    }
  }

}