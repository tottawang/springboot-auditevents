package com.sample.servicename.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.boot.actuate.audit.listener.AuditApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher {

  /**
   * Instance type is AnnotationConfigEmbeddedWebApplicationContext
   * 
   * TODO need to understand AnnotationConfigApplicationContext a bit more, which is default
   * publisher who calls AbstractApplicationContext.publishEvent.
   */
  @Autowired
  private ApplicationEventPublisher applicationEventPublisher;

  /**
   * Build AuditApplicationEvent instance and publish it.
   * 
   * 
   * AuditListener setup in AuditAutoConfiguration listens to AuditApplicationEvent events
   * EventPublisher.publishEvent will publish AuditApplicationEvent event which AuditListener
   * subscribes to.
   * 
   * @param message
   */
  public void publishEvent(final String message) {
    System.out.println("Publishing custom event.");
    Map<String, Object> data = new HashMap<>();
    data.put("data", message);
    AuditEvent auditEvent = new AuditEvent("someUser", "someType", data);
    AuditApplicationEvent auditAppEvent = new AuditApplicationEvent(auditEvent);
    applicationEventPublisher.publishEvent(auditAppEvent);
  }

}
