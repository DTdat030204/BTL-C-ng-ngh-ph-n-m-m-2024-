package com.se.ssps.server.stat;

import java.util.List;
import com.se.ssps.server.helper.PaperTypeUsage;

public class ChartValueByType {
    private String label; // Ví dụ: "January 2024"
    private List<PaperTypeUsage> paperTypes;

    public ChartValueByType(String label, List<PaperTypeUsage> paperTypes) {
        this.label = label;
        this.paperTypes = paperTypes;
    }

    // Getter và Setter
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<PaperTypeUsage> getPaperTypes() {
        return paperTypes;
    }

    public void setPaperTypes(List<PaperTypeUsage> paperTypes) {
        this.paperTypes = paperTypes;
    }
}
