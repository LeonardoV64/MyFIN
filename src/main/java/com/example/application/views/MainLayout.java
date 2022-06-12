package com.example.application.views;

import com.example.application.views.list.ContasPagarView;
import com.example.application.views.list.DashBoardView;
import com.example.application.views.list.DespesasView;
import com.example.application.views.list.ListView;
import com.example.application.views.list.ReceitasView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {
	public MainLayout() {
		createHeader();
		createDrawer();
	}
	

	private void createHeader() {
		H2 logo = new H2("Minhas Finanças");
		logo.addClassNames("text-l", "m-m");
		
		
	HorizontalLayout header =	new HorizontalLayout(new DrawerToggle(), logo);
		
		header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
		header.expand(logo);
		header.setWidthFull();
		header.addClassNames("py-0", "px-m");
		
		
		
		
		addToNavbar(header);
	}
	
	private void createDrawer() {
		
		RouterLink listView	=	new RouterLink("Contas Correntes", ListView.class);
		
		RouterLink DespesasView	=	new RouterLink("Despesas", DespesasView.class);
		
		RouterLink ContasPagarView	=	new RouterLink("Contas", ContasPagarView.class);
		
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
