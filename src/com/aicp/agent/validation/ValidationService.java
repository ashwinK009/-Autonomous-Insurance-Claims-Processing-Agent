package com.aicp.agent.validation;

import com.aicp.agent.model.ExtractedFields;

public class ValidationService {
	 public static void validate(ExtractedFields f) {

	        if (f.policyInfo.policyNumber == null)
	            f.missingFields.add("Policy Number");

	        if (f.claimType == null)
	            f.missingFields.add("Claim Type");

	        if (f.assetInfo.estimatedDamage == null)
	            f.missingFields.add("Estimated Damage");

	        if (f.attachments == null)
	            f.missingFields.add("Attachments");

	        if (f.initialEstimate == null)
	            f.missingFields.add("Initial Estimate");
	    }
}
