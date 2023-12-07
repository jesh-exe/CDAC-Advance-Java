package config;

import org.springframework.context.annotation.*;

/*
 * This class is basically used to provide the implementation in place of
 * config file we used to write (.xml) 
 * 
 * All we need to do is to write @Configuration to tell Spring Container that this 
 * is our configuration class we will be using to tell all the dependencies packages and all
 * 
 * In tester we need to make the object of AnnotationConfigApplicationContext class and
 * we need to pass this class name as the parameter in the constructor of it.
 * 
 */

//Enables the Annotation support for Spring Bean
@Configuration

//Works as context:component-scan 
@ComponentScan(basePackages = {"dependency","dependent"})

public class MyConfiguration {}
