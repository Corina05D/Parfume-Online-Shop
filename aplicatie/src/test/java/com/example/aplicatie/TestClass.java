package com.example.aplicatie;

import com.example.aplicatie.model.Order;
import com.example.aplicatie.model.Product;
import com.example.aplicatie.model.User;
import com.example.aplicatie.repository.OrderRepository;
import com.example.aplicatie.service.OrderServiceImpl;
import com.example.aplicatie.service.ProductServiceImpl;
import com.example.aplicatie.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@Transactional
public class TestClass {

    @Autowired
    private UserServiceImpl userRepository;
    private OrderRepository or;

    @Autowired
    private ProductServiceImpl productRepository;

    @Autowired
    private OrderServiceImpl orderRepository;

    @Test
    public void testCRUDOperations(){
        //create 3 users
        User u1= new User("utilizator1","email@yahoo.com","123456789");
        User u2= new User("utilizator2","email2@yahoo.com","1tyu6789");
        User u3= new User("utilizator3","email3@yahoo.com","1tyu6789");

        //create 3 products
        Product p1=new Product("La vie en rose","Lancome",344,10);
        Product p2=new Product("Idole","Lancome",390,10);
        Product p3=new Product("Fragrance","Lancome",490,10);
        Product p4=new Product("Allien","Mugler",380,10);

        //save the users in the database
        userRepository.saveUser(u1);
        userRepository.saveUser(u2);
        userRepository.saveUser(u3);

        //save the products in the database
        productRepository.saveProduct(p1);
        productRepository.saveProduct(p2);
        productRepository.saveProduct(p3);
        productRepository.saveProduct(p4);

        //I modified the email of an user and uptae its value in the database
        u1.setEmail("EmailModificat@yahoo.com");
        userRepository.updateUser(u1);

        //delete the 3rd user
        userRepository.deleteUser(u3.getId());

        //update the value of a product
        p3.setPrice(600);
        productRepository.updateProduct(p3);

        //delete the first product
        productRepository.deleteProduct(p4.getId());

        //create a list of products
        ArrayList<Product> products=new ArrayList<>();
        products.add(p1);
        products.add(p2);

        //another list of products
        ArrayList<Product> products2=new ArrayList<>();
        products2.add(p2);
        products2.add(p3);

        //create2 orders
        Order o1=new Order(u1,products);
        Order o2=new Order(u2,products2);

        //save the orders in database
        orderRepository.saveOrder(o1);
        orderRepository.saveOrder(o2);

        //modify a product
        p3.setPrice(200);
        p3.setName("New Parfume");
        productRepository.updateProduct(p3);

        //delete a product and it aldo delete from order
        productRepository.deleteProduct(p2.getId());

    }

}
