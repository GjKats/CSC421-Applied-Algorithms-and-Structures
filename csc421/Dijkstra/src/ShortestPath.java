import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShortestPath {

	Map<String,VertexNode> verticesMap;

	public ShortestPath(String inputFileName) {
		verticesMap = readVertices(inputFileName);
	}

	private static Map<String,VertexNode> readVertices(String inputFileName) {
		Map<String,VertexNode> vertices = new HashMap<String,VertexNode>();
		BufferedReader reader = null;
		try {

			reader = new BufferedReader(new FileReader(inputFileName));

			reader.readLine();

			String row;
			while ((row = reader.readLine()) != null && row.trim().length() != 0) {

				String[] split = row.trim().split("\\s+");
				String vertexId = split[0];
				String neighborId = split[1];

				VertexNode vertex = vertices.get(vertexId);
				if (vertex == null) {
					vertex = new VertexNode(vertexId);
					vertices.put(vertexId, vertex);
				}
				vertex.getNeighborNodes().add(new NeighborNode(neighborId, Integer.parseInt(split[2])));

				VertexNode neighborVertex = vertices.get(neighborId);
				if (neighborVertex == null) {
					neighborVertex = new VertexNode(neighborId);
					vertices.put(neighborId, neighborVertex);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(inputFileName);
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			System.out.println(inputFileName);
			e.printStackTrace();
			return null;
		} finally {
			if (reader != null) {
				try { reader.close(); } catch (IOException e) {}
			}
		}
		
		return vertices;
	}

	public ShortestPathResult compute() {
		VertexNode startVertex = verticesMap.get("A");
		startVertex.setDistance(0);

		MinHeap minHeap = new MinHeap();
		for (Map.Entry<String,VertexNode> entry : verticesMap.entrySet()) {
			minHeap.insert(entry.getValue());
		}
		while (!minHeap.isEmpty()) {
			VertexNode currentVertex = minHeap.extract();
			for (NeighborNode neighbor : currentVertex.getNeighborNodes()) {
				VertexNode neighborVertex = verticesMap.get(neighbor.getVertexId());
				if (neighborVertex.getDistance() > currentVertex.getDistance() + neighbor.getDistance()) {
					neighborVertex.setParentNode(currentVertex);
					neighborVertex.setDistance(currentVertex.getDistance() + neighbor.getDistance());
					minHeap.update(neighborVertex);
				}
			}
		}

		VertexNode endVertex = verticesMap.get("B");

		return new ShortestPathResult(endVertex);
	}

	public static void main(String[] args) {
		String inputFileName = null;

		switch(args.length) {
		case 1:
			inputFileName = args[0];
			break;
		default:
			System.out.println("Command line error. Usage:\njava ShortestPath <input filename>");
			System.exit(1);
		}

		ShortestPath shortestPath = new ShortestPath(inputFileName);
		ShortestPathResult result = shortestPath.compute();
		
		System.out.println(result);
	}

	private static class ShortestPathResult {
    int totalDistance;
		List<String> vertexIds = new ArrayList<String>();

		public ShortestPathResult(VertexNode endVertex) {
			this.totalDistance = endVertex.getDistance();
			this.vertexIds = reversePath(endVertex);
		}

		private static List<String> reversePath(VertexNode vertex) {
			if (vertex == null) {
				return new ArrayList<String>();
			}
			List<String> result = reversePath(vertex.getParentNode());
			result.add(vertex.getVertexId());
			return result;
		}

		@Override
		public String toString() {
			StringBuilder result = new StringBuilder();
			result.append(totalDistance).append("\n");
			for (String vertexId : vertexIds) {
				result.append(vertexId).append(" ");
			}
			
			return result.toString();
		}
	}
	
}

