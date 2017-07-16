package name.kropp.intellij.proselint

import com.intellij.codeInsight.intention.impl.BaseIntentionAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiFile

class ProselintFix(private val range: TextRange, private val replacement: String) : BaseIntentionAction() {
  override fun getText() = if (replacement.isEmpty()) "Remove word" else "Replace with $replacement"

  override fun getFamilyName() = "Proselint"
  override fun isAvailable(project: Project, editor: Editor?, file: PsiFile?) = true

  override fun invoke(project: Project, editor: Editor?, file: PsiFile?) {
    editor?.document?.replaceString(range.startOffset, range.endOffset, replacement)
  }
}