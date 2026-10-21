package de.spricom.zaster2.views.bitget;

import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import de.spricom.zaster2.entities.BitgetFutureOrderEntity;

@PageTitle("Bitget Future Orders")
@Route("")
@RouteAlias("bitget-future-orders")
@Menu(order = 10, icon = "vaadin:list")
public class BitgetFutureOrderView extends VerticalLayout {

    private final BitgetFutureOrderService service;
    private final BitgetFutureOrderModel model;
    private final Grid<BitgetFutureOrderEntity> grid;

    public BitgetFutureOrderView(BitgetFutureOrderService service) {
        this.service = service;
        this.model = new BitgetFutureOrderModel();
        this.grid = new Grid<>(BitgetFutureOrderEntity.class);

        setSizeFull();
        configureGrid();
        add(getFilters(), grid);
        updateList();
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.setColumns("createdAt", "symbol", "side", "orderType", "price", "size", "fee", "totalProfits", "status");
        grid.getColumnByKey("createdAt").setSortable(true).setHeader("Date");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private HorizontalLayout getFilters() {
        TextField symbolFilter = new TextField("Currency/Symbol");
        symbolFilter.setPlaceholder("Filter by symbol...");
        symbolFilter.setClearButtonVisible(true);
        symbolFilter.setValueChangeMode(ValueChangeMode.LAZY);
        symbolFilter.addValueChangeListener(e -> {
            model.setSymbol(e.getValue());
            updateList();
        });

        DatePicker fromDate = new DatePicker("From Date");
        fromDate.addValueChangeListener(e -> {
            model.setFromDate(e.getValue());
            updateList();
        });

        DatePicker toDate = new DatePicker("To Date");
        toDate.addValueChangeListener(e -> {
            model.setToDate(e.getValue());
            updateList();
        });

        HorizontalLayout filters = new HorizontalLayout(symbolFilter, fromDate, toDate);
        filters.setAlignItems(Alignment.END);
        return filters;
    }

    private void updateList() {
        grid.setItems(service.fetchOrders(model));
    }
}
