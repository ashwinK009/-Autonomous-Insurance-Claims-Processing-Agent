package com.aicp.agent.model;
import java.util.ArrayList;
import java.util.List;

public class ExtractedFields {
	public PolicyInfo policyInfo = new PolicyInfo();
    public IncidentInfo incidentInfo = new IncidentInfo();
    public AssetInfo assetInfo = new AssetInfo();

    public ContactDetails contactDetails = new ContactDetails();
    public List<ThirdParty> thirdParties = new ArrayList<>();

    public String claimType;
    public Boolean attachments;
    public Integer initialEstimate;

    public List<String> missingFields = new ArrayList<>();
}
