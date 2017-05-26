import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.e                model.clear();
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
