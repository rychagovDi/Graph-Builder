package data;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

public class Graph {

  private ArrayList<Vertex> vertices;
  private Vertex currentStart;
  private Vertex currentEnd;

  public Graph() {
    vertices = new ArrayList<>();
    currentStart = null;
    currentEnd = null;
  }

  public void setStart(Vertex vertex) {
    if (currentStart != null) {
      currentStart.setFill(Paint.valueOf(Color.BLACK.toString()));
    }

    vertex.setFill(Paint.valueOf(Color.GREEN.toString()));
    currentStart = vertex;
  }
}
