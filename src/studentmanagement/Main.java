package studentmanagement;
import java.io.*;
import java.util.Scanner;

import java.util.ArrayList;

public class Main {
	@SuppressWarnings("unchecked")
	public static ArrayList<StudentManager> loadFromFile() {
	    ArrayList<StudentManager> students = new ArrayList<>();

	    try {
	        ObjectInputStream ois =
	                new ObjectInputStream(new FileInputStream("students.dat"));
	        students = (ArrayList<StudentManager>) ois.readObject();
	        ois.close();
	    } catch (Exception e) {
	        // first time file may not exist, so ignore
	    }

	    return students;
	}

	
	public static void saveToFile(ArrayList<StudentManager> students) {
	    try {
	        ObjectOutputStream oos =
	                new ObjectOutputStream(new FileOutputStream("students.dat"));
	        oos.writeObject(students);
	        oos.close();
	    } catch (IOException e) {
	        System.out.println("Error saving file");
	    }
	}


    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);

    	ArrayList<StudentManager> students = loadFromFile();


        

        
        while (true) {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Search Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.println("7. Sort by Roll Number");
            System.out.println("8. Sort by Marks");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            
            
            switch (choice) {
            case 1:
                System.out.print("Enter Roll Number: ");
                int roll = sc.nextInt();

                // 🔍 Check for duplicate roll number
                boolean rollExists = false;
                for (StudentManager s : students) {
                    if (s.getRollNo() == roll) {
                        rollExists = true;
                        break;
                    }
                }

                if (rollExists) {
                    System.out.println("❌ Roll number already exists. Please enter a unique roll number.");
                    break; // stop add operation
                }

                sc.nextLine(); // clear buffer
                System.out.print("Enter Name: ");
                String name = sc.nextLine();

                System.out.print("Enter Marks: ");
                double marks = sc.nextDouble();

                students.add(new StudentManager(roll, name, marks));
                saveToFile(students); // ✅ auto-save

                System.out.println("✅ Student added successfully!");
                break;


            case 2:
                if (students.isEmpty()) {
                    System.out.println("No students found.");
                } else {
                    for (StudentManager s : students) {
                        s.display();
                    }
                }
                break;

                
            case 3:
                System.out.print("Enter Roll Number to Update: ");
                int rollToUpdate = sc.nextInt();

                boolean studentFound = false;

                for (StudentManager s : students) {
                    if (s.getRollNo() == rollToUpdate) {

                        sc.nextLine(); // clear buffer
                        System.out.print("Enter New Name: ");
                        String newName = sc.nextLine();

                        // 🔍 Check for duplicate name
                        boolean nameExists = false;
                        for (StudentManager other : students) {
                            if (other.getName().equalsIgnoreCase(newName)
                                    && other.getRollNo() != rollToUpdate) {
                                nameExists = true;
                                break;
                            }
                        }

                        if (nameExists) {
                            System.out.println("Enter another name because it already exists.");
                            studentFound = true; // student exists, but update blocked
                            break; // exit for-loop, NOT program
                        }

                        System.out.print("Enter New Marks: ");
                        double newMarks = sc.nextDouble();

                        s.setName(newName);
                        s.setMarks(newMarks);

                        System.out.println("Student updated successfully!");
                        saveToFile(students);
                        studentFound = true;
                        break;
                    }
                }

                if (!studentFound) {
                    System.out.println("Student not found!");
                }
                break;
            case 4:
                System.out.print("Enter Roll Number to Search: ");
                int rollToSearch = sc.nextInt();

                boolean found = false;

                for (StudentManager s : students) {
                    if (s.getRollNo() == rollToSearch) {
                        System.out.println("Student Found:");
                        System.out.println("Roll No: " + s.getRollNo());
                        System.out.println("Name: " + s.getName());
                        System.out.println("Marks: " + s.getMarks());
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println("Student not found!");
                }
                break;
                
            case 5:
                System.out.print("Enter Roll Number to Delete: ");
                int rollToDelete = sc.nextInt();

                boolean deleted = false;

                for (int i = 0; i < students.size(); i++) {
                    if (students.get(i).getRollNo() == rollToDelete) {
                        students.remove(i);
                        System.out.println("Student deleted successfully!");
                        saveToFile(students);
                        deleted = true;
                        break;
                    }
                }

                if (!deleted) {
                    System.out.println("Student not found!");
                }
                break;




            case 6:
                saveToFile(students); // ✅ SAVE BEFORE EXIT
                System.out.println("Exiting Student Management System...");
                sc.close();
                System.exit(0);
                
            case 7:
                if (students.isEmpty()) {
                    System.out.println("No students to sort.");
                    break;
                }

                students.sort((s1, s2) -> Integer.compare(s1.getRollNo(), s2.getRollNo()));

                System.out.println("Students sorted by Roll Number:");
                for (StudentManager s : students) {
                    s.display();
                }
                break;
                
            case 8:
                if (students.isEmpty()) {
                    System.out.println("No students to sort.");
                    break;
                }

                students.sort((s1, s2) -> Double.compare(s2.getMarks(), s1.getMarks()));

                System.out.println("Students sorted by Marks (High → Low):");
                for (StudentManager s : students) {
                    s.display();
                }
                break;



        }

        }
        
        
        

        

       
        }
        }
    

