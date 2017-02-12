package data;

import javafx.scene.shape.Circle;

public class Vertex extends Circle{

  private int sumWeight;
  private boolean isStart;
  private boolean isEnd;

  public Vertex(double centerX, double centerY, double radius) {
    super(centerX, centerY, radius);
  }
}