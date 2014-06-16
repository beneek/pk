package pl.Java.kalorie;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

@SuppressWarnings("serial")
public abstract class WeightTextField extends JTextField{

		
		JTextField pole;
		int ktory;
		int HL;
		int flaga;
		
		public WeightTextField (){
			super();
			pole=null;
			HL=0;
			ktory=0;
			flaga=0;
			
			this.addFocusListener(new FocusListener() {
				
				
				public void focusLost(FocusEvent e) {
					flaga=0;
				}
				
				
				public void focusGained(FocusEvent e) {
					flaga=1;
					setCaretPosition(getText().length());
				}
			});
			((AbstractDocument) this.getDocument()).setDocumentFilter( new DocumentFilter(){

	            @Override
	            public void insertString(FilterBypass fb, int offset,
	                    String string, AttributeSet attr)
	                    throws BadLocationException {
	                fb.insertString(offset, string.replaceAll("[^\\d]", ""), attr);
	            }

	            @Override
	            public void replace(FilterBypass fb, int offset, int length,
	                    String text, AttributeSet attrs)
	                    throws BadLocationException {

	                fb.replace(offset, length, text.replaceAll("[^\\d]", ""), attrs);
	            }
	        });
	        
	        this.getDocument().addDocumentListener(new DocumentListener() {

	            public void removeUpdate(DocumentEvent e) {
	                change();
	            }

	            public void insertUpdate(DocumentEvent e) {
	                change();
	            }

	            public void changedUpdate(DocumentEvent e) {
	            	
	           }
	        });

		}
		
		void setValue(int ii,JTextField i_pole){
			pole=i_pole;
			ktory=ii;
		}
		
		public void  change(){};
}