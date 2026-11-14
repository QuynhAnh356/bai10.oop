public class DeviceFactory {
    public static IDevice create(String type, String name) {
        if (type == null) {
            throw new IllegalArgumentException("Device type cannot be null");
        }

        String normalizedType = type.trim().toLowerCase();
        switch (normalizedType) {
            case "light":
                return new SmartLight(name);
            case "fan":
                return new SmartFan(name);
            case "door":
                return new SmartDoor(name);
            default:
                throw new IllegalArgumentException("Unsupported device type: " + type);
        }
    }
}