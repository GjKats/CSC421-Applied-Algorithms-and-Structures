import java.util.ArrayList;
import java.util.List;

public class VertexNode {

	String vertexId;

	VertexNode parentNode;

	int distance;

	List<NeighborNode> neighborNodes;

	public VertexNode(String vertexId) {
		this.vertexId = vertexId;
		this.distance = Integer.MAX_VALUE;
		this.parentNode = null;
		this.neighborNodes = new ArrayList<NeighborNode>();
	}

	public String getVertexId() {
		return vertexId;
	}

	public VertexNode getParentNode() {
		return parentNode;
	}

	public void setParentNode(VertexNode parentNode) {
		this.parentNode = parentNode;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public List<NeighborNode> getNeighborNodes() {
		return neighborNodes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((vertexId == null) ? 0 : vertexId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VertexNode other = (VertexNode) obj;
		if (vertexId == null) {
			if (other.vertexId != null)
				return false;
		} else if (!vertexId.equals(other.vertexId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "VertexNode [vertexId=" + vertexId + ", distance=" + distance + "]";
	}
	
}
