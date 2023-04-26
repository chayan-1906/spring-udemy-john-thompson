package guru.springframework.spring6dependencyinjection.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile({"dev", "default"})
@Service
public class EnvironmentServiceDev implements IEnvironmentService {

    /**
     * @return
     */
    @Override
    public String getEnv() {
        return "dev";
    }
}
