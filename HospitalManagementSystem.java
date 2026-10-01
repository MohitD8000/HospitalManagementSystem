import java.util.ArrayList;
import java.util.Scanner;

public class HospitalManagementSystem {

    static class Patient {
        int id;
        String name;
        int age;
        String gender;
        String disease;

        Patient(int id, String name, int age, String gender, String disease) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.gender = gender;
            this.disease = disease;
        }

        void display() {
            System.out.println("-----------------------------------");
            System.out.println("Patient ID  : " + id);
            System.out.println("Name        : " + name);
            System.out.println("Age         : " + age);
            System.out.println("Gender      : " + gender);
            System.out.println("Disease     : " + disease);
            System.out.println("-----------------------------------");
        }
    }

    static class Doctor {
        int id;
        String name;
        String specialization;
        int fee;

        Doctor(int id, String name, String specialization, int fee) {
            this.id = id;
            this.name = name;
            this.specialization = specialization;
            this.fee = fee;
        }

        void display() {
            System.out.println("-----------------------------------");
            System.out.println("Doctor ID   : " + id);
            System.out.println("Name        : " + name);
            System.out.println("Speciality  : " + specialization);
            System.out.println("Fee         : " + fee);
            System.out.println("-----------------------------------");
        }
    }

    static class Appointment {
        int appointmentId;
        int patientId;
        int doctorId;
        String date;
        String status;

        Appointment(int appointmentId, int patientId, int doctorId, String date, String status) {
            this.appointmentId = appointmentId;
            this.patientId = patientId;
            this.doctorId = doctorId;
            this.date = date;
            this.status = status;
        }

        void display() {
            System.out.println("-----------------------------------");
            System.out.println("Appointment : " + appointmentId);
            System.out.println("Patient ID  : " + patientId);
            System.out.println("Doctor ID   : " + doctorId);
            System.out.println("Date        : " + date);
            System.out.println("Status      : " + status);
            System.out.println("-----------------------------------");
        }
    }

    static ArrayList<Patient> patients = new ArrayList<>();
    static ArrayList<Doctor> doctors = new ArrayList<>();
    static ArrayList<Appointment> appointments = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static int appointmentCounter = 0;

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n===== HOSPITAL MANAGEMENT SYSTEM =====");
            System.out.println("1. Register Patient");
            System.out.println("2. Display All Patients");
            System.out.println("3. Search Patient by ID");
            System.out.println("4. Add Doctor");
            System.out.println("5. Display All Doctors");
            System.out.println("6. Book Appointment");
            System.out.println("7. Display All Appointments");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    registerPatient();
                    break;
                case 2:
                    displayAllPatients();
                    break;
                case 3:
                    searchPatient();
                    break;
                case 4:
                    addDoctor();
                    break;
                case 5:
                    displayAllDoctors();
                    break;
                case 6:
                    bookAppointment();
                    break;
                case 7:
                    displayAllAppointments();
                    break;
                case 8:
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 8);

        scanner.close();
    }

    static void registerPatient() {
        System.out.print("Enter Patient ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Gender (M/F/O): ");
        String gender = scanner.nextLine();

        System.out.print("Enter Disease: ");
        String disease = scanner.nextLine();

        for (Patient p : patients) {
            if (p.id == id) {
                System.out.println("Patient with ID " + id + " already exists!");
                return;
            }
        }

        patients.add(new Patient(id, name, age, gender, disease));
        System.out.println("Patient registered successfully!");
    }

    static void displayAllPatients() {
        if (patients.isEmpty()) {
            System.out.println("No records found!");
        } else {
            System.out.println("\n--- All Patients ---");
            for (Patient p : patients) {
                p.display();
            }
        }
    }

    static void searchPatient() {
        System.out.print("Enter Patient ID to search: ");
        int id = scanner.nextInt();
        boolean found = false;

        for (Patient p : patients) {
            if (p.id == id) {
                p.display();
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Patient with ID " + id + " not found!");
        }
    }

    static void addDoctor() {
        System.out.print("Enter Doctor ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Specialization: ");
        String specialization = scanner.nextLine();

        System.out.print("Enter Consultation Fee: ");
        int fee = scanner.nextInt();

        for (Doctor d : doctors) {
            if (d.id == id) {
                System.out.println("Doctor with ID " + id + " already exists!");
                return;
            }
        }

        doctors.add(new Doctor(id, name, specialization, fee));
        System.out.println("Doctor added successfully!");
    }

    static void displayAllDoctors() {
        if (doctors.isEmpty()) {
            System.out.println("No records found!");
        } else {
            System.out.println("\n--- All Doctors ---");
            for (Doctor d : doctors) {
                d.display();
            }
        }
    }

    static void bookAppointment() {
        if (patients.isEmpty() || doctors.isEmpty()) {
            System.out.println("Add at least one patient and one doctor first!");
            return;
        }

        System.out.print("Enter Patient ID: ");
        int patientId = scanner.nextInt();

        if (!patientExists(patientId)) {
            System.out.println("Patient with ID " + patientId + " not found!");
            return;
        }

        System.out.print("Enter Doctor ID: ");
        int doctorId = scanner.nextInt();

        if (!doctorExists(doctorId)) {
            System.out.println("Doctor with ID " + doctorId + " not found!");
            return;
        }

        scanner.nextLine();
        System.out.print("Enter Appointment Date (dd/mm/yyyy): ");
        String date = scanner.nextLine();

        appointmentCounter++;
        appointments.add(new Appointment(appointmentCounter, patientId, doctorId, date, "Scheduled"));
        System.out.println("Appointment booked successfully! Appointment No: " + appointmentCounter);
    }

    static void displayAllAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No records found!");
        } else {
            System.out.println("\n--- All Appointments ---");
            for (Appointment a : appointments) {
                a.display();
            }
        }
    }

    static boolean patientExists(int id) {
        for (Patient p : patients) {
            if (p.id == id) {
                return true;
            }
        }
        return false;
    }

    static boolean doctorExists(int id) {
        for (Doctor d : doctors) {
            if (d.id == id) {
                return true;
            }
        }
        return false;
    }
}
