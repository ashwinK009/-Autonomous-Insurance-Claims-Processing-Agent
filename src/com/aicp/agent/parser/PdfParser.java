package com.aicp.agent.parser;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

public class PdfParser {

    // This method extracts ALL form fields as key-value pairs
    public Map<String, String> extractFormFields(File pdf) throws Exception {

        Map<String, String> fieldMap = new HashMap<>();

        PDDocument document = PDDocument.load(pdf);

        PDAcroForm form = document.getDocumentCatalog().getAcroForm();
        if (form == null) {
            document.close();
            return fieldMap; // empty map
        }

        for (PDField field : form.getFieldTree()) {
            String name = field.getFullyQualifiedName();
            String value = field.getValueAsString();
            fieldMap.put(name, value);
        }

        document.close();
        return fieldMap;
    }
}
