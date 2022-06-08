package com.example.application.views.list;

import com.example.application.data.entity.Contas;
import com.example.application.data.service.CrmService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.login.LoginI18n.Form;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
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
    ContasForm form;
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
        
        
        closeEditor();

    }
    
    private void closeEditor() {
    	
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
        /*
        form.addListener(ContasForm.SaveEvent.class, this::saveConta);
        form.addListener(ContasForm.DeleteEvent.class, this::deleteConta);
        form.addListener(ContasForm.CloseEvent.class, e -> closeEditor());
        */
    }
    /*
    private void saveConta(ContasForm.SaveEvent event) {
    	service.salvarConta(event.getConta());
    	updateList();
    	closeEditor();
    }
    
    private void deleteConta(ContasForm.DeleteEvent event) {
    	service.deletarConta(event.getConta());
    	updateList();
    	closeEditor();
    }
    */

    private void configureGrid() {
        grid.addClassNames("contas-grid");
        grid.setSizeFull();
        grid.setColumns("conta", "saldo");
        grid.addColumn(contact -> contact.getStatus().getName()).setHeader("Status");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        
        
        
        grid.asSingleSelect().addValueChangeListener(e -> editConta(e.getValue()));
    }
 
    
    private void editConta(Contas conta) {
    	if(conta == null) {
    		closeEditor();
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
        addContaButton.addClickListener(e -> addConta());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addContaButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

	private void addConta() {
		grid.asSingleSelect().clear();
		editConta(new Contas());
	}
}