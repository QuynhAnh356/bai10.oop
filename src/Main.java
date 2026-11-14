public class Main {
    public static void main(String[] args) {
        System.out.println("T1: Kiem tra Factory tao light, fan, door");
        IDevice light = DeviceFactory.create("light", "Living Room Light");
        IDevice fan = DeviceFactory.create("fan", "Living Room Fan");
        IDevice door = DeviceFactory.create("door", "Main Door");
        System.out.println("light is SmartLight: " + (light instanceof SmartLight));
        System.out.println("fan is SmartFan: " + (fan instanceof SmartFan));
        System.out.println("door is SmartDoor: " + (door instanceof SmartDoor));

        System.out.println("\nT2: Kiem tra tao loai camera chua ho tro");
        try {
            DeviceFactory.create("camera", "Security Camera");
            System.out.println("Ket qua: That bai (khong co exception)");
        } catch (IllegalArgumentException e) {
            System.out.println("Ket qua thanh cong bat ngoai le: " + e.getMessage());
        }
git a
        System.out.println("\nT3: Kiem tra Singleton");
        HomeConfiguration c1 = HomeConfiguration.getInstance();
        HomeConfiguration c2 = HomeConfiguration.getInstance();
        System.out.println("c1 == c2: " + (c1 == c2));

        System.out.println("\nT4: Kiem tra goi goToWork()");
        SmartHomeFacade facade = new SmartHomeFacade(light, fan, (ILockable) door);
        facade.goToWork();

        System.out.println("\nT5: Kiem tra goi goToSleep()");
        facade.goToSleep();
    }
}