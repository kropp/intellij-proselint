package name.kropp.intellij.proselint

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.components.StoragePathMacros

@State(name = "Proselint.Settings", storages = arrayOf(Storage(StoragePathMacros.WORKSPACE_FILE)))
class ProselintProjectSettings : PersistentStateComponent<ProselintSettings> {
  var settings: ProselintSettings? = ProselintSettings()

  override fun getState() = settings

  override fun loadState(state: ProselintSettings?) {
    settings = state
  }
}