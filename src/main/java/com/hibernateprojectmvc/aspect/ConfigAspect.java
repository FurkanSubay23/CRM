package com.hibernateprojectmvc.aspect;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.hibernateprojectmvc.aspect")
public class ConfigAspect {
}
