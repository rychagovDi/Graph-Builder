package controllers;

import data.Graph;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class MatrixPreviewController {

  @FXML
  private GridPane matrix;

  private Graph graph;

  public void initialize() {
    initMatrix();
  }

  private void initMatrix() {
    matrix.setGridLinesVisible(true);
  }

  void setGraph(Graph graph) {
    this.graph = graph;
    drawMatrix();
  }

  // Заполнение GridPane матрицой смежности
  private void drawMatrix() {
    int[][] m = graph.getMatrix();
    matrix.add(new Text(" "), 0,0);

    for (int i = 0; i < m.length; i++) {

      matrix.add(new Text(" " + (i + 1) + " "),0, i + 1);
      matrix.add(new Text(" " + (i + 1) + " "), i + 1, 0);

      for (int j = 0; j < m.length; j++) {
        matrix.add(new Text(" " + m[i][j] + " "), i + 1, j + 1);
      }
    }
  }
}
