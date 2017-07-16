package name.kropp.intellij.proselint

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.execution.util.ExecUtil
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.ExternalAnnotator
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiFile
import org.jetbrains.io.JsonReaderEx
import org.jetbrains.jsonProtocol.JsonReaders

class ProselintAnnotator : ExternalAnnotator<ProselintAnnotatorInitialInfo, ProselintResult>() {
  override fun collectInformation(file: PsiFile) = ProselintAnnotatorInitialInfo(file.project, file.virtualFile.path)

  override fun collectInformation(file: PsiFile, editor: Editor, hasErrors: Boolean) = collectInformation(file)

  override fun apply(file: PsiFile, annotationResult: ProselintResult, holder: AnnotationHolder) {
    for (error in annotationResult.inspections) {
      try {
        val range = TextRange((error["start"] as Double).toInt() - 1, (error["end"] as Double).toInt() - 2)
        val message = error["message"] as String
        when (error["severity"]) {
          "warning" -> holder.createWarningAnnotation(range, message)
          "error" -> holder.createErrorAnnotation(range, message)
          else -> holder.createWeakWarningAnnotation(range, message)
        }
      } catch (e: Throwable) {
      }
    }
  }

  override fun doAnnotate(collectedInfo: ProselintAnnotatorInitialInfo) = try {
    val path = ServiceManager.getService(collectedInfo.project, ProselintProjectSettings::class.java).settings?.path ?: ""
    val output = ExecUtil.execAndGetOutput(GeneralCommandLine(path, "--json", collectedInfo.filepath))
    val json = JsonReaders.readRawStringOrMap(JsonReaderEx(output.stdout)) as Map<*, *>
    ProselintResult((json["data"] as Map<*, *>)["errors"] as Iterable<Map<*, *>>)
  } catch(e: Throwable) {
    ProselintResult(emptyList(), e.message)
  }
}

