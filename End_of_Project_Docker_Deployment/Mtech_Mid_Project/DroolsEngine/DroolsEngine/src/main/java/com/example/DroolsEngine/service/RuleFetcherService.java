package com.example.DroolsEngine.service;

import com.example.DroolsEngine.model.RuleDefinition;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class RuleFetcherService {

    private final RestTemplate restTemplate;

    public RuleFetcherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<RuleDefinition> fetchRules() {
        String url = "http://rulemanager:8081/api/rules"; // RuleManager endpoint
        try {
            RuleDefinition[] rules = restTemplate.getForObject(url, RuleDefinition[].class);
            if (rules != null && rules.length > 0) {
                for (RuleDefinition rule : rules) {
                    // Ensure ruleFile is properly handled
                    if (rule.getRuleFile() != null && rule.getRuleFile().length > 0) {
                        System.out.println("Loaded file-based rule: " + rule.getRuleName() +
                                " (" + rule.getRuleType() + ")");
                    }
                    if (rule.getRuleContent() != null && !rule.getRuleContent().isBlank()) {
                        System.out.println("Loaded text-based rule: " + rule.getRuleName());
                    }
                }
                return Arrays.asList(rules);
            } else {
                return Collections.emptyList();
            }
        } catch (RestClientException ex) {
            System.err.println("Could not fetch rules from RuleManager: " + ex.getMessage());
            return Collections.emptyList();
        }
    }
}
