package com.coffeemachine.ui;

import com.coffeemachine.config.ControllersConfiguration;
import com.coffeemachine.entity.Good;
import com.coffeemachine.services.GoodService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.List;

@SuppressWarnings("SpringJavaAutowiringInspection")
public class MainController {

    @Autowired
    private GoodService goodService;

    @Value("${ui.title:JavaFX приложение}")//
    private String windowTitle;

    @Qualifier("managmentView")
    @Autowired
    private ControllersConfiguration.ViewHolder view;
    @FXML
    private TableView<Good> table;

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
    public void manager() {
        Stage stage = new Stage();
        stage.setTitle(windowTitle);
        stage.setScene(new Scene(view.getView()));
        stage.setResizable(true);
        stage.centerOnScreen();

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                init();
            }
        });
        
        stage.show();
    }
}
