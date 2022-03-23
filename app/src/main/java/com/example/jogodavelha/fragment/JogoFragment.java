package com.example.jogodavelha.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.jogodavelha.R;
import com.example.jogodavelha.databinding.FragmentJogoBinding;
    public class JogoFragment extends Fragment {

        //variável p acessar os elementos da view
        private FragmentJogoBinding binding;

        private Button[] botoes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentJogoBinding.inflate(inflater, container, false);

        botoes = new Button[9];

        botoes[0] = binding.bt00;
        botoes[1] = binding.bt01;
        botoes[2] = binding.bt02;
        botoes[3] = binding.bt10;
        botoes[4] = binding.bt11;
        botoes[5] = binding.bt12;
        botoes[6] = binding.bt20;
        botoes[7] = binding.bt21;
        botoes[8] = binding.bt22;

        for (Button bt : botoes){
            bt.setOnClickListener(listenerBotoes);
        }

        return  binding.getRoot();

    }

    private View.OnClickListener listenerBotoes = btPress -> {

        //obtem o nome do botão
        String nomeBotao = getContext().getResources().getResourceName(btPress);

        String posicao = nomeBotao.substring(nomeBotao.length()-2);

        //extrai linha e coluna da na string posição
        int linha = Character.getNumericValue(posicao.charAt(0));
        int coluna = Character.getNumericValue(posicao.charAt(1));

        Log.w("BOTAO", linha+"");
        Log.w("BOTAO", coluna+"");
    };
}