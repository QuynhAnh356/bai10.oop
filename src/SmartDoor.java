public class SmartDoor implements IDevice, ILockable {
    private String name;
    private boolean isLocked;

    public SmartDoor(String name) {
        this.name = name;
        this.isLocked = false;
    }

    @Override
    public void turnOn() {
        System.out.println(name + ": ON");
    }

    @Override
    public void turnOff() {
        System.out.println(name + ": OFF");
    }

    @Override
    public void lock() {
        this.isLocked = true;
        System.out.println(name + ": LOCKED");
    }

    @Override
    public void unlock() {
        this.isLocked = false;
        System.out.println(name + ": UNLOCKED");
    }
}