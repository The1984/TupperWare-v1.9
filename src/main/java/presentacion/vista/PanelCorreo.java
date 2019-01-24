package presentacion.vista;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.text.StyledEditorKit.AlignmentAction;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Element;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyledEditorKit.FontFamilyAction;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.FlowLayout;
import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit.BoldAction;
import javax.swing.text.StyledEditorKit.ItalicAction;
import javax.swing.text.StyledEditorKit.UnderlineAction;
import javax.swing.text.StyledEditorKit.FontSizeAction;

public class PanelCorreo extends JPanel{

	private static final long serialVersionUID = 6488307815380903821L;
	private JTextField txtReceptor;
	private JTextField txtAsunto;
	private JTextPane editor__;
	private JTextField txtFecha;
	private JButton btnCrearMensaje;
	private JButton btnAdjuntarArchivo;
	private JButton btnAsignarReceptor;
	
	private JComboBox<String> fontSizeComboBox__;
	private JComboBox<String> textAlignComboBox__;
	private JComboBox<String> fontFamilyComboBox__;

	// This flag checks true if the caret position within a bulleted para
	// is at the first text position after the bullet (bullet char + space).
	// Also see EditorCaretListener and BulletParaKeyListener.
	private boolean startPosPlusBullet__;

	// This flag checks true if the caret position within a numbered para
	// is at the first text position after the number (number + dot + space).
	// Alse see EditorCaretListener and NumbersParaKeyListener.	
	
	private static final List<String> FONT_LIST = Arrays.asList(new String [] {"Arial", "Calibri", "Cambria", "Courier New", "Comic Sans MS", "Dialog", "Georgia", "Helevetica", "Lucida Sans", "Monospaced", "Tahoma", "Times New Roman", "Verdana"});
	private static final String [] FONT_SIZES  = {"Font Size", "12", "14", "16", "18", "20", "22", "24", "26", "28", "30"};
	private static final String [] TEXT_ALIGNMENTS = {"Text Align", "Left", "Center", "Right", "Justified"};
	private static final char BULLET_CHAR = '\u2022';
	private static final String BULLET_STR = new String(new char [] {BULLET_CHAR});
	private static final String BULLET_STR_WITH_SPACE = BULLET_STR + " ";
	private static final int BULLET_LENGTH = BULLET_STR_WITH_SPACE.length();

	//-------------------------------------------------------------------
	
	public PanelCorreo() 
	{
		initialize();
	}
	
	private void initialize() 
	{
		setLayout(new BorderLayout(0, 0));
		
		txtReceptor = new JTextField();
		txtReceptor.setHorizontalAlignment(SwingConstants.CENTER);
		txtReceptor.setBounds(93, 27, 86, 20);
		txtReceptor.setColumns(13);
		
		JLabel lblReceptor = new JLabel("Receptor");
		lblReceptor.setHorizontalAlignment(SwingConstants.CENTER);
		lblReceptor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblReceptor.setBounds(24, 30, 59, 14);
		
		txtAsunto = new JTextField();
		txtAsunto.setHorizontalAlignment(SwingConstants.CENTER);
		txtAsunto.setPreferredSize(new Dimension(200, 25));
		txtAsunto.setBounds(93, 65, 400, 20);
		txtAsunto.setColumns(30);
		
		JLabel lblAsunto = new JLabel("Asunto");
		lblAsunto.setHorizontalAlignment(SwingConstants.CENTER);
		lblAsunto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAsunto.setBounds(24, 68, 59, 14);

		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFecha.setBounds(235, 30, 46, 14);
		
		txtFecha = new JTextField();
		txtFecha.setHorizontalAlignment(SwingConstants.CENTER);
		txtFecha.setBounds(291, 27, 86, 20);
		txtFecha.setColumns(10);
				
		btnCrearMensaje = new JButton("Enviar");
		btnCrearMensaje.setBounds(10, 365, 383, 27);
		btnCrearMensaje.setBackground(Color.cyan);
		btnCrearMensaje.setForeground(Color.black);
		Border border = BorderFactory.createLineBorder(Color.black, 2);
		btnCrearMensaje.setBorder(border);

		btnAsignarReceptor = new JButton("...");
		btnAsignarReceptor.setBounds(180, 26, 28, 23);
		
		btnAdjuntarArchivo = new JButton("Adjuntar PDF");
		btnAdjuntarArchivo.setBounds(180, 26, 28, 23);
		btnAdjuntarArchivo.setBackground(Color.darkGray);
		
		JPanel panelNorte = new JPanel();
		panelNorte.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelNorte.setBackground(new Color(5,35,27));
		panelNorte.add(lblReceptor);
		panelNorte.add(txtReceptor);
		panelNorte.add(btnAsignarReceptor);	
		JLabel lblSeparador1 = new JLabel("                  -                  ");
		panelNorte.add(lblSeparador1);
		panelNorte.add(lblAsunto);
		panelNorte.add(txtAsunto);
		JLabel lblSeparador2 = new JLabel("                  -                  ");
		panelNorte.add(lblSeparador2);
		panelNorte.add(lblFecha);
		panelNorte.add(txtFecha);
		JLabel lblSeparador3 = new JLabel("                  -                  ");
		panelNorte.add(lblSeparador3);
		panelNorte.add(btnAdjuntarArchivo);
		add(panelNorte , BorderLayout.NORTH);
		
		//----------------------------------------------------------------------------------------------------

		editor__ = new JTextPane();
		editor__.setBackground(new Color(239,228,176));
		
		JScrollPane editorScrollPane = new JScrollPane(editor__);

		editor__.setContentType("text/html");
		editor__.addKeyListener(new BulletParaKeyListener());

		EditButtonActionListener editButtonActionListener =
				new EditButtonActionListener();
		
		Border borderBtn = BorderFactory.createLineBorder(Color.black, 1);
		
		JButton boldButton = new JButton(new BoldAction());
		boldButton.setHideActionText(true);
		boldButton.setText("Bold");
		boldButton.setBorder(borderBtn);
		boldButton.addActionListener(editButtonActionListener);
		JButton italicButton = new JButton(new ItalicAction());
		italicButton.setHideActionText(true);
		italicButton.setText("Italic");
		italicButton.setBorder(borderBtn);
		italicButton.addActionListener(editButtonActionListener);
		JButton underlineButton = new JButton(new UnderlineAction());
		underlineButton.setHideActionText(true);
		underlineButton.setText("Underline");
		underlineButton.setBorder(borderBtn);
		underlineButton.addActionListener(editButtonActionListener);
		JButton colorButton = new JButton("Set Color");
		colorButton.setBorder(borderBtn);
		colorButton.addActionListener(new ColorActionListener());

		textAlignComboBox__ = new JComboBox<String>(TEXT_ALIGNMENTS);
		textAlignComboBox__.setEditable(false);
		textAlignComboBox__.addItemListener(new TextAlignItemListener());
		
		fontSizeComboBox__ = new JComboBox<String>(FONT_SIZES);
		fontSizeComboBox__.setEditable(false);
		fontSizeComboBox__.addItemListener(new FontSizeItemListener());

		Vector<String> editorFonts = getEditorFonts();
		editorFonts.add(0, "Font Family");
		fontFamilyComboBox__ = new JComboBox<String>(editorFonts);
		fontFamilyComboBox__.setEditable(false);
		fontFamilyComboBox__.addItemListener(new FontFamilyItemListener());
				
		JPanel panelSur = new JPanel();
		panelSur.setLayout(new GridLayout(1, 0, 0, 0));
		panelSur.setBackground(new Color(5,35,27));
		panelSur.add(boldButton);
		panelSur.add(italicButton);
		panelSur.add(underlineButton);
		panelSur.add(colorButton);
		panelSur.add(textAlignComboBox__);
		panelSur.add(fontSizeComboBox__);
		panelSur.add(fontFamilyComboBox__);
		panelSur.add(new JSeparator(SwingConstants.VERTICAL));
		panelSur.add(btnCrearMensaje);
		
		JPanel toolBarPanel = new JPanel();
		toolBarPanel.setLayout(new BoxLayout(toolBarPanel, BoxLayout.PAGE_AXIS));
		toolBarPanel.add(panelSur);

		add(toolBarPanel, BorderLayout.SOUTH);
		add(editorScrollPane, BorderLayout.CENTER);
		
		editor__.requestFocusInWindow();		
	}
	
	public JTextPane getEditor__() 
	{
		return editor__;
	}
	
	public JTextField getTxtReceptor() 
	{
		return txtReceptor;
	}

	public JTextField getTxtFecha() 
	{
		return txtFecha;
	}
	
	public JTextField getTxtAsunto() 
	{
		return txtAsunto;
	}
	
	public JTextPane getTextAreaMensaje() 
	{
		return editor__;
	}

	public JButton getBtnCrearMensaje() 
	{
		return btnCrearMensaje;
	}

	public JButton getBtnAdjuntarArchivo() 
	{
		return btnAdjuntarArchivo;
	}
	
	public JButton getBtnAsignarReceptor() 
	{
		return btnAsignarReceptor;
	}
	
	public void show() 
	{
		setVisible(true);
	}
	
	//-------------------------------------------------------------------------------------	
	/*
	 * Returns a collection of Font names that are available from the
	 * system fonts and are matched with the desired font list (FONT_LIST).
	 */
	private Vector<String> getEditorFonts() 
	{
		String [] availableFonts =
			GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		Vector<String> returnList = new Vector<>();
	
		for (String font : availableFonts) 
		{
			if (FONT_LIST.contains(font)) 
			{
				returnList.add(font);
			}
		}
		return returnList;
	}

	private class EditButtonActionListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			editor__.requestFocusInWindow();
		}
	}

	private class ColorActionListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			Color newColor =
				JColorChooser.showDialog(new JFrame(), "Choose a color", Color.BLACK);
			if (newColor == null) 
			{
				editor__.requestFocusInWindow();
				return;
			}
			SimpleAttributeSet attr = new SimpleAttributeSet();
			StyleConstants.setForeground(attr, newColor);
			editor__.setCharacterAttributes(attr, false);
			editor__.requestFocusInWindow();
		}
	}

	private class TextAlignItemListener implements ItemListener 
	{
		@Override
		public void itemStateChanged(ItemEvent e) 
		{
			if ((e.getStateChange() != ItemEvent.SELECTED) ||
				(textAlignComboBox__.getSelectedIndex() == 0)) 
			{
				return;
			}
			
			String alignmentStr = (String) e.getItem();			
			int newAlignment = textAlignComboBox__.getSelectedIndex() - 1;
			// New alignment is set based on these values defined in StyleConstants:
			// ALIGN_LEFT 0, ALIGN_CENTER 1, ALIGN_RIGHT 2, ALIGN_JUSTIFIED 3
			textAlignComboBox__.setAction(new AlignmentAction(alignmentStr, newAlignment));	
			textAlignComboBox__.setSelectedIndex(0); // initialize to (default) select
			editor__.requestFocusInWindow();
		}
	} // TextAlignItemListener

	private class FontSizeItemListener implements ItemListener 
	{
		@Override
		public void itemStateChanged(ItemEvent e) 
		{
			if ((e.getStateChange() != ItemEvent.SELECTED) ||
				(fontSizeComboBox__.getSelectedIndex() == 0)) 
			{
				return;
			}	
			String fontSizeStr = (String) e.getItem();			
			int newFontSize = 0;
			try 
			{
				newFontSize = Integer.parseInt(fontSizeStr);
			}
			catch (NumberFormatException ex) 
			{
				return;
			}
			fontSizeComboBox__.setAction(new FontSizeAction(fontSizeStr, newFontSize));	
			fontSizeComboBox__.setSelectedIndex(0); // initialize to (default) select
			editor__.requestFocusInWindow();
		}
	} // FontSizeItemListener
	
	private class FontFamilyItemListener implements ItemListener 
	{
		@Override
		public void itemStateChanged(ItemEvent e) 
		{
			if ((e.getStateChange() != ItemEvent.SELECTED) ||
				(fontFamilyComboBox__.getSelectedIndex() == 0)) 
			{
				return;
			}
			String fontFamily = (String) e.getItem();
			fontFamilyComboBox__.setAction(new FontFamilyAction(fontFamily, fontFamily));	
			fontFamilyComboBox__.setSelectedIndex(0); // initialize to (default) select
			editor__.requestFocusInWindow();
		}
	} // FontFamilyItemListener
		
	private StyledDocument getEditorDocument() 
	{
		StyledDocument doc = (DefaultStyledDocument) editor__.getDocument();	
		return doc;
	}

	private boolean isBulletedPara(int paraEleStart) 
	{					
		if (getParaFirstCharacter(paraEleStart) == BULLET_CHAR) 
		{	
			return true;
		}	
		return false;
	}
	
	private char getParaFirstCharacter(int paraEleStart) 
	{	
		String firstChar = "";	
		try 
		{
			firstChar = editor__.getText(paraEleStart, 1);
		}
		catch (BadLocationException ex) 
		{	
			throw new RuntimeException(ex);
		}	
		return firstChar.charAt(0);
	}

	private void insertBullet(int insertPos, int attributesPos) 
	{							
		try 
		{
			getEditorDocument().insertString(insertPos,
												BULLET_STR_WITH_SPACE,
												getParaStartAttributes(attributesPos));
		}
		catch(BadLocationException ex) 
		{		
			throw new RuntimeException(ex);
		}
	}

	private AttributeSet getParaStartAttributes(int pos) 
	{
		StyledDocument doc = (DefaultStyledDocument) editor__.getDocument();
		Element	charEle = doc.getCharacterElement(pos);
		return charEle.getAttributes();
	}

	private void removeBullet(int removePos, int length) 
	{
		try 
		{
			getEditorDocument().remove(removePos, length);
		}
		catch(BadLocationException ex) 
		{		
			throw new RuntimeException(ex);
		}
	}

	public class BulletParaKeyListener implements KeyListener 
	{
		// These two variables are derived in the keyPressed and are used in
		// keyReleased method.
		private String prevParaText_;
		private int prevParaEleStart_;	
		// Identifies if a key is pressed in a bulleted para. 
		// This is required to distinguish from the numbered para.
		private boolean bulletedPara_; 

		@Override
		public void keyTyped(KeyEvent e) 
		{
			
		}

		@Override
		public void keyPressed(KeyEvent e) 
		{	
			bulletedPara_ = false;
			int pos = editor__.getCaretPosition();
			
			if (! isBulletedParaForPos(pos)) 
			{
				return;
			}
			
			Element paraEle = getEditorDocument().getParagraphElement(pos);
			
			switch (e.getKeyCode()) 
			{
				case KeyEvent.VK_LEFT: // same as that of VK_KP_LEFT
				case KeyEvent.VK_KP_LEFT: int newPos = pos - (BULLET_LENGTH + 1);
										doLeftArrowKeyRoutine(newPos, startPosPlusBullet__);
										break;			
				case KeyEvent.VK_DELETE: doDeleteKeyRoutine(paraEle, pos);
										break;
				case KeyEvent.VK_BACK_SPACE: doBackspaceKeyRoutine(paraEle);
										break;
				case KeyEvent.VK_ENTER: getPrevParaDetails(pos);
			}
		} // keyPressed()
		
		private boolean isBulletedParaForPos(int caretPos) 
		{
			Element paraEle = getEditorDocument().getParagraphElement(caretPos);
			if (isBulletedPara(paraEle.getStartOffset())) 
			{
				return true;
			}
			return false;
		}
		
		// This method is used with Enter key press routine.
		// Two instance variable values are derived here and are used
		// in the keyReleased() method: prevParaEleStart_ and prevParaText_
		private void getPrevParaDetails(int pos) 
		{
			pos =  pos - 1;
			if (isBulletedParaForPos(pos)) 
			{
				bulletedPara_ = true;
				Element paraEle = getEditorDocument().getParagraphElement(pos);
				prevParaEleStart_ = paraEle.getStartOffset();
				prevParaText_ =
						getPrevParaText(prevParaEleStart_, paraEle.getEndOffset());
			}
		}
		
		// Delete key press routine within bulleted para.
		private void doDeleteKeyRoutine(Element paraEle, int pos) 
		{
			int paraEleEnd = paraEle.getEndOffset();
			if (paraEleEnd > getEditorDocument().getLength()) 
			{
				return; // no next para, end of document text
			}
			if (pos == (paraEleEnd - 1)) 
			{ // last char of para; -1 is for CR	
				if (isBulletedParaForPos(paraEleEnd + 1)) 
				{
					// following para is bulleted, remove
					removeBullet(pos, BULLET_LENGTH);
				}
				// else, not a bulleted para
				// delete happens normally (one char)
			}
		}
		
		// Backspace key press routine within a bulleted para.
		// Also, see EditorCaretListener.
		private void doBackspaceKeyRoutine(Element paraEle) 
		{	
			// In case the position of cursor at the backspace is just 
			// before the bullet (that is BULLET_LENGTH).
			if (startPosPlusBullet__) 
			{
				removeBullet(paraEle.getStartOffset(), BULLET_LENGTH);
				startPosPlusBullet__ = false;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) 
		{
			if (! bulletedPara_) 
			{
				return;
			}	
			switch (e.getKeyCode()) 
			{
				case KeyEvent.VK_ENTER: doEnterKeyRoutine();
										break;
			}
		}

		// Enter key press routine within a bulleted para.
		// Also, see keyPressed().
		private void doEnterKeyRoutine() 
		{
			String prevParaText = prevParaText_;
			int prevParaEleStart = prevParaEleStart_;
			// Check if prev para with bullet has text					
			if (prevParaText.length() < 4) 
			{	
				// Para has bullet and no text, remove bullet+CR from para
				removeBullet(prevParaEleStart, (BULLET_LENGTH + 1));
				editor__.setCaretPosition(prevParaEleStart);
				return;
			}
			// Prev para with bullet and text
			// Insert bullet for next para (current position), and
			// prev para attributes are used for this bullet.	
			insertBullet(editor__.getCaretPosition(), prevParaEleStart); 
		}		
	} // BulletParaKeyListener

	private String getPrevParaText(int prevParaEleStart, int prevParaEleEnd) 
	{		
		String prevParaText = "";		
		try 
		{
			prevParaText = getEditorDocument().getText(prevParaEleStart, 
											(prevParaEleEnd -  prevParaEleStart));
		}
		catch(BadLocationException ex) 
		{		
			throw new RuntimeException(ex);
		}	
		return prevParaText;
	}
	
	private void doLeftArrowKeyRoutine(int pos, boolean startTextPos) 
	{
		if (! startTextPos) 
		{
			return;
		}
		// Check if this is start of document
		Element paraEle =
				getEditorDocument().getParagraphElement(editor__.getCaretPosition());
		int newPos = (paraEle.getStartOffset() == 0) ? 0 : pos;
		// Position the caret in an EDT, otherwise the caret is
		// positioned at one less position than intended.
		SwingUtilities.invokeLater(new Runnable() 
		{
			public void run() 
			{
				editor__.setCaretPosition(newPos);
			}
		});
	}
	
}