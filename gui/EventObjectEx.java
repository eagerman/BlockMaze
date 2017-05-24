package gui;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;

import static javax.swing.JFrame.EXIT_ON_CLOSE;


public class EventObjectEx extends JFrame {

    private JList mylist;
    private DefaultListModel model;
    

    public EventObjectEx() {
        
        initUI();
    }
    
    private void initUI() {

        model = new DefaultListModel();
        mylist = new JList(model);
        mylist.setBorder(BorderFactory.createEtchedBorder());

        JButton okBtn = new JButton("OK");
        okBtn.addActionListener(new ClickAction());
      
        createLayout(okBtn, mylist);

        setTitle("Event object");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    private void createLayout(JComponent... arg) {
        
        Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);        
        
        gl.setAutoCreateContainerGaps(true);
        gl.setAutoCreateGaps(true);        

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
                .addComponent(arg[1], 250, GroupLayout.PREFERRED_SIZE, 
                        GroupLayout.DEFAULT_SIZE)
        );

        gl.setVerticalGroup(gl.createParallelGroup()
                .addComponent(arg[0])
                .addComponent(arg[1], 150, GroupLayout.PREFERRED_SIZE, 
                        GroupLayout.DEFAULT_SIZE)
        );

        pack();
    }        
    
    private class ClickAction extends AbstractAction {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            Locale locale = Locale.getDefault();
            Date date = new Date(e.getWhen());
            String tm = DateFormat.getTimeInstance(DateFormat.SHORT,
                    locale).format(date);

            if (!model.isEmpty()) {
                model.clear();
            }

            if (e.getID() == ActionEvent.ACTION_PERFORMED) {
                model.addElement("Event Id: ACTION_PERFORMED");
            }

            model.addElement("Time: " + tm);

            String source = e.getSource().getClass().getName();
            model.addElement("Source: " + source);

            int mod = e.getModifiers();

            StringBuffer buffer = new StringBuffer("Modifiers: ");

            if ((mod & ActionEvent.ALT_MASK) > 0) {
                buffer.append("Alt ");
            }

            if ((mod & ActionEvent.SHIFT_MASK) > 0) {
                buffer.append("Shift ");
            }

            if ((mod & ActionEvent.META_MASK) > 0) {
                buffer.append("Meta ");
            }

            if ((mod & ActionEvent.CTRL_MASK) > 0) {
                buffer.append("Ctrl ");
            }

            model.addElement(buffer);
        }
    }        
/*
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            EventObjectEx ex = new EventObjectEx();
            ex.setVisible(true);
        });
    }
  */  
}
