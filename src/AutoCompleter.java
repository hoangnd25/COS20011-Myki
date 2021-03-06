import javax.swing.text.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * The simple Auto completer. <p>
 * This class will create a popup list with suggestion. <p>
 * When user selected an item from the list, a formatted string will be set to the text field <p>
 * Children class must implement two methods: <p>
 * - {@link #getSuggestionForValue(String) getSuggestionForValue} <p>
 * - {@link #formatSelectedValue(Object) formatSelectedValue} <p>
 * Create auto completer associate with a text field
 */
public abstract class AutoCompleter{

    private JList list = new JList();
    private JPopupMenu popup = new JPopupMenu();
    private JTextComponent textComp;

    /**
     * Instantiates a new Auto completer for a text field
     *
     * @param comp the text field that will be attached with an auto completer
     */
    public AutoCompleter(JTextField comp){
        textComp = comp;
        // Retain selection after losing focus
        textComp.setCaret(new DefaultCaret(){
            private boolean isFocused;
            public void setSelectionVisible(boolean hasFocus) {
                if (hasFocus != isFocused) {
                    isFocused = hasFocus;
                    super.setSelectionVisible(false);
                    super.setSelectionVisible(true);
                }
            }
        });

        JScrollPane scroll = new JScrollPane(list);
        scroll.setBorder(null);

        list.setFocusable(false);
        list.setRequestFocusEnabled(false);
        scroll.getVerticalScrollBar().setFocusable( false );
        scroll.getHorizontalScrollBar().setFocusable( false );

        popup.setBorder(BorderFactory.createLineBorder(Color.black));
        popup.add(scroll);

        textComp.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "showAction");
        textComp.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "upAction");
        textComp.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "hidePopupAction");

        registerActionMap();
        registerEventListener();
    }

    private void showPopup(){
        popup.setVisible(false);
        List suggestions = getSuggestionForValue(textComp.getText());
        list.setListData(suggestions.toArray());
        int listSize = list.getModel().getSize();

        if(textComp.isEnabled() && suggestions.size() > 0 && listSize > 0){
            textComp.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "acceptAction");
            list.setVisibleRowCount(listSize < 10 ? listSize : 10);
            int x = 0;
//            try{
//                int pos = Math.min(textComp.getCaret().getDot(), textComp.getCaret().getMark());
//                x = textComp.getUI().modelToView(textComp, pos).x;
//            } catch(BadLocationException e){
//                // this should never happen!!!
//                e.printStackTrace();
//            }
            popup.show(textComp, x, textComp.getHeight());
        }else{
            popup.setVisible(false);
        }
        textComp.requestFocus();
    }

    /**
     * Try to select an item
     * @param value number that specify position of the needed item related to current selected item
     */
    private void selectItem(int value){
        int potentialIndex = list.getSelectedIndex() + value;

        if(potentialIndex < 0 || potentialIndex > (list.getModel().getSize() - 1)){
            return;
        }else{
            list.setSelectedIndex(potentialIndex);
            list.ensureIndexIsVisible(potentialIndex);
        }
    }

    /**
     * Get currently selected item and format it then set it to the textfield
     */
    private void acceptItem(){
        Object selected = list.getSelectedValue();
        if(selected == null)
            return;

        textComp.setText(formatSelectedValue(selected));
        popup.setVisible(false);
    }

    private void registerActionMap(){
        textComp.getActionMap().put("showAction", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                JComponent tf = (JComponent)e.getSource();
                //AutoCompleter completer = (AutoCompleter)tf.getClientProperty(AutoCompleter);
                if(tf.isEnabled()){
                    if(popup.isVisible())
                        selectItem(+1);
                    else
                        showPopup();
                }
            }
        });
        textComp.getActionMap().put("upAction", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                JComponent tf = (JComponent)e.getSource();
                if(tf.isEnabled()){
                    if(popup.isVisible())
                        selectItem(-1);
                }
            }
        });
        textComp.getActionMap().put("hidePopupAction", new AbstractAction() {
            public void actionPerformed(ActionEvent e){
                JComponent tf = (JComponent)e.getSource();
                if(tf.isEnabled())
                    popup.setVisible(false);
            }
        });
        textComp.getActionMap().put("acceptAction", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                acceptItem();
            }
        });
    }

    private void registerEventListener(){
        textComp.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                showPopup();
            }
            public void removeUpdate(DocumentEvent e) {
                showPopup();
            }
            public void changedUpdate(DocumentEvent e) {}
        });

        popup.addPopupMenuListener(new PopupMenuListener() {
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                textComp.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), null);
            }

            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            }

            public void popupMenuCanceled(PopupMenuEvent e) {
            }
        });

        list.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                acceptItem();
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        });
    }

    /**
     * Gets suggestion for a given string.
     *
     * @param value this value will be used to generate suggestions
     * @return the suggestion for value
     */
    protected abstract List getSuggestionForValue(String value);

    /**
     * Format selected item.
     *
     * @param value the item
     * @return the formatted string taht will be set to the text field
     */
    protected abstract String formatSelectedValue(Object value);
}