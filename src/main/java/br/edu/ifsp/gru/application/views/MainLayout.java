package br.edu.ifsp.gru.application.views;

import br.edu.ifsp.gru.application.security.SecurityService;
import br.edu.ifsp.gru.application.views.list.ContaBancariaView;
import br.edu.ifsp.gru.application.views.list.ContaBancariaDashboardView;
import br.edu.ifsp.gru.application.views.list.DespesaView;
import br.edu.ifsp.gru.application.views.list.ReceitaView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

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
		
		Button logout = new Button("Sair", e -> securityService.logout());
		
		
	HorizontalLayout header =	new HorizontalLayout(new DrawerToggle(), logo, logout);
		
		header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
		header.expand(logo);
		header.setWidthFull();
		header.addClassNames("py-0", "px-m");
		
		
		
		
		addToNavbar(header);
	}
	
	private void createDrawer() {
		
		RouterLink listView	=	new RouterLink("Contas Bancárias", ContaBancariaView.class);
		
		RouterLink DespesasView	=	new RouterLink("Despesas", DespesaView.class);
		
		RouterLink ReceitasView	=	new RouterLink("Receitas", ReceitaView.class);
		
		listView.setHighlightCondition(HighlightConditions.sameLocation());
		
		addToDrawer(new VerticalLayout(
				listView,
				new RouterLink("Dashboard", ContaBancariaDashboardView.class),
				DespesasView,
				ReceitasView
				));
		
	}
		

}
