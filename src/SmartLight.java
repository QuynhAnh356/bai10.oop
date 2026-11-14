/****************/
// Ma sinh vien: 202418841
// Ho ten: Duong Thi Quynh Anh
/****************/
public class SmartLight implements IDevice {
    private String name;

    public SmartLight(String name) {
        this.name = name;
    }

    @Override
    public void turnOn() {
        System.out.println(name + ": ON");
    }

    @Override
    public void turnOff() {
        System.out.println(name + ": OFF");
    }
}