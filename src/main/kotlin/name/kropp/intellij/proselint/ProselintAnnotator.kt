package name.kropp.intellij.proselint

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.ExternalAnnotator
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiFile

class ProselintAnnotator : ExternalAnnotator<ProselintAnnotatorInitialInfo, ProselintResult>() {
  override fun collectInformation(file: PsiFile): ProselintAnnotatorInitialInfo? {
    return ProselintAnnotatorInitialInfo()
  }

  override fun collectInformation(file: PsiFile, editor: Editor, hasErrors: Boolean) = collectInformation(file)

  override fun apply(file: PsiFile, annotationResult: ProselintResult?, holder: AnnotationHolder) {
  }

  override fun doAnnotate(collectedInfo: ProselintAnnotatorInitialInfo?): ProselintResult? {
    // run proselint
    return ProselintResult()
  }
}

