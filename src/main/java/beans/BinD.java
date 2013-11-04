/**
 * 
 */
package beans;

/**
 * @author stefano
 * 
 */
public class BinD {

	private static long size = 0;

	private long id;

	private long level;

	private long loChild;

	private long hiChild;

	public BinD(long level, long hiChild, long loChild) {

		this.hiChild = hiChild;
		this.id = size++;
		this.level = level;
		this.loChild = loChild;

	}

}
