package com.coder.zzq.lib.annotation.compiler.compler;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;

public class Util {
    public static final String OPTION_ITEM_PACKAGE_NAME = "com.coder.zzq.smartshow.dialog";
    public static final TypeName TYPE_NAME_INT_OPTION_ITEM = ClassName.get(OPTION_ITEM_PACKAGE_NAME, "IntOptionItem");
    public static final TypeName TYPE_NAME_BOOL_OPTION_ITEM = ClassName.get(OPTION_ITEM_PACKAGE_NAME, "BoolOptionItem");
    public static final TypeName TYPE_NAME_FLOAT_OPTION_ITEM = ClassName.get(OPTION_ITEM_PACKAGE_NAME, "FloatOptionItem");
    public static final TypeName TYPE_NAME_STRING_OPTION_ITEM = ClassName.get(OPTION_ITEM_PACKAGE_NAME, "StringOptionItem");


    public static TypeName objectOptionItemTypeName(String typeVar) {
        return ClassName.get(OPTION_ITEM_PACKAGE_NAME, "ObjectOptionItem<" + typeVar + ">");
    }

    public static TypeName applierInterface(TypeName dialog, TypeName options) {
        return ClassName.get("com.coder.zzq.smartshow.dialog", "ISmartDialogApplier<" + dialog + "," + options + ">");
    }

    public static TypeName dialogHostFragmentType(TypeName applier) {
        return ClassName.get("com.coder.zzq.smartshow.dialog", "DialogHostFragment<" + applier + ">");
    }

}
