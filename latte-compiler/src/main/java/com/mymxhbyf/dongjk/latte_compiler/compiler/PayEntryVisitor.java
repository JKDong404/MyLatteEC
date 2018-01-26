package com.mymxhbyf.dongjk.latte_compiler.compiler;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor7;

/**
 * Created by DongJK on 2018/1/26.
 */

public final class PayEntryVisitor extends SimpleAnnotationValueVisitor7<Void,Void>{

    private Filer mFiler = null;
    private TypeMirror mTypeMirror = null;
    private String mPackgeName = null;

    public void setmFiler(Filer mFiler) {
        this.mFiler = mFiler;
    }

    @Override
    public Void visitString(String s, Void p) {
        mPackgeName = s;
        return p;
    }

    @Override
    public Void visitType(TypeMirror t, Void p) {
        mTypeMirror = t;
        generateJavaCode();
        return p;
    }

    private void generateJavaCode(){
        final TypeSpec targetActivity =
                TypeSpec.classBuilder("WXPayEntryActivity")//微信规定入口文件名字必须为WXEntryActivity
                .addModifiers(Modifier.PUBLIC)
                .addModifiers(Modifier.FINAL)
                .superclass(TypeName.get(mTypeMirror))
                .build();

        final JavaFile javaFile = JavaFile.builder(mPackgeName+".wxapi",targetActivity)
                .addFileComment("微信支付入口文件")
                .build();
        try {
            javaFile.writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
