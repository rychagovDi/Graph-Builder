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

  public Edge(Vertex firstVertex, Vertex secondVertex) {
    setFirstVertex(firstVertex);
    setSecondVertex(secondVertex);
    setStrokeWidth(1);

    weightField = new Text(
            Math.abs((firstVertex.getCenterX() + secondVertex.getCenterX())/2) - 10,
            Math.abs((firstVertex.getCenterY() + secondVertex.getCenterY())/2) - 3,
            "weight");
    weightField.setFill(Paint.valueOf(Color.STEELBLUE.toString()));
  }

  private void setFirstVertex(Vertex firstVertex) {
    this.firstVertex = firstVertex;
    setStartX(firstVertex.getCenterX());
    setStartY(firstVertex.getCenterY());
  }
  Vertex getFirstVertex() {
    return firstVertex;
  }

  private void setSecondVertex(Vertex secondVertex) {
    this.secondVertex = secondVertex;
    setEndX(secondVertex.getCenterX());
    setEndY(secondVertex.getCenterY());
  }
  Vertex getSecondVertex() {
    return secondVertex;
  }

  Text getWeightField() {
    return weightField;
  }

  public void setWeight(int weight) {
    this.weight = weight;
    weightField.setText("" + weight);
  }
  int getWeight() {
    return weight;
  }
}
