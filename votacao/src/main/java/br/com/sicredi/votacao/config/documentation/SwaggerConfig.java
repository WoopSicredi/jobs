package br.com.sicredi.votacao.config.documentation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.sicredi.votacao.controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Teste para Desenvolvedor(a) Backend do Woop Sicredi (Alex Radavelli)")
				.description("Documentação da API Rest de votação em pautas, que serve como teste para desenvolvedor(a) backend do Woop Sicredi")
				.version("1.0")
				.contact(new Contact("Alex Radavelli", null, "alexradavelli@gmail.com"))
				.build();
	}

}
