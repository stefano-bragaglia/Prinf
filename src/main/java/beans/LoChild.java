/**
 * 
 */
package beans;

/**
 * @author stefano
 * 
 */
public class LoChild {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LoChild [edge=" + edge + ", next=" + next + "]";
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
		result = prime * result + ((edge == null) ? 0 : edge.hashCode());
		result = prime * result + ((next == null) ? 0 : next.hashCode());
		return result;
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
		LoChild other = (LoChild) obj;
		if (edge == null) {
			if (other.edge != null)
				return false;
		} else if (!edge.equals(other.edge))
			return false;
		if (next == null) {
			if (other.next != null)
				return false;
		} else if (!next.equals(other.next))
			return false;
		return true;
	}

	/**
	 * The edge.
	 */
	private Edge edge;

	/**
	 * The next.
	 */
	private Bind next;

	/**
	 * @param edge
	 *            the edge
	 * @param next
	 *            the next
	 */
	public LoChild(Edge edge, Edge next) {
		if (edge == null)
			throw new IllegalArgumentException("Illegal 'edge' argument in LoChild(Edge, Edge): " + edge);
		if (next == null)
			throw new IllegalArgumentException("Illegal 'next' argument in LoChild(Edge, Edge): " + next);
		this.edge = edge;
		this.next = next;
		assert invariant() : "Illegal state in LoChild(Edge, Edge)";
	}

	/**
	 * @param edge
	 *            the edge
	 * @param next
	 *            the next
	 */
	public LoChild(Edge edge, Terminal next) {
		if (edge == null)
			throw new IllegalArgumentException("Illegal 'edge' argument in LoChild(Edge, Terminal): " + edge);
		if (next == null)
			throw new IllegalArgumentException("Illegal 'next' argument in LoChild(Edge, Terminal): " + next);
		this.edge = edge;
		this.next = next;
		assert invariant() : "Illegal state in LoChild(Edge, Terminal)";
	}

	/**
	 * @return the edge
	 */
	public Edge getEdge() {
		assert invariant() : "Illegal state in LoChild.getEdge()";
		return edge;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		long result = edge.getId();
		assert invariant() : "Illegal state in LoChild.getId()";
		return result;
	}

	/**
	 * @return the node
	 */
	public Node getNode() {
		Node result = edge.getTail();
		assert invariant() : "Illegal state in LoChild.getNode()";
		return result;
	}

	/**
	 * @return the next
	 */
	public Bind getNext() {
		assert invariant() : "Illegal state in LoChild.getNext()";
		return next;
	}

	/**
	 * @return <code>true</code> if consistent, <code>false</code> otherwise
	 */
	private boolean invariant() {
		return (edge != null && next != null);
	}

}
