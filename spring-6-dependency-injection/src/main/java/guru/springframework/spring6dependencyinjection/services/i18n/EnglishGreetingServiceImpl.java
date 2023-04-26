package guru.springframework.spring6dependencyinjection.services.i18n;

import guru.springframework.spring6dependencyinjection.services.IGreetingService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile({"EN", "default"})
@Service("i18nService")
public class EnglishGreetingServiceImpl implements IGreetingService {

    /**
     * @return
     */
    @Override
    public String sayGreeting() {
        return "Hello World - EN";
    }
}
