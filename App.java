package Quantization;

import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.filechooser.FileFilter;

public class App extends JFrame {

	private JButton Beowse;
	private JButton save;

	String path, newpath;
	private TextField txt, ext, lev;
	private JLabel l1, l2, l3;
	private JRadioButton radiocompres;
	private JRadioButton radiodecompres;
	private ButtonGroup g;

	App() {
		super("Quantization");
		Panel f = new Panel();
		setLayout(null);
		setVisible(true);
		setBounds(400, 200, 400, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Beowse = new JButton("Browse");
		save = new JButton("save");
		radiocompres = new JRadioButton("compress", true);
		radiodecompres = new JRadioButton("Decompress");
		g = new ButtonGroup();
		g.add(radiocompres);
		g.add(radiodecompres);
		txt = new TextField();
		txt.setBounds(100, 30, 115, 25);
		f.add(txt);
		add(txt);
		ext = new TextField();
		ext.setBounds(100, 60, 115, 25);
		f.add(ext);
		ext.setText(".jpg");
		add(ext);
		lev = new TextField();
		lev.setBounds(100, 90, 115, 25);
		f.add(lev);
		add(lev);
		Beowse.setBounds(250, 40, 115, 30);
		f.add(Beowse);
		add(Beowse);
		save.setBounds(140, 150, 115, 30);
		save.setEnabled(false);
		f.add(save);
		add(save);

		l1 = new JLabel("Name");

		l1.setBounds(20, 30, 115, 30);
		f.add(l1);
		add(l1);
		l1.setVisible(true);
		l2 = new JLabel("Extention");

		l2.setBounds(20, 60, 115, 30);
		f.add(l2);
		add(l2);
		l2.setVisible(true);
		l3 = new JLabel("level");

		l3.setBounds(20, 90, 115, 30);
		f.add(l3);
		add(l3);
		l3.setVisible(true);
		radiocompres.setVisible(true);
		radiocompres.setBounds(220, 120, 100, 30);
		f.add(radiocompres);
		add(radiocompres);
		radiodecompres.setVisible(true);
		radiodecompres.setBounds(50, 120, 100, 30);
		f.add(radiodecompres);
		add(radiodecompres);

		Beowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser fileChooser = new JFileChooser();

				fileChooser.setFileFilter(new FileFilter() {

					@Override
					public String getDescription() {
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public boolean accept(File arg0) {
						// TODO Auto-generated method stub
						if (arg0.isDirectory())
							return true;
						if (arg0.getName().contains(".jpg")
								|| arg0.getName().contains(".gif")
								|| arg0.getName().contains(".png"))
							return true;
						return false;
					}
				});
				;

				int rVal = fileChooser.showOpenDialog(null);
				if (rVal == JFileChooser.APPROVE_OPTION) {
					path = (fileChooser.getSelectedFile().getAbsolutePath());
					newpath = path.substring(0, path.indexOf(fileChooser
							.getSelectedFile().getName()))
							+ txt.getText() + ext.getText();

					save.setEnabled(true);
					// System.out.println(newpath);

				}
			}
		});
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (radiocompres.isSelected()) {
					quantization.compress(path, newpath, lev);
				} else
					quantization.Decompress(path, newpath, lev);

			}
		});

	}
}
