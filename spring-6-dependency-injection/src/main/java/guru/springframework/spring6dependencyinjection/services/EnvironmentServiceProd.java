package guru.springframework.spring6dependencyinjection.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("prod")
@Service
public class EnvironmentServiceProd implements IEnvironmentService {

    /**
     * @return
     */
    @Override
    public String getEnv() {
        return "prod";
    }
}
