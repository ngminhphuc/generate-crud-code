//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.madao.plugin.utils;

import com.intellij.ide.IdeView;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectFileIndex;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.PsiShortNamesCache;
import com.intellij.psi.util.TypeConversionUtil;
import org.jetbrains.jps.model.java.JavaModuleSourceRootTypes;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * IDEA工具类
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2021-11-13 02:37
 */
public class PsiUtils {
    private Project project;

    private PsiUtils(Project project) {
        this.project = project;
    }

    public static PsiUtils of(Project project) {
        return new PsiUtils(project);
    }

    public static PsiDirectory getTestDirectory(Project project) {
        PsiDirectory baseDirectory = PsiManager.getInstance(project).findDirectory(project.getProjectFile());

        assert baseDirectory != null;

        return baseDirectory;
    }

    public void importClass(PsiClass srcClass, PsiClass... toImportClasses) {
        PsiClass[] var3 = toImportClasses;
        int var4 = toImportClasses.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            PsiClass toImportClass = var3[var5];
            ((PsiJavaFile)srcClass.getContainingFile()).importClass(toImportClass);
        }

    }

    public String getPackageName(PsiClass psiClass) {
        return ((PsiJavaFile)psiClass.getContainingFile()).getPackageName();
    }

    public Optional<String> getAnnotationValue(PsiClass psiClass, String annotation, String field) {
        return (Optional)Optional.ofNullable(psiClass.getAnnotation(annotation)).map((a) -> {
            PsiAnnotationMemberValue value = a.findAttributeValue(field);
            return null != value ? Optional.of(value.getText()) : Optional.empty();
        }).orElse(Optional.empty());
    }

    public Optional<String> getAnnotationValue(PsiFile psiFile, String annotation, String field) {
        return this.getAnnotationValue(((PsiJavaFile)psiFile).getClasses()[0], annotation, field);
    }

    public String getPackageAndName(PsiClass psiClass) {
        return ((PsiJavaFile)psiClass.getContainingFile()).getPackageName().concat(".").concat(psiClass.getName());
    }

    public void format(PsiElement psiElement) {
        CodeStyleManager.getInstance(this.project).reformat(psiElement);
    }

    public Optional<PsiClass> findClass(String className) {
        return this.findClass(className, (psiClass) -> {
            return true;
        });
    }

    public Optional<PsiClass> findClass(String className, Predicate<PsiClass> predicate) {
        PsiShortNamesCache shortNamesCache = PsiShortNamesCache.getInstance(this.project);
        int idx = className.lastIndexOf(".");
        if (-1 != idx) {
            String packageName = className.substring(0, idx);
            String name = className.substring(idx + 1);
            PsiClass[] classes = shortNamesCache.getClassesByName(name, GlobalSearchScope.projectScope(this.project));
            if (0 != classes.length) {
                PsiClass[] var8 = classes;
                int var9 = classes.length;

                for(int var10 = 0; var10 < var9; ++var10) {
                    PsiClass aClass = var8[var10];
                    PsiJavaFile javaFile = (PsiJavaFile)aClass.getContainingFile();
                    if (javaFile.getPackageName().equals(packageName) && predicate.test(aClass)) {
                        return Optional.of(aClass);
                    }
                }
            }
        } else {
            PsiClass[] classes = shortNamesCache.getClassesByName(className, GlobalSearchScope.projectScope(this.project));
            if (0 != classes.length) {
                PsiClass[] var14 = classes;
                int var15 = classes.length;

                for(int var16 = 0; var16 < var15; ++var16) {
                    PsiClass aClass = var14[var16];
                    if (predicate.test(aClass)) {
                        return Optional.ofNullable(aClass);
                    }
                }
            }
        }

        return Optional.empty();
    }

    public PsiDirectory getOrCreateSubDirectory(PsiDirectory parentDirectory, String subDirectoryName) {
        return (PsiDirectory)Optional.ofNullable(parentDirectory.findSubdirectory(subDirectoryName)).orElseGet(() -> {
            Runnable r = () -> {
                parentDirectory.createSubdirectory(subDirectoryName);
            };
            WriteCommandAction.runWriteCommandAction(this.project, r);
            return parentDirectory.findSubdirectory(subDirectoryName);
        });
    }

    public static PsiDirectory getTheFirstTestPsiDirectoryInTheProject(AnActionEvent e, Project project) {
        IdeView view = (IdeView)e.getData(LangDataKeys.IDE_VIEW);
        if (view == null) {
            return null;
        } else {
            ProjectRootManager rootManager = ProjectRootManager.getInstance(project);
            ProjectFileIndex fileIndex = rootManager.getFileIndex();
            Optional<PsiDirectory> sourceDirectory = Stream.of(view.getDirectories()).filter((directory) -> {
                VirtualFile virtualFile = directory.getVirtualFile();
                return fileIndex.isUnderSourceRootOfType(virtualFile, JavaModuleSourceRootTypes.TESTS);
            }).findFirst();
            return (PsiDirectory)sourceDirectory.orElse(null);
        }
    }

    public static String getIdType(PsiClass psiClass) {
        return psiClass.getFields()[0].getType().getPresentableText();
    }

    public Optional<PsiClass> findClassGlobal(String className, Predicate<PsiClass> predicate) {
        PsiShortNamesCache shortNamesCache = PsiShortNamesCache.getInstance(this.project);
        int idx = className.lastIndexOf(".");
        if (-1 != idx) {
            String packageName = className.substring(0, idx);
            String name = className.substring(idx + 1);
            PsiClass[] classes = shortNamesCache.getClassesByName(name, GlobalSearchScope.allScope(this.project));
            if (0 != classes.length) {
                PsiClass[] var8 = classes;
                int var9 = classes.length;

                for(int var10 = 0; var10 < var9; ++var10) {
                    PsiClass aClass = var8[var10];
                    PsiJavaFile javaFile = (PsiJavaFile)aClass.getContainingFile();
                    if (javaFile.getPackageName().equals(packageName) && predicate.test(aClass)) {
                        return Optional.of(aClass);
                    }
                }
            }
        } else {
            PsiClass[] classes = shortNamesCache.getClassesByName(className, GlobalSearchScope.allScope(this.project));
            if (0 != classes.length) {
                PsiClass[] var14 = classes;
                int var15 = classes.length;

                for(int var16 = 0; var16 < var15; ++var16) {
                    PsiClass aClass = var14[var16];
                    if (predicate.test(aClass)) {
                        return Optional.ofNullable(aClass);
                    }
                }
            }
        }

        return Optional.empty();
    }

    public boolean isPrimitiveAndNotNullOrWrapper(PsiType type) {
        if (type instanceof PsiClassType) {
            return PsiPrimitiveType.getUnboxedType(type) != null;
        } else {
            return TypeConversionUtil.isPrimitiveAndNotNull(type);
        }
    }
}
