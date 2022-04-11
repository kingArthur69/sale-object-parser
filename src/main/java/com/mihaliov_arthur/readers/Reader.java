package com.mihaliov_arthur.readers;

import java.io.File;
import java.util.List;

public interface Reader<T> {
    List<T> readAll(File file);
}
