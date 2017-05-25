import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import static javax.swing.GroupLayout.Alignment.CENTER;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class ListModels extends JFrame {

    private DefaultListModel model;
    private JList list;
    private JButton remallbtn;
    private JButton addbtn;
    private JButton renbtn;
    private JButton delbtn;

    public ListModels() {

        initUI();
    }

    private void createList() {

        model = new DefaultListModel();
        model.addElement("Amelie");
        model.addElement("Aguirre, der Zorn Gottes");
        model.addElement("Fargo");
        model.addElement("Exorcist");
        model.addElement("Schindler's list");

        list = new JList(model);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        list.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getClickCount() == 2) {
                    int index = list.locationToIndex(e.getPoint());
                    Object item = model.getElementAt(index);
                    String text = JOptionPane.showInputDialog("Rename item", item);
                    String newitem = null;
                    if (text != null) {
                        newitem = text.trim();
                    } else {
                        return;
                    }

                    if (!newitem.isEmpty()) {
                        model.remove(index);
                        model.add(index, newitem);
                        ListSelectionModel selmodel = list.getSelectionModel();
                        selmodel.setLeadSelectionIndex(index);
                    }
                }
            }
        });
    }

    private void createButtons() {

        remallbtn = new JButton("Remove All");
        addbtn = new JButton("Add");
        renbtn = new JButton("Rename");
        delbtn = new JButton("Delete");

        addbtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String text = JOptionPane.showInputDialog("Add a new item");
                String item = null;

                if (text != null) {
                    item = text.trim();
                } else {
                    return;
                }

                if (!item.isEmpty()) {
                    model.addElement(item);
                }
            }
        });

        delbtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {

                ListSelectionModel selmodel = list.getSelectionModel();
                int index = selmodel.getMinSelectionIndex();
                if (index >= 0) {
                    model.remove(index);
                }
            }

        });

        renbtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                ListSelectionModel selmodel = list.getSelectionModel();
                int index = selmodel.getMinSelectionIndex();
                if (index == -1) {
                    return;
                }

                Object item = model.getElementAt(index);
                String text = JOptionPane.showInputDialog("Rename item", item);
                String newitem = null;

                if (text != null) {
                    newitem = text.trim();
                } else {
                    return;
                }

                if (!newitem.isEmpty()) {
                    model.remove(index);
                    model.add(index, newitem);
                }
            }
        });

        remallbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.clear();
            }
        });

    }

    private void initUI() {

        createList();
        createButtons();
        JScrollPane scrollpane = new JScrollPane(list);

        Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);

        gl.setAutoCreateContainerGaps(true);
        gl.setAutoCreateGaps(true);

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addComponent(scrollpane)
                .addGroup(gl.createParallelGroup()
                        .addComponent(addbtn)
                        .addComponent(renbtn)
                        .addComponent(delbtn)
                        .addComponent(remallbtn))
        );

        gl.setVerticalGroup(gl.createParallelGroup(CENTER)
                .addComponent(scrollpane)
                .addGroup(gl.createSequentialGroup()
                        .addComponent(addbtn)
                        .addComponent(renbtn)
                        .addComponent(delbtn)
                        .addComponent(remallbtn))
        );

        gl.linkSize(addbtn, renbtn, delbtn, remallbtn);

        pack();

        setTitle("JList models");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
/*
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ListModels ex = new ListModels();
                ex.setVisible(true);
            }
        });
    }
    */
}