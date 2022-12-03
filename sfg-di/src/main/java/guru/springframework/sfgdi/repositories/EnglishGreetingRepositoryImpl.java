package guru.springframework.sfgdi.repositories;

public class EnglishGreetingRepositoryImpl implements EnglishGreetingRepository {

	/**
	 * @return
	 */
	@Override
	public String getGreeting() {
		return "Hello World - EN";
	}
}
