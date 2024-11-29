package de.easyCalculator.sqz0111.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MathStringBuilder {
    private String prefix;
    private List<String> content;
    private String suffix;
    private StringBuilder builder = new StringBuilder();

    public MathStringBuilder() {
        createBasicMathOperationsString(this.builder);

    }

    private void createPrefixIntro() {
        this.prefix = "*".repeat(20) + " MATH WIZARD " + "*".repeat(20) + "\n";
    }

    private void createBasicMathContent() {
        this.content = new ArrayList<>(Arrays.asList("Addition", "Subtract", "Multiply", "Division"));
    }

    private void createBasicMathOperationsString(StringBuilder builder) {
        this.createPrefixIntro();
        builder.append(this.prefix);
        this.createBasicMathContent();
        int index = 0;
        for (String element : content) {
            builder.append(index++);
            builder.append("\t");
            builder.append(element);
            builder.append("\n");
        }

    }

    @Override
    public String toString() throws Error {
            if (!this.builder.isEmpty()) {
                return this.builder.toString();
            } else {
                throw new Error("First build the string!");
            }
    }
}
