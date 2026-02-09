package com.aicp.agent.routing;
import com.aicp.agent.model.ExtractedFields;
import com.aicp.agent.model.FinalResponse;

public class RoutingEngine {
	public static FinalResponse route(ExtractedFields f) {

        FinalResponse r = new FinalResponse();
        r.extractedFields = f;

        if (!f.missingFields.isEmpty()) {
            r.recommendedRoute = "Manual Review";
            r.reasoning = "Mandatory fields missing";
            return r;
        }

        if (f.incidentInfo.description != null &&
            f.incidentInfo.description.toLowerCase().contains("fraud")) {
            r.recommendedRoute = "Investigation Flag";
            r.reasoning = "Suspicious keywords detected";
            return r;
        }

        if ("Injury".equalsIgnoreCase(f.claimType)) {
            r.recommendedRoute = "Specialist Queue";
            r.reasoning = "Injury-related claim";
            return r;
        }

        if (f.assetInfo.estimatedDamage < 25000) {
            r.recommendedRoute = "Fast-track";
            r.reasoning = "Low estimated damage";
        } else {
            r.recommendedRoute = "Manual Review";
            r.reasoning = "High estimated damage";
        }

        return r;
    }
}
