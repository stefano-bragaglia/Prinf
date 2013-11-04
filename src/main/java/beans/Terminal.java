/**
 * 
 */
package beans;

/**
 * @author stefano
 * 
 */
public class Terminal implements Bind {

	public static final Terminal ONE = new Terminal(true);

	public static final Terminal ZERO = new Terminal(false);

	/**
	 * The kind.
	 */
	private boolean kind;

	/**
	 * @param kind
	 *            the kind
	 */
	private Terminal(boolean kind) {
		this.kind = kind;
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
		Terminal other = (Terminal) obj;
		if (kind != other.kind)
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see beans.Bind#getId()
	 */
	@Override
	public long getId() {
		return Edge.size();
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
		result = prime * result + (kind ? 1231 : 1237);
		return result;
	}

	/**
	 * @return
	 */
	public boolean isNegative() {
		return !kind;
	}

	/**
	 * @return
	 */
	public boolean isPositive() {
		return kind;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Terminal [id=" + Edge.size() + ", kind=" + (kind ? "ONE" : "ZERO") + "]";
	}

}
