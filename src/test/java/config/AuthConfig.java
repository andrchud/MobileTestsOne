package config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:auth.properties"})
public interface AuthConfig extends Config {

    @Key("username")
    String userName();

    @Key("password")
    String passUser();

}
