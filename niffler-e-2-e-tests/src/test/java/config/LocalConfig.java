package config;

public class LocalConfig implements Config{
    @Override
    public String getDbHost() {
        return "localhost";
    }

    @Override
    public int getDbPort() {
        return 5432;
    }

    @Override
    public String getDBLogin() {
        return "postgres";
    }

    @Override
    public String getDBPassword() {
        return "secret";
    }
}
