package br.edu.ifsp.gru.application.views.list;

<<<<<<< HEAD:src/main/java/com/example/application/views/list/ListView.java
import com.example.application.data.entity.Contas;
import com.example.application.data.repository.ContasRepository;
import com.example.application.data.service.CrmService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.AttachEvent;
=======
import br.edu.ifsp.gru.application.data.entity.ContaBancaria;
import br.edu.ifsp.gru.application.data.service.CrmService;
import br.edu.ifsp.gru.application.views.MainLayout;
>>>>>>> baf4f2245e3c5ab5f9816e97b5d73e304a47ad79:src/main/java/br/edu/ifsp/gru/application/views/list/ContaBancariaView.java
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
<<<<<<< HEAD:src/main/java/com/example/application/views/list/ListView.java
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.server.frontend.TaskRunNpmInstall.Stats;

import java.util.Collections;
=======
>>>>>>> baf4f2245e3c5ab5f9816e97b5d73e304a47ad79:src/main/java/br/edu/ifsp/gru/application/views/list/ContaBancariaView.java

import javax.annotation.security.PermitAll;

import org.springframework.beans.factory.DisposableBean;

@Route(value = "", layout = MainLayout.class)
@PageTitle("Contas Bancárias")
@PermitAll
public class ContaBancariaView extends VerticalLayout {
    Grid<ContaBancaria> grid = new Grid<>(ContaBancaria.class);
    TextField filterText = new TextField();
    ContaBancariaForm form;
    Chart chart;
    Span stats = new Span();
    private CrmService service;
    

    //Desenha a view de contas bancárias
    public ContaBancariaView(CrmService service) {
        this.service = service;
        var header = new H2();
        addClassName("list-view");
        setSizeFull();
        

        configureGrid();
        configureForm();
        
        header.add(getContaStats());
        

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

    //Traz todos os valores de contas do usuário
    private void updateList() {
        grid.setItems(service.buscaTodasContasBancarias(filterText.getValue()));
    }

    //Retorna o conteúdo
    private Component getContent() {
        HorizontalLayout conteudo = new HorizontalLayout(grid, form);
        conteudo.setFlexGrow(2, grid);
        conteudo.setFlexGrow(1, form);
        conteudo.addClassName("conteudo");
        conteudo.setSizeFull();

        return conteudo;
    }

    //Configura o formulário de criação/edição/exclusão de contas adicionando o "Listener" de botões
    private void configureForm() {
        form = new ContaBancariaForm(service.buscaTodosTipos());
        ((HasSize) form).setWidth("25em");

        form.addListener(ContaBancariaForm.SalvarEvento.class, this::salvarConta);
        form.addListener(ContaBancariaForm.DeletarEvento.class, this::deletarConta);
        form.addListener(ContaBancariaForm.FecharEvento.class, e -> fecharEditor());

    }

    private void salvarConta(ContaBancariaForm.SalvarEvento event) {
    	service.salvarConta(event.getContas());
    	updateList();
    	fecharEditor();
    	UI.getCurrent().getPage().reload();
    }
    
    private void deletarConta(ContaBancariaForm.DeletarEvento event) {
    	service.deletarConta(event.getContas());
    	updateList();
    	fecharEditor();
    }

    //Configura a tabela
    private void configureGrid() {
        grid.addClassNames("contas-grid");
        grid.setAllRowsVisible(true);
        grid.setColumns("conta", "saldo");
        grid.addColumn(contact -> contact.getStatus().getName()).setHeader("Tipo");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        
        grid.asSingleSelect().addValueChangeListener(e -> editarConta(e.getValue()));
    }
 
    
    private void editarConta(ContaBancaria conta) {
    	if(conta == null) {
    		fecharEditor();
    	}else {
    		form.setConta(conta);
    		form.setVisible(true);
    		addClassName("editing");
    	}
    }

    //Configura a barra acima da tabela com os filtros de busca e botão de pesquisa
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
		editarConta(new ContaBancaria());
	}
	
	//Retorna o valor total somando os saldos de todas as contas do usuário
	private Component getContaStats() {
		Span stats = new Span("Saldo Total R$ " + service.somaSaldo());
		stats.addClassNames("text-xl", "mt-m");
		setDefaultHorizontalComponentAlignment(Alignment.START);
		return stats;
	}
	



}