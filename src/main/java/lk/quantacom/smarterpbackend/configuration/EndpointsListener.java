package lk.quantacom.smarterpbackend.configuration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Component
public class EndpointsListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();


//        System.out.println("first working on startup");

        applicationContext.getBean(RequestMappingHandlerMapping.class).getHandlerMethods()
                .forEach((requestMappingInfo, handlerMethod) -> {
//                    System.out.println(requestMappingInfo.toString());

                    // 1. call the delete all method of 'EndPoints'
//                    System.out.println("second working on startup");
                    // 2. insert the base URL's (substring and get only url part) of GET methods into 'EndPoints' using bulk insert method


                });
    }
}