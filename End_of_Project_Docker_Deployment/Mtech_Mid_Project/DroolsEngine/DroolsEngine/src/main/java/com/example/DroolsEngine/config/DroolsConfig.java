package com.example.DroolsEngine.config;

import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DroolsConfig {

    @Bean
    public KieContainer kieContainer() {

        //Provides access to core components like KieContainer, KieSession, KieFileSystem
        KieServices kieServices = KieServices.Factory.get();

        // Create empty file system to work with rule uploaded files (no rules are loaded from classpath at startup)
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();

        // Build empty container (rules will be added dynamically at runtime)
        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem).buildAll();
        ReleaseId releaseId = kieServices.getRepository().getDefaultReleaseId();

        return kieServices.newKieContainer(releaseId);
    }
}
