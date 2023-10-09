package com.example.app;

import com.example.app.dto.Order;
import com.example.app.dto.User;
import com.example.app.dto.UserOrder;
import com.example.app.repository.GeneralQueryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AppTest {

  @Autowired
  GeneralQueryBuilder generalQueryBuilder;

  @Test
  void fetchUserFromDB() {

    List<User> users = generalQueryBuilder.fetchUser();
    System.out.println(users.get(0).getFirstName());
    assertEquals(2, users.size());
  }

  @Test
  void fetchOrderFromDB() {

    List<Order> orders = generalQueryBuilder.fetchOrder();
    System.out.println(orders.get(0).getProductName());
    assertEquals(3, orders.size());
  }

  @Test
  void fetchUserOrder() {
    List<UserOrder> userOrders = generalQueryBuilder.fetchUserOrder();
    System.out.println(userOrders);
    assertNotNull(userOrders);
  }

  @Test
  void fetchUserOrderInString() {
    String userOrders = generalQueryBuilder.fetchUserOrderInString();
    System.out.println(userOrders);
    assertNotNull(userOrders);
  }

  @Test
  void fetchOrdersByUser() {
    String userOrders = generalQueryBuilder.fetchOrdersByUser("Zakir");
    System.out.println(userOrders);
    assertNotNull(userOrders);
  }

  @Test
  void fetchUsersLazily() {
    List<User> users = generalQueryBuilder.fetchUserLazily();
    assertNotNull(users);
  }
}

