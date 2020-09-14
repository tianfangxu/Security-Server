package com.mot.factory.send;

import com.mot.config.properties.ServiceConfig;
import com.mot.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SendMessageServiceFactory {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    ServiceConfig serviceConfig;

    public MessageService getInstance(String type){
        MessageService service = null;
        for (ServiceConfig.Nods nod : serviceConfig.nods) {
            if (nod.getType().equals(type)){
                service = (MessageService)applicationContext.getBean(nod.getBeanName());
                break;
            }
        }
        return service;
    }

}
