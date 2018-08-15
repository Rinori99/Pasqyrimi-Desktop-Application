import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PasqyrimiClass extends JFrame {

	private JPanel contentPane;
	private JTextField hexAdresa;
	private JPanel panel;
	private JLabel picLabel;
	private JLabel lblPassqyrimiDirekt;
	private JLabel lblMadhesiaEMemorjes;
	private JLabel lblMadhesiaEMemorjes_1;
	private JLabel lblNumriIBitve;
	private JButton paraqitMemorjeButton;
	private JButton ndryshoDataButton;
	private JLabel lblTDhnatRreth;
	private JSeparator separator;
	private JSeparator separator_1;
	private JLabel lblTDhnatRreth_1;
	private JComboBox loadStoreCombo;
	private JSeparator separator_2;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblMisses;
	private JLabel lblHits;
	private JLabel lblHitRate;
	private JLabel lblHitRate_1;
	private JLabel totalInstruksione;
	private JLabel missLabel;
	private JLabel hitLabel;
	private JLabel missRateLabel;
	private JLabel hitRateLabel;
	private JSeparator separator_3;
	private JButton rifreskoAktivitetButton;
	private JLabel lblSiFunksiononPasqyrimi;
	private JButton btnShfaqmbyllFlowchartin;
	private JSeparator separator_4;
	private JLabel lblPaneliIInformatave;
	private JSeparator separator_5;
	private JSeparator separator_6;
	private JLabel hitMisResult;
	private JComboBox comboBox;
	
	private DefaultTableModel model;
	
	private JPanel mmPanel;
	private JLabel lblMemorjaKryesore;
	private JPanel cmPanel;
	private JLabel lblMemorjaCache;
	private JLabel lblAdresaEInstruksionit_1;
	private JPanel adrPanel;
	
	private boolean b;
	private BufferedImage img;
	
	private String[][] rowsMM;
	private String[] colsMM;
	private JTable mmTable;
	private JScrollPane mmScrollPane;
	
	private String[][] rowsCM;
	private String[] colsCM;
	private JTable cmTable;
	private JScrollPane cmScrollPane;
	
	private String[][] rowsAdr;
	private String[] colsAdr;
	private JTable adrTable;
	private JScrollPane adrScrollPane;
	private JButton ekzekutoInsButton;
	
	private AbstractPasqyrimi pasqyrimi;
	private JLabel lblZgjidhLlojinE;
	private JComboBox mmSizeCB;
	private JComboBox cmSizeCB;
	private JComboBox nrBiteveOffset;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PasqyrimiClass frame = new PasqyrimiClass();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PasqyrimiClass() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(130, 20, 1100, 700);
		setResizable(false);
		setTitle("Pasqyrimi Direkt");
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, Color.GRAY, Color.GRAY, new Color(128, 128, 128), Color.GRAY));
		panel.setBounds(26, 99, 500, 480);
		try{
			img = ImageIO.read(new File("images/flowChart.png"));
		}catch(IOException e){
			e.printStackTrace();
		}
		picLabel = new JLabel(new ImageIcon(img));
		panel.add(picLabel);
		panel.setVisible(b);
		contentPane.add(panel);
		
		lblPassqyrimiDirekt = new JLabel("PASQYRIMET");
		lblPassqyrimiDirekt.setBounds(524, -2, 141, 14);
		contentPane.add(lblPassqyrimiDirekt);
		
		lblMadhesiaEMemorjes = new JLabel("Madhesia e Memorjes Kryesore:");
		lblMadhesiaEMemorjes.setBounds(26, 122, 192, 14);
		contentPane.add(lblMadhesiaEMemorjes);
		
		lblMadhesiaEMemorjes_1 = new JLabel("Madhesia e Memorjes Cache:");
		lblMadhesiaEMemorjes_1.setBounds(26, 147, 192, 14);
		contentPane.add(lblMadhesiaEMemorjes_1);
		
		lblNumriIBitve = new JLabel("Numri i bit\u00EBve p\u00EBr Offset:");
		lblNumriIBitve.setBounds(26, 172, 192, 14);
		contentPane.add(lblNumriIBitve);
		
		paraqitMemorjeButton = new JButton("PARAQIT");
		paraqitMemorjeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				paraqitMemorjeButton.setBackground(new JButton().getBackground());
				ndryshoDataButton.setEnabled(true);
				ekzekutoInsButton.setEnabled(true);
				int ramSize = (int)(Math.pow(2, Integer.parseInt((String)mmSizeCB.getSelectedItem())));
				int cacheSize = (int)(Math.pow(2, Integer.parseInt((String)cmSizeCB.getSelectedItem())));
				int offset = Integer.parseInt((String)nrBiteveOffset.getSelectedItem());
				
				if(comboBox.getSelectedIndex() == 0){
					pasqyrimi = new PasqyrimiDirekt(ramSize, cacheSize, offset);
				}else{
					pasqyrimi = new PasqyrimiAsociativ(ramSize, cacheSize, offset);
				}
				
				rowsMM = new String[pasqyrimi.getBlocksNumber()][pasqyrimi.getBlockLineSize()];
				
				for(int i = 0; i < rowsMM.length; i++){
					for(int u = 0; u < rowsMM[i].length; u++){
						rowsMM[i][u] = pasqyrimi.getWord(i, u);
					}
				}
				
				colsMM = new String[pasqyrimi.getBlockLineSize()];
				for(int i = 0; i < colsMM.length; i++){
					colsMM[i] = "Word " + i;
				}
				
				
				
//				mmTable = new JTable(rowsMM, colsMM);
//				mmTable.setBorder(new LineBorder(new Color(0, 0, 0)));
//				mmTable.setBounds(30, 30, 700, 300);
//			  //mmTable.setValueAt("Blloku: 0, Word: 0", 0, 0);
//////				mmTable.revalidate();
//			    mmScrollPane = new JScrollPane(mmTable);
//			    mmScrollPane.setBounds(0, 0, 669, 192);
//////				mmScrollPane.revalidate();
//				mmPanel.add(mmScrollPane);
////				mmPanel.revalidate();
				
				
				//mmTable.setRowSelectionInterval(0, 0);
				
				
				String[] colsContent = {"INDEX", "VALID BIT", "TAG", "DATA"};
				colsCM = new String[4];
				for(int i = 0; i < colsCM.length; i++){
					colsCM[i] = colsContent[i];
				}
				
				rowsCM = new String[pasqyrimi.getLinesNumber()][4];
				for(int i = 0; i < rowsCM.length; i++){
					for(int u = 0; u < rowsCM[i].length; u++){
						rowsCM[i][u] = pasqyrimi.getCacheString(i, u);
					}
				}
				
				resetMMCM();
				
//				cmTable = new JTable(rowsCM, colsCM);
//				cmTable.setBorder(new LineBorder(new Color(0, 0, 0)));
//				cmTable.setBounds(30, 30, 700, 300);
////				cmTable.revalidate();
//				cmScrollPane = new JScrollPane(cmTable);
//				cmScrollPane.setBounds(0, 0, 669, 192);
////				cmScrollPane.revalidate();
//				cmPanel.add(cmScrollPane);
////				cmPanel.revalidate();
//				paraqitMemorjeButton.setEnabled(false);
				
				comboBox.setEnabled(false);
				mmSizeCB.setEnabled(false);
	    		cmSizeCB.setEnabled(false);
	    		nrBiteveOffset.setEnabled(false);
	    		paraqitMemorjeButton.setEnabled(false);
	    		
				mmPanel.revalidate();
				mmPanel.repaint();
				
				cmPanel.revalidate();
				cmPanel.repaint();
				
				contentPane.revalidate();
				contentPane.repaint();
			}
		});
		paraqitMemorjeButton.setBounds(199, 197, 115, 23);
		contentPane.add(paraqitMemorjeButton);
		
	    ndryshoDataButton = new JButton("NDRYSHO");
	    ndryshoDataButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
//	    		int rowCount = mmTable.getRowCount();
//				for(int i = rowCount-1; i >= 0; i--){
//					((DefaultTableModel)mmTable.getModel()).removeRow(i);
//				}
	    		////////////////////////////
//	    		mmPanel = new JPanel();
//	    		mmPanel.setBounds(370, 72, 670, 194);
//	    		contentPane.add(mmPanel);
//	    		mmPanel.setLayout(new BorderLayout(0, 0));
//	    		
//	    		mmPanel.revalidate();
////	    		mmPanel.repaint();
	    		//////////////////////////
	    		hitMisResult.setText("");
	    		ekzekutoInsButton.setEnabled(false);
	    		
	    		adrPanel.removeAll();
	    		
	    		mmPanel.removeAll();
	    		cmPanel.removeAll();
	    		
	    		
	    		mmPanel.revalidate();
	    		mmPanel.repaint();
	    		
	    		cmPanel.revalidate();
	    		cmPanel.repaint();
	    		
	    		adrPanel.revalidate();
	    		adrPanel.repaint();
	    		
	    		pasqyrimi.rifreskoAdresen();
	    		totalInstruksione.setText(pasqyrimi.getCountAll());
				hitLabel.setText(pasqyrimi.getCountHit());
				missLabel.setText(pasqyrimi.getCountMiss());
				hitRateLabel.setText(pasqyrimi.getHitRate());
				missRateLabel.setText(pasqyrimi.getMissRate());
	    		
	    		mmSizeCB.setEnabled(true);
	    		cmSizeCB.setEnabled(true);
	    		nrBiteveOffset.setEnabled(true);
	    		comboBox.setEnabled(true);
	    		paraqitMemorjeButton.setEnabled(true);
	    		ndryshoDataButton.setEnabled(false);
	    	}
	    });
	    ndryshoDataButton.setEnabled(false);
		ndryshoDataButton.setBounds(26, 197, 165, 23);
		contentPane.add(ndryshoDataButton);
		
		lblTDhnatRreth = new JLabel("T\u00CB DH\u00CBNAT RRETH KRIJIMIT TE MEMORJEVE");
		lblTDhnatRreth.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTDhnatRreth.setHorizontalAlignment(SwingConstants.CENTER);
		lblTDhnatRreth.setBounds(26, 96, 288, 14);
		contentPane.add(lblTDhnatRreth);
		
		separator = new JSeparator();
		separator.setBounds(26, 231, 288, 14);
		contentPane.add(separator);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(26, 51, 288, 14);
		contentPane.add(separator_1);
		
		lblTDhnatRreth_1 = new JLabel("T\u00CB DH\u00CBNAT RRETH DH\u00CBNIES S\u00CB INSTRUKSIONEVE");
		lblTDhnatRreth_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTDhnatRreth_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTDhnatRreth_1.setBounds(26, 249, 288, 14);
		contentPane.add(lblTDhnatRreth_1);
		
		loadStoreCombo = new JComboBox();
		loadStoreCombo.setModel(new DefaultComboBoxModel(new String[] {"Load", "Store"}));
		loadStoreCombo.setBounds(26, 301, 100, 20);
		contentPane.add(loadStoreCombo);
		
		JLabel lblAdresaEInstruksionit = new JLabel("Adresa e Instruksionit (n\u00EB hex.):");
		lblAdresaEInstruksionit.setBounds(26, 274, 218, 14);
		contentPane.add(lblAdresaEInstruksionit);
		
		hexAdresa = new JTextField();
		hexAdresa.setBounds(228, 271, 86, 20);
		contentPane.add(hexAdresa);
		hexAdresa.setColumns(10);
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		ekzekutoInsButton = new JButton("EKZEKUTO");
		ekzekutoInsButton.setEnabled(false);
		ekzekutoInsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(paraqitMemorjeButton.isEnabled()){
					paraqitMemorjeButton.setBackground(Color.RED);
					return;
				}
				
				if(hexAdresa.getText().equals("")){
					hexAdresa.setBackground(new Color(255,178,178));
					return;
				}else{
					hexAdresa.setBackground(null);
				}
				
				String adresa = hexAdresa.getText();
				String adr = "";
				for(int i = 0; i < adresa.length(); i++){
					if(Character.isLetter(adresa.charAt(i))){
						adr += Character.toString(adresa.charAt(i)).toUpperCase();
					}else{
						adr += Character.toString(adresa.charAt(i));
					}
				}
				adresa = adr;
				if(AbstractPasqyrimi.hexToDec(adresa) > pasqyrimi.getMmSize()){ 
					return;
				}
				
				pasqyrimi.setAdresa(adresa);
				String[] colsAdrContent = null;
				if(pasqyrimi instanceof PasqyrimiDirekt){
					colsAdrContent = new String[3];
					for(int i = 0; i < 3; i++){
						if(i == 0){
							colsAdrContent[i] = "TAG";
						}else if(i == 1){
							colsAdrContent[i] = "INDEX";
						}else{
							colsAdrContent[i] = "OFFSET";
						}
					}
					colsAdr = colsAdrContent;
					rowsAdr = new String[1][colsAdr.length];
					
					for(int i = 0; i < 3; i++){
						if(i == 0){
							rowsAdr[0][i] = pasqyrimi.getAddressTag();
						}else if(i == 1){
							rowsAdr[0][i] = ((PasqyrimiDirekt)pasqyrimi).getAddressIndex();
						}else{
							rowsAdr[0][i] = pasqyrimi.getAddressOffset();
						}
					}
				}else{
					colsAdrContent = new String[2];
					for(int i = 0; i < 2; i++){
						if(i == 0){
							colsAdrContent[i] = "TAG";
						}else{
							colsAdrContent[i] = "OFFSET";
						}
					}
					colsAdr = colsAdrContent;
					rowsAdr = new String[1][colsAdr.length];
					for(int i = 0; i < 2; i++){
						if(i == 0){
							rowsAdr[0][i] = pasqyrimi.getAddressTag();
						}else{
							rowsAdr[0][i] = pasqyrimi.getAddressOffset();
						}
					}
				}	
				
				adrTable = new JTable(rowsAdr, colsAdr);
				adrTable.setBorder(new LineBorder(new Color(0, 0, 0)));
				adrTable.setBounds(30, 30, 490, 40);
				adrScrollPane = new JScrollPane(adrTable);
				adrScrollPane.setBounds(0, 0, 490, 40);
				adrPanel.add(adrScrollPane);
				
				mmTable.setRowSelectionInterval(AbstractPasqyrimi.hexToDec(adresa)/pasqyrimi.getBlockLineSize(), AbstractPasqyrimi.hexToDec(adresa)/pasqyrimi.getBlockLineSize());
				
				
				if(pasqyrimi instanceof PasqyrimiDirekt){
					int checkIndex = AbstractPasqyrimi.binToDec(((PasqyrimiDirekt)pasqyrimi).getAddressIndex());
					Line line = pasqyrimi.getLine(checkIndex);
					
					if(line.getValidBit() == 0){
						hitMisResult.setText("MISS: Valid bit ne linjën me index " + checkIndex + " eshte 0! Blloku " + AbstractPasqyrimi.hexToDec(adresa)/pasqyrimi.getBlockLineSize() + " u shkrua në Cache ne ate linje!");
						cmTable.setSelectionBackground(Color.RED);
						cmTable.setRowSelectionInterval(checkIndex, checkIndex);
					}else{
						if(line.getTag().equals(pasqyrimi.getAddressTag())){
							cmTable.setSelectionBackground(Color.GREEN);
							cmTable.setRowSelectionInterval(checkIndex, checkIndex);
							hitMisResult.setText("HIT: Valid bit ne linjën me index " + checkIndex + " eshte 1 dhe bitët e TAG-ut në të dy rastet ishin " + line.getTag());
						}else{
							cmTable.setSelectionBackground(Color.red);
							cmTable.setRowSelectionInterval(checkIndex, checkIndex);
							hitMisResult.setText("MISS: Valid bit ne linjen me index " + checkIndex + "eshte 1 por bitët e TAG-ut të instruksionit (" + pasqyrimi.getAddressTag() + ") dhe në Cache nuk janë identik!");
						}
					}
					
					pasqyrimi.load(adresa);
					///////////////////////Rigjenerim i tabeles se CACHE///////////////////////
					for(int i = 1; i < 4; i++){
						if(i == 1){
							cmTable.setValueAt("1", checkIndex, i);
						}else if(i == 2){
							cmTable.setValueAt(line.getTag(), checkIndex, i);
						}else{
							cmTable.setValueAt(pasqyrimi.getBlockData((AbstractPasqyrimi.hexToDec(adresa))/(pasqyrimi.getBlockLineSize())), checkIndex, i);
						}
					}
				}else{
					int index = 0;
					
					pasqyrimi.load(adresa);
					
					if(!((PasqyrimiAsociativ)pasqyrimi).getLastFound()){
						index = ((PasqyrimiAsociativ)pasqyrimi).getCurrentIndex();
						hitMisResult.setText("MISS: Nuk u gjend blloku i kerkuar ne ndonjeren prej linjave te Cache, Blloku " + AbstractPasqyrimi.hexToDec(adresa)/pasqyrimi.getBlockLineSize() + " u vendos ne linjen: " + index + "!");
						cmTable.setSelectionBackground(Color.RED);
						cmTable.setRowSelectionInterval(index, index);
					}else{
						index = ((PasqyrimiAsociativ)pasqyrimi).getLastFoundIndex();
						hitMisResult.setText("HIT: Blloku i kerkuar u gjend ne linjen: " + index + "!");
						cmTable.setSelectionBackground(Color.GREEN);
						cmTable.setRowSelectionInterval(index, index);
					}
					
					
					Line line = pasqyrimi.getLine(index);
					
					
					for(int i = 1; i < 4; i++){
						if(i == 1){
							cmTable.setValueAt("1", index, i);
						}else if(i == 2){
							cmTable.setValueAt(line.getTag(), index, i);
						}else{
							cmTable.setValueAt(pasqyrimi.getBlockData((AbstractPasqyrimi.hexToDec(adresa))/(pasqyrimi.getBlockLineSize())), index, i);
						}
					}
				}
				
				totalInstruksione.setText(pasqyrimi.getCountAll());
				hitLabel.setText(pasqyrimi.getCountHit());
				missLabel.setText(pasqyrimi.getCountMiss());
				hitRateLabel.setText(pasqyrimi.getHitRate());
				missRateLabel.setText(pasqyrimi.getMissRate());
				pasqyrimi.rifreskoAdresen();
			}		
		});
		ekzekutoInsButton.setBounds(142, 300, 172, 23);
		contentPane.add(ekzekutoInsButton);
		
		separator_2 = new JSeparator();
		separator_2.setBounds(26, 338, 288, 14);
		contentPane.add(separator_2);
		
		lblNewLabel = new JLabel("STATISTIKA RRETH AKTIVITETIT DHE \"RATE\"");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(26, 359, 288, 14);
		contentPane.add(lblNewLabel);
		
	    lblNewLabel_1 = new JLabel("Total instruksione te perdorura: ");
		lblNewLabel_1.setBounds(26, 398, 208, 14);
		contentPane.add(lblNewLabel_1);
		
		lblMisses = new JLabel("Misses (jo të qëlluara): ");
		lblMisses.setBounds(26, 421, 165, 14);
		contentPane.add(lblMisses);
		
		lblHits = new JLabel("Hits (të qëlluara): ");
		lblHits.setBounds(26, 446, 165, 14);
		contentPane.add(lblHits);
		
		lblHitRate = new JLabel("Miss Rate (shkalla e gabimit)(%): ");
		lblHitRate.setBounds(26, 471, 208, 14);
		contentPane.add(lblHitRate);
		
		lblHitRate_1 = new JLabel("Hit Rate (shkalla e të qëlluarit)(%): ");
		lblHitRate_1.setBounds(26, 496, 218, 14);
		contentPane.add(lblHitRate_1);
		
		totalInstruksione = new JLabel("0");
		totalInstruksione.setBackground(Color.LIGHT_GRAY);
		totalInstruksione.setHorizontalAlignment(SwingConstants.RIGHT);
		totalInstruksione.setBounds(249, 398, 65, 14);
		contentPane.add(totalInstruksione);
		
		missLabel = new JLabel("0");
		missLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		missLabel.setBackground(Color.LIGHT_GRAY);
		missLabel.setBounds(249, 421, 65, 14);
		contentPane.add(missLabel);
		
		hitLabel = new JLabel("0");
		hitLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		hitLabel.setBackground(Color.LIGHT_GRAY);
		hitLabel.setBounds(249, 446, 65, 14);
		contentPane.add(hitLabel);
		
		missRateLabel = new JLabel("0");
		missRateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		missRateLabel.setBackground(Color.LIGHT_GRAY);
		missRateLabel.setBounds(249, 471, 65, 14);
		contentPane.add(missRateLabel);
		
		hitRateLabel = new JLabel("0");
		hitRateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		hitRateLabel.setBackground(Color.LIGHT_GRAY);
		hitRateLabel.setBounds(249, 496, 65, 14);
		contentPane.add(hitRateLabel);
		
		separator_3 = new JSeparator();
		separator_3.setBounds(26, 565, 288, 14);
		contentPane.add(separator_3);
		
		rifreskoAktivitetButton = new JButton("RIFRESKO STATISTIKAT");
		rifreskoAktivitetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pasqyrimi.rifreskoStatistikat();
				totalInstruksione.setText(pasqyrimi.getCountAll());
				hitLabel.setText(pasqyrimi.getCountHit());
				missLabel.setText(pasqyrimi.getCountMiss());
				hitRateLabel.setText(pasqyrimi.getHitRate());
				missRateLabel.setText(pasqyrimi.getMissRate());
			}
		});
		rifreskoAktivitetButton.setBounds(26, 523, 288, 23);
		contentPane.add(rifreskoAktivitetButton);
		
		lblSiFunksiononPasqyrimi = new JLabel("SI FUNKSIONON CACHE?");
		lblSiFunksiononPasqyrimi.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSiFunksiononPasqyrimi.setHorizontalAlignment(SwingConstants.CENTER);
		lblSiFunksiononPasqyrimi.setBounds(36, 581, 271, 14);
		contentPane.add(lblSiFunksiononPasqyrimi);
		
		btnShfaqmbyllFlowchartin = new JButton("SHFAQ/MBYLL FLOWCHARTIN");
		btnShfaqmbyllFlowchartin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				b = !b;
				panel.setVisible(b);
				rifreskoAktivitetButton.setVisible(!b);
				loadStoreCombo.setVisible(!b);
				ekzekutoInsButton.setVisible(!b);
				ndryshoDataButton.setVisible(!b);
				paraqitMemorjeButton.setVisible(!b);
				cmSizeCB.setVisible(!b);
				nrBiteveOffset.setVisible(!b);
				hexAdresa.setVisible(!b);
			}
		});
		btnShfaqmbyllFlowchartin.setBounds(26, 606, 293, 23);
		contentPane.add(btnShfaqmbyllFlowchartin);
		
		separator_4 = new JSeparator();
		separator_4.setBounds(26, 640, 288, 14);
		contentPane.add(separator_4);
		
		lblPaneliIInformatave = new JLabel("PANELI I INFORMATAVE");
		lblPaneliIInformatave.setHorizontalAlignment(SwingConstants.CENTER);
		lblPaneliIInformatave.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPaneliIInformatave.setBounds(26, 22, 288, 30);
		contentPane.add(lblPaneliIInformatave);
		
		separator_5 = new JSeparator();
		separator_5.setBounds(26, 23, 1058, 2);
		contentPane.add(separator_5);
		
	    separator_6 = new JSeparator();
		separator_6.setOrientation(SwingConstants.VERTICAL);
		separator_6.setBounds(324, 24, 1, 616);
		contentPane.add(separator_6);
		
	    mmPanel = new JPanel();
		mmPanel.setBounds(370, 72, 670, 194);
		contentPane.add(mmPanel);
		mmPanel.setLayout(new BorderLayout(0, 0));
		
		lblMemorjaKryesore = new JLabel("MEMORJA KRYESORE");
		lblMemorjaKryesore.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMemorjaKryesore.setHorizontalAlignment(SwingConstants.CENTER);
		lblMemorjaKryesore.setBounds(633, 45, 156, 20);
		contentPane.add(lblMemorjaKryesore);
		
	    cmPanel = new JPanel();
		cmPanel.setBounds(370, 446, 670, 194);
		contentPane.add(cmPanel);
		cmPanel.setLayout(new BorderLayout(0, 0));
		
		lblMemorjaCache = new JLabel("MEMORJA CACHE");
		lblMemorjaCache.setHorizontalAlignment(SwingConstants.CENTER);
		lblMemorjaCache.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMemorjaCache.setBounds(633, 417, 156, 20);
		contentPane.add(lblMemorjaCache);
		
		lblAdresaEInstruksionit_1 = new JLabel("Instruksioni Aktual/i Fundit: ");
		lblAdresaEInstruksionit_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAdresaEInstruksionit_1.setBounds(386, 338, 165, 20);
		contentPane.add(lblAdresaEInstruksionit_1);
		
		hitMisResult = new JLabel("");
		hitMisResult.setHorizontalAlignment(SwingConstants.CENTER);
		hitMisResult.setBounds(375, 384, 663, 20);
		contentPane.add(hitMisResult);
		
		adrPanel = new JPanel();
		adrPanel.setBounds(550, 327, 490, 40);
		contentPane.add(adrPanel);
		adrPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel power2 = new JLabel("2 ^");
		power2.setBounds(228, 122, 30, 14);
		contentPane.add(power2);
		
		JLabel label = new JLabel("2 ^");
		label.setBounds(228, 147, 30, 14);
		contentPane.add(label);
		
		lblZgjidhLlojinE = new JLabel("Zgjidh llojin e pasqyrimit:");
		lblZgjidhLlojinE.setBounds(26, 71, 192, 14);
		contentPane.add(lblZgjidhLlojinE);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Direkt", "Asociativ"}));
		comboBox.setBounds(228, 68, 86, 20);
		contentPane.add(comboBox);
		
		mmSizeCB = new JComboBox();
		mmSizeCB.setModel(new DefaultComboBoxModel(new String[] {"8", "9", "10", "11", "12"}));
		mmSizeCB.setBounds(255, 119, 59, 20);
		contentPane.add(mmSizeCB);
		
		cmSizeCB = new JComboBox();
		cmSizeCB.setModel(new DefaultComboBoxModel(new String[] {"4", "5", "6"}));
		cmSizeCB.setBounds(255, 144, 59, 20);
		contentPane.add(cmSizeCB);
		
		nrBiteveOffset = new JComboBox();
		nrBiteveOffset.setModel(new DefaultComboBoxModel(new String[] {"2", "4"}));
		nrBiteveOffset.setBounds(228, 169, 86, 20);
		contentPane.add(nrBiteveOffset);
	}
	
	public void resetMMCM(){
		
		//mmPanel = new JPanel();
		//mmPanel.setBounds(370, 72, 670, 194);
		//contentPane.add(mmPanel);
		//mmPanel.setLayout(new BorderLayout(0, 0));
		
		//mmPanel.repaint();
		//mmPanel.revalidate();
		
		
		//////////////////////////////////////////////////////
		
		mmTable = new JTable(rowsMM, colsMM);
		mmTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		mmTable.setBounds(30, 30, 700, 300);
		
		mmTable.repaint();
		mmTable.revalidate();
		
		mmScrollPane = new JScrollPane(mmTable);
		mmScrollPane.setBounds(0, 0, 669, 192);
		mmPanel.add(mmScrollPane);
		
		mmScrollPane.repaint();
		mmScrollPane.revalidate();
		
		mmPanel.setVisible(true);
		mmScrollPane.setVisible(true);
		mmTable.setVisible(true);
		
		//mmTable.selectAll();
		
		
		
		
//		mmTable.revalidate();
//		mmTable.repaint();
		
//		contentPane.repaint();
//		contentPane.revalidate();
		//////////////////////////////////////////////////////////////////////////////
		
		
		
		cmTable = new JTable(rowsCM, colsCM);
		cmTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		cmTable.setBounds(30, 30, 700, 300);
		
		cmTable.repaint();
		cmTable.revalidate();
		
		cmScrollPane = new JScrollPane(cmTable);
		cmScrollPane.setBounds(0, 0, 669, 192);
		cmPanel.add(cmScrollPane);
		
		cmScrollPane.repaint();
		cmScrollPane.revalidate();
		
		cmPanel.setVisible(true);
		cmScrollPane.setVisible(true);
		cmTable.setVisible(true);
	}
}
