package controller;

import command.CreateDustRequest;
import dto.DeviceAndDust;
import dto.Dust;
import error.DeviceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.DustService;

import java.util.List;

@RestController
public class DustController {

  @Autowired
  private DustService service;

  @GetMapping("/devices/dust")
  public List<DeviceAndDust> list() {
    return service.getList();
  }

  @PostMapping("/devices/dust")
  public void create(@RequestBody CreateDustRequest request) {
    try {
      service.create(request.getDensity(), request.getDeviceId());
    } catch (DeviceNotFoundException e) {
      throw new DeviceNotFoundException();
    }
  }

}
