package guru.springframework.spring6dependencyinjection.services.i18n;

import guru.springframework.spring6dependencyinjection.services.IGreetingService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("ES")
@Service("i18nService")
public class SpanishGreetingServiceImpl implements IGreetingService {

    /**
     * @return
     */
    @Override
    public String sayGreeting() {
        return "Hola Mundo - ES";
    }
}
