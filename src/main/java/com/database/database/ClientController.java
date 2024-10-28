package com.database.database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/client")
public class ClientController {
  @Autowired
  private ClientRepository clientRepository;
  
 @PostMapping(path="/addClient")
  public @ResponseBody String addNewClient (@RequestParam String name) {
    try{
      System.out.println("Adding client");
      Client client = new Client();
      client.setName(name);
      clientRepository.save(client);
      return "Saved";
    }
    catch (Exception e){
      System.out.println("Error: " + e.getMessage());
      return "Error";
    }
  }
  
  @GetMapping(path="/clients")
  public @ResponseBody String getAllClients() {
    StringBuilder clients = new StringBuilder();
    for (Client client : clientRepository.findAll()) {
      clients.append(client.toString()).append("\n");
    }
    
    return clients.toString();
  }
}
