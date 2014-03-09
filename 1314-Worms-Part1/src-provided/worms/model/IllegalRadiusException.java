package worms.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;


/**
 * A class of exceptions signaling an illegal radius of worms.
 * 	Each illegal radius exception involves the illegal radius and the worm.
 * 
 * @author Laurens Loots, Pieter Vos
 */
@SuppressWarnings("serial")
public class IllegalRadiusException extends RuntimeException
{
	/**
	 * Initialize this new illegal radius exception with given radius and given worm.
	 * 
	 * @param	radius
	 * 			The radius for this new illegal radius exception.
	 * @param	worm
	 * 			The Worm for this new illegal radius exception.
	 * @post	The radius of this new illegal radius exception is equal to the given radius.
	 * 			| new.getRadius() == radius
	 * @post	The worm of this new illegal radius exception is equal to the given worm.
	 * 			| new.getWorm() == worm
	 * @effect	This new illegal radius exception is further initialized as a new runtime exception
	 * 			involving no diagnostic message and no cause.
	 * 			| super()
	 */
	public IllegalRadiusException(double radius, Worm worm)
	{
		this.radius = radius;
		this.worm = worm;
	}
	
	
	/**
	 * Return the radius of this illegal radius exception.
	 */
	@Basic @Raw @Immutable
	public double getRadius()
	{
		return this.radius;
	}
	
	/**
	 * Variable registering the radius of this illegal radius exception.
	 */
	private final double radius;
	
	
	/**
	 * Return the Worm of this illegal radius exception.
	 */
	@Basic @Raw @Immutable
	public Worm getWorm()
	{
		return this.worm;
	}
	
	/**
	 * Variable referencing the worm of this illegal radius exception.
	 */
	private final Worm worm;
}