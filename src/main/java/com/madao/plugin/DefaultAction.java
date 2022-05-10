package com.madao.plugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiPackageStatement;
import com.madao.plugin.utils.PsiUtils;
import org.jetbrains.annotations.NotNull;

public class DefaultAction extends AnAction {

	protected static final String TITLE_INFORMATION = "Information";
	protected static final String TITLE = "Madaoo Demo";
	protected Project project;
	protected PsiDirectory containerDirectory;
	protected PsiUtils psiUtils;
	protected Module module;
	protected PsiDirectory parentDirectory;
	protected PsiPackageStatement packageFile;
	protected PsiDirectory containerDirectoryTest;
	protected Project testProject;
	protected PsiDirectory controllerTestDirectory;

	@Override
	public synchronized void actionPerformed(@NotNull AnActionEvent anActionEvent) {

		int controllerType = Messages.showChooseDialog(
				"",
				"Choose Controller Type",
				new String[]{"WebFlux and MongoDB", "WebMvc and JPA"},
				"",
				Messages.getQuestionIcon());

		Messages.showWarningDialog(this.project,"1. If the EntityMapper.java file already exists, it will not be created repeatedly\n2. Please add dependencies according to the project type selected in the previous step ","notice");
		if (controllerType == 0){
			new WebFluxAction().actionPerformed(anActionEvent);
		}else {
			 new WebMvcAction().actionPerformed(anActionEvent);
		}

	}
}
