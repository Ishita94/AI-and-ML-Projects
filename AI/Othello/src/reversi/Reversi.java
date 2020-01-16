/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Scanner;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class Reversi extends JFrame {

    /**
     * @param args the command line arguments
     */
    
        //JPanel p=new JPanel();
        JPanel container,panel1,panel2;
        JLabel label1,label2,label3,label4,label5,label6,label7 ;
        JButton one,two;
	sqbutton buttons[]=new sqbutton[64];
	
	public static void main(String args[]){
		new Reversi();
                
	}
	
	public Reversi(){
		super("Othello");
                System.out.println("If human vs machine enter 0, otherwise 1");
                Scanner sc = new Scanner(System.in);
                int choice = sc.nextInt();
		setSize(1400,700);
                  container = new JPanel();
                
            container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));

             panel1 = new JPanel();
             panel2 = new JPanel();
             one=new JButton();
             two=new JButton();
             label2=new JLabel();
             label3=new JLabel();
             label4=new JLabel();
             label5=new JLabel();
             label6=new JLabel();
             label7=new JLabel();
             
            //panel1.set[Preferred/Maximum/Minimum]Size()

            
            
            
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		panel1.setLayout(new GridLayout(8,8));
                panel1.setPreferredSize(new Dimension(512,512));
                Agent human = new HumanTTTAgent("Human");
                Agent machine1 = new AlphabetaAgent("Machine");
                Agent machine2 = new AlphabetaAgent("Machine");
                 Game game;
                if(choice==0)
                {
                     game = new Reversitic(human,machine2,this);
                }
                else
                {
                     game = new Reversitic(machine1,machine2,this);
                    
                }
              
		for(int i=0;i<64;i++){
                    buttons[i]=new sqbutton(i,game);
                    panel1.add(buttons[i]);
		}
                container.add(panel1);
                //label1 = new JLabel("Othello", JLabel.LEFT);
                //label1.setFont(new Font("Serif", Font.PLAIN, 24));
                panel2.setLayout(new FlowLayout()); 
               // panel2.add(label1);
               
                     panel2.add(label2);
                panel2.add(label3);
                panel2.add(one);
                panel2.add(label4);
                panel2.add(two);
            
                    panel2.add(label5);
                 panel2.add(label6);
                 panel2.add(label7);
                 
                


                
                container.add(panel2);
		add(container);
		setLocationRelativeTo(null);
		setVisible(true);
                
		game.play();
	}
}
    
