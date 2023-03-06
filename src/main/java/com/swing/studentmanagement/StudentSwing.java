package com.swing.studentmanagement;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.dao.student.StudentDao;
import com.databasetable.student.Student;

public class StudentSwing extends JFrame implements ActionListener {
	private Container container;
	private JLabel titleLabel, idLabel, nameLabel, marksLabel, phNumLabel, emailLabel, genderLabel;
	private JCheckBox maleBox, femaleBox, otherBox;
	private ButtonGroup genderGroup;
	private Font font;
	private JTable table;
	private StudentDao sDao = new StudentDao();
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	private JTextField idField, nameField, marksField, phNumField, emailField;
	private JButton addButton, updateButton, deleteButton, clearButton;

	private String[] col = { "id", "name", "marks", "phone number", "email id", "gender" };

	private String[] row = new String[6];

	public StudentSwing() {
		// TODO Auto-generated constructor stub
		setSize(780, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Student Management System");
		setLocationRelativeTo(null);
		initComponents();
		tableDataOfStudents();
	}

	public void tableDataOfStudents() {
		List<Student> s = sDao.getAllStudents();

		for (Student student : s) {
			String[] row = { Integer.toString(student.getId()), student.getName(), Double.toString(student.getMarks()),
					Long.toString(student.getPh_num()), student.getEmail(), student.getGender() };
			model.addRow(row);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == addButton) {
			row[0] = idField.getText();
			row[1] = nameField.getText();
			row[2] = marksField.getText();
			row[3] = phNumField.getText();
			row[4] = emailField.getText();

			Student student = new Student();
			student.setId(Integer.parseInt(idField.getText()));
			student.setName(nameField.getText());
			student.setMarks(Double.parseDouble(marksField.getText()));
			student.setPh_num(Long.parseLong(phNumField.getText()));
			student.setEmail(emailField.getText());

			if (maleBox.isSelected()) {
				row[5] = "Male";
				student.setGender("Male");
			} else if (femaleBox.isSelected()) {
				row[5] = "Female";
				student.setGender("Female");
			} else {
				row[5] = "Other";
				student.setGender("Other");
			}

			int numberOfRow = table.getRowCount();
			int count = 0;
			for (int i = 0; i < numberOfRow; i++) {
				if (idField.getText().equals(model.getValueAt(i, 0).toString())) {
					count = 1;
				}
			}
			if (count == 1) {
				JOptionPane.showMessageDialog(null, "Id already present");
			} else {
				sDao.saveStudent(student);
				model.setRowCount(0);
				tableDataOfStudents();
			}
		} else if (e.getSource() == updateButton) {
			int numberOfRow = table.getSelectedRow();
			String iString = idField.getText();
			String mString = marksField.getText();
			String nString = nameField.getText();
			String phString = phNumField.getText();
			String eString = emailField.getText();
			Student s = new Student();
			s.setId(Integer.parseInt(iString));
			s.setName(nameField.getText());
			s.setMarks(Double.parseDouble(mString));
			s.setPh_num(Long.parseLong(phString));
			s.setEmail(emailField.getText());

			if (maleBox.isSelected()) {
				s.setGender("Male");
			} else if (femaleBox.isSelected()) {
				s.setGender("Female");
			} else {
				s.setGender("Other");
			}
			sDao.updateStudent(s);
			model.setRowCount(0);
			tableDataOfStudents();
		} else if (e.getSource() == deleteButton) {
			int numOfRow = table.getSelectedRow();
			String iString = idField.getText();
			String mString = marksField.getText();
			String phString = phNumField.getText();

			Student s = new Student();
			s.setId(Integer.parseInt(iString));
			s.setName(nameField.getText());
			s.setMarks(Double.parseDouble(mString));
			s.setPh_num(Long.parseLong(phString));
			s.setEmail(emailField.getText());

			if (maleBox.isSelected()) {
				s.setGender("Male");
			} else if (femaleBox.isSelected()) {
				s.setGender("Female");
			} else {
				s.setGender("Other");
			}
			if (numOfRow >= 0) {
				JOptionPane.showMessageDialog(null, "Deleted Successfully");
				sDao.deleteStudent(s);
				model.setRowCount(0);
				tableDataOfStudents();
			} else {
				JOptionPane.showMessageDialog(null, "No row selected");
			}
		} else if (e.getSource() == clearButton) {
			idField.setText("");
			marksField.setText("");
			nameField.setText("");
			phNumField.setText("");
			emailField.setText("");
			genderGroup.clearSelection();
		}
	}

	public void initComponents() {
		container = this.getContentPane();
		container.setLayout(null);
		container.setBackground(Color.ORANGE);

		font = new Font("Arial", Font.BOLD, 16);
		genderGroup = new ButtonGroup();

		titleLabel = new JLabel("Student Management");
		titleLabel.setFont(font);
		titleLabel.setBounds(140, 10, 250, 50);
		container.add(titleLabel);

		idLabel = new JLabel("ID   ");
		idLabel.setBounds(10, 80, 140, 30);
		idLabel.setFont(font);
		container.add(idLabel);

		idField = new JTextField();
		idField.setBounds(130, 80, 200, 30);
		idField.setFont(font);
		container.add(idField);

		addButton = new JButton("Add");
		addButton.setBounds(430, 80, 100, 30);
		addButton.setFont(font);
		addButton.addActionListener(this);
		container.add(addButton);

		nameLabel = new JLabel("Name   ");
		nameLabel.setBounds(10, 130, 140, 30);
		nameLabel.setFont(font);
		container.add(nameLabel);

		nameField = new JTextField();
		nameField.setBounds(130, 130, 200, 30);
		nameField.setFont(font);
		container.add(nameField);

		updateButton = new JButton("Update");
		updateButton.setBounds(430, 130, 100, 30);
		updateButton.setFont(font);
		updateButton.addActionListener(this);
		container.add(updateButton);

		marksLabel = new JLabel("Marks   ");
		marksLabel.setBounds(10, 180, 140, 30);
		marksLabel.setFont(font);
		container.add(marksLabel);

		marksField = new JTextField();
		marksField.setBounds(130, 180, 200, 30);
		marksField.setFont(font);
		container.add(marksField);

		deleteButton = new JButton("Delete");
		deleteButton.setBounds(430, 180, 100, 30);
		deleteButton.setFont(font);
		deleteButton.addActionListener(this);
		container.add(deleteButton);

		phNumLabel = new JLabel("Phone Number   ");
		phNumLabel.setBounds(10, 230, 140, 30);
		phNumLabel.setFont(font);
		container.add(phNumLabel);

		phNumField = new JTextField();
		phNumField.setBounds(130, 230, 200, 30);
		phNumField.setFont(font);
		container.add(phNumField);

		clearButton = new JButton("Clear");
		clearButton.setBounds(430, 230, 100, 30);
		clearButton.setFont(font);
		clearButton.addActionListener(this);
		container.add(clearButton);

		emailLabel = new JLabel("Email id   ");
		emailLabel.setBounds(10, 280, 140, 30);
		emailLabel.setFont(font);
		container.add(emailLabel);

		emailField = new JTextField();
		emailField.setBounds(130, 280, 200, 30);
		emailField.setFont(font);
		container.add(emailField);

		genderLabel = new JLabel("Gender   ");
		genderLabel.setBounds(10, 330, 140, 30);
		genderLabel.setFont(font);
		container.add(genderLabel);

		maleBox = new JCheckBox("Male");
		maleBox.setBounds(130, 330, 70, 30);
		maleBox.setBackground(Color.ORANGE);
		maleBox.setFont(font);
		container.add(maleBox);

		femaleBox = new JCheckBox("Female");
		femaleBox.setBounds(200, 330, 90, 30);
		femaleBox.setBackground(Color.ORANGE);
		femaleBox.setFont(font);
		container.add(femaleBox);

		otherBox = new JCheckBox("Other");
		otherBox.setBounds(290, 330, 90, 30);
		otherBox.setBackground(Color.ORANGE);
		otherBox.setFont(font);
		container.add(otherBox);

		genderGroup.add(maleBox);
		genderGroup.add(femaleBox);
		genderGroup.add(otherBox);

		table = new JTable();
		model = new DefaultTableModel();
		model.setColumnIdentifiers(col);
		table.setModel(model);
		table.setFont(font);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				int selected = table.getSelectedRow();
				String idString = model.getValueAt(selected, 0).toString();
				String nameString = model.getValueAt(selected, 1).toString();
				String marksString = model.getValueAt(selected, 2).toString();
				String phNumString = model.getValueAt(selected, 3).toString();
				String emailString = model.getValueAt(selected, 4).toString();
				String genderString = model.getValueAt(selected, 5).toString();

				idField.setText(idString);
				nameField.setText(nameString);
				marksField.setText(marksString);
				phNumField.setText(phNumString);
				emailField.setText(emailString);
				if (genderString.equals("Male")) {
					maleBox.setSelected(true);
				} else if (genderString.equals("Female")) {
					femaleBox.setSelected(true);
				} else {
					otherBox.setSelected(true);
				}
			}
		});
		table.setSelectionBackground(Color.GREEN);
		table.setBackground(Color.WHITE);
		table.setRowHeight(30);

		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 390, 740, 265);
		container.add(scrollPane);

	}

	public static void main(String[] args) {
		StudentSwing frame = new StudentSwing();
		frame.setVisible(true);
	}
}
