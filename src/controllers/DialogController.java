package controllers;

import data.Graph;
import data.Vertex;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DialogController {
  @FXML
  private TextField weightField;
  @FXML
  private Button applyButton;
  @FXML
  private Button cancelButton;

  private Graph graph;
  private Vertex vertex1;
  private Vertex vertex2;
  private Stage dialog;

  public void setData(Graph graph, Vertex vertex1, Vertex vertex2, Stage dialog) {
    this.graph = graph;
    this.vertex1 = vertex1;
    this.vertex2 = vertex2;
    this.dialog = dialog;
  }

  @FXML
  private void apply() {

  }

  @FXML
  private void cancel() {
    dialog.close();
  }
}
