package broz.tito.usadebt.presentation.screens.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import broz.tito.usadebt.databinding.FragmentAboutAppBinding




class AboutAppFragment : Fragment() {

    private lateinit var binding: FragmentAboutAppBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutAppBinding.inflate(inflater,container,false)
        binding.imageButton.setOnClickListener {
            findNavController().navigateUp()
        }
        return binding.root
    }

}