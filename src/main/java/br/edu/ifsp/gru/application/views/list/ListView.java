package br.edu.ifsp.gru.application.views.list;

import br.edu.ifsp.gru.application.data.entity.Contas;
import br.edu.ifsp.gru.application.data.service.CrmService;
import br.edu.ifsp.gru.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@Route(value = "", layout = MainLayout.class)
@PageTitle("Contas Correntes")
@PermitAll
public class ListView extends VerticalLayout {
    Grid<Contas> grid = new Grid<>(Contas.class);
    TextField filterText = new TextField();
    ContasForm form;
    Chart chart;
    private CrmService service;
    


    public ListView(CrmService service) {
        this.service = service;
        addClassName("list-view");
        setSizeFull();

        configureGrid();
        configureForm();
        
        getContaStats();
        

        add(
        		getContaStats(),
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
        conteudo.addClassName("conteudo");
        conteudo.setSizeFull();

        return conteudo;
    }

    private void configureForm() {
        form = new ContasForm(service.buscaTodosStatus());
        ((HasSize) form).setWidth("25em");

        form.addListener(ContasForm.SalvarEvento.class, this::salvarConta);
        form.addListener(ContasForm.DeletarEvento.class, this::deletarConta);
        form.addListener(ContasForm.FecharEvento.class, e -> fecharEditor());

    }

    private void salvarConta(ContasForm.SalvarEvento event) {
    	service.salvarConta(event.getContas());
    	updateList();
    	fecharEditor();
    }
    
    private void deletarConta(ContasForm.DeletarEvento event) {
    	service.deletarConta(event.getContas());
    	updateList();
    	fecharEditor();
    }


    private void configureGrid() {
        grid.addClassNames("contas-grid");
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
	}
	
	
	private Component getContaStats() {
		Span stats = new Span("Saldo Total " + service.somaSaldo());
		stats.addClassNames("text-xl", "mt-m");
		setDefaultHorizontalComponentAlignment(Alignment.CENTER);
		return stats;
	}



}