/**
 * 
 */
package core;

import java.util.Hashtable;
import java.util.Map;

import beans.Edge;
import beans.Goal;
import beans.Node;

/**
 * @author stefano
 * 
 */
public class Factory {

	private static Map<String, Node> nodes = new Hashtable<>();

	public static Goal createGoal(String source, String target) {
		if (source == null || (source = source.trim()).isEmpty())
			throw new IllegalArgumentException(
					"Illegal 'source' argument in Factory.createGoal(String, String): "
							+ source);
		if (target == null || (target = target.trim()).isEmpty())
			throw new IllegalArgumentException(
					"Illegal 'target' argument in Factory.createGoal(String, String): "
							+ target);
		Node sourceNode = Factory.createNode(source);
		Node targetNode = Factory.createNode(target);
		return new Goal(sourceNode, targetNode);
	}

	public static Edge createEdge(String tail, String head, double prob) {
		if (tail == null || (tail = tail.trim()).isEmpty())
			throw new IllegalArgumentException(
					"Illegal 'tail' argument in Factory.createEdge(String, String, double): "
							+ tail);
		if (head == null || (head = head.trim()).isEmpty())
			throw new IllegalArgumentException(
					"Illegal 'head' argument in Factory.createEdge(String, String, double): "
							+ head);
		if (prob < 0.0 || prob > 1.0)
			throw new IllegalArgumentException(
					"Illegal 'prob' argument in Factory.createEdge(String, String, double): "
							+ prob);
		Node tailNode = Factory.createNode(tail);
		Node headNode = Factory.createNode(head);
		return new Edge(tailNode, headNode, prob);
	}

	public static Node createNode(String name) {
		if (name == null || (name = name.trim()).isEmpty())
			throw new IllegalArgumentException(
					"Illegal 'name' argument in Factory.createNode(String): "
							+ name);
		Node node = nodes.get(name);
		if (node == null) {
			node = new Node(name);
			nodes.put(name, node);
		}
		return node;
	}

}
