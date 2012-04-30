package Models;

import java.util.List;

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
	private static final String[] processorTypes = {"Intel", "AMD"};
	// The states that the build can be in
	public static final String[] buildStates = {"processor", "motherboard", "memory", "video", "power", "drive", "case"};
	
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
	 * Default Constructor
	 */
	public Build()
	{
		state = 0;
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
		components.add(component);
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
		return components;
	}
	
	public void setComponents(List<Component> components) {
		this.components = components;
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
