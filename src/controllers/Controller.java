package controllers;

import data.Graph;
import data.Vertex;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.SubScene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;

public class Controller {

  @FXML
  private RadioButton leftMenuVertex;
  @FXML
  private RadioButton leftMenuEdge;
  @FXML
  private RadioButton leftMenuStart;
  @FXML
  private RadioButton leftMenuEnd;
  @FXML
  private SubScene drawField;

  private Group drawFieldGroup;
  private Graph graph;

  public void initialize() {
    initLeftMenu();
    initDrawField();
    graph = new Graph(drawFieldGroup);
  }

  // Инициализирует боковое меню
  private void initLeftMenu(){
    ToggleGroup toggleGroup = new ToggleGroup(); // Объединяет кнопки в группу

    leftMenuVertex.setToggleGroup(toggleGroup);
    leftMenuVertex.setTooltip(new Tooltip("Режим рисования вершин")); // Подсказка при наведении курсора
    leftMenuVertex.setCursor(Cursor.HAND);                                 // Вид курсора
    leftMenuVertex.setSelected(true);

    leftMenuEdge.setToggleGroup(toggleGroup);
    leftMenuEdge.setTooltip(new Tooltip("Режим рисования рёбер"));
    leftMenuEdge.setCursor(Cursor.HAND);

    leftMenuStart.setToggleGroup(toggleGroup);
    leftMenuStart.setTooltip(new Tooltip("Режим выбора начала"));
    leftMenuStart.setCursor(Cursor.HAND);

    leftMenuEnd.setToggleGroup(toggleGroup);
    leftMenuEnd.setTooltip(new Tooltip("Режим выбора конца"));
    leftMenuEnd.setCursor(Cursor.HAND);
  }

  private void initDrawField() {

    drawFieldGroup = new Group();
    drawField.setRoot(drawFieldGroup);
    drawField.setFill(Paint.valueOf("white"));

    drawField.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {

        // Рассчет координат клика внутри области для рисования
        int clickX = (int) event.getSceneX() - (int)drawField.getLayoutX();
        int clickY = (int) event.getSceneY() - (int)drawField.getLayoutY();

        // TODO Классы точек, линий и обработка их создания

        // Создается объект Vertex и помещается на экран
        if (leftMenuVertex.isSelected()) {
          Vertex vertex = new Vertex(clickX, clickY, 7);

          // Для каждой вершины создается слушатель, который реагирует на нажатие по этой вершине
          vertex.setOnMouseClicked(event1 -> {
            if (leftMenuStart.isSelected()){
              graph.setStart(vertex);
            }

            if (leftMenuEnd.isSelected()){
              graph.setEnd(vertex);
            }
          });

          graph.addVertex(vertex);
        }

      }
    });
  }
}
