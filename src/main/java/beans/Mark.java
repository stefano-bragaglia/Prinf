/**
 * 
 */
package beans;

/**
 * @author stefano
 * 
 */
public class Mark {

	private Node node;

	public Mark(Node node) {
		if (node == null)
			throw new IllegalArgumentException(
					"Illegal 'node' argument in Mark(Node): " + node);
		this.node = node;
		assert invariant() : "Illegal state in Mark(Node)";
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
		Mark other = (Mark) obj;
		if (node == null) {
			if (other.node != null)
				return false;
		} else if (!node.equals(other.node))
			return false;
		return true;
	}

	public Node getNode() {
		assert invariant() : "Illegal state in Mark.getNode()";
		return node;
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
		result = prime * result + ((node == null) ? 0 : node.hashCode());
		return result;
	}

	private boolean invariant() {
		return (node != null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Mark [node=" + node + "]";
	}

}
