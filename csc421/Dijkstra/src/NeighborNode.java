public class NeighborNode {

	String vertexId;

	int distance;
	
	public NeighborNode(String vertexId, int distance) {
		this.vertexId = vertexId;
		this.distance = distance;
	}

	public String getVertexId() {
		return vertexId;
	}

	public int getDistance() {
		return distance;
	}
	
}
