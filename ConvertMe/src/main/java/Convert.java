
import com.aspose.words.*;
import com.spire.pdf.FileFormat;
import com.spire.pdf.PdfDocument;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Convert extends JFrame {

    final private JLabel choose;
    final private JTextField paths;
    final private JComboBox type;
    final private JButton open,save,clear,close,update;
    final private JFileChooser opn,sav;

    final private FileNameExtensionFilter ex = new FileNameExtensionFilter("only word file (.doc)", ("doc"));
    final private FileNameExtensionFilter ex1 = new FileNameExtensionFilter("only word file (.docx)", ("docx"));
    final private FileNameExtensionFilter ex2 = new FileNameExtensionFilter("only pdf file (.pdf)", ("pdf"));
    final private FileNameExtensionFilter ex3 = new FileNameExtensionFilter("only xps file (.xps)", ("xps"));
    final private FileNameExtensionFilter ex4 = new FileNameExtensionFilter("only html file (.html)", ("html"));

    private String from,to;

    public Convert(){
        setVisible(true);
        setSize(700,500);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Convert Files");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = this.getContentPane();
        c.setBackground(new Color(204,255,153));
        c.setLayout(null);


        choose = new JLabel("Choose file");
        choose.setBounds(15,15,100,30);
        choose.setFont(new Font("arial",Font.BOLD,15));

        paths = new JTextField();
        paths.setBounds(120,15,400,30);
        paths.setBackground(Color.WHITE);
        paths.setEditable(false);

        String arr[] ={"SELECT THE FORMAT TO CONVERT -:","DOC to PDF","DOCX to PDF","XPS to PDF","PDF to DOCX","HTML to PDF"};
        type = new JComboBox(arr);
        type.setBackground(Color.WHITE);
        type.setEditable(false);
        type.setToolTipText("select your option");
        type.setBounds(120,60,400,30);

        open = new JButton("Open");
        open.setBounds(530,15,100,30);
        open.setFont(new Font("arial",Font.BOLD,15));
        open.setCursor(new Cursor(Cursor.HAND_CURSOR));


        update = new JButton("Update");
        update.setBounds(530,60,100,30);
        update.setFont(new Font("arial",Font.BOLD,15));
        update.setCursor(new Cursor(Cursor.HAND_CURSOR));

        clear = new JButton("Clear");
        clear.setBounds(270,400,100,30);
        clear.setFont(new Font("arial",Font.BOLD,15));
        clear.setCursor(new Cursor(Cursor.HAND_CURSOR));

        save = new JButton("Save");
        save.setBounds(380,400,100,30);
        save.setFont(new Font("arial",Font.BOLD,15));
        save.setCursor(new Cursor(Cursor.HAND_CURSOR));
        save.setEnabled(false);

        close = new JButton("Close");
        close.setBounds(490,400,100,30);
        close.setFont(new Font("arial",Font.BOLD,15));
        close.setCursor(new Cursor(Cursor.HAND_CURSOR));

        c.add(choose);
        c.add(paths);
        c.add(type);
        c.add(open);
        c.add(clear);
        c.add(close);
        c.add(save);
        c.add(update);

            //adding actions to clear button
            clear.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    paths.setText("");
                    save.setEnabled(false);
                }
            });

            //adding actions to update button
            open.setEnabled(false);

            update.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    paths.setText("");

                    if(type.getSelectedIndex()==0){
                        open.setEnabled(false);
                    }  else if (type.getSelectedIndex() == 1) {
                        open.setEnabled(true);
                        opn.resetChoosableFileFilters();
                        opn.addChoosableFileFilter(ex);
                    } else if (type.getSelectedIndex() == 2) {
                        open.setEnabled(true);
                        opn.resetChoosableFileFilters();
                        opn.addChoosableFileFilter(ex1);
                    } else if (type.getSelectedIndex() == 3) {
                        open.setEnabled(true);
                        opn.resetChoosableFileFilters();
                        opn.addChoosableFileFilter(ex3);
                    } else if (type.getSelectedIndex() == 4) {
                        open.setEnabled(true);
                        opn.resetChoosableFileFilters();
                        opn.addChoosableFileFilter(ex2);
                    } else if (type.getSelectedIndex() == 5) {
                        open.setEnabled(true);
                        opn.resetChoosableFileFilters();
                        opn.addChoosableFileFilter(ex4);
                    }
                    else {

                        open.setEnabled(false);
                    }
                }
            });

            //adding actions to close button
            close.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   System.exit(0);
                }
            });

            //adding actions to open button
            opn = new JFileChooser("D:/");
            opn.setAcceptAllFileFilterUsed(false);
            open.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    int a = opn.showOpenDialog(null);
                        if (a == JFileChooser.APPROVE_OPTION) {
                            from = opn.getSelectedFile().getAbsolutePath();
                            paths.setText(from);
                            save.setEnabled(true);
                        }

                }
            });

            //adding actions to save button
            sav = new JFileChooser("d:/");
            PdfSaveOptions ps = new PdfSaveOptions();
            ps.setCompliance(PdfCompliance.PDF_15) ;
            save.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int a = sav.showSaveDialog(null);
                    if (a == JFileChooser.APPROVE_OPTION) {
                        to = sav.getSelectedFile().getAbsolutePath();
                        try {
                            switch(type.getSelectedIndex()){
                                case 1:
                                    Document d = new Document(from);
                                    d.save(to,ps);
                                    break;
                                case 2:
                                    Document d1 = new Document(from);
                                    d1.save(to,ps);
                                    break;
                                case 3:
                                    Document d2 = new Document(from);
                                    d2.save(to,ps);
                                    break;
                                case 4:
                                    PdfDocument doc4 = new PdfDocument();
                                    //load a sample PDF file
                                    doc4.loadFromFile(from);
                                    //save as .doc file
                                    doc4.saveToFile(to, FileFormat.DOCX);
                                    doc4.close();
                                    break;
                                case 5:
                                    Document d5 = new Document(from);
                                    d5.save(to,ps);
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(null, "CANT PERFORM THE OPERATION", "Error", JOptionPane.ERROR_MESSAGE);
                                    break;
                            }
                            JOptionPane.showMessageDialog(null, "Document Created", "Message", JOptionPane.INFORMATION_MESSAGE);
                            paths.setText("");
                            to="";
                            from="";
                            save.setEnabled(false);
                        }catch (Exception e1){
                            e1.printStackTrace();
                            JOptionPane.showMessageDialog(null, "CANT PERFORM THE OPERATION", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "CANCELLED OPERATION", "Message", JOptionPane.INFORMATION_MESSAGE);
                        sav.setEnabled(false);
                    }
                }
            });

    }

    public static void main(String...args) throws Exception{
        new Convert();
    }
}
