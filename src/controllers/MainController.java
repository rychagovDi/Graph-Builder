package controllers;

import data.Edge;
import data.Graph;
import data.Vertex;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class MainController {

  public TextField weightField;
  /*
        main.fxml
     */
  @FXML
  private RadioButton leftMenuVertex; // Кнопки бокового меню
  @FXML
  private RadioButton leftMenuEdge;
  @FXML
  private RadioButton leftMenuStart;
  @FXML
  private RadioButton leftMenuEnd;
  @FXML
  private RadioButton leftMenuRemove;
  @FXML
  private RadioButton leftMenuAutoMode;
  @FXML
  private RadioButton leftMenuHandMode;
  @FXML
  private SubScene drawField; // Дополнительня сцена, внутри которой размещается группа drawFieldGroup для графа

  private Graph graph;

  private Vertex tempVertex;
  private boolean isEdgeStarted;

  public void initialize() {
    initLeftMenu();
    initDrawField();
  }

  // Инициализирует боковое меню
  private void initLeftMenu(){
    ToggleGroup clickGroup = new ToggleGroup(); // Объединяет кнопки в группу

    leftMenuVertex.setToggleGroup(clickGroup);
    leftMenuVertex.setTooltip(new Tooltip("Режим рисования вершин")); // Подсказка при наведении курсора
    leftMenuVertex.setCursor(Cursor.HAND);                                 // Вид курсора
    leftMenuVertex.setSelected(true);

    leftMenuEdge.setToggleGroup(clickGroup);
    leftMenuEdge.setTooltip(new Tooltip("Режим рисования рёбер"));
    leftMenuEdge.setCursor(Cursor.HAND);

    leftMenuStart.setToggleGroup(clickGroup);
    leftMenuStart.setTooltip(new Tooltip("Режим выбора начала"));
    leftMenuStart.setCursor(Cursor.HAND);

    leftMenuEnd.setToggleGroup(clickGroup);
    leftMenuEnd.setTooltip(new Tooltip("Режим выбора конца"));
    leftMenuEnd.setCursor(Cursor.HAND);

    leftMenuRemove.setToggleGroup(clickGroup);
    leftMenuRemove.setTooltip(new Tooltip("Режим удаления"));
    leftMenuRemove.setCursor(Cursor.HAND);

    ToggleGroup weightGroup = new ToggleGroup();

    leftMenuAutoMode.setToggleGroup(weightGroup);
    leftMenuAutoMode.setTooltip(new Tooltip("Автоматическое задание веса ребру"));
    leftMenuAutoMode.setCursor(Cursor.HAND);
    leftMenuAutoMode.setSelected(true);

    leftMenuHandMode.setToggleGroup(weightGroup);
    leftMenuHandMode.setTooltip(new Tooltip("Ручное задание веса ребру"));
    leftMenuHandMode.setCursor(Cursor.HAND);
  }

  // Инициализирует поле для рисования графа
  private void initDrawField() {

    Group drawFieldGroup = new Group();
    graph = new Graph(drawFieldGroup);
    drawField.setRoot(drawFieldGroup);
    drawField.setFill(Paint.valueOf("white"));

    drawField.setOnMouseClicked(new SceneClickHandler());
  }

  @FXML
  private void clearGraph() {
    graph.clearGraph();
  }

  @FXML
  private void openMatrixWindow() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("../scheme/matrix_preview.fxml"));
      Parent root = loader.load();

      MatrixPreviewController matrixPreviewController = loader.getController();
      matrixPreviewController.setGraph(graph);

      Stage stage = new Stage();
      stage.setTitle("Matrix preview");
      stage.setScene(new Scene(root, 450, 450));
      stage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private class SceneClickHandler implements EventHandler<MouseEvent> {

    @Override
    public void handle(MouseEvent event) {

      // Рассчет координат клика внутри области для рисования
      int clickX = (int) event.getSceneX() - (int) drawField.getLayoutX();
      int clickY = (int) event.getSceneY() - (int) drawField.getLayoutY();

      // Создается объект Vertex и помещается на экран
      if (leftMenuVertex.isSelected()) {
        Vertex vertex = new Vertex(clickX, clickY, 7);

        // Для каждой вершины создается слушатель, который реагирует на нажатие по этой вершине
        vertex.setOnMouseClicked(new VertexClickHandler(vertex));
        graph.addVertex(vertex);
      }

    }
  }

  private class VertexClickHandler implements EventHandler<MouseEvent> {

    private Vertex vertex;

    VertexClickHandler(Vertex vertex) {
      this.vertex = vertex;
    }

    @Override
    public void handle(MouseEvent event) {

      // Если выбран пункт меню "Start", выделяет стартовую вершину графа
      if (leftMenuStart.isSelected()) {
        graph.setStart(vertex);
        return;
      }

      // Если выбран пункт меню "End", выделяет конечную вершину графа
      if (leftMenuEnd.isSelected()) {
        graph.setEnd(vertex);
        return;
      }

      // Если выбран пункт меню "Remove", удаляет вершину графа
      if (leftMenuRemove.isSelected()) {
        removeVertex();
        return;
      }

      // Если выбран пункт меню "Edge", производит действия по созданию ребра
      if (leftMenuEdge.isSelected()) {
        addEdge();
      }

    }

    private void removeVertex() {
      if (tempVertex != null && tempVertex.equals(vertex)) { // Если удаляемая вершина участвует в создании ребра, отменяет режим создания ребра
        tempVertex = null;
        isEdgeStarted = false;
      }
      graph.removeVertex(vertex);
    }

    private void addEdge() {
      // Если еще не выбрана первая вершина для ребра, выбирает её
      if (!isEdgeStarted) {
        tempVertex = vertex;
        tempVertex.setStrokeWidth(3); // Выделение контура вершины
        isEdgeStarted = true;

        // Если выбраны 2 вершины для ребра, создает ребро и добавляет его
      } else {
        if (tempVertex != vertex) {
          if (leftMenuAutoMode.isSelected()) {
            addAutoEdge();
          }

          if (leftMenuHandMode.isSelected()) {
            openWeightDialog();
          }
        }
        tempVertex.setStrokeWidth(0);
        isEdgeStarted = false;
      }
    }

    private void addAutoEdge() {
      Edge edge = new Edge(tempVertex, vertex);

      Random random = new Random();
      edge.setWeight(random.nextInt(50) + 1);

      graph.addEdgeWithCheck(edge);
      tempVertex.toFront();
      vertex.toFront();
    }

    private void openWeightDialog() {
      try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../scheme/dialog.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        Stage dialog = new Stage();
        dialog.setScene(scene);
        dialog.setTitle("Choose weight");
        dialog.setResizable(false);
        dialog.show();

        DialogController dialogController = loader.getController();
        dialogController.setData(graph, tempVertex, vertex, dialog);

      } catch (IOException e) {
        e.printStackTrace();
      }
    }


    private void apply() {

    }
  }
}
