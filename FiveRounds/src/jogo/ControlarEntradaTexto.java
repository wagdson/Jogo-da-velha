package jogo;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class ControlarEntradaTexto extends PlainDocument {

     @Override
    public void insertString(int offs, String str, AttributeSet attr) throws BadLocationException {
        int tamanho = (this.getLength() + str.length());

        if (tamanho <= 10) {
            
            super.insertString(offs, str.toUpperCase().replaceAll("^A-Z",""), attr);
        } else {
            
        }
    }
}
