package name.kropp.intellij.proselint

data class ProselintResult(val inspections: Iterable<Map<*, *>>, val error: String? = null)