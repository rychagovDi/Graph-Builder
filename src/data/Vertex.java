package data;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Vertex extends Circle{

  private int sumWeight;
  private boolean isStart;
  private boolean isEnd;

  public Vertex(double centerX, double centerY, double radius) {
    super(centerX, centerY, radius);
    sumWeight = Integer.MAX_VALUE;
    setStroke(Paint.valueOf(Color.ORANGE.toString())); // Необходимо для цветного выделения контура вершины при добавлении рёбер
    setStrokeWidth(0);
    isStart = false;
    isEnd = false;
  }

  public boolean isStart() {
    return isStart;
  }
  public void setStart(boolean start) {
    isStart = start;
  }

  public boolean isEnd() {
    return isEnd;
  }
  public void setEnd(boolean end) {
    isEnd = end;
  }

  public int getSumWeight() {
    return sumWeight;
  }
  public void setSumWeight(int sumWeight) {
    this.sumWeight = sumWeight;
  }
}