package controllers;

import data.Edge;
import data.Graph;
import data.Vertex;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.SubScene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Paint;

import java.util.logging.Logger;

public class Controller {

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
  private SubScene drawField; // Дополнительня сцена, внутри которой размещается группа drawFieldGroup для графа

  private Group drawFieldGroup;
  private Graph graph;

  private Vertex tempVertex;
  private boolean isEdgeStarted;
  private Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

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

    leftMenuRemove.setToggleGroup(toggleGroup);
    leftMenuRemove.setTooltip(new Tooltip("Режим удаления"));
    leftMenuRemove.setCursor(Cursor.HAND);
  }

  // Инициализирует поле для рисования графа
  private void initDrawField() {

    drawFieldGroup = new Group();
    drawField.setRoot(drawFieldGroup);
    drawField.setFill(Paint.valueOf("white"));

    drawField.setOnMouseClicked(event -> {

      // Рассчет координат клика внутри области для рисования
      int clickX = (int) event.getSceneX() - (int)drawField.getLayoutX();
      int clickY = (int) event.getSceneY() - (int)drawField.getLayoutY();

      // TODO Классы линий и обработка их создания

      // Создается объект Vertex и помещается на экран
      if (leftMenuVertex.isSelected()) {
        final Vertex vertex = new Vertex(clickX, clickY, 7);

        // Для каждой вершины создается слушатель, который реагирует на нажатие по этой вершине
        vertex.setOnMouseClicked(event1 -> {

          // Если выбран пункт меню "Start", выделяет стартовую вершину графа
          if (leftMenuStart.isSelected()){
            graph.setStart(vertex);
          }

          // Если выбран пункт меню "End", выделяет конечную вершину графа
          if (leftMenuEnd.isSelected()) {
            graph.setEnd(vertex);
          }

          // Если выбран пункт меню "Remove", выделяет конечную вершину графа
          if (leftMenuRemove.isSelected()) {
            graph.removeVertex(vertex);
          }

          // Если выбран пункт меню "Edge", производит действия по созданию ребра
          if (leftMenuEdge.isSelected()) {

            // Если еще не выбрана первая вершина для ребра, выбирает её
            if (!isEdgeStarted) {
              tempVertex = vertex;
              isEdgeStarted = true;

            // Если уже выбраны 2 вершины для ребра, создает ребро и добавляет его
            } else {
              graph.addEdge(new Edge(tempVertex, vertex));
              isEdgeStarted = false;
            }
          }

        });

        graph.addVertex(vertex);
      }

    });
  }
}
