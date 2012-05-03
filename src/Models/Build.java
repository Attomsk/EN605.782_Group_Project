package Models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

/**
 * Represents a current build in the Build a PC wizard
 * @author Ben Morlok
 */
public class Build implements java.io.Serializable{

	
	
	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = -6410839184790176752L;
	
	/**
	 * Static Members
	 */
	// The types of processors that the system is compatible with
	public static final String[] processorTypes = {"None", "AMD", "Intel", "Both"};
	// The states that the build can be in
	public static final String[] buildStates = {"Processor", "Motherboard", "Memory", "Video Card", "Power Supply", "Hard Drive", "Case"};
	
	/**
	 * Members
	 */
	// The current state of the build (aka, processor, motherboard, memory, etc...)
	private int state;
	// The current components selected for the build
	private List<Component> components;
	// The type of processor this build is for (
	private int processorType;
	
	/**
	 * private helpers
	 */
	private void initializeComponents()
	{
		if(null == components)
		{
			components =  new ArrayList<Component>();
		}
	}
	
	
	/** 
	 * Default Constructor
	 */
	public Build()
	{
		state = 0;
		components =  new ArrayList<Component>();
	}
	
	/**
	 * Optional Constructor
	 */
	public Build(int state, List<Component> components, int processorType)
	{
		this.state = state;
		this.components = components;
		this.processorType = processorType;
	}
	
	
	/*** Utility/Interaction Functions ***/
	/**
	 * Increment the state
	 */
	public void incrementState() {
		state++;
	}

	/**
	 * Add component to list of components.
	 * 
	 * @param component
	 */
	public void addComponent(Component component) {
		initializeComponents();
		components.add(component);
	}
	
	/**
	 * Removes the last component from the component list
	 */
	public void removeLastComponent() {
		if(components.size() > 0)
		{
			components.remove(components.size()-1);
		}
	}
	
	/**
	 * Computes the total build cost
	 * @return
	 */
	public double getTotalBuildCost(){
		double total = 0;
		Iterator<Component> itr = components.iterator();
		while (itr.hasNext()) {
			Component cmp = itr.next();
			total += cmp.getPrice();
		}
		return total;
	}
	
	// Returns the build in URL form
	public String getBuildUrl(HttpServletRequest request){
		URL myUrl;
		String output = "#";
		String buildUrl=" http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +
				"/Build?buildCombo=true&ProcessorType="+this.processorType;
		for(int i=0; i<this.components.size(); i++)
		{
			buildUrl += "&" + buildStates[i]+"="+this.components.get(i).getId();
		}
		try {
			myUrl = new URL(buildUrl);
			output = myUrl.toString();
		} catch (MalformedURLException e) {
			output = "#";
		}
		return output;
	}
	
	/**
	 * Getters and Setters
	 */
	public int getState() {
		return state;
	}
	
	public String getStateString(){
		return buildStates[state];
	}
	
	public void setState(int currentState) {
		this.state = currentState;
	}
	
	public List<Component> getComponents() {
		initializeComponents();
		return components;
	}
	
	public void setComponents(List<Component> components) {
		this.components.clear();
		for(int i=0; i< components.size(); i++)
		{
			this.components.add(components.get(i));
		}
	}
	
	public int getProcessorType() {
		return processorType;
	}
	
	public String getProcessorTypeString() {
		return processorTypes[processorType];
	}
	
	public void setProcessorType(int processorType) {
		this.processorType = processorType;
	}
}
