package guru.qa.niffler.config;

public interface Config {
    static Config getConfig() {
//  if(System.getProperty("env").equals("docker")) - плохая запись, так как System.getProperty("env") может быть null
        if ("docker".equals(System.getProperty("test.env"))) {
            return DockerConfig.INSTANCE;
        } else if ("local".equals(System.getProperty("test.env"))) {
            return LocalConfig.INSTANCE;
        } else throw new IllegalStateException("Can't reed test.env System.getProperty()");
    }

    String getDbHost();

    int getDbPort();

    String getDBLogin();

    String getDBPassword();

    String getSpendUrl();

    String getFrontUrl();

    String getAuthUrl();

    String getUserDataUrl();

    String getCurrencyGrpcAddress();

    int getCurrencyGrpcPort();
}
