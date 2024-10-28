package com.database.database;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import java.io.Serializable;

@Setter
@Getter
@Entity
public class Organization implements Serializable {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;
  private String name;
  
  @ManyToMany(mappedBy = "organizations")
  Set<Event> events = new HashSet<>();
  
  public Organization() {
  }
  
  public Organization(int id, String name) {
    this.id = id;
    this.name = name;
  }
  
  @Override
  public String toString() {
    return id + " " + name;
  }
}
