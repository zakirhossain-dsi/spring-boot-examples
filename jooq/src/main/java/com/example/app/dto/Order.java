package com.example.app.dto;

import lombok.Data;

@Data
public class Order {

  private Integer id;
  private String productName;
  private Integer orderAmount;
  private Integer userId;
}
