package com.coder.zzq.lib.annotation.compiler;

import com.squareup.javapoet.ClassName;

public interface Classes {
    ClassName ACTIVITY = ClassName.get("androidx.appcompat.app", "AppCompatActivity");
    ClassName FRAGMENT = ClassName.get("androidx.fragment.app", "Fragment");
}
