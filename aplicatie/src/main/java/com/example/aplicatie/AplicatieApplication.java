package com.example.aplicatie;


import com.example.aplicatie.exporter.RaportCreated;
import com.example.aplicatie.model.Order;
import com.example.aplicatie.model.Product;
import com.example.aplicatie.model.User;
//import com.example.aplicatie.service.MailServiceImpl;
import com.example.aplicatie.service.OrderServiceImpl;
import com.example.aplicatie.service.ProductServiceImpl;
import com.example.aplicatie.service.UserServiceImpl;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

import java.util.ArrayList;

@SpringBootApplication
public class AplicatieApplication {
	@Autowired

	public static void main(String[] args) {
		try {
			// Create a Scheduler
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

			// Define a JobDetail with the ScheduledTask class as the job
			JobDetail jobDetail = JobBuilder.newJob(RaportCreated.class)
					.withIdentity("scheduledTaskJob", "group1")
					.build();

			// Define a Trigger to schedule the job
			Trigger trigger = TriggerBuilder.newTrigger()
					.withIdentity("scheduledTaskTrigger", "group1")
					.withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(17, 42)) // Schedule to run daily at 9:00 AM
					.build();

			// Associate the JobDetail and Trigger with the Scheduler
			scheduler.scheduleJob(jobDetail, trigger);

			// Start the Scheduler
			scheduler.start();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		SpringApplication.run(AplicatieApplication.class, args);
	}



	@Bean
	CommandLineRunner init(UserServiceImpl userRepository, ProductServiceImpl productRepository, OrderServiceImpl orderRepository){
		return args->{
			//User u1= new User("utilizator1","email@yahoo.com","123456789");
			//User u2= new User("utilizator2","email2@yahoo.com","1tyu6789");
			//User u3= new User("utilizator3","email3@yahoo.com","1tyu6789");

			//Product p1=new Product("La vie en rose","Lancome",344,0);
			//Product p2=new Product("Idole","Lancome",390,0);
			//Product p3=new Product("Idole","Lancome",490,0);

			//ArrayList<Product> products=new ArrayList<>();
			//products.add(p1);
			//products.add(p2);
			//Order o1=new Order(u1,products);

			//ArrayList<Product> products2=new ArrayList<>();
			//products2.add(p2);
			//products2.add(p3);
			//Order o2=new Order(u2,products2);

			//userRepository.saveUser(u1);
			//userRepository.saveUser(u2);
			//productRepository.saveProduct(p1);
			//productRepository.saveProduct(p2);
			//productRepository.saveProduct(p3);

			//orderRepository.saveOrder(o1);
			//orderRepository.saveOrder(o2);
			//o1.setProducts(products2);
			//orderRepository.updateOrder(o1);
			//productRepository.deleteProduct(p2.getId());

			//p1.setName("La vie est belle");
			//p2.setPrice(555);
			//productRepository.updateProduct(p1);
			//productRepository.updateProduct(p2);
			//productRepository.deleteProduct(p1.getId());
			//u1.setEmail("email3");
			//userRepository.updateUser(u1);
			//userRepository.deleteUser(u1.getId());

		};
	}

}
