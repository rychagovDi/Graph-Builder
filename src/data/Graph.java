package data;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

public class Graph {

  private ArrayList<Vertex> vertices;
  private ArrayList<Edge> edges;
  private Vertex currentStart;
  private Vertex currentEnd;
  private Group drawFieldGroup;

  public Graph(Group group) {
    vertices = new ArrayList<>();
    edges = new ArrayList<>();
    drawFieldGroup = group;
  }

  // Сохраняет новое значение начала графа
  public void setStart(Vertex vertex) {
    if (currentStart != null) {
      currentStart.setFill(Paint.valueOf(Color.BLACK.toString()));
      currentStart.setStart(false);
    }

    // Если новое начало графа совпадает с текущим концом - очищает значение конца
    if (vertex.equals(currentEnd)){
      currentEnd = null;
    }

    vertex.setFill(Paint.valueOf(Color.GREEN.toString()));
    vertex.setStart(true);
    currentStart = vertex;
  }

  // Сохраняет новое значение конца графа
  public void setEnd(Vertex vertex) {
    if (currentEnd != null) {
      currentEnd.setFill(Paint.valueOf(Color.BLACK.toString()));
      currentEnd.setEnd(false);
    }

    // Если новый конец графа совпадает с текущим началом - очищает значение начала
    if (vertex.equals(currentStart)){
      currentStart = null;
    }

    vertex.setFill(Paint.valueOf(Color.RED.toString()));
    vertex.setEnd(true);
    currentEnd = vertex;
  }

  // Добавляет новую вершину в граф
  public void addVertex(Vertex vertex) {
    vertices.add(vertex);
    drawFieldGroup.getChildren().add(vertex);
  }

  // Удаляет вершину из графа, а так же связанные с ней ребра
  public void removeVertex(Vertex vertex) {
    if (vertex.equals(currentStart)) {
      currentStart = null;
    }

    if (vertex.equals(currentEnd)) {
      currentEnd = null;
    }

    vertices.remove(vertex);
    drawFieldGroup.getChildren().remove(vertex);

    ArrayList<Edge> edgesForRemove = new ArrayList<>();

    for(Edge edge : edges) {
      if (vertex.equals(edge.getFirstVertex()) || vertex.equals(edge.getSecondVertex())) {
        edgesForRemove.add(edge);
      }
    }

    removeEdges(edgesForRemove);
  }

  // Добавляет новое ребро в граф
  public void addEdge(Edge edge) {
    edges.add(edge);
    drawFieldGroup.getChildren().add(edge);
  }

  // Удаляет вершины
  private void removeEdges(ArrayList<Edge> edgesForRemove) {
    edges.removeAll(edgesForRemove);
    drawFieldGroup.getChildren().removeAll(edgesForRemove);
  }
}
