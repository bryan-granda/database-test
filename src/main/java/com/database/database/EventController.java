package com.database.database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.NoSuchElementException;

@Controller
@RequestMapping(path="/event")
public class EventController {
  @Autowired
  private EventRepository eventRepository;
  @Autowired
  private OrganizationRepository organizationRepository;
  
  @PostMapping(path="/addEvent")
  public @ResponseBody String addNewEvent (@RequestParam String name) {
    try{
      System.out.println("Adding event");
      Event event = new Event();
      event.setName(name);
      eventRepository.save(event);
      return "Saved";
    }
    catch (Exception e){
      System.out.println("Error: " + e.getMessage());
      return "Error";
    }
  }
  
  @GetMapping(path="/events")
  public @ResponseBody String getAllEvents() {
    StringBuilder events = new StringBuilder();
    for (Event event : eventRepository.findAll()) {
      events.append(event.toString()).append("\n");
    }
    
    return events.toString();
  }
}
