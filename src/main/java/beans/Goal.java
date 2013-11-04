/**
 * 
 */
package beans;

/**
 * @author stefano
 * 
 */
public class Goal {

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
	 * The id.
	 */
	private long id;

	/**
	 * The source.
	 */
	private Node source;

	/**
	 * The target.
	 */
	private Node target;

	/**
	 * @param source
	 *            the source
	 * @param target
	 *            the target
	 */
	public Goal(Node source, Node target) {
		if (source == null)
			throw new IllegalArgumentException(
					"Illegal 'tail' argument in Goal(Node, Node): " + source);
		if (target == null)
			throw new IllegalArgumentException(
					"Illegal 'head' argument in Goal(Node, Node): " + target);
		this.id = size++;
		this.source = source;
		this.target = target;
		assert invariant() : "Illegal state in Goal(Node, Node)";
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
		Goal other = (Goal) obj;
		if (id != other.id)
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		if (target == null) {
			if (other.target != null)
				return false;
		} else if (!target.equals(other.target))
			return false;
		return true;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		assert invariant() : "Illegal state in Goal.getId()";
		return id;
	}

	/**
	 * @return the source
	 */
	public Node getSource() {
		assert invariant() : "Illegal state in Goal.getSource()";
		return source;
	}

	/**
	 * @return the target
	 */
	public Node getTarget() {
		assert invariant() : "Illegal state in Goal.getTarget()";
		return target;
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
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((target == null) ? 0 : target.hashCode());
		return result;
	}

	/**
	 * @return <code>true</code> if consistent, <code>false</code> otherwise
	 */
	private boolean invariant() {
		return (id >= 0 && source != null && target != null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Goal [id=" + id + ", source=" + source + ", target=" + target
				+ "]";
	}

}
