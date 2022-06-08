package com.example.application.views.list;

import com.example.application.data.service.CrmService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "dashboard", layout = MainLayout.class)
@PageTitle("Gráficos")
public class DashBoardView extends VerticalLayout{
	private CrmService service;
	
	
	public DashBoardView(CrmService service) {
		this.service = service;
		addClassName("dashboard-view");
		setDefaultHorizontalComponentAlignment(Alignment.CENTER);
		add(getContaStats(), getContasSaldoChart());
	}


	private Component getContasSaldoChart() {
		Chart chart = new Chart(ChartType.PIE);
		
		
		DataSeries dataSeries = new DataSeries();
		service.buscaTodasContas(null).forEach(conta->{
			dataSeries.add(new DataSeriesItem(conta.getConta(), conta.getSaldo()));
			});
		chart.getConfiguration().setSeries(dataSeries);
		return chart;
	}


	private Component getContaStats() {
		Span stats = new Span(service.countContas() + "conta");
		stats.addClassNames("text-xl", "mt-m");
		return null;
	}
	

}
