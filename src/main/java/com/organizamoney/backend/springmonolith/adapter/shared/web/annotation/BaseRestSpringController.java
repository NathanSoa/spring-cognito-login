package com.organizamoney.backend.springmonolith.adapter.shared.web.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@RestController
@RequestMapping
@CrossOrigin
public @interface BaseRestSpringController {

    @AliasFor(annotation = RequestMapping.class, attribute = "value")
    String[] value();

    @AliasFor(annotation = CrossOrigin.class, attribute = "origins")
    String[] origins() default {"*"};
}
