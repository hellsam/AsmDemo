package com.hellsam.asm;

import com.hellsam.Account;
import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by binshenchen on 2018/11/19.
 */
public class NewAccountCreator {
    public static void main(String[] args) throws Exception {
        String className = Account.class.getName();
        ClassReader classReader = new ClassReader(className);
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        CheckClassAdapter classAdapter = new CheckClassAdapter(cw);
        classReader.accept(classAdapter, ClassReader.SKIP_DEBUG);
        byte[] data = cw.toByteArray();
        File file = new File("./out/production/AsmDemo/" + className.replaceAll("\\.", "/") + ".class");
//        if (file.exists()){
//            System.out.println("exists:" + file.getAbsolutePath());
//        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(data);
        fileOutputStream.flush();
        fileOutputStream.close();
    }
}
