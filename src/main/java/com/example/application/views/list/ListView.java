package com.example.application.views.list;

import com.example.application.data.entity.Contas;
import com.example.application.data.service.CrmService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.login.LoginI18n.Form;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Collections;

@Route(value = "", layout = MainLayout.class)
@PageTitle("Contas Correntes")
public class ListView extends VerticalLayout {
    Grid<Contas> grid = new Grid<>(Contas.class);
    TextField filterText = new TextField();
    ContaForm form;

    Chart chart;

    private CrmService service;
    


    public ListView(CrmService service) {
        this.service = service;
        addClassName("list-view");
        setSizeFull();

        configureGrid();
        configureForm();

        add(
                getToolbar(),
                getContent()
        );

        updateList(); 	
        
        
        fecharEditor();

    }
    
    private void fecharEditor() {
    	
    	form.setConta(null);
    	form.setVisible(false);
    	removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(service.buscaTodasContas(filterText.getValue()));
    }

    private Component getContent() {
        HorizontalLayout conteudo = new HorizontalLayout(grid, form);
        conteudo.setFlexGrow(2, grid);
        conteudo.setFlexGrow(1, form);
        //conteudo.setFlexGrow(1, chart);
        conteudo.addClassName("conteudo");
        conteudo.setSizeFull();

        return conteudo;
    }

    private void configureForm() {
        form = new ContaForm(service.buscaTodosStatus());
        ((HasSize) form).setWidth("25em");

        form.addListener(ContaForm.SalvarEvento.class, this::salvarConta);
        form.addListener(ContaForm.DeletarEvento.class, this::deletarConta);
        form.addListener(ContaForm.FecharEvento.class, e -> fecharEditor());

    }

    private void salvarConta(ContaForm.SalvarEvento event) {
    	service.salvarConta(event.getContas());
    	updateList();
    	fecharEditor();
    }
    
    private void deletarConta(ContaForm.DeletarEvento event) {
    	service.deletarConta(event.getContas());
    	updateList();
    	fecharEditor();
    }


    private void configureGrid() {
        grid.addClassNames("contas-grid");
        //grid.setSizeFull();
        grid.setAllRowsVisible(true);
        grid.setColumns("conta", "saldo");
        grid.addColumn(contact -> contact.getStatus().getName()).setHeader("Status");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        
        
        
        grid.asSingleSelect().addValueChangeListener(e -> editarConta(e.getValue()));
    }
 
    
    private void editarConta(Contas conta) {
    	if(conta == null) {
    		fecharEditor();
    	}else {
    		form.setConta(conta);
    		form.setVisible(true);
    		addClassName("editing");
    	}
    	
    }
    

    private Component getToolbar() {
        filterText.setPlaceholder("Procurar por conta...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addContaButton = new Button("Criar Conta");
        addContaButton.addClickListener(e -> adicionarConta());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addContaButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

	private void adicionarConta() {
		grid.asSingleSelect().clear();
		editarConta(new Contas());
<<<<<<< HEAD
	}
	
	//Gráficos
	
	public void DashBoardView(CrmService service) {
		this.service = service;
		addClassName("dashboard-view");
		setDefaultHorizontalComponentAlignment(Alignment.CENTER);
		add(getContaStats(), getContasSaldoChart());
	}
	
	private Component getContaStats() {
		Span stats = new Span("Saldo Total " + service.somaSaldo());
		stats.addClassNames("text-xl", "mt-m");
		return stats;
	}


	private Component getContasSaldoChart() {
		Chart chart = new Chart(ChartType.PIE);
				
		DataSeries dataSeries = new DataSeries();
		service.buscaTodasContas(null).forEach(conta->{
			dataSeries.add(new DataSeriesItem(conta.getConta(), conta.getSaldo()));
			});
		chart.getConfiguration().setSeries(dataSeries);
		return chart;
=======
>>>>>>> 26da96d1339fcf738388b9b35ef94ac227251b2a
	}
}