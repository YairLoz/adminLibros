package com.project.adminLibros.utils;

import java.text.Normalizer;

public class StringUtil {

    public String normalizeString(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        return normalized.toLowerCase();
    }

}
