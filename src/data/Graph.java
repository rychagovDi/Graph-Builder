package data;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.min;

public class Graph {

  private final int INF = Integer.MAX_VALUE / 2;

  private ArrayList<Vertex> vertices;
  private ArrayList<Edge> edges;
  private Vertex currentStart;
  private Vertex currentEnd;
  private Group drawFieldGroup;
  private ArrayList<Integer> way;

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

    if (currentEnd != null) {
      for (int vert : getWay()){
        System.out.print(" > " + (vert + 1));
      }
      System.out.println();
    }
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

    if (currentStart != null) {
      for (int vert : getWay()){
        System.out.print(" > " + (vert + 1));
      }
      System.out.println();
    }
  }

  // Добавляет новую вершину в граф
  public void addVertex(Vertex vertex) {
    vertex.set_id(vertices.size());
    vertices.add(vertex);
    drawFieldGroup.getChildren().add(vertex);
    drawFieldGroup.getChildren().add(vertex.get_idField());
  }

  // Удаляет вершину из графа, а так же связанные с ней ребра
  public void removeVertex(Vertex vertex) {
    if (vertex.isStart()) {
      currentStart = null;
    }

    if (vertex.isEnd()) {
      currentEnd = null;
    }

    vertices.remove(vertex);
    removeEdges(vertex);
    drawFieldGroup.getChildren().remove(vertex);
    drawFieldGroup.getChildren().remove(vertex.get_idField());

    recalculateVertexId();
  }

  // Добавляет новое ребро в граф
  public void addEdge(Edge edge) {
    Random random = new Random();
    edge.setWeight(random.nextInt(50) + 1);
    edges.add(edge);
    drawFieldGroup.getChildren().add(edge);
    drawFieldGroup.getChildren().add(edge.getWeightField());
  }

  // Добавлет новое ребро в граф с проверкой на существование этого ребра. Если ребро существует - не создает новое.
  public void addEdgeWithCheck(Edge edge) {

    Vertex checkingVertex1 = edge.getFirstVertex();
    Vertex checkingVertex2 = edge.getSecondVertex();

    for (Edge existingEdge: edges) {
      // Если между двумя вершинами уже находится ребро - не создает новое
      if (checkingVertex1.equals(existingEdge.getFirstVertex()) && checkingVertex2.equals(existingEdge.getSecondVertex())
              || checkingVertex1.equals(existingEdge.getSecondVertex()) & checkingVertex2.equals(existingEdge.getFirstVertex())) {
        return;
      }
    }

    addEdge(edge);
  }

  // Строит и возвращает матрицу смежности графа.
  public int[][] getMatrix() {
    int[][] matrix = new int[vertices.size()][vertices.size()];
    for (Edge edge : edges) {
      matrix[edge.getFirstVertex().get_id()][edge.getSecondVertex().get_id()] = edge.getWeight();
      matrix[edge.getSecondVertex().get_id()][edge.getFirstVertex().get_id()] = edge.getWeight();
    }

    return matrix;
  }

  public int[][] findRoute() {
    // TODO Рефакторинг
    int vNum = vertices.size();
    int[][] matrix = getMatrix();
    int[][] dist = new int[vNum][vNum]; // dist[i][j] = минимальное_расстояние(i, j)

    for (int i = 0; i < vNum; i++) System.arraycopy(matrix[i], 0, dist[i], 0, vNum);

    for (int i = 0; i < dist.length; i++) {
      for (int j = 0; j < dist[i].length; j++) {
        if (dist[i][j] == 0) {
          if (i != j) {
            dist[i][j] = INF;
          }
        }
      }
    }
    for (int k = 0; k < vNum; k++) {
      for (int i = 0; i < vNum; i++) {
        for (int j = 0; j < vNum; j++) {
          dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);
        }
      }
    }

//    for (int k = 0; k < vNum; k++) {
//      for (int i = 0; i < vNum; i++) {
//        System.out.print(" " + dist[k][i]);
//      }
//      System.out.println();
//    }

    return dist;
  }

  public ArrayList<Integer> getWay(int start, int end) {
    way.add(end);

    if (start != end) {
      int[][] matrix = getMatrix();
      int[][] route = findRoute();
      int min = route[end][start];

      if (min == INF){
        return way;
      }

      int minIndex = end;

      for (int i = 0; i < matrix.length; i++) {
        if (matrix[end][i] != 0) {
          if ((route[end][i] + route[i][start]) <= min) {
            min = route[end][i] + route[i][start];
            minIndex = i;
          }
        }
      }

      getWay(start, minIndex);
    }

    return way;
  }

  public ArrayList<Integer> getWay() {
    way = new ArrayList<>();
    return getWay(currentStart.get_id(), currentEnd.get_id());
  }

  // Удаляет ребра, свзянные с вершиной vertex
  private void removeEdges(Vertex vertex) {
    ArrayList<Edge> edgesForRemove = new ArrayList<>();
    ArrayList<Text> textForRemove = new ArrayList<>();

    for(Edge edge : edges) {
      if (vertex.equals(edge.getFirstVertex()) || vertex.equals(edge.getSecondVertex())) {
        edgesForRemove.add(edge);
        textForRemove.add(edge.getWeightField());
      }
    }
    
    edges.removeAll(edgesForRemove);
    drawFieldGroup.getChildren().removeAll(edgesForRemove);
    drawFieldGroup.getChildren().removeAll(textForRemove);
  }

  // Пересчитывает id у вершин
  private void recalculateVertexId() {
    for (Vertex vertex : vertices) {
      vertex.set_id(vertices.indexOf(vertex));
    }
  }
}
