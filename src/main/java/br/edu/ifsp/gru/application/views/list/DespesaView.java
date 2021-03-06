package br.edu.ifsp.gru.application.views.list;

import javax.annotation.security.PermitAll;

import br.edu.ifsp.gru.application.views.MainLayout;
import br.edu.ifsp.gru.application.data.service.CrmService;
import com.vaadin.flow.component.charts.model.style.GradientColor;
import com.vaadin.flow.component.charts.model.style.SolidColor;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route(value = "despesa", layout = MainLayout.class)
@PageTitle("Despesas Mensais")
@PermitAll
public class DespesaView extends VerticalLayout{
	private CrmService service;
	
	
	public DespesaView(CrmService service) {
		this.service = service;
		addClassName("despesas-view");
		GradientColor color = GradientColor.createLinear(0, 0, 0, 1);
		color.addColorStop(0, new SolidColor("#000000"));
		setDefaultHorizontalComponentAlignment(Alignment.CENTER);
	}
	
	

	

}