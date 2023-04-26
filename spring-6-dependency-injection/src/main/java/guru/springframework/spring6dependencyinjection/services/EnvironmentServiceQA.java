package guru.springframework.spring6dependencyinjection.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("qa")
@Service
public class EnvironmentServiceQA implements IEnvironmentService {

    /**
     * @return
     */
    @Override
    public String getEnv() {
        return "qa";
    }
}
