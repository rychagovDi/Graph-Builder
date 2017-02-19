package data;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class Vertex extends Circle{

  private int _id; // Номер вершины
  private boolean isStart;
  private boolean isEnd;
  private Text _idField; // Поле для отображения номера вершины

  public Vertex(double centerX, double centerY, double radius) {
    super(centerX, centerY, radius);
    setStroke(Paint.valueOf(Color.ORANGE.toString())); // Необходимо для цветного выделения контура вершины при добавлении рёбер
    setStrokeWidth(0);
    isStart = false;
    isEnd = false;
    _idField = new Text(getCenterX() + 5, getCenterY() - 5, "" + _id);
    _idField.setFill(Paint.valueOf(Color.BLUEVIOLET.toString()));
  }

  boolean isStart() {
    return isStart;
  }
  void setStart(boolean start) {
    isStart = start;
  }

  boolean isEnd() {
    return isEnd;
  }
  void setEnd(boolean end) {
    isEnd = end;
  }

  int get_id() {
    return _id;
  }
  void set_id(int id) {
    this._id = id;
    _idField.setText("" + (_id + 1));
  }

  Text get_idField() {
    return _idField;
  }
}