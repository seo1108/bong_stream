package com.fanlight.livestream.config;

import com.fasterxml.classmate.TypeResolver;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    private String version;
    private String title;

    @Autowired
    private TypeResolver typeResolver;

    @Bean
    public Docket AppApi() {

        version = "V1";
        title = "livestream API " + version;

        return new Docket(DocumentationType.SWAGGER_2)
                //.alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(Pageable.class), typeResolver.resolve(Page.class)))
                .apiInfo(apiInfo(title, version))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.fanlight.livestream.controller.rest"))
                .paths(PathSelectors.ant("/m/v1/**"))
                .build()
                .useDefaultResponseMessages(false) // 기본으로 세팅되는 200,401,403,404 메시지를 표시 하지 않음
                //.securityContexts(Collections.singletonList(securityContext()))
                //.securitySchemes(Arrays.asList(apiKey()))
                .groupName("app_v1");
    }


    private ApiInfo apiInfo(String title, String version) {
        return new ApiInfo(
                title,
                "앱 개발시 사용되는 서버 API에 대한 연동 문서입니다.",
                version,
                "www.fanlight.co.kr",
                new Contact("Contact Me", "www.fanlight.co.kr", "admin@fanlight.co.kr"),
                "fanlight",
                "www.fanlight.co.kr",
                new ArrayList<>());
    }


    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    private List<SecurityReference> defaultAuth() {

        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Collections.singletonList(new SecurityReference("JWT", authorizationScopes));

        // oauth
        // final AuthorizationScope[] authorizationScopes = new AuthorizationScope[] {
        //         new AuthorizationScope("read", "read all"),
        //         new AuthorizationScope("write", "write all")
        // };

        // return Collections.singletonList(new SecurityReference("oauth2", authorizationScopes));
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }



// private OAuth securitySchema() {
//     final List<AuthorizationScope> authorizationScopeList = new ArrayList<>(2);

//     authorizationScopeList.add(new AuthorizationScope("read", "read all"));
//     authorizationScopeList.add(new AuthorizationScope("write", "access all"));

//     final List<GrantType> grantTypes = new ArrayList<>(1);
//     // 토큰 end point (http://localhost:3000/oauth/token)
//     grantTypes.add(new ResourceOwnerPasswordCredentialsGrant("http://localhost:3000/oauth/token"));

//     return new OAuth("oauth2", authorizationScopeList, grantTypes);
// }

    /*@Getter
    @Setter
    @ApiModel
    static class Page {
        @ApiModelProperty(value = "page num 0..N")
        private Integer page;

        @ApiModelProperty(value = "page size", allowableValues="range[0,100]")
        private Integer size;

        @ApiModelProperty(value = "sort(사용법 : 컬럼명,ASC|DESC)")
        private List<String> sort;
    }*/


}
