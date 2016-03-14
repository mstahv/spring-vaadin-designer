package com.example;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.UI;

@SpringBootApplication
public class SpringVaadinDesignerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringVaadinDesignerApplication.class, args);
	}
	
	
	@Service
	static class MyService {
		public String sayHi() {
			return "Hi there!";
		}
	}
	
	@Theme("valo")
	@SpringUI
	public static class MyUI extends UI {
		
		@Autowired
		DesignerView designerView;
		
		@Override
		protected void init(VaadinRequest request) {
			setContent(designerView);
		}
		
	}
	
	@SpringComponent
	@UIScope
	public static class DesignerView extends DesignerViewDesign {
		
		@Autowired
		MyService service;
		
		@PostConstruct
		public void init() {
			// messageFromService is introduced in Vaadin Designer
			messageFromService.setValue(service.sayHi());
		}
		
	}
	
}
