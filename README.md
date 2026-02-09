#  Autonomous Insurance Claims Processing Agent (Java)

This project is a simple Java application that processes First Notice of Loss (FNOL) insurance forms.  
It reads data from an ACORD FNOL PDF, checks whether all required information is present, and decides how the claim should be handled based on basic rules.

The goal of this project is to focus on document parsing and decision logic, without using a UI or database.

---

## How to run the project

1. Clone the repository.
2. Open the project in Eclipse or STS.
3. Add all JAR files from the `lib` folder to the build path. I'll explain it below:

```   a) Right-click on the project → **Build Path** → **Configure Build Path** ```
```   b) Go to the **Libraries** tab ```
```   c) Click on Classpath first & then click on **Add JARs** ```
```   d) Select all JAR files from the `lib` folder ```
```   e) Click **Apply and Close** ```
   
4. Place a filled ACORD FNOL PDF inside the `sample-files` directory.
5. Update the file path in `Main.java` if needed.
6. Run `Main.java`.

The output JSON will be printed in the console.

---

## What the program does

- Reads filled ACORD FNOL PDF forms using Apache PDFBox
- Extracts important details such as policy information, incident details, asset information, and involved parties
- Checks for missing mandatory fields
- Routes the claim to fast-track, manual review, or specialist handling based on predefined rules
- Prints the final result as JSON in the console

---

## How it works (high level)

- PDF form fields are read directly from the ACORD document (AcroForm fields)
- Extracted values are mapped into Java objects
- Mandatory fields are validated as per the problem statement
- Routing is decided using simple if-else rules
- The output is generated in JSON format using Jackson

---

## Technologies used

- Java 17
- Apache PDFBox
- Jackson (JSON serialization)

---

## Project structure
```
AICP-Agent
├── lib/ // External JAR files
├── sample-files/ // Sample filled ACORD FNOL PDFs
├── src/
│ └── com.aicp.agent
│ ├── parser
│ ├── model
│ ├── validation
│ └── routing
└── README.md ```

---

## Notes

- Some mandatory fields like attachments and initial estimate are not part of the ACORD FNOL form, so they are intentionally marked as missing.
- Third-party roles are assigned conservatively when the PDF does not clearly define them.



 
