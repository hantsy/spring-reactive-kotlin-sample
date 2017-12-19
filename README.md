# spring-webflux-kotlin-sample

This sample is derived from [sdeleuze/spring-kotlin-functional @Github](https://github.com/sdeleuze/spring-kotlin-functional).

It demonstrates building a simple web application built with Spring 5 reactive stack, and it is written in Kotlin language.

The following features are included:

* Programmatic application bootstrap
* Spring Data Mongodb Reactive
* Mustache template for web view
* Functional RESTful APIs with Spring webflux specific `RouterFunction`
* Spring Security Reactive
* Kotlin favored `BeanDefinitionDSL` for declaring beans in fluent DSL

> Note: If you want to explore the feature list of Spring reactive stack, go to [spring-reactive-sample](https://github.com/hantsy/spring-reactive-sample
).

## Run

1. Get the source codes, and import it into your favorite IDE.

       git clone https://github.com/hantsy/spring-webflux-kotlin-sample

2. Run Mongodb. A `docker-compose.yml` is provided in the root folder.

        docker-compose up mongodb

3. Run `Application.kt` in IDE directly. Or open command line terminal and switch to the root directory of this project, execute `gradlew build`, when it is done, there is a `XXX-all.jar` in the *build/lib* folder. Execute `java -jar XXX-all.jar` to start the application.

## Contribute 

Welcome to contribute!