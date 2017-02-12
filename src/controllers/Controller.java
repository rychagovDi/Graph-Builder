package controllers;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;

public class Controller {

  @FXML
  private RadioButton leftMenuDot;
  @FXML
  private RadioButton leftMenuLine;
  @FXML
  private RadioButton leftMenuStart;
  @FXML
  private RadioButton leftMenuEnd;

  public void initialize() {
    initToggle();
  }

  private void initToggle(){
    ToggleGroup toggleGroup = new ToggleGroup(); // Объединяет кнопки в группу

    leftMenuDot.setToggleGroup(toggleGroup);
    leftMenuDot.setTooltip(new Tooltip("Режим рисования вершин")); // Подсказка при наведении курсора
    leftMenuDot.setCursor(Cursor.HAND);                                 // Вид курсора

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
}
