package guru.springframework.spring6dependencyinjection.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("uat")
@Service
public class EnvironmentServiceUat implements IEnvironmentService {

    /**
     * @return
     */
    @Override
    public String getEnv() {
        return "uat";
    }
}
