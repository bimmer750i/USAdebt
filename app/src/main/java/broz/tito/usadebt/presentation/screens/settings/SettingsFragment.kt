package broz.tito.usadebt.presentation.screens.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import broz.tito.usadebt.R
import broz.tito.usadebt.data.themedata.ThemeRepositoryImpl
import broz.tito.usadebt.databinding.FragmentSettingsBinding
import broz.tito.usadebt.domain.themedomain.ThemeUseCase

private lateinit var binding: FragmentSettingsBinding
private lateinit var useCase: ThemeUseCase


class SettingsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        useCase = ThemeUseCase(ThemeRepositoryImpl(requireActivity()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater,container,false)
        binding.themecardview.setOnClickListener {
            val list = arrayOf(getString(R.string.system_theme),getString(R.string.light_theme),getString(R.string.dark_theme))
            val builder = AlertDialog.Builder(requireContext())
            val checkeditem = useCase.getTheme()
            builder.setTitle(getString(R.string.choose_app_theme))
                .setSingleChoiceItems(list,checkeditem) { dialogInterface,i ->
                    when(i) {
                        0 -> {
                            useCase.setTheme(i)
                            dialogInterface.dismiss()
                        }
                        1 -> {
                            useCase.setTheme(i)
                            dialogInterface.dismiss()
                        }
                        2 -> {
                            useCase.setTheme(i)
                            dialogInterface.dismiss()
                        }
                    }
                }

            val dialog = builder.create()
            dialog.show()
        }
        binding.aboutappcardview.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_aboutAppFragment2)
        }
        return binding.root
    }
}