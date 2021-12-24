package com.example.passwordtestingiesb

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.passwordtestingiesb.databinding.FragmentPasswordBinding
import com.example.passwordtestingiesb.managers.InvalidType
import com.example.passwordtestingiesb.managers.PasswordManager
import com.example.passwordtestingiesb.managers.PasswordStatus
import com.example.passwordtestingiesb.managers.StrengthLevel


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
                val passwordCheck = PasswordManager.calculatePassword(passwordStr)

                changeAllAlertsToVisible()

                if (passwordCheck is PasswordStatus.Invalid) {
                    if ( ! passwordCheck.type.contains(InvalidType.Minimo1Numero) ) {
                        binding.includePassword.numbers.visibility = View.GONE
                        binding.includePassword.numbersImg.visibility = View.GONE
                    }
                    if ( ! passwordCheck.type.contains(InvalidType.Minimo8Caracteres) ) {
                        binding.includePassword.eightCharacters.visibility = View.GONE
                        binding.includePassword.eightImg.visibility = View.GONE
                    }
                    if ( ! passwordCheck.type.contains(InvalidType.Minimo1CaractereEspecial) ) {
                        binding.includePassword.characters.visibility = View.GONE
                        binding.includePassword.charactersImg.visibility = View.GONE
                    }
                } else { changeAllAlertsToGone() }

                when {
                    passwordStr.isEmpty() -> {
                        setStrengthLevelTxt("", 0)
                    }
                    passwordStr.length < 8 -> {
                        setStrengthLevelTxt("Sua senha é fraca", 1)
                    }
                    passwordStr.length in 8..9 -> {
                        setStrengthLevelTxt("Sua senha é média", 2)
                    }
                    passwordStr.length > 10 -> {
                        setStrengthLevelTxt("Sua senha é forte", 3)
                    }
                }
            }

            fun changeAllAlertsToVisible() {
                binding.includePassword.numbers.visibility = View.VISIBLE
                binding.includePassword.numbersImg.visibility = View.VISIBLE
                binding.includePassword.eightCharacters.visibility = View.VISIBLE
                binding.includePassword.eightImg.visibility = View.VISIBLE
                binding.includePassword.characters.visibility = View.VISIBLE
                binding.includePassword.charactersImg.visibility = View.VISIBLE
            }

            fun changeAllAlertsToGone() {
                binding.includePassword.numbers.visibility = View.GONE
                binding.includePassword.numbersImg.visibility = View.GONE
                binding.includePassword.eightCharacters.visibility = View.GONE
                binding.includePassword.eightImg.visibility = View.GONE
                binding.includePassword.characters.visibility = View.GONE
                binding.includePassword.charactersImg.visibility = View.GONE
            }

            fun setStrengthLevelTxt(str: String, strengthLvl: Int) {
                binding.includePassword.strengthLevelTxt.text = str
                when {
                    strengthLvl < 1 -> {
                        setBarColor(StrengthLevel.NONE.color, StrengthLevel.NONE.color, StrengthLevel.NONE.color)
                    }
                    strengthLvl == 1 -> {
                        setBarColor(StrengthLevel.WEAK.color, StrengthLevel.NONE.color, StrengthLevel.NONE.color)
                    }
                    strengthLvl == 2 -> {
                        setBarColor(StrengthLevel.MEDIUM.color, StrengthLevel.MEDIUM.color, StrengthLevel.NONE.color)
                    }
                    strengthLvl == 3 -> {
                        setBarColor(StrengthLevel.STRONG.color, StrengthLevel.STRONG.color, StrengthLevel.STRONG.color)
                    }
                }
            }

            fun setBarColor(colorWeak: Int, colorMedium: Int, colorStrong: Int) {
                binding.includePassword.strengthLevelIndicatorWeak.setBackgroundResource(colorWeak)
                binding.includePassword.strengthLevelIndicatorMedium.setBackgroundResource(colorMedium)
                binding.includePassword.strengthLevelIndicatorStrong.setBackgroundResource(colorStrong)
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