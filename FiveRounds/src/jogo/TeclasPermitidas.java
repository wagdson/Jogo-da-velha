package jogo;

import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class TeclasPermitidas extends PlainDocument {
    

    @Override
    public void insertString(int offset, String str, javax.swing.text.AttributeSet attr) 
            throws BadLocationException{
  
        super.insertString(offset, str.toUpperCase().replaceAll("[^a-z|^A-Z]", ""), attr);
        
      
    }

    public void replace(int offset, String str, javax.swing.text.AttributeSet attr) throws BadLocationException {
        super.insertString(offset, str.toUpperCase().replaceAll("[^a-z|^A-Z]", ""), attr);
   }
}