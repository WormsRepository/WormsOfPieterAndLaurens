package worms.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Model;
import be.kuleuven.cs.som.annotate.Raw;


/**
 * A class of worms involving an x-coordinate, an y-coordinate, a direction in radians, a radius (in meter), a name, a mass (in kilogram),
 *  
 * @author Laurens Loots, Pieter Vos
 */
public class Worm {
	
	
	//TODO documentatie constructor aanvullen.
	/**
	 * Create a new worm that is positioned at the given location,
	 * looks in the given direction, has the given radius and the given name.
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
	 */
	public Worm(double x, double y, double direction, double radius,String name) 
			throws IllegalRadiusException, IllegalNameException
	{
		this.direction = direction;
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
	
	//TODO methodes in verband met positie hier zetten.
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
	
	//TODO documentatie van jump aanvullen
	/**
	 * 
	 * @param worm
	 */
	public void jump() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Moves the worm in the current direction with the given number of steps.
	 * @param 	nbSteps
	 * 			The number of steps to move.
	 * @Post 	The x position and the y position are changed based on the number of steps, the radius,
	 * 			the direction and the starting values of x and y. The new amount of current action points is the old
	 * 			amount of action points minus the used action points.
	 * 			| new.getX() == Math.cos(getDirection()) * getRadius() * nbSteps
	 * 			| new.getY() == Math.sin(getDirection()) * getRadius() * nbSteps
	 * 			| new.getCurrentActionPoints() ==
	 * 			|		 getCurrentActionPoints() - (int)(Math.ceil((Math.cos(getDirection()) + 4*Math.sin(getDirection())) * nbSteps))
	 * @Throws	IllegalArgumentException("The argument 'number of steps' is invalid.")
	 * 			the given amount of steps is not a valid amount of steps
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
		
		int costOfActionPoints = (int)(Math.ceil((Math.cos(getDirection()) + 4*Math.sin(getDirection())) * nbSteps));
		setCurrentActionPoints(getCurrentActionPoints() - costOfActionPoints);
	}
	
	/**
	 * variable registering the x-coordinate of a worm in meters.
	 */
	private double x;
	
	/**
	 * variable registering the y-coordinate of a worm in meters.
	 */
	private double y;
	
	//TODO methodes in verband met radius hier zetten.
	
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
	
	
	
	//TODO methodes in verband met minimumradius hier zetten.
	
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
	//TODO methodes in verband met direction en angle hier zetten.
	
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
	 * 			| result == ( (getDirection() >= 0) && (getdirection() < 2*Math.PI) )
	 */
	public boolean isValidDirection(double direction)
	{
		return ( (getDirection() >= 0) && (getDirection() < 2*Math.PI) );
	}
	
	/**
	 * Checks wether a worm can turn with the given angle.
	 * 
	 * @param 	angle
	 * 			The angle to check.
	 * @return 	False if the absolute value of the given angle is bigger than pi or zero.
	 * 			| if (Math.abs(angle) > Math.PI || angle == 0)
	 * 			|	then result == false
	 * 			Otherwise, true if and only if the amount of action points needed for such a turn
	 * 			is smaller than the curren amount of action points.
	 * 			| else result == (getCurrentActionPoints() >= angle / (2*Math.PI) * 60)
	 */
	public boolean canTurn(double angle) {
		if(Math.abs(angle) > Math.PI || angle == 0) {
			return false;
		}
		return getCurrentActionPoints() >= angle / (2*Math.PI) * 60;
	}
	
	//TODO documentatie in verband met turn, aanvullen
	/**
	 * 
	 * @param angle
	 */
	public void turn(double direction) {
		// TODO Auto-generated method stub
		
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
	
	//alle methodes ivm mass hier zetten
	
	/**
	 * Return the mass of this worm.
	 * 	The Mass of a worm expresses how heavy a worm is.
	 */
	public double getMass() 
	{
		return this.mass;
	}
	
	
	//TODO methodes op de juiste plaats implementeren
	
	//TODO documentatie aanvullen omdat setmass defensief is.
	/**
	 * @param	radius
	 * 			The radius needed for the calculation of the worm's mass.
	 * @post	The new mass is calculated with the radius and the density.
	 * 			| new.mass = DENSITY * ((4/3) * Math.PI * Math.pow(radius,3))
	 */
	private void setMass(double radius)
	{
		mass = DENSITY * ((4/3) * Math.PI * Math.pow(radius,3));
	}
	
	/**
	 * variable registering the mass of a worm
	 */
	private double mass = 0;
	
	// alle methodes ivm density hier zetten
	/**
	 * final class variable registering the density of all worms.
	 */
	private final static double DENSITY = 1062;
	
	//alle methodes ivm maxActionPoints hier zetten
	//TODO methodes op de juiste plaats implementeren
	/**
	 * Returns the variable maxActionPoints.
	 * 		The maximum amount of action points a worm has is represented by the variable maxActionPoints.
	 */
	@Basic @Raw
	public long getMaxActionPoints()
	{
		return maxActionPoints;
	}
	
	/**
	 * set the max action points of this worm according to the given mass.
	 * 
	 * @param mass
	 * @post the new maximum action points is the mass rounded to the nearest integer.
	 * 		|new.maxActionPoints = Math.round(mass)
	 */
	public void setMaxActionPoints(double mass)
	{
		maxActionPoints = Math.round(mass);
	}
	
	/**
	 * variable registering the maximum of action points of a worm, derived from the worm's mass.
	 */
	private long maxActionPoints = 0;
	
	//alle methodes ivm currentActionPoints hier zetten.
	
	//TODO documentatie ivm getActionPoints hier zetten
	/**
	 * 
	 * @return
	 */
	public int getCurrentActionPoints() {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * 
	 * @param newActionPoints
	 */
	@Model
	private void setCurrentActionPoints(int newActionPoints){
		this.currentActionPoints = newActionPoints;
	}
	
	/**
	 * variable registering the current amount of aciton points of a worm.
	 */
	private long currentActionPoints = 0;
	//alle methodes ivm met name hier zetten.
	

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
