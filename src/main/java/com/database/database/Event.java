package com.database.database;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
public class Event implements Serializable {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;
  private String name;
  
  @ManyToMany
  @JoinTable(
          name = "organization_event",
          joinColumns = @JoinColumn(name = "organization_id"),
          inverseJoinColumns = @JoinColumn(name = "event_id"))
  Set<Organization> organizations = new HashSet<>();
  
  public Event() {
  }
  
  public Event(int id, String name) {
    this.id = id;
    this.name = name;
  }
  
  @Override
  public String toString() {
    return id + " " + name;
  }
}
