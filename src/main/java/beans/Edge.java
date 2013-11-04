/**
 * 
 */
package beans;

/**
 * @author stefano
 * 
 */
public class Edge {

	/**
	 * The size.
	 */
	private static long size = 0;

	/**
	 * @return the size
	 */
	public static long size() {
		return size;
	}

	/**
	 * The head.
	 */
	private Node head;

	/**
	 * The id.
	 */
	private long id;

	/**
	 * The prob.
	 */
	private double prob;

	/**
	 * The tail.
	 */
	private Node tail;

	/**
	 * @param tail
	 *            the tail
	 * @param head
	 *            the head
	 * @param prob
	 *            the prob
	 */
	public Edge(Node tail, Node head, double prob) {
		if (tail == null)
			throw new IllegalArgumentException(
					"Illegal 'tail' argument in Edge(Node, Node, double): "
							+ tail);
		if (head == null)
			throw new IllegalArgumentException(
					"Illegal 'head' argument in Edge(Node, Node, double): "
							+ head);
		if (prob <= 0.0 || prob > 1.0)
			throw new IllegalArgumentException(
					"Illegal 'prob' argument in Edge(Node, Node, double): "
							+ prob);
		this.head = head;
		this.id = size++;
		this.prob = prob;
		this.tail = tail;
		assert invariant() : "Illegal state in Edge(Node, Node, double)";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (head == null) {
			if (other.head != null)
				return false;
		} else if (!head.equals(other.head))
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(prob) != Double
				.doubleToLongBits(other.prob))
			return false;
		if (tail == null) {
			if (other.tail != null)
				return false;
		} else if (!tail.equals(other.tail))
			return false;
		return true;
	}

	/**
	 * @return the head
	 */
	public Node getHead() {
		assert invariant() : "Illegal state in Edge.getHead()";
		return head;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		assert invariant() : "Illegal state in Edge.getId()";
		return id;
	}

	/**
	 * @return the prob
	 */
	public double getProb() {
		assert invariant() : "Illegal state in Edge.getProb()";
		return prob;
	}

	/**
	 * @return the tail
	 */
	public Node getTail() {
		assert invariant() : "Illegal state in Edge.getTail()";
		return tail;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((head == null) ? 0 : head.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		long temp;
		temp = Double.doubleToLongBits(prob);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((tail == null) ? 0 : tail.hashCode());
		return result;
	}

	/**
	 * @return <code>true</code> if consistent, <code>false</code> otherwise
	 */
	private boolean invariant() {
		return (head != null && id >= 0 && 0.0 <= prob && prob <= 1.0 && tail != null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String result = "Edge [id=" + id + ", head=" + head + ", tail=" + tail
				+ ", prob=" + prob + "]";
		assert invariant() : "Illegal state in Edge.toString()";
		return result;
	}

}
