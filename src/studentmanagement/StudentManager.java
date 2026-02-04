package studentmanagement;
import java.io.Serializable;

public class StudentManager implements Serializable {
	private int rollNo;
    private String name;
    private double marks;

	public StudentManager(int rollNo, String name, double marks) {
		        this.rollNo = rollNo;
		        this.name = name;
		        this.marks = marks;
		// TODO Auto-generated constructor stub
	}
	
	// Getters and Setters
    public int getRollNo() {
        return rollNo;
    }

    public String getName() {
        return name;
    }

    public double getMarks() {
        return marks;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setMarks(double marks) {
        this.marks = marks;
    }

    // Display student details
    public void display() {
        System.out.println("Roll No: " + rollNo +
                           ", Name: " + name +
                           ", Marks: " + marks);
    }
}


