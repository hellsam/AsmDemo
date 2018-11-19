package com.hellsam.asm;

import jdk.internal.org.objectweb.asm.Label;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * Created by binshenchen on 2018/11/19.
 */
public class CheckMethodAdapter extends MethodVisitor {
    public CheckMethodAdapter(MethodVisitor mv) {
        super(Opcodes.ASM5, mv);
    }

    @Override
    public void visitCode() {
        Label continueLabel = new Label();
        visitMethodInsn(Opcodes.INVOKESTATIC, "com/hellsam/SecurityChecker", "checkSecurity", "()Z");
        visitJumpInsn(Opcodes.IFNE, continueLabel);
        visitInsn(Opcodes.RETURN);
        visitLabel(continueLabel);
        super.visitCode();
    }
}
