# APU Automotive Service Centre Management System

The APU Automotive Service Centre Management System is a Java SE desktop application developed for an Object-Oriented Programming group assignment. The system supports the daily operations of an automotive service centre through four user roles: Manager, Counter Staff, Technician and Customer.

The application uses Java Swing for its graphical user interface and text files for data storage. No database is required.

## Main Features

### Manager

- Create, view, update and delete manager, counter staff and technician accounts
- Set the prices for minor and major services
- View technician feedback and customer comments
- Generate appointment, service, revenue, technician performance and monthly reports

### Counter Staff

- View and edit personal profile
- Create, view, search, update and delete customer accounts
- Register, view, search, update and delete customer cars
- Book appointments and assign available technicians
- Apply the correct duration for minor and major services
- Collect payments and automatically generate receipts

### Technician

- View and edit personal profile
- View assigned appointments
- View related customer and car information
- Record completed service details
- Update an appointment to `COMPLETED`
- View individual service history
- Provide feedback for completed appointments

### Customer

- View and edit personal profile
- View and update personal car details
- View appointment and service history
- View payment and receipt history
- View technician feedback
- Submit ratings and comments for completed appointments

## Technologies and Concepts

- Java SE
- Java Swing and AWT
- Event handling
- Object-oriented programming
- Inheritance, encapsulation and abstraction
- ArrayList collections
- Exception handling
- File handling using character text files
- Input validation

## Project Structure

```text
APU_ASC/
├── src/
│   ├── GUI/
│   │   ├── MainGUI.java
│   │   ├── ManagerGUI.java
│   │   ├── CounterStaffDashboardGUI.java
│   │   ├── TechnicianGUI.java
│   │   └── CustomerGUI.java
│   ├── apu_asc/model/
│   │   ├── User.java
│   │   ├── Staff.java
│   │   ├── Manager.java
│   │   ├── CounterStaff.java
│   │   ├── Technician.java
│   │   ├── Customer.java
│   │   ├── Car.java
│   │   ├── Appointment.java
│   │   ├── Payment.java
│   │   └── Receipt.java
│   ├── utility/
│   │   └── DataIO.java
│   └── data/
│       ├── users.txt
│       ├── staff.txt
│       ├── cars.txt
│       ├── appointments.txt
│       ├── payments.txt
│       ├── receipts.txt
│       ├── service_prices.txt
│       ├── service_records.txt
│       ├── technician_feedback.txt
│       └── customer_comments.txt
├── nbproject/
├── build.xml
└── manifest.mf
```

## Service Rules

- Minor service duration: 1 hour
- Major service duration: 3 hours
- An appointment can only be assigned to an available technician
- Overlapping technician appointments are rejected
- Payment can only be collected after an appointment is completed
- A receipt is generated after a successful payment
- Appointment dates use the `yyyy-MM-dd` format

## How to Run

### Using Apache NetBeans

1. Download or clone this repository.
2. Open Apache NetBeans.
3. Select **File > Open Project**.
4. Select the `APU_ASC` project folder.
5. Select **Run > Clean and Build Project**.
6. Run the project or execute `src/GUI/MainGUI.java`.

The project folder must remain the working directory because the application reads and writes files using paths under `src/data`.

### Using Git

```bash
git clone https://github.com/WKAI-CODE/APU_ASC.git
cd APU_ASC
```

Open the cloned folder as a NetBeans project and run `GUI.MainGUI`.

## Demonstration Accounts

| Role | Username | Password |
|---|---|---|
| Manager | `manager01` | `M001@123` |
| Counter Staff | `counter01` | `CS001@123` |
| Technician | `technician01` | `T001@123` |
| Customer | `customer01` | `C001@123` |

These accounts use the sample records provided in the `src/data` folder.

## Data Storage

All system information is stored in character text files inside `src/data`. The vertical bar (`|`) is used as the field separator. Changes made through the application are written back to these files.

It is recommended to back up the data files before testing functions that create, update or delete records.

## Main Class

```text
GUI.MainGUI
```

## Academic Project

This system was developed as a group assignment for the Object-Oriented Programming module at Asia Pacific University of Technology & Innovation.
