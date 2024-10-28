package com.database.database;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Entity
public class Client implements Serializable {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;
  private String name;
  
  public Client() {
  }
  
  public Client(int id, String name) {
    this.id = id;
    this.name = name;
  }
  
  @Override
  public String toString() {
    return id + " " + name;
  }
}
