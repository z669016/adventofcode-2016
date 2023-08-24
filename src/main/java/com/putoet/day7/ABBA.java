package com.putoet.day7;

import java.util.ArrayList;
import java.util.List;

class ABBA {
    public static boolean containsABBA(String text) {
        assert text != null;

        if (text.length() < 4)
            return false;

        for (int idx = 0; idx < text.length() - 3; idx++) {
            if ((text.charAt(idx) == text.charAt(idx + 3))
                    && (text.charAt(idx) != text.charAt(idx + 1))
                    && (text.charAt(idx + 1) == text.charAt(idx + 2)))
                return true;
        }

        return false;
    }

    public static List<String> listABA(String text) {
        assert text != null;

        if (text.length() < 3)
            return List.of();

        final List<String> list = new ArrayList<>();
        for (int idx = 0; idx < text.length() - 2; idx++) {
            if ((text.charAt(idx) == text.charAt(idx + 2)) && (text.charAt(idx) != text.charAt(idx + 1)))
                list.add(text.substring(idx, idx + 3));
        }

        return list;
    }

    public static boolean containsBAB(List<String> abaList, String text) {
        assert abaList != null;
        assert abaList.size() > 0;
        assert text != null;

        return abaList.stream().anyMatch(aba -> text.contains(bab(aba)));
    }

    static String bab(String aba) {
        assert aba != null;
        assert aba.length() == 3;

        return aba.substring(1) + aba.charAt(1);
    }
}
