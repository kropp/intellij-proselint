package name.kropp.intellij.proselint

import com.intellij.openapi.project.Project

data class ProselintAnnotatorInitialInfo(val project: Project, val filepath: String)