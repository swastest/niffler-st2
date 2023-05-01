package config;

public class DockerConfig implements Config {
    @Override
    public String getDbHost() {
        return "niffler-all-db";
    }

    @Override
    public int getDbPort() {
        return 5432;
    }

    @Override
    public String getDBLogin() {
        return null;
    }

    @Override
    public String getDBPassword() {
        return null;
    }
}
