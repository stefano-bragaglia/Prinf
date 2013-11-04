/**
 * 
 */
package example;

import core.Factory;
import core.Reasoner;

/**
 * @author stefano
 * 
 */
public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Reasoner reasoner = new Reasoner();
		reasoner.insert(Factory.createEdge("A", "B", 0.1));
		reasoner.insert(Factory.createEdge("A", "C", 0.2));
		reasoner.insert(Factory.createEdge("B", "C", 0.3));
		reasoner.insert(Factory.createEdge("B", "D", 0.4));
		reasoner.insert(Factory.createEdge("C", "D", 0.5));
		reasoner.saveDot("graph", Factory.createGoal("A", "D"));
		reasoner.execute(Factory.createGoal("A", "D"));
		reasoner.saveBDD("bdd", Factory.createGoal("A", "D"));

	}

}
