package com.liuyanzhao.blog.controller.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableWebMvc
@ComponentScan(basePackages ="com.liuyanzhao.blog.controller")
public class SwaggerConfig extends WebMvcConfigurationSupport{
	
	  @Bean
	  public Docket createRestApi() {
	    return new Docket(DocumentationType.SWAGGER_2)
	        .apiInfo(apiInfo())
	        .select()
	        .apis(RequestHandlerSelectors.basePackage("com.liuyanzhao.blog.controller"))
	        .paths(PathSelectors.any())
	        .build();
	  }
	 
	  private ApiInfo apiInfo() {
	    return new ApiInfoBuilder()
	        .title("api接口文档")
	        .description("api接口文档")
	        .termsOfServiceUrl("http://localhost:8080/swagger-ui.html") // 将“url”换成自己的ip:port
	        .version("1.0")
	        .build();
	  }
}
