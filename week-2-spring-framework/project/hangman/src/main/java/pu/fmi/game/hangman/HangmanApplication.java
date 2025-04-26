package pu.fmi.game.hangman;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class HangmanApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext applicationContext
				= SpringApplication.run(HangmanApplication.class, args);

		// whatIsBean(applicationContext);

		HangmanGameService hangmanGameService = applicationContext.getBean(HangmanGameService.class);
		HangmanGame hangmanGame = hangmanGameService.startNewGame();
		System.out.println(hangmanGame);

		HangmanGameService hangmanGameServiceTwo = applicationContext.getBean(HangmanGameService.class);
		System.out.println(hangmanGameServiceTwo);
	}

	private static void whatIsBean(ConfigurableApplicationContext applicationContext){

		String thisIsMyFirstBean = applicationContext.getBean(String.class);
		System.out.println("Get by class name: " + thisIsMyFirstBean);

		String myFirstBeanGetByItsName = (String) applicationContext.getBean("myFirstBean");
		System.out.println("Get by bean name: " + myFirstBeanGetByItsName);

		// Дава ни всички bean-ове от Spring
		String[] allSpringBeans = applicationContext.getBeanDefinitionNames();

		for(String currentBean : allSpringBeans){
			System.out.println("Bean: " + currentBean);
		}

		BeanDefinition myFirstBeanInfo =
				applicationContext.getBeanFactory().getBeanDefinition("myFirstBean");

		System.out.println(myFirstBeanInfo);
	}

}


