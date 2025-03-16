package config;

import org.aeonbits.owner.Config;


@Config.Sources({"classpath:${deviceHost}.properties"})


public interface BrowserstackConfig extends Config {
    @Key("username")
    String userName();

    @Key("password")
    String passUser();

    @Key("app")
    String app();

    @Key("device")
    String device();


    @Key("name")
    String name();

    @Key("browserstackUrl")
    String browserstackUrl();

    String buildName();

    String osVersion();

    String projectName();
}
