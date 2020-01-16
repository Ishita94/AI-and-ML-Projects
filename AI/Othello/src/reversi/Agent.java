/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi;

/**
 *
 * @author User
 */

/**
 * Extend this abstract class for human/ AI agent
 * @author Azad
 *
 */
public abstract class Agent 
{
	String name; // Name of the agent
	int role; // This is important, Each agent will be assigned a role beforehand. 
	String color;	
        Buzz b;//For example, for tick tack toe X will be assigned to one agent, and 0 will be assigned to another agent
	//int buzz;
        int depth;// The roles are stored as integer. 
        int score;
	public Agent(String name) 
	{
		// TODO Auto-generated constructor stub
		this.name = name;
                //buzz=0;
                b=new Buzz(-1,-1);
                this.depth=5;
                this.score=2;
		
	}
	
	/**
	 * Sets the role of this agent. Typlically will be called by your extended Game class (The  class which extends the Game Class).
	 * @param role
	 */
	public void setRole(int role) {
		this.role = role;
	}
        public void setcolor(String c) {
		this.color = c;
	}

	/**
	 * Implement this method to select a move, and change the game state according to the chosen move.
	 * @param game
	 */
	public abstract void makeMove(Game game);
        public abstract void setbuzz(int r,int c);
        public abstract int checkbuzz();
	

}

