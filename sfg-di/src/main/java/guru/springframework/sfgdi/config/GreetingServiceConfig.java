package guru.springframework.sfgdi.config;

import guru.springframework.pets.PetService;
import guru.springframework.pets.PetServiceFactory;
import guru.springframework.sfgdi.datasource.FakeDataSource;
import guru.springframework.sfgdi.repositories.EnglishGreetingRepository;
import guru.springframework.sfgdi.repositories.EnglishGreetingRepositoryImpl;
import guru.springframework.sfgdi.services.*;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;

@EnableConfigurationProperties(SfgConstructorConfig.class)
@ImportResource("classpath:sfgdi-config.xml")
@Configuration
public class GreetingServiceConfig {

	@Bean
	FakeDataSource fakeDataSource(SfgConstructorConfig sfgConstructorConfig) {
		FakeDataSource fakeDataSource = new FakeDataSource ( );
		fakeDataSource.setUsername ( sfgConstructorConfig.getUsername ( ) );
		fakeDataSource.setPassword ( sfgConstructorConfig.getPassword ( ) );
		fakeDataSource.setJdbcUrl ( sfgConstructorConfig.getJdbcUrl ( ) );
		return fakeDataSource;
	}

	PetServiceFactory getServiceFactory() {
		return new PetServiceFactory ( );
	}

	@Profile({"dog", "default"})
	@Bean
	PetService dogPetService(PetServiceFactory petServiceFactory) {
		return petServiceFactory.getPetService ( "dog" );
	}

	@Profile("cat")
	@Bean
	PetService catPetService(PetServiceFactory petServiceFactory) {
		return petServiceFactory.getPetService ( "cat" );
	}

	@Profile({"ES", "default"})
	@Bean("i18nService")
	I18nSpanishService i18nSpanishService() {
		return new I18nSpanishService ( );
	}

	@Bean
	EnglishGreetingRepository englishGreetingRepository() {
		return new EnglishGreetingRepositoryImpl ( );
	}

	@Profile("EN")
	@Bean
	I18nEnglishGreetingService i18nService(EnglishGreetingRepository englishGreetingRepository) {
		return new I18nEnglishGreetingService ( englishGreetingRepository );
	}

	@Primary
	@Bean
	PrimaryGreetingService primaryGreetingService() {
		return new PrimaryGreetingService ( );
	}

	//	@Bean
	ConstructorGreetingService constructorGreetingService() {
		return new ConstructorGreetingService ( );
	}

	@Bean
	PropertyInjectedGreetingService propertyInjectedGreetingService() {
		return new PropertyInjectedGreetingService ( );
	}

	@Bean
	SetterInjectedGreetingService setterInjectedGreetingService() {
		return new SetterInjectedGreetingService ( );
	}
}
