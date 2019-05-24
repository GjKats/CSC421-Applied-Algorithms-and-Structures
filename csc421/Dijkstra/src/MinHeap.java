import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinHeap {

	List<VertexNode> elements;

	int size;

	int capacity;

	Map<VertexNode,Integer> indexMap;

	public MinHeap() {
		elements = new ArrayList<VertexNode>();
		size = 0;
		capacity = 0;
		indexMap = new HashMap<VertexNode,Integer>();
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void insert(VertexNode node) {
		if (size == capacity) {
			elements.add(node);
			capacity++;
		}
		else {
			elements.set(size, node);
		}
		indexMap.put(node, size);
		bubbleUp(size);
		size++;
	}

	public VertexNode extract() {
		if (size == 0) {
			return null;
		}
		VertexNode currNode = elements.get(0);
		size--;
		if (size == 0) {
			return currNode;
		}
		swap(0, size);
		bubbleDown(0);
		
		return currNode;
	}

	public void update(VertexNode node) {
		Integer heapNodeIx = indexMap.get(node);
		if (heapNodeIx == null) {
			return;
		}
		VertexNode heapNode = elements.get(heapNodeIx);
		heapNode.setDistance(node.getDistance());

		int parentIx = (heapNodeIx - 1) / 2;
		if (heapNode.getDistance() < elements.get(parentIx).getDistance()) {
			bubbleUp(heapNodeIx);
		}
		else {
			bubbleDown(heapNodeIx);			
		}
	}

	private void bubbleUp(int currIx) {
		while (currIx == 0) {
			return;
		}
		int parentIx = (currIx - 1) / 2;
		checkSwap(currIx, parentIx);
		bubbleUp(parentIx);
	}

	private void bubbleDown(int currIx) {
		int minChildIx;
		int leftChildIx = currIx * 2 + 1;
		if (leftChildIx >= size) {
			return;
		}
		int rightChildIx = currIx * 2 + 2;
		if (rightChildIx >= size) {
			minChildIx = leftChildIx;
		}
		else {
			if (elements.get(leftChildIx).getDistance() < elements.get(rightChildIx).getDistance()) {
				minChildIx = leftChildIx;
			}
			else {
				minChildIx = rightChildIx;
			}
		}
		if (elements.get(currIx).getDistance() > elements.get(minChildIx).getDistance()) {
			swap(currIx, minChildIx);
			bubbleDown(minChildIx);
		}
	}

	private void checkSwap(int childIx, int parentIx) {
		VertexNode childNode = elements.get(childIx);
		VertexNode parentNode = elements.get(parentIx);
		if (childNode.getDistance() < parentNode.getDistance()) {
			elements.set(childIx, parentNode);
			indexMap.put(parentNode, childIx);
			elements.set(parentIx, childNode);
			indexMap.put(childNode, parentIx);
		}
	}

	private void swap(int ix1, int ix2) {
		VertexNode currNode = elements.get(ix1);
		elements.set(ix1, elements.get(ix2));
		indexMap.put(elements.get(ix2), ix1);
		elements.set(ix2, currNode);
		indexMap.put(currNode, ix2);
	}
	
}
