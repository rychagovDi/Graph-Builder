package data;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class Edge extends Line {

  private int weight;
  private Vertex firstVertex;
  private Vertex secondVertex;
  private Text weightField;

  public Edge() {

  }

  public Edge(Vertex firstVertex, Vertex secondVertex) {
    setFirstVertex(firstVertex);
    setSecondVertex(secondVertex);

    weightField = new Text(
            Math.abs((firstVertex.getCenterX() + secondVertex.getCenterX())/2) - 10,
            Math.abs((firstVertex.getCenterY() + secondVertex.getCenterY())/2) - 3,
            "weight");
    weightField.setFill(Paint.valueOf(Color.STEELBLUE.toString()));
  }

  public void setFirstVertex(Vertex firstVertex) {
    this.firstVertex = firstVertex;
    setStartX(firstVertex.getCenterX());
    setStartY(firstVertex.getCenterY());
  }
  public Vertex getFirstVertex() {
    return firstVertex;
  }

  public void setSecondVertex(Vertex secondVertex) {
    this.secondVertex = secondVertex;
    setEndX(secondVertex.getCenterX());
    setEndY(secondVertex.getCenterY());
  }
  public Vertex getSecondVertex() {
    return secondVertex;
  }

  public Text getWeightField() {
    return weightField;
  }

  public void setWeight(int weight) {
    this.weight = weight;
    weightField.setText("" + weight);
  }
  public int getWeight() {
    return weight;
  }
}
