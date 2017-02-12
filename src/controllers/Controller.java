package controllers;

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
import javafx.scene.shape.Circle;

public class Controller {

  @FXML
  private RadioButton leftMenuDot;
  @FXML
  private RadioButton leftMenuLine;
  @FXML
  private RadioButton leftMenuStart;
  @FXML
  private RadioButton leftMenuEnd;
  @FXML
  private SubScene drawField;

  private Group drawFieldGroup;

  public void initialize() {
    initLeftMenu();
    initDrawField();
  }

  // Инициализирует боковое меню
  private void initLeftMenu(){
    ToggleGroup toggleGroup = new ToggleGroup(); // Объединяет кнопки в группу

    leftMenuDot.setToggleGroup(toggleGroup);
    leftMenuDot.setTooltip(new Tooltip("Режим рисования вершин")); // Подсказка при наведении курсора
    leftMenuDot.setCursor(Cursor.HAND);                                 // Вид курсора
    leftMenuDot.setSelected(true);

    leftMenuLine.setToggleGroup(toggleGroup);
    leftMenuLine.setTooltip(new Tooltip("Режим рисования рёбер"));
    leftMenuLine.setCursor(Cursor.HAND);

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

        // Здесь создается объект Circle и помещается на экран
        if (leftMenuDot.isSelected()) {
          drawFieldGroup.getChildren().add(new Circle(clickX, clickY, 5));
        }

      }
    });
  }
}
