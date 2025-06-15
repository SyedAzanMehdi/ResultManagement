📊 Result Management System – Java Swing UI + File Handling
A desktop application built with Java Swing for managing student result records efficiently. The system allows users to input student details and marks, calculate the average, determine pass/fail status, and automatically save the data to a text file. Designed with a clean and modern user interface, this application ensures simplicity, usability, and persistent storage using file handling.

✨ Key Features
🎓 Student Info Input
Enter student name, roll number, and marks for 3 subjects.

📈 Automatic Result Calculation
Computes average marks and determines Pass/Fail based on subject-wise criteria.

💾 File Handling Support

Results are automatically saved to results.txt.

Data is reloaded from the file every time the application starts.

📋 Tabular Data Display
Results are shown in a scrollable JTable with proper formatting.

🖼️ User-Friendly GUI
Built using Java Swing with custom colors and layout for a modern feel.

🖊️ Branding
Footer label: Made by Azan Mehdi Shah.

🛠️ Technologies Used
Java SE 8+

Java Swing (GUI components)

File I/O (FileReader, FileWriter, BufferedReader)

JTable and DefaultTableModel

📂 File Structure
bash
Copy
Edit
📁 ResultManagementSystem/
├── ResultManagementSystem.java    # Main application file
├── results.txt                    # File for saved student records
└── README.md                      # Project documentation
📁 File Format (results.txt)
Each line represents one student’s result in CSV format:

pgsql
Copy
Edit
Name,RollNo,Subject1,Subject2,Subject3,Average,Status
Example:


Azan,21SW001,85,90,80,85.00,Pass
Sara,21SW002,70,80,40,63.33,Pass
🚀 How to Run
Make sure Java is installed (java -version)

Clone or download the repository

Open the file ResultManagementSystem.java in any Java IDE (e.g., IntelliJ, NetBeans, Eclipse)

Compile and run the program

Input student data and click "Add Result"

Check results.txt for saved entries

🧠 Pass/Fail Logic
A student is marked Pass only if all three subjects have marks ≥ 33

Otherwise, the student is marked as Fail

The average is calculated as (sub1 + sub2 + sub3) / 3

