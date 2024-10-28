package com.database.database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Set;

@Controller
@RequestMapping(path="/organization")
public class OrganizationController {
  @Autowired
  private OrganizationRepository organizationRepository;
  @Autowired
  private EventRepository eventRepository;
  
  @PostMapping(path="/addOrganization")
  public @ResponseBody String addNewOrganization (@RequestParam String name) {
    try{
      System.out.println("Adding organization");
      Organization organization = new Organization();
      organization.setName(name);
      organizationRepository.save(organization);
      return "Saved";
    }
    catch (Exception e){
      System.out.println("Error: " + e.getMessage());
      return "Error";
    }
  }
  
  @GetMapping(path="/organizations")
  public @ResponseBody String getAllOrganizations() {
    StringBuilder organizations = new StringBuilder();
    for (Organization organization : organizationRepository.findAll()) {
      organizations.append(organization.toString()).append("\n");
    }
    
    return organizations.toString();
  }
  
  @PatchMapping(path="/addEventToOrganization")
  public @ResponseBody String addEventToOrganization (@RequestParam Integer organizationId, @RequestParam Integer eventId) {
    try{
      boolean organizationExists = organizationRepository.findById(organizationId).isPresent();
      if (!organizationExists){
        System.out.println("Organization Not Found");
        return "Error";
      }
      Organization organization = organizationRepository.findById(organizationId).get();
      Event event = eventRepository.findById(eventId).get();
      organization.getEvents().add(event);
      event.getOrganizations().add(organization);
      organizationRepository.save(organization);
      eventRepository.save(event);
      return "Saved";
    }
    catch (NoSuchElementException e){
      System.out.println("Event Not Found");
      return "Error";
    }
    catch (Exception e){
      System.out.println("Error: " + e.getMessage());
      return "Error";
    }
  }
  
  @GetMapping(path="/organizationEvents")
  public @ResponseBody String getOrganizationEvents (@RequestParam Integer organizationId) {
    try{
      boolean organizationExists = organizationRepository.findById(organizationId).isPresent();
      if (!organizationExists){
        System.out.println("Organization Not Found");
        return "Error";
      }
      Organization organization = organizationRepository.findById(organizationId).get();
      StringBuilder events = new StringBuilder();
      Set<Event> organizationEvents = organization.getEvents();
      if (organizationEvents.isEmpty()){
        return "No Events";
      }
      for (Event event : organizationEvents) {
        events.append(event.toString()).append("\n");
      }
      
      return events.toString();
    }
    catch (Exception e){
      System.out.println("Error: " + e.getMessage());
      return "Error";
    }
  }
  
  @DeleteMapping(path="/deleteOrganization")
  public @ResponseBody String deleteOrganization (@RequestParam Integer organizationId) {
    try{
      boolean organizationExists = organizationRepository.findById(organizationId).isPresent();
      if (!organizationExists){
        System.out.println("Organization Not Found");
        return "Error";
      }
      Organization organization = organizationRepository.findById(organizationId).get();
      organizationRepository.delete(organization);
      return "Deleted";
    }
    catch (Exception e){
      System.out.println("Error: " + e.getMessage());
      return "Error";
    }
  }
}
