package global;

import play.ApplicationLoader;
import play.Configuration;
import play.inject.guice.GuiceApplicationBuilder;
import play.inject.guice.GuiceApplicationLoader;

public class CustomApplicationLoader extends GuiceApplicationLoader {
	
	@Override
	public GuiceApplicationBuilder builder(final ApplicationLoader.Context context) {
		Configuration extra = new Configuration("a = 1");
		return this.initialBuilder
			.in(context.environment())
			.loadConfig(extra.withFallback(context.initialConfiguration()))
			.overrides(overrides(context));
	}
	
}