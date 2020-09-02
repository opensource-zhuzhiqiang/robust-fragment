package com.coder.zzq.lib.annotation.compiler.compler;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;

import javax.lang.model.type.TypeMirror;

public class ClassType {
    public TypeMirror mTypeMirror;
    public TypeName mTypeName;

    public ClassType(TypeMirror typeMirror) {
        mTypeMirror = typeMirror;
        mTypeName = ClassName.get(mTypeMirror);
    }

}
