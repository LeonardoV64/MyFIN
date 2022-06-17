package com.example.application.views;

import com.example.application.security.SecurityService;
import com.example.application.views.list.ContasPagarView;
import com.example.application.views.list.DashBoardView;
import com.example.application.views.list.DespesasView;
import com.example.application.views.list.ListView;
import com.example.application.views.list.ReceitasView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.StreamResource;

public class MainLayout extends AppLayout {
	private SecurityService securityService;
	
	public MainLayout(SecurityService securityService) {
		this.securityService = securityService;
		createHeader();
		createDrawer();
	}
	

	private void createHeader() {

		H2 logo = new H2("MyFIN");
		logo.addClassNames("text-l", "m-m");
		
		StreamResource imageResource = new StreamResource("icon.png",
			    () -> getClass().getResourceAsStream("/META-INF/resources/icons/headericon.png"));
		
		Image logoIf = new Image(imageResource, "Logo IFSP");
		logoIf.getStyle().set("width", "5%");
		
		
		
		Button logout = new Button("Sair", e -> securityService.logout());
		
		
		HorizontalLayout header =	new HorizontalLayout(new DrawerToggle(), logoIf, logo, logout);
	
		header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
		header.expand(logo);
		header.setWidthFull();
		header.addClassNames("py-0", "px-m");
		
		
		
		
		addToNavbar(header);
	}
	
	private void createDrawer() {
		//Icon icon = viewIcon.create();
		
		RouterLink listView	=	new RouterLink("Contas Correntes", ListView.class);
		
		
		RouterLink DespesasView	=	new RouterLink("Despesas", DespesasView.class);
		
		RouterLink ContasPagarView	=	new RouterLink("Contas a Pagar", ContasPagarView.class);
		
		RouterLink ReceitasView	=	new RouterLink("Receitas", ReceitasView.class);
		
		listView.setHighlightCondition(HighlightConditions.sameLocation());
		
		addToDrawer(new VerticalLayout(
				listView,
				new RouterLink("Dashboard", DashBoardView.class),
				DespesasView,
				ContasPagarView,
				ReceitasView
				));
		
	}
		

}
