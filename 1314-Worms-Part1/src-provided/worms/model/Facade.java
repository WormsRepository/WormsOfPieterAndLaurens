package worms.model;

public class Facade implements IFacade {

	@Override
	public Worm createWorm(double x, double y, double direction, double radius,
			String name) throws ModelException{
		try{
		Worm worm = new Worm(x,y,direction,radius,name);
		return worm;
		}
		catch(IllegalRadiusException z){
			throw new ModelException("This is not a valid radius!");
		}
		catch(IllegalNameException z){
			throw new ModelException("This is not a valid name!");
		}
	}

	@Override
	public boolean canMove(Worm worm, int nbSteps) {
		return worm.canMove(nbSteps);
	}

	@Override
	public void move(Worm worm, int nbSteps) 
			throws IllegalArgumentException{
		try{
			worm.move(nbSteps);
		}
		catch(IllegalArgumentException x){
			throw new ModelException("This worm cannot move :(");
		}
		
	}

	@Override
	public boolean canTurn(Worm worm, double angle) {
		return worm.canTurn(angle);
	}

	@Override
	public void turn(Worm worm, double angle) {
		worm.turn(angle);
	}

	@Override
	public void jump(Worm worm) 
			throws ModelException{
		try{
			worm.jump();
		}
		catch (IllegalDirectionException x){
			throw new ModelException("You can not jump in this direction!");
		}
		catch(IllegalActionPointsException x){
			throw new ModelException("You can not jump without action points!");
		}
	}

	@Override
	public double getJumpTime(Worm worm) 
			throws ModelException{
		try{
			return worm.getJumpTime();
		}
		catch(IllegalDirectionException x){
			return 0;
		}
		catch(IllegalActionPointsException x){
			return 0;
		}
	}

	@Override
	public double[] getJumpStep(Worm worm, double t) 
			throws ModelException{
		try{
			return worm.getJumpStep(t);
		}
		catch(IllegalDirectionException x){
			return null;
		}
		catch(IllegalActionPointsException x){
			return null;
		}
	}

	@Override
	public double getX(Worm worm) {
		return worm.getX();
	}

	@Override
	public double getY(Worm worm) {
		return worm.getY();
	}

	@Override
	public double getOrientation(Worm worm) {
		return worm.getDirection();
	}

	@Override
	public double getRadius(Worm worm) {
		return worm.getRadius();
	}

	@Override
	public void setRadius(Worm worm, double newRadius) 
			throws ModelException{
		try{
			worm.setRadius(newRadius);
		}
		catch(IllegalRadiusException x){
			throw new ModelException("This is not a valid radius!");
		}
	}

	@Override
	public double getMinimalRadius(Worm worm) {
		return worm.getMinimalRadius();
	}

	@Override
	public int getActionPoints(Worm worm) {
		return (int)worm.getCurrentActionPoints();
	}

	@Override
	public int getMaxActionPoints(Worm worm) {
		return (int)worm.getMaxActionPoints();
	}

	@Override
	public String getName(Worm worm) {
		return worm.getName();
	}

	@Override
	public void rename(Worm worm, String newName) 
			throws ModelException{
		try{
			worm.setName(newName);
		}
		catch(IllegalNameException x){
			throw new ModelException("This is not a valid name!");
		}
	}

	@Override
	public double getMass(Worm worm) {
		return worm.getMass();
	}

}
