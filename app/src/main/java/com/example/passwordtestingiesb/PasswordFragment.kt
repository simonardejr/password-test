package com.example.passwordtestingiesb

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.passwordtestingiesb.databinding.FragmentPasswordBinding


class PasswordFragment : Fragment() {

    private var _binding: FragmentPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.includePassword.tiePassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(password: Editable) {
                val passwordStr = password.toString()

                when {
                    passwordStr.isEmpty() -> {
                        binding.includePassword.strengthLevelTxt.text = ""
                    }
                    passwordStr.length < 8 -> {
                        binding.includePassword.strengthLevelTxt.text = "Sua senha é fraca"
                    }
                    passwordStr.length in 8..9 -> {
                        binding.includePassword.strengthLevelTxt.text = "Sua senha é média"
                    }
                    passwordStr.length > 10 -> {
                        binding.includePassword.strengthLevelTxt.text = "Sua senha é forte"
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}