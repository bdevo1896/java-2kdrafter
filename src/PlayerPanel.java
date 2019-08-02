import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;


public class PlayerPanel extends JPanel {
	private JTextField draftBox,tradeBox;
	private String playerName;
	
	public PlayerPanel(String name){
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBorder(new LineBorder(new Color(113,126,181)));
		this.setBackground(Color.WHITE);
		playerName = name;
		
		/*
		 * Adding a label for the name of the player.
		 */
		JLabel lblName = makeLabel(name,18,Font.BOLD);
		this.add(lblName);
		
		/*
		 * Adding a label for the draft text box.
		 */
		JLabel lblDraft = makeLabel("Draft",12,Font.PLAIN);
		this.add(lblDraft);
		
		/*
		 * Adding the draft text box.
		 */
		draftBox = makeTextBox();
		this.add(draftBox);
		
		/*
		 *Adding a label for the trades
		 */
		JLabel lblTrade = makeLabel("Trade",12,Font.PLAIN);
		this.add(lblTrade);
		
		/*
		 * Adding the trade text box
		 */
		tradeBox = makeTextBox();
		this.add(tradeBox);
		
		
		
	}
	
	public JLabel makeLabel(String name,int size, int fontType){
		JLabel rtnLbl = new JLabel(name);
		rtnLbl.setFont(new Font("Arial",fontType,size));
		rtnLbl.setMaximumSize(new Dimension(100,20));
		rtnLbl.setMinimumSize(new Dimension(100,20));
		rtnLbl.setPreferredSize(new Dimension(100,20));
		return rtnLbl;
	}
	
	public JTextField makeTextBox(){
		JTextField rtnField = new JTextField(10);
		rtnField.setEditable(false);
		return rtnField;
	}

	public JTextField getDraftBox() {
		return draftBox;
	}

	public JTextField getTradeBox() {
		return tradeBox;
	}

	public String getPlayerName() {
		return playerName;
	}
	
	
}
