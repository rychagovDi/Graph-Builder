package data;

import javafx.scene.shape.Line;

public class Edge extends Line {

  private int weight;
  private Vertex firstVertex;
  private Vertex secondVertex;

  public Edge() {

  }

  public Edge(Vertex firstVertex, Vertex secondVertex) {
    setFirstVertex(firstVertex);
    setSecondVertex(secondVertex);
  }

  public void setFirstVertex(Vertex firstVertex) {
    this.firstVertex = firstVertex;
    setStartX(firstVertex.getCenterX());
    setStartY(firstVertex.getCenterY());
  }
  public void setSecondVertex(Vertex secondVertex) {
    this.secondVertex = secondVertex;
    setEndX(secondVertex.getCenterX());
    setEndY(secondVertex.getCenterY());
  }
}
