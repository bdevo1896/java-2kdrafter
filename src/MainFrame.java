import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;

import java.awt.Component;

import javax.swing.Box;


public class MainFrame extends JFrame {
	private ArrayList<PlayerPanel> names;
	private JPanel pnlInfo;
	private Random rand;
	private static final int MAXX = 600,MAXY = 500;
	private JCheckBox boxPicks;
	public MainFrame() {
		
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		names = new ArrayList<PlayerPanel>();
		rand = new Random();
		
		JPanel mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout(0, 0));
		mainPanel.setMinimumSize(new Dimension(MAXX,MAXY));
		mainPanel.setMaximumSize(new Dimension(MAXX,MAXY));
		mainPanel.setPreferredSize(new Dimension(MAXX,MAXY));
		
		JPanel pnlButtons = new JPanel();
		mainPanel.add(pnlButtons, BorderLayout.SOUTH);
		
		JLabel lblCheck = new JLabel("Randomize");
		boxPicks = new JCheckBox();
		pnlButtons.add(lblCheck);
		pnlButtons.add(boxPicks);
		
		JButton btnMakePicks = new JButton("Make Picks");
		btnMakePicks.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				onMakePicks();
			}
			
		});
		pnlButtons.add(btnMakePicks);
		
		JButton btnAddPlayer = new JButton("Add Player");
		btnAddPlayer.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				onAddPlayer();
			}
			
		});
		pnlButtons.add(btnAddPlayer);
		
		JButton btnRemovePlayer = new JButton("Remove Player");
		btnRemovePlayer.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				onRemovePlayer();
			}
			
		});
		pnlButtons.add(btnRemovePlayer);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
			
		});
		pnlButtons.add(btnQuit);
		
		pnlInfo = new JPanel();
		mainPanel.add(pnlInfo, BorderLayout.CENTER);
		pnlInfo.setLayout(new BoxLayout(pnlInfo, BoxLayout.Y_AXIS));
		
		this.pack();
	}
	
	/**
	 * This method will be able to remove a player with the inputed name of the player.
	 */
	public void onRemovePlayer() {
		String str = JOptionPane.showInputDialog("Enter the player's name.");
		if(str==null)return;
		if(str.trim().length()==0)return;
		removePlayer(str);
	}

	/**
	 * This method will add a new player panel.
	 */
	public void onAddPlayer() {
		String name = JOptionPane.showInputDialog(this,"Enter the new player's name.");
		if(name == null)return;
		if(name.trim().length()==0)return;
		PlayerPanel newPlayer = new PlayerPanel(name);
		names.add(newPlayer);
		pnlInfo.add(newPlayer);
		pnlInfo.revalidate();
		pnlInfo.repaint();
		
		
	}
	
	/**
	 * This method will randomize the picks for everyone.
	 */
	public void onMakePicks() {
		makeTrades(boxPicks.isSelected());
		repaint();
	}
	
	/**
	 * Thus method will shuffle the inputed array
	 * @param args
	 */
	public void shuffle(int[] picks){
		int MAX_SHUFFLES = 5;
		for(int i = 0; i < MAX_SHUFFLES; i++){
			for(int k  =0; k < MAX_SHUFFLES; k++){
				int firstE = rand.nextInt(picks.length);
				int temp = picks[firstE];
				int secondE = rand.nextInt(picks.length);
				picks[firstE] = picks[secondE];
				picks[secondE] = temp;
				
			}
		}
	}
	
	/**
	 * This method will remove a player.
	 */
	public void removePlayer(String name){
		boolean hasBeenRemoved = false;
		for(PlayerPanel p: names){
			if(p.getPlayerName().toLowerCase().equals(name.toLowerCase())){
				p.setVisible(false);
				names.remove(p);
				JOptionPane.showConfirmDialog(this, name+" has been removed.");
				hasBeenRemoved = true;
			}
		}
		if(!hasBeenRemoved)
			JOptionPane.showMessageDialog(this, "Couldn't find "+name+". No player removed.");
		this.revalidate();
		this.repaint();
	}
	
	/**
	 * Makes the trade picks randomly.
	 * @param args
	 */
	public void makeTrades(boolean random){
		int[] picks = new int[names.size()];
		for(int i = 1; i < picks.length+1; i++){
			picks[i-1] = i;
		}
		
		shuffle(picks);
		
		int index = 0;
		for(PlayerPanel player: names){
			player.getDraftBox().setText(picks[index]+"");
			index++;
		}
		index = 0;
		if(random){
			shuffle(picks);
		}
		for(PlayerPanel player: names){
			if(random){
				player.getTradeBox().setText(picks[index]+"");
			}else{
				player.getTradeBox().setText((picks.length+1)-picks[index]+"");
			}
			index++;
		}
	}

	public static void main(String[] args) {
		MainFrame mf = new MainFrame();

	}

}
