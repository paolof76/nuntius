package com.group.nuntius;

import java.util.List;
import java.util.Optional;

public class Tools {

    public static <T> Optional<T> getFirst(List<T> list) {
        return list.isEmpty() ? Optional.empty() : Optional.ofNullable(list.get(0));
    }

}
