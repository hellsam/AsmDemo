package com.hellsam.asm;

import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * Created by binshenchen on 2018/11/19.
 */
public class CheckClassAdapter extends ClassVisitor {

    public CheckClassAdapter(ClassVisitor classVisitor) {
        super(Opcodes.ASM5, classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int i, String s, String s1, String s2, String[] strings) {
        MethodVisitor mv = super.visitMethod(i, s, s1, s2, strings);

        MethodVisitor wrappedMv = mv;

        if (mv != null) {
            if (s.equals("delete")) {
                wrappedMv = new CheckMethodAdapter(mv);
            }
        }
        return wrappedMv;
    }
}
