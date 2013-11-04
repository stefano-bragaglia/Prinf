/**
 * 
 */
package core;

import java.io.PrintWriter;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseConfiguration;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.conf.EventProcessingOption;
import org.drools.io.ResourceFactory;
import org.drools.runtime.KnowledgeSessionConfiguration;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.conf.ClockTypeOption;

import beans.Clear;
import beans.Edge;
import beans.Goal;
import beans.HiChild;
import beans.LoChild;
import beans.Terminal;

/**
 * @author stefano
 * 
 */
public class Reasoner {

	/**
	 * 
	 */
	private StatefulKnowledgeSession session;

	/**
	 * 
	 */
	public Reasoner() {
		KnowledgeBuilder builder = KnowledgeBuilderFactory
				.newKnowledgeBuilder();
		builder.add(ResourceFactory.newClassPathResource("Definitions.drl"),
				ResourceType.DRL);
		builder.add(ResourceFactory.newClassPathResource("Clear.drl"),
				ResourceType.DRL);
		builder.add(ResourceFactory.newClassPathResource("Convert.drl"),
				ResourceType.DRL);
		KnowledgeBuilderErrors errors = builder.getErrors();
		if (errors.size() > 0) {
			for (KnowledgeBuilderError error : errors)
				System.err.println(error);
			throw new IllegalArgumentException("Could not parse knowledge.");
		}
		KnowledgeBaseConfiguration baseCfg = KnowledgeBaseFactory
				.newKnowledgeBaseConfiguration();
		baseCfg.setOption(EventProcessingOption.STREAM);
		KnowledgeBase base = KnowledgeBaseFactory.newKnowledgeBase(baseCfg);
		base.addKnowledgePackages(builder.getKnowledgePackages());
		KnowledgeSessionConfiguration sessionCfg = KnowledgeBaseFactory
				.newKnowledgeSessionConfiguration();
		sessionCfg.setOption(ClockTypeOption.get("realtime"));
		session = base.newStatefulKnowledgeSession(sessionCfg, null);
		session.insert(Terminal.ZERO);
		session.insert(Terminal.ONE);
		assert invariant() : "Illegal state in Reasoner()";
	}

	/**
	 * @return
	 */
	private boolean invariant() {
		return (session != null);
	}

	/**
	 * @param edge
	 */
	public void insert(Edge edge) {
		if (edge == null)
			throw new IllegalArgumentException(
					"Illegal 'edge' argument in Reasoner.insert(Edge): " + edge);
		session.insert(edge);
		assert invariant() : "Illegal state in Reasoner.insert(Edge)";
	}

	/**
	 * 
	 */
	public void execute(Goal goal) {
		if (goal == null)
			throw new IllegalArgumentException(
					"Illegal 'goal' argument in Reasoner.execute(Goal): "
							+ goal);
		session.insert(Clear.getInstance());
		session.fireAllRules();
		session.insert(goal);
		session.fireAllRules();
		assert invariant() : "Illegal state in Reasoner.execute()";
	}

	public void saveBDD(String filename, Goal goal) {
		if (filename == null || (filename = filename.trim()).isEmpty())
			throw new IllegalArgumentException(
					"Illegal 'filename' argument in Reasoner.saveBDD(String, Goal): "
							+ filename);
		if (goal == null)
			throw new IllegalArgumentException(
					"Illegal 'goal' argument in Reasoner.saveBDD(Goal): "
							+ goal);
		session.insert(Clear.getInstance());
		session.fireAllRules();
		session.insert(goal);
		try {
			filename += ".gv";
			PrintWriter writer = new PrintWriter(filename);

			writer.append("digraph G {\n");
			writer.append("\tnode [shape=box, style=filled, fillcolor=lightgrey, fontname=Helvetica];\n");
			writer.append("\tsubgraph _terminals {\n");
			writer.append("\t\trank=same;\n");
			writer.append("\t\t0 1;\n");
			writer.append("\t}\n");
			writer.append("\tnode [shape=circle, style=hollow, fontname=Helvetica];\n");
			for (Object o : session.getObjects()) {
				if (o instanceof HiChild) {
					HiChild h = (HiChild) o;
					writer.append("\t" + h.getEdge().getTail().getName()
							+ " -> " + h.getEdge().getHead().getName()
							+ " [style=solid, label=\"" + h.getProb()
							+ "\"];\n");
				}
				if (o instanceof LoChild) {
					LoChild l = (LoChild) o;
					writer.append("\t" + l.getEdge().getTail().getName()
							+ " -> " + l.getEdge().getHead().getId()
							+ " [style=dashed, label=\"" + l.getProb()
							+ "\"];\n");
				}
			}
			writer.append("}\n");

			writer.flush();
			writer.close();
			String[] cmd = { "/usr/local/bin/dot", "-Tpdf", filename, "-o",
					filename.substring(0, filename.length() - 3) + ".pdf" };
			Runtime.getRuntime().exec(cmd);
		} catch (Exception e) {
			System.err.println("I couldn't write " + filename + ".gv!");
			e.printStackTrace();
		}
		assert invariant() : "Illegal state in Reasoner.saveBDD()";
	}

	public void saveDot(String filename, Goal goal) {
		if (filename == null || (filename = filename.trim()).isEmpty())
			throw new IllegalArgumentException(
					"Illegal 'filename' argument in Reasoner.saveDot(String, Goal): "
							+ filename);
		if (goal == null)
			throw new IllegalArgumentException(
					"Illegal 'goal' argument in Reasoner.saveDot(Goal): "
							+ goal);
		session.insert(Clear.getInstance());
		session.fireAllRules();
		session.insert(goal);
		try {
			filename += ".gv";
			PrintWriter writer = new PrintWriter(filename);
			writer.append("digraph G {\n");
			writer.append("\tnode [shape=point, fontname=Helvetica]; _start;\n");
			writer.append("\tnode [shape=doublecircle, fontname=Helvetica]");
			for (Object o : session.getObjects())
				if (o instanceof Goal)
					writer.append("; \"" + ((Goal) o).getTarget().getName()
							+ "\"");
			writer.append(";\n");
			writer.append("\tnode [shape=circle, fontname=Helvetica];\n");
			for (Object o : session.getObjects())
				if (o instanceof Goal)
					writer.append("\t_start -> \""
							+ ((Goal) o).getSource().getName() + "\";\n");
			// writer.append("\tnode [shape=circle,color=black,style=hollow];\n");
			for (Object o : session.getObjects())
				if (o instanceof Edge) {
					Edge e = (Edge) o;
					writer.append("\t\"" + e.getTail().getName() + "\" -> \""
							+ e.getHead().getName() + "\" [label=\""
							+ e.getId() + ": " + e.getProb() + "\"];\n");
				}
			writer.append("}\n");
			writer.flush();
			writer.close();
			String[] cmd = { "/usr/local/bin/dot", "-Tpdf", filename, "-o",
					filename.substring(0, filename.length() - 3) + ".pdf" };
			Runtime.getRuntime().exec(cmd);
		} catch (Exception e) {
			System.err.println("I couldn't write " + filename + ".gv!");
			e.printStackTrace();
		}
		assert invariant() : "Illegal state in Reasoner.saveDot()";
	}

}
