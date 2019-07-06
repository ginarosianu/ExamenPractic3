package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sample.Domain.Examinare;
import sample.Service.ExaminareService;

import javax.swing.*;

public class ExaminareController {
    public TableView < Examinare > tableViewExaminare;
    public TableColumn tableColumnIdExaminare;
    public TableColumn tableColumnIdPersExam;
    public TableColumn tableColumnRezultat;
    public TableColumn tableColumnData;
    public TextField txtExaminareId;
    public TextField txtIdPersExam;
    public TextField txtExaminareRezultat;
    public TextField txtData;
    public Button btnAddExaminare;
    private ExaminareService examinareService;



    public void setService(ExaminareService examinareService) {
        this.examinareService = examinareService;
    }

    private ObservableList < Examinare > examinari = FXCollections.observableArrayList();

    @FXML
    private void initialize() {

        Platform.runLater( new Runnable() {
            public void run() {
                examinari.addAll( examinareService.getAll() );
                tableViewExaminare.setItems( examinari );
            }
        } );
    }

    public void btnAddExaminare(ActionEvent actionEvent) {
        try {
            Integer idExaminare = Integer.parseInt(txtExaminareId.getText());
            Integer idPersExam = Integer.parseInt(txtIdPersExam.getText());
            String rezultat = txtExaminareRezultat.getText();
            Integer data = Integer.parseInt(txtData.getText());


            examinareService.add(idExaminare, idPersExam, rezultat, data);

            examinari.clear();
            examinari.addAll(examinareService.getAll());
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
    }
}
