package sio.devoir2graphiques;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import sio.devoir2graphiques.Tools.ConnexionBDD;
import sio.devoir2graphiques.Tools.GraphiqueController;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Devoir2GraphiquesController implements Initializable
{
    @FXML
    private Button btnGraph1;
    @FXML
    private Button btnGraph2;
    @FXML
    private Button btnGraph3;
    @FXML
    private AnchorPane apGraph1;
    @FXML
    private LineChart graph1;
    @FXML
    private Label lblTitre;
    @FXML
    private AnchorPane apGraph2;
    @FXML
    private AnchorPane apGraph3;
    @FXML
    private PieChart graph3;
    @FXML
    private BarChart graph2;

    ConnexionBDD connexionBDD;
    GraphiqueController graphiqueController;

    HashMap<Integer, Double> graphiques1datas;
    HashMap<String, ArrayList<Double>> graphiques2datas;

    HashMap<String,Integer> graphiques3datas;



    XYChart.Series<Integer,Double> serieGraph1;
    XYChart.Series<String,ArrayList<Double>> serieGraph2;






    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblTitre.setText("Devoir : Graphique n°1");
        apGraph1.toFront();

        try {
            connexionBDD = new ConnexionBDD();
            graphiqueController = new GraphiqueController();

            serieGraph1.getData().clear();

            graphiques1datas = new HashMap<>();
            graphiques1datas = graphiqueController.getGraphique1Datas();

            serieGraph1 = new XYChart.Series<>();
            serieGraph1.setName("moyenne");

            for (Integer value : graphiques1datas.keySet() ){
                serieGraph1.getData().add(new XYChart.Data<>(value, graphiques1datas.get(value)));
            }

            graph1.getData().add(serieGraph1);

            graph1.setTitle("Moyennes des salaires par âge");


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // A vous de jouer

    }

    @FXML
    public void menuClicked(Event event) throws SQLException {
        if(event.getSource() == btnGraph1)
        {
            lblTitre.setText("Devoir : Graphique n°1");
            apGraph1.toFront();

            // A vous de jouer

            graphiques1datas = new HashMap<>();
            graphiques1datas = graphiqueController.getGraphique1Datas();

            serieGraph1 = new XYChart.Series<>();

            for (Integer value : graphiques1datas.keySet() ){
                serieGraph1.getData().add(new XYChart.Data<>(value, graphiques1datas.get(value)));
            }

            graph1.getData().add(serieGraph1);

        }
        else if(event.getSource() == btnGraph2)
        {
            lblTitre.setText("Devoir : Graphique n°2");
            apGraph2.toFront();

            // A vous de jouer

            graphiques2datas = graphiqueController.getGraphique2Datas();

            for (String value : graphiques2datas.keySet()){
                serieGraph2 = new XYChart.Series<>();
                serieGraph2.setName(value);
                for (int i = 0; i<graphiques2datas.get(value).size();i+=2)
                {
                    serieGraph2.getData().add(new XYChart.Data<>(graphiques2datas.get(value).get(i), Double.parseDouble(graphiques2datas.get(value).get(i+1))));
                }
                graph2.getData().add(serieGraph2);

            }

        }
        else
        {
            lblTitre.setText("Devoir : Graphique n°3");
            apGraph3.toFront();

            // A vous de jouer

            ObservableList<PieChart.Data> datasGraph2 = FXCollections.observableArrayList();
            graphiques3datas =  graphiqueController.getGraphique3Datas();

            for (String value : graphiques3datas.keySet())
            {
                datasGraph2.add(new PieChart.Data(value,graphiques3datas.get(value) ));
            }
            graph3.setData(datasGraph2);


        }
    }
}