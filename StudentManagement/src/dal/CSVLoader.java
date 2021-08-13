package dal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import bll.Student;
import gui.StudentList;

public class CSVLoader {

	private StudentList sl;

	public CSVLoader(StudentList sl) {
		this.sl = sl;
	}

	public void loadFromFile() {
		File f = chooseFile();
		BufferedReader inputStream = null;
		String l;
		Student tempStudent = null;
		String[] splittedLine = null;
		ArrayList<Student> newStudents = new ArrayList<Student>();
		try {
			inputStream = new BufferedReader(new FileReader(f.getAbsolutePath()));
			while ((l = inputStream.readLine()) != null) {
				tempStudent = new Student();
				splittedLine = l.split(";");
				tempStudent.setNumber(Integer.parseInt(splittedLine[0]));
				tempStudent.setLastName(splittedLine[1]);
				tempStudent.setFirstName(splittedLine[2]);
				newStudents.add(tempStudent);
			}
			sl.FillList(newStudents);
		} catch (IOException e) {
			System.err.println("Fehler beim Laden der Datei!");
		} catch (NullPointerException e) {
			System.err.println("Keine Datei wurde ausgewaehlt!");
		} catch (NumberFormatException ex) {
			System.err.println("Fehler beim Parsen der Katalognummer!");
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void writeFile() {
		File f = chooseFile();
		BufferedWriter outputStream = null;
		ArrayList<Student> students = sl.getStudents();
		try {
			outputStream = new BufferedWriter(new FileWriter(f.getAbsolutePath()));
			for(Student s : students) {
				outputStream.write(s.getNumber() + ";" + s.getLastName() +";" + s.getFirstName() + "\n");
			}
		} catch (IOException e) {
			System.err.println("Fehler beim Schreiben der Datei!");
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private File chooseFile() {
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Waehle die Datei");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv");
		fc.addChoosableFileFilter(filter);
		File selectedFile = null;
		if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			selectedFile = fc.getSelectedFile();
		}
		return selectedFile;
	}

}
