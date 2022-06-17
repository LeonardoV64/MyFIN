package br.edu.ifsp.gru.application.views.list;

import javax.annotation.security.PermitAll;

import br.edu.ifsp.gru.application.views.MainLayout;
import br.edu.ifsp.gru.application.data.service.CrmService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.charts.model.style.GradientColor;
import com.vaadin.flow.component.charts.model.style.SolidColor;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route(value = "dashboard", layout = MainLayout.class)
@PageTitle("Dashboard de contas")
@PermitAll
public class ContaBancariaDashboardView extends VerticalLayout{
	private CrmService service;
	
	//Desenha a view de dashboard de contas
	public ContaBancariaDashboardView(CrmService service) {
		this.service = service;
		addClassName("dashboard-view");
		GradientColor color = GradientColor.createLinear(0, 0, 0, 1);
		color.addColorStop(0, new SolidColor("#000000"));
		setDefaultHorizontalComponentAlignment(Alignment.CENTER);
		add(getContaStats(), getContasSaldoChart());
	}

	//Retorna o valor da soma de todos os saldos no topo, acima da caixa de busca
	private Component getContaStats() {
		Span stats = new Span("Saldo Total R$ " + service.somaSaldo());
		stats.addClassNames("text-xl", "mt-m");
		return stats;
	}

	//Cria um gráfico de pizza baseado em todos os valores de saldo de contas bancárias que o usuário cadastrou
	private Component getContasSaldoChart() {
		Chart chart = new Chart(ChartType.PIE);
		
		
		DataSeries dataSeries = new DataSeries();
		service.buscaTodasContasBancarias(null).forEach(conta->{
			dataSeries.add(new DataSeriesItem(conta.getConta(), conta.getSaldo()));
			});
		chart.getConfiguration().setSeries(dataSeries);
		return chart;
	}
	


	

}
