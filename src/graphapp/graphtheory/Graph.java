package graphapp.graphtheory;

import java.util.*;

public class Graph {
    private int idTotal = 1;
    private final Set<Edge> edges;
    private final Map<String, Vertex> vertices;
    private String name;
    private String URI;
    private boolean isWeighted;
    private boolean isDirected;

    public Graph() {
        edges = new HashSet<>();
        vertices = new HashMap<>();
        name = "Untitled";
    }

    public Set<Edge> getEdges() {
        return edges;
    }

    public Map<String, Vertex> getVertices() {
        return vertices;
    }

    public Vertex addVertex(double x, double y) {
        String id = nextId();
        Vertex v = new Vertex(x, y, id);
        vertices.put(id, v);

        return v;
    }

    private String nextId() {
        return "" + idTotal++;
    }

    public int getIdTotal() {
        return idTotal;
    }

    public boolean hasEdgeOn(Vertex a, Vertex b)
    {
        for(Edge e : a.getEdges())
        {
            if(e.hasVertex(a) && e.hasVertex(b))
                return true;
        }
        return false;
    }

    public Edge addEdgeOn(Vertex a, Vertex b, int weight) {
        Edge e = new Edge(a, b, weight);
        a.getEdges().add(e);
        b.getEdges().add(e);
        edges.add(e);

        return e;
    }

    public void removeEdge(Edge e) {
        e.getVertex1().getEdges().remove(e);
        e.getVertex2().getEdges().remove(e);
        edges.remove(e);
    }

    public void removeVertex(Vertex v) {
        for (Edge e : v.getEdges()) {
            edges.remove(e);
        }
        vertices.remove(v.getId());
    }

    /*
        Returns true if new id not taken, and id changing successful, or if swapping successful
        returns false if new id is taken, or if swap is true and there is nothing to swap with
     */

    public boolean changeEdgeWeight(Edge e, int newWeight){
        e.setWeight(newWeight);
        return true;
    }

    public boolean changeVertexId(Vertex v, String newId, boolean swap) {
        if(v.getId().equals(newId))
            return true;
        if(!vertices.containsKey(newId)) {
            if(swap)
                return false;
            vertices.remove(v.getId());
            v.setId(newId);
            vertices.put(newId, v);
            return true;
        }
        else if(swap) {
            Vertex v1 = vertices.get(newId);
            vertices.remove(v.getId());
            vertices.remove(newId);
            String temp = v.getId();
            v.setId(newId);
            v1.setId(temp);
            vertices.put(temp, v1);
            vertices.put(newId, v);
            return true;
        }
        else return false;

    }

    public void updateVertexPos(Vertex v, double x, double y) {
        v.setX(x);
        v.setY(y);
    }

    public String getName() {
        return name;
    }

    public void setName(String s) {
        name = s;
    }

    public boolean isWeighted() {
        return isWeighted;
    }

    public void setWeighted(boolean weighted) {
        isWeighted = weighted;
    }

    public String getURI() {
        return URI;
    }

    public void setURI(String URI) {
        this.URI = URI;
    }

    public void setIdTotal(int idTotal) {
        this.idTotal = idTotal;
    }

    public void setDirected(boolean directed) {
        isDirected = directed;
    }
    public boolean isDirected() {
        return isDirected;
    }
}
