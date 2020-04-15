package command;

public class CreateDeviceResponse {

  private int deviceId;

  public CreateDeviceResponse() {}

  public CreateDeviceResponse(int deviceId) {
    this.deviceId = deviceId;
  }

  public int getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(int deviceId) {
    this.deviceId = deviceId;
  }

}