// versie: dinsdag 11 maart 21:50

package worms.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Model;
import be.kuleuven.cs.som.annotate.Raw;

// TODO documentation...
/**
 * A class of worms involving an x-coordinate, an y-coordinate, a direction in radians, a radius (in meter), a name, a mass (in kilogram),
 *  
 * @author Laurens Loots, Pieter Vos
 */
public class Worm {
	
	
	//TODO documentatie constructor nakijken
	/**
	 * Create a new worm that is positioned at the given location,
	 * looks in the given direction, has the given radius, the given name,
	 * the right mass and the right maximum amount of action points
	 * 
	 * @param	x
	 * 			The x-coordinate of the position of the new worm (in meter).
	 * @param 	y
	 * 			The y-coordinate of the position of the new worm (in meter).
	 * @param 	direction
	 * 			The direction of the new worm (in radians).
	 * @param 	radius 
	 * 			The radius of the new worm (in meter).
	 * @param 	name
	 * 			The name of the new worm
	 * @pre		The given initial direction must be a valid direction for any worm.
	 * 		    | isValidDirection(direction)
	 * @post	The new x coordinate of this new worm is equal to the given x coordinate
	 * 			| new.getX() == x
	 * @post	The new y coordinate of this new worm is equal to the given y coordinate
	 * 			| new.getY() == y
	 * @post	The new direction of this new worm is equal to the given direction.
	 * 			| new.getDirection() == direction
	 * @post	The new radius of this new worm is equal to the given radius.
	 * 			| new.getRadius() == radius
	 * @post	The new name of this worm is equal to the given name.
	 * 			| new.getName() == name
	 * @throws	IllegalRadiusException(radius,this)
	 * 			This new worm cannot have the given radius as its radius
	 * 			| !canHaveAsRadius(radius)
	 * @throws	IllegalNameException(name,this)
	 * 			This new worm cannot have the given name as its name.
	 * 			| !canHaveAsName(name)
	 * @effect	The mass of this new worm and the maximum amount of action points of this new worm
	 * 			is set, it depends on the radius of this new worm. There is only a mass and a maximum
	 * 			amount of action points if the radius is a valid radius for any worm.
	 * 			| this.setMass(radius)
	 * 			| this.setMaxActionPoints(mass)
	 */
	public Worm(double x, double y, double direction, double radius,String name) 
			throws IllegalRadiusException, IllegalNameException
	{
		setX(x);
		setY(y);
		setDirection(direction);
		setRadius(radius);
		setMass(radius);
		setMaxActionPoints(mass);
		setName(name);
	}
	

	/**
	 * Return the x-coordinate of the position of the worm (in meter).
	 */
	public double getX() {
		return this.x;
	}
	
	/**
	 * Set the x-coordinate for this worm to the given x-coordinate.
	 * 
	 * @param 	x
	 * 			The new x-coordinate for this worm (in meter).
	 * @post	The new x-coordinate for this worm is equal to the given x-coordinate.
	 * 			| new.getX() == x
	 */
	@Model
	private void setX(double x)
	{
		this.x = x;
	}
	
	/**
	 * Return the y-coordinate of the position of the worm (in meter).
	 */
	public double getY() {
		return this.y;
	}
	
	/**
	 * Set the y-coordinate for this worm to the given y-coordinate.
	 * 
	 * @param 	y
	 * 			The new y-coordinate for this worm (in meter).
	 * @post	The new y-coordinate for this worm is equal to the given y-coordinate.
	 * 			| new.getY() == y
	 */
	@Model
	private void setY(double y)
	{
		this.y = y;
	}
	
	/**
	 * Check whether the given number of steps is a valid amount of steps for the worm.
	 * 
	 * @param 	nbSteps
	 * 			The amount of steps to check.
	 * @return	True if and only if the amount of steps is larger than zero 
	 * 			and there are enough action points available for the move.
	 * 			| nbSteps > 0 && 
	 * 			| currentActionPoints >= Math.ceil((Math.cos(getDirection()) + 4*Math.sin(getDirection())) * nbSteps)
	 */
	public boolean canMove(int nbSteps) 
	{
		return nbSteps > 0 && 
				currentActionPoints >= Math.ceil((Math.cos(getDirection()) + 4*Math.sin(getDirection())) * nbSteps);
	}
	
	//TODO documentatie van getJumpStep aanvullen
	/**
	 * 
	 * @param worm
	 * @param t
	 * @return
	 */
	public double[] getJumpStep(double t) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//TODO documentatie van getJumpTime aanvullen
	/**
	 * 
	 * @param worm
	 * @return
	 */
	public double getJumpTime() {
		// TODO Auto-generated method stub
		return 0;
	}
	
//right exception?
	/**
	 * Change the position of the worm as the result of a jump from the current position 
	 * and with respect to the worm's orientation and the number of remaining action points.
	 * 
	 * @post 	The x position is changed based on the current direction, the number of remaining action points,
	 * 			the mass, the starting position of the worm and the standard acceleration. 
	 * 			The new amount of current action points is zero.
	 * 			| double force == (5.0*(double)getCurrentActionPoints()) + (getMass() * STANDARD_ACCELERATION)
	 * 			| double initialVelocity == (force/getMass()) * 0.5
	 * 			| double distance == (Math.pow(initialVelocity, 2) * Math.sin(2*getDirection()))/STANDARD_ACCELERATION
	 * 			| new.getX() == getX() + distance
	 * 			| new.getCurrentActionPoints() == 0
	 * @throws	RuntimeException("This is not a valid direction for a jump!")
	 * 			The current direction is not a valid direction for a jump
	 * 			| Math.PI < getDirection()
	 */
	public void jump() 
			throws RuntimeException
	{
		if(Math.PI < getDirection())
			throw new RuntimeException("This is not a valid direction for a jump!");
		double force = (5.0*(double)getCurrentActionPoints()) + (getMass() * STANDARD_ACCELERATION);
		double initialVelocity = (force/getMass()) * 0.5;
		double distance = (Math.pow(initialVelocity, 2) * Math.sin(2*getDirection()))/STANDARD_ACCELERATION;
		setX(getX() + distance);
		setCurrentActionPoints(0);
	}
	
	/**
	 * Moves the worm in the current direction with the given number of steps.
	 * 
	 * @param 	nbSteps
	 * 			The number of steps to move.
	 * @post 	The x position and the y position are changed based on the number of steps, the radius,
	 * 			the direction and the starting values of x and y. The new amount of current action points is the old
	 * 			amount of action points minus the used action points.
	 * 			| new.getX() == Math.cos(getDirection()) * getRadius() * nbSteps
	 * 			| new.getY() == Math.sin(getDirection()) * getRadius() * nbSteps
	 * 			| new.getCurrentActionPoints() ==
	 * 			|		 getCurrentActionPoints() - (int)(Math.ceil((Math.cos(getDirection()) + 4*Math.sin(getDirection())) * nbSteps))
	 * @Throws	IllegalArgumentException("The argument 'number of steps' is invalid.")
	 * 			The given amount of steps is not a valid amount of steps.
	 * 			| !canMove(nbSteps)
	 */
	public void move(int nbSteps) 
			throws IllegalArgumentException
	{
		if(! canMove(nbSteps))
			throw new IllegalArgumentException("The argument 'number of steps' is invalid.");
		
		setX(Math.cos(getDirection()) * getRadius() * nbSteps);
		//TODO rekening houden met overflow?
		setY(Math.sin(getDirection()) * getRadius() * nbSteps);
		
		long costOfActionPoints = (long)(Math.ceil((Math.cos(getDirection()) + 4*Math.sin(getDirection())) * nbSteps));
		setCurrentActionPoints(getCurrentActionPoints() - costOfActionPoints);
	}
	
	/**
	 * Variable registering the x-coordinate of a worm in meters.
	 */
	private double x;
	
	/**
	 * Variable registering the y-coordinate of a worm in meters.
	 */
	private double y;
	
	/**
	 * Final class variable registering the standard acceleration (m/(s*s)).
	 */
	private final static double STANDARD_ACCELERATION = 9.80665;
	
	
	
	/**
	 * Return the radius of this worm.
	 * 	The radius expresses how big a worm actually is.
	 */
	public double getRadius() 
	{
		return this.radius;
	}
	
	/**
	 * Set the radius of this worm to the given radius.
	 * 
	 * @param 	radius
	 * 			The new radius for this worm.
	 * @post	The new radius of this worm is equal to the given radius.
	 * 			| new.getRadius() == radius
	 * @throws	IllegalRadiusException(radius,this)
	 * 			The given radius is not a valid radius for this worm.
	 * 			| !canHaveAsRadius(radius)
	 */
	public void setRadius(double radius) 
			throws IllegalRadiusException
	{
		if(! canHaveAsRadius(radius))
			throw new IllegalRadiusException(radius,this);
		this.radius = radius;
	}
	
	/**
	 * 	Variable registering the radius of a worm.
	 */
	private double radius = 0;
	
	
	

	
	/**
	 * Check whether the given radius is a valid radius for this worm.
	 * 
	 * @param 	radius
	 * 			The radius to check.
	 * @return	True if and only if the given radius is not below the minimum radius.
	 * 			| radius >= getMinimalRadius()
	 */
	public boolean canHaveAsRadius(double radius)
	{
		return radius >= getMinimalRadius();
	}
	
	/**
	 * Return the variable minRadius of this worm.
	 * 	The variable minRadius expresses the minimum radius the worm has.
	 */
	public double getMinimalRadius() 
	{
		return this.minRadius;
	}
	
	/**
	 * Set the minimal radius to the given minimal radius.
	 * 
	 * @param 	minRadius
	 * 			The new minimum radius of this worm.
	 * @post	The new minimum radius of this worm is equal to the given minimum radius.
	 * 			new.getMinimalRadius() == minRadius
	 * @throws 	IllegalRadiusException(minRadius,this)
	 * 			The given minimum radius is not a valid radius for any worm.
	 * 			| (minRadius <= 0)
	 */
	public void setMinimalRadius(double minRadius) 
			throws IllegalRadiusException
	{
		if(minRadius <= 0)
			throw new IllegalRadiusException(minRadius, this);
		this.minRadius = minRadius;
	}
	
	/**
	 * 	Variable registering the minimal radius of a worm.
	 */
	private double minRadius = 0.25;

	
	
	
	/**
	 * Return the direction of this worm.
	 * 	The direction of a worm expresses the orientation the worm has.
	 */
	public double getDirection() 
	{
		return this.direction;
	}
	
	/**
	 * Check whether the given direction is a valid direction for any worm.
	 * 
	 * @param 	direction
	 * 			The direction to check.
	 * @return	True if and only if the given direction is not below zero and not above or equal to 2 pi.
	 * 			| result == ( (direction >= 0) && (direction < 2*Math.PI) )
	 */
	public boolean isValidDirection(double direction)
	{
		return ( (direction >= 0) && (direction < 2*Math.PI) );
	}
	
	/**
	 * Checks whether a worm can turn with the given angle.
	 * 
	 * @param 	angle
	 * 			The angle to check.
	 * @return 	False if the absolute value of the given angle is bigger than pi or zero.
	 * 			| if (Math.abs(angle) > Math.PI || angle == 0)
	 * 			|	then result == false
	 * 			Otherwise, true if and only if the amount of action points needed for such a turn
	 * 			is smaller than the current amount of action points.
	 * 			| else result == (getCurrentActionPoints() >= angle / (2*Math.PI) * 60)
	 */
	public boolean canTurn(double angle) {
		if(Math.abs(angle) > Math.PI || angle == 0) {
			return false;
		}
		return getCurrentActionPoints() >= angle / (2*Math.PI) * 60;
	}

	/**
	 * Changes the direction with the given angle.
	 * 
	 * @param 	angle
	 * 			The angle to turn.
	 * @pre		The worm must be able to turn with the given angle.
	 * 		    | canTurn(angle)
	 * @post	The new direction is equal to the old direction incremented with the given angle.
	 * 			| new.getDirection() == getDirection() + angle
	 */
	public void turn(double angle) {
		direction += angle;
		//preincrement used correctly?
	}
	
	/**
	 * Set the direction of this worm to the given direction.
	 * 
	 * @param 	direction
	 * 			The new direction for this worm.
	 * @pre		The given direction must be a valid direction for any worm.
	 * 		    | isValidDirection(direction)
	 * @post	The new direction of this worm is equal to the given direction.
	 * 			| new.getDirection() == direction
	 */
	@Model
	private void setDirection(double direction)
	{
		this.direction = direction;
	}
	
	/**
	 * variable registering the direction of a worm.
	 */
	private double direction = 0;
	

	
	
	
	/**
	 * Return the mass of this worm.
	 * 	The Mass of a worm expresses how heavy a worm is.
	 */
	public double getMass() 
	{
		return this.mass;
	}
	
	

	
	
	/**
	 * @param	radius
	 * 			The radius needed for the calculation of the worm's mass.
	 * @post	The new mass is calculated with the radius and the density.
	 * 			| new.mass = DENSITY * ((4/3) * Math.PI * Math.pow(radius,3))
	 * @throws	IllegalRadiusException(radius,this)
	 * 			The given radius is not a valid radius for this worm.
	 * 			| !canHaveAsRadius(radius)
	 */
	private void setMass(double radius) 
			throws IllegalRadiusException
	{
		if(! canHaveAsRadius(radius))
			throw new IllegalRadiusException(radius, this);
		mass = DENSITY * ((4/3) * Math.PI * Math.pow(radius,3));
	}
	
	/**
	 * variable registering the mass of a worm
	 */
	private double mass = 0;
	

	
	/**
	 * final class variable registering the density of all worms.
	 */
	private final static double DENSITY = 1062;
	

	
	
	/**
	 * Returns the variable maxActionPoints.
	 * 		The maximum amount of action points a worm has is represented by the variable maxActionPoints.
	 */
	@Basic @Raw
	public long getMaxActionPoints()
	{
		return this.maxActionPoints;
	}
	
	/**
	 * set the max action points of this worm according to the given mass.
	 * 
	 * @param 	mass
	 * @post 	the new maximum action points is the mass rounded to the nearest integer.
	 * 			| new.maxActionPoints = Math.round(mass)
	 */
	private void setMaxActionPoints(double mass)
	{
		maxActionPoints = Math.round(mass);
	}
	
	/**
	 * variable registering the maximum of action points of a worm, derived from the worm's mass.
	 */
	private long maxActionPoints = 0;
	

	
	
	/**
	 * Return the current amount of action points of this worm.
	 */
	public long getCurrentActionPoints() {
		return this.currentActionPoints;
	}
	
	/**
	 * Set the amount of current action points of this worm to the given amount.
	 * 
	 * @param 	newActionPoints
	 * 			The new amount of current action points for this worm.
	 * @post	The new amount of current action points is equal to the given amount.
	 * 			| new.getCurrentActionPoints() == newActionPoints
	 */
	@Model
	private void setCurrentActionPoints(long newActionPoints){
		this.currentActionPoints = newActionPoints;
	}
	
	/**
	 * variable registering the current amount of aciton points of a worm.
	 */
	private long currentActionPoints = 0;

	
	

	/**
	 * Check whether the given name is a valid name.
	 * 
	 * @param 	name
	 * 			The name to check.
	 * @return	True if and only if the given name is at least two characters long,
	 * 			starts with an uppercase letter and only uses letters, quates and spaces.
	 * 			| name.length()>1 && name.substring(0,1).matches("[A-Z]+") && name.matches("[A-Za-z '\"]+")
	 */
	public boolean canHaveAsName(String name)
	{
		return name.length()>1 && name.substring(0,1).matches("[A-Z]+") && name.matches("[A-Za-z '\"]+");
	}
	
	/**
	 * Return the name of this worm.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Set the name of this worm to the given name.
	 * 
	 * @param	name
	 * 			The new name for this worm.
	 * @post	The new name of this worm is equal to the given name.
	 * 			| new.getName() == name
	 */
	public void setName(String name) 
			throws IllegalNameException
	{
		if(! canHaveAsName(name))
			throw new IllegalNameException(name,this);
		this.name = name;
	}
	
	private String name =" ";
}
