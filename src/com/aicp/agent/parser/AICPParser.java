package com.aicp.agent.parser;

import java.io.File;
import java.util.Map;

import com.aicp.agent.model.ExtractedFields;
import com.aicp.agent.model.FinalResponse;
import com.aicp.agent.model.ThirdParty;
import com.aicp.agent.routing.RoutingEngine;
import com.aicp.agent.validation.ValidationService;

public class AICPParser {

    public FinalResponse process(File pdf) throws Exception {

        PdfParser pdfParser = new PdfParser();
        Map<String, String> fields = pdfParser.extractFormFields(pdf);
        
     // 🔍 DEBUG: print all fields extracted from PDF
        //System.out.println("---- DEBUG: ACORD PDF FORM FIELDS ----");
        //fields.forEach((k, v) -> System.out.println(k + " = " + v));
        //System.out.println("-------------------------------------");

        ExtractedFields extracted = new ExtractedFields();

        /* ================= POLICY INFORMATION ================= */
        extracted.policyInfo.policyNumber =
                fields.get("Text7");

        extracted.policyInfo.policyholderName =
                fields.get("NAME OF INSURED First Middle Last");

        extracted.policyInfo.effectiveDates = null; // Not in ACORD FNOL

        /* ================= INCIDENT INFORMATION ================= */
        extracted.incidentInfo.date =
                fields.get("Text3");

        extracted.incidentInfo.time =
                fields.get("Text4");

        extracted.incidentInfo.location =
                fields.get("STREET LOCATION OF LOSS");

        extracted.incidentInfo.description =
                fields.get("DESCRIBE DAMAGE");

        /* ================= ASSET DETAILS ================= */
        extracted.assetInfo.assetType = "Vehicle";

        extracted.assetInfo.assetId =
                fields.get("PLATE NUMBER");

        extracted.assetInfo.estimatedDamage =
                parseAmount(fields.get("Text45"));

        /* ================= CLAIMANT CONTACT DETAILS ================= */
        extracted.contactDetails.phone =
                fields.get("PHONE  CELL HOME BUS PRIMARY");

        extracted.contactDetails.email =
                fields.get("PRIMARY EMAIL ADDRESS");

        /* ================= THIRD PARTIES ================= */
        addThirdParty(extracted, fields.get("Text81"), "Role Unclear", fields.get("PHONE  CELL HOME BUS PRIMARY_6"));
        addThirdParty(extracted, fields.get("NAME  ADDRESSRow1"), "Role Unclear", fields.get("PHONE AC NoRow1"));
        addThirdParty(extracted, fields.get("NAME  ADDRESSRow1_2"), "Role Unclear", fields.get("PHONE AC NoRow1_2"));
        addThirdParty(extracted, fields.get("Text48"), "Role Unclear", fields.get("PHONE CELL HOME BUS PRIMARY_5"));

        /* ================= OTHER MANDATORY FIELDS ================= */
        extracted.claimType = "Vehicle"; // derived
        extracted.attachments = null;    // not in ACORD
        extracted.initialEstimate = null; // not in ACORD

        /* ================= VALIDATION & ROUTING ================= */
        ValidationService.validate(extracted);
        return RoutingEngine.route(extracted);
    }

    /* ================= HELPERS ================= */

    private void addThirdParty(ExtractedFields extracted, String name, String role, String phone) {
        if (name != null && !name.isBlank()) {
            ThirdParty tp = new ThirdParty();
            tp.name = name;
            tp.role = role;
            tp.phone = phone;
            extracted.thirdParties.add(tp);
        }
    }

    private Integer parseAmount(String value) {
        try {
            if (value == null || value.isBlank()) return null;
            return Integer.parseInt(value.replaceAll("[^0-9]", ""));
        } catch (Exception e) {
            return null;
        }
    }
}

