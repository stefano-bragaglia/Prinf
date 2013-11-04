/**
 * 
 */
package beans;

/**
 * @author stefano
 * 
 */
public class Clear {

	private static Clear instance = null;

	public static Clear getInstance() {
		if (instance == null)
			instance = new Clear();
		return instance;
	}

	private Clear() {
	}

}
