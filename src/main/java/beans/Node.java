/**
 * 
 */
package beans;

/**
 * @author stefano
 * 
 */
public class Node {

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
	 * The name.
	 */
	private String name;

	/**
	 * @param name
	 */
	public Node(String name) {
		if (name == null || (name = name.trim()).isEmpty())
			throw new IllegalArgumentException(
					"Illegal 'name' argument in Node(String): " + name);
		this.id = size++;
		this.name = name;
		assert invariant() : "Illegal state in Node(String)";
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
		Node other = (Node) obj;
		if (id != other.id)
			return false;
		return true;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		assert invariant() : "Illegal state in Node.getId()";
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		assert invariant() : "Illegal state in Node.getName()";
		return name;
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
		return result;
	}

	/**
	 * @return <code>true</code> if consistent, <code>false</code> otherwise
	 */
	private boolean invariant() {
		return (id >= 0 && name == null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String result = "Node [id=" + id + ", name=" + name + "]";
		assert invariant() : "Illegal state in Node.toString()";
		return result;
	}

}
