package com.coder.zzq.lib.annotation.compiler;

import androidx.annotation.IdRes;

import com.coder.zzq.lib.annotations.RobustFragment;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

@AutoService(Processor.class)
public class RobustFragmentProcessor extends AbstractProcessor {
    private Filer mFiler;
    private ProcessingEnvironment mProcessingEnvironment;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mFiler = processingEnvironment.getFiler();
        mProcessingEnvironment = processingEnvironment;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(RobustFragment.class.getCanonicalName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        Set<? extends Element> annotatedElements = roundEnvironment.getElementsAnnotatedWith(RobustFragment.class);
        for (Element element : annotatedElements) {
            String packageName = mProcessingEnvironment.getElementUtils().getPackageOf(element).toString();
            ClassName srcObj = ClassName.get(packageName, element.getSimpleName().toString());
            ClassName productionObj = ClassName.get(packageName, element.getSimpleName() + "Master");
            ClassName activity = ClassName.get("androidx.appcompat.app", "AppCompatActivity");
            ClassName fragment = ClassName.get("androidx.fragment.app", "Fragment");
            ClassName injectorClass = ClassName.get("com.coder.zzq.lib.robustfragment.injector", "FragmentInjector");
            ClassName createFragmentCallback = ClassName.get("com.coder.zzq.lib.robustfragment.injector", "OnCreateFragmentCallback");

            ClassName fragmentRetriever = ClassName.get("com.coder.zzq.lib.robustfragment.retriever", "FragmentRetriever");

            ClassName viewPage = ClassName.get("androidx.viewpager.widget", "ViewPager");

            ClassName robustPageAdapter = ClassName.get("com.coder.zzq.lib.robustfragment.viewpager", "IRobustPagerAdapter");

            MethodSpec createFragmentMethod = MethodSpec.methodBuilder("onCreate")
                    .addModifiers(Modifier.PUBLIC)
                    .returns(fragment)
                    .addStatement("return new $T()", srcObj)
                    .build();

            TypeSpec createCallback = TypeSpec.classBuilder("onCreateFragmentCallbackImpl")
                    .addSuperinterface(createFragmentCallback)
                    .addModifiers(Modifier.PRIVATE, Modifier.STATIC)
                    .addMethod(createFragmentMethod)
                    .build();

            MethodSpec injectorMethod = MethodSpec.methodBuilder("injector")
                    .returns(injectorClass)
                    .addModifiers(Modifier.STATIC, Modifier.PUBLIC)
                    .addStatement("return new $T(new onCreateFragmentCallbackImpl())", injectorClass)
                    .build();

            MethodSpec retrieveFromActivityById = MethodSpec.methodBuilder("retrieveFrom")
                    .addModifiers(Modifier.STATIC, Modifier.PUBLIC)
                    .addParameter(activity, "hostActivity")
                    .addParameter(
                            ParameterSpec.builder(TypeName.INT, "containerId")
                                    .addAnnotation(IdRes.class)
                                    .build()
                    )
                    .addStatement("return $T.<$T>retrieveFrom(hostActivity, containerId)", fragmentRetriever, srcObj)
                    .returns(srcObj)
                    .build();

            MethodSpec retrieveFromActivityByTag = MethodSpec.methodBuilder("retrieveFrom")
                    .addModifiers(Modifier.STATIC, Modifier.PUBLIC)
                    .addParameter(activity, "hostActivity")
                    .addParameter(String.class, "tag")
                    .addStatement("return $T.<$T>retrieveFrom(hostActivity, tag)", fragmentRetriever, srcObj)
                    .returns(srcObj)
                    .build();


            MethodSpec retrieveFromFragmentById = MethodSpec.methodBuilder("retrieveFrom")
                    .addModifiers(Modifier.STATIC, Modifier.PUBLIC)
                    .addParameter(fragment, "parentFragment")
                    .addParameter(
                            ParameterSpec.builder(TypeName.INT, "containerId")
                                    .addAnnotation(IdRes.class)
                                    .build()
                    )
                    .addStatement("return $T.<$T>retrieveFrom(parentFragment, containerId)", fragmentRetriever, srcObj)
                    .returns(srcObj)
                    .build();

            MethodSpec retrieveFromFragmentByTag = MethodSpec.methodBuilder("retrieveFrom")
                    .addModifiers(Modifier.STATIC, Modifier.PUBLIC)
                    .addParameter(fragment, "parentFragment")
                    .addParameter(String.class, "tag")
                    .addStatement("return $T.<$T>retrieveFrom(parentFragment, tag)", fragmentRetriever, srcObj)
                    .returns(srcObj)
                    .build();

            MethodSpec retrieveFromViewPager = MethodSpec.methodBuilder("retrieveFrom")
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                    .addParameter(viewPage, "viewPager")
                    .addParameter(TypeName.INT, "position")
                    .addStatement("return $T.<$T>retrieveFrom(viewPager, position)", fragmentRetriever, srcObj)
                    .returns(srcObj)
                    .build();

            MethodSpec retrieveFromRobustPagerAdapter = MethodSpec.methodBuilder("retrieveFrom")
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                    .addParameter(robustPageAdapter, "robustPagerAdapter")
                    .addParameter(TypeName.INT, "position")
                    .addStatement("return $T.<$T>retrieveFrom(robustPagerAdapter, position)", fragmentRetriever, srcObj)
                    .returns(srcObj)
                    .build();

            String[] argumentNames = element.getAnnotation(RobustFragment.class).argumentKeyNames();
            List<FieldSpec> fields = new ArrayList<>(argumentNames.length);
            for (String name : argumentNames) {
                FieldSpec field = FieldSpec.builder(String.class, "ARGUMENT_" + name.toUpperCase())
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                        .initializer("$S", name.toLowerCase())
                        .build();
                fields.add(field);
            }


            TypeSpec classType = TypeSpec.classBuilder(productionObj)
                    .addModifiers(Modifier.PUBLIC)
                    .addMethod(injectorMethod)
                    .addType(createCallback)
                    .addMethod(retrieveFromActivityById)
                    .addMethod(retrieveFromActivityByTag)
                    .addMethod(retrieveFromFragmentById)
                    .addMethod(retrieveFromFragmentByTag)
                    .addMethod(retrieveFromViewPager)
                    .addMethod(retrieveFromRobustPagerAdapter)
                    .addFields(fields)
                    .build();
            try {
                JavaFile.builder(packageName, classType)
                        .addFileComment("Generated code from Robust Fragment Lib. Do not modify!")
                        .build()
                        .writeTo(mFiler);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return true;
    }
}
