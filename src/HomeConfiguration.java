/****************/
// Ma sinh vien: 202418841
// Ho ten: Duong Thi Quynh Anh
/****************/
public final class HomeConfiguration {
    private static volatile HomeConfiguration instance;

    private String homeName = "My Home";
    private int nightLightLevel = 30;

    private HomeConfiguration() {
    }

    public static HomeConfiguration getInstance() {
        if (instance == null) {
            synchronized (HomeConfiguration.class) {
                if (instance == null) {
                    instance = new HomeConfiguration();
                }
            }
        }
        return instance;
    }

    public String getHomeName() {
        return homeName;
    }

    public void setHomeName(String homeName) {
        this.homeName = homeName;
    }

    public int getNightLightLevel() {
        return nightLightLevel;
    }

    public void setNightLightLevel(int nightLightLevel) {
        this.nightLightLevel = nightLightLevel;
    }
}