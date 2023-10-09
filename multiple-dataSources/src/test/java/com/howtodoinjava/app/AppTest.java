package com.howtodoinjava.app;


import com.howtodoinjava.app.model.order.Order;
import com.howtodoinjava.app.model.user.User;
import com.howtodoinjava.app.repositories.order.OrderRepository;
import com.howtodoinjava.app.repositories.user.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AppTest {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private OrderRepository orderRepository;

  @PersistenceContext(unitName = "order")
  private EntityManager entityManager;
  
  @Test
  void saveUserDataInDB() {

    User user = new User();
    user.setEmail("johndoe@gmail.com");
    user.setFirstName("John");
    user.setLastName("DOe");

    User savedUser = userRepository.save(user);
    Optional<User> userData = userRepository.findById(savedUser.getId());
    assertTrue(userData.isPresent());

  }

  @Test
  void saveOrderDataInDB() {

    Order order = new Order();
    order.setProductName("Mobile");
    order.setOrderAmount(200);
    order.setUserId(1);

    Order savedOrder = orderRepository.save(order);
    Optional<Order> orderData = orderRepository.findById(savedOrder.getId());
    assertTrue(orderData.isPresent());
  }

  @Test
  void joinCrossDB(){
    String jpqlQuery = "SELECT user.email, order.productName " +
            "FROM User user " +
            "JOIN Order order ON order.userId = user.userId";


    TypedQuery<Object[]> query = entityManager.createQuery(jpqlQuery, Object[].class);
    System.out.println(query.getResultList().size());
  }


}

