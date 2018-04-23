package com.sample.servicename.resources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.boot.actuate.audit.AuditEventRepository;

import com.sample.servicename.service.EventPublisher;

@Path("/events")
@Produces(MediaType.APPLICATION_JSON)
public class SampleResource {

  SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");

  @Autowired
  private EventPublisher eventPublisher;

  @Autowired
  private AuditEventRepository auditEventRepository;

  @POST
  public void addEvent() {
    eventPublisher.publishEvent("test");
  }

  @GET
  public List<AuditEvent> getEvent() throws ParseException {
    String dateInString = "7-Jun-2013";
    List<AuditEvent> test = auditEventRepository.find(formatter.parse(dateInString));
    return test;
  }

}
