package com.coffeemachine.ui;

import com.coffeemachine.entity.Good;
import com.coffeemachine.services.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;

@SuppressWarnings("SpringJavaAutowiringInspection")
public class ManagementController {

    @Autowired
    private GoodService goodService;

    @FXML private TableView<Good> table;
    @FXML private TextField txtName;
    @FXML private TextField txtCost;

    private ObservableList<Good> data;


    @FXML
    public void initialize() {
    }

    @SuppressWarnings("unchecked")
    @PostConstruct
    public void init() {

        List<Good> goods = goodService.findAll();
        data = FXCollections.observableArrayList(goods);

        TableColumn<Good, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Good, String> nameColumn = new TableColumn<>("Наименование");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Good, String> phoneColumn = new TableColumn<>("Цена");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));

        table.getColumns().setAll(idColumn, nameColumn, phoneColumn);

        table.setItems(data);
    }

    @FXML
    public void addGood() {
        String name = txtName.getText();
        String costStr = txtCost.getText();

        if (!StringUtils.hasText(name) || !StringUtils.hasText(costStr)) {
            return;
        }

        BigDecimal cost = BigDecimal.valueOf(Long.parseLong(costStr));

        Good good = new Good();
        good.setName(name);
        good.setCost(cost);
        goodService.save(good);
        data.add(good);

        txtName.setText("");
        txtCost.setText("");
    }
}
