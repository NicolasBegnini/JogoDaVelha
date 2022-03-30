package com.example.jogodavelha.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.jogodavelha.R;
import com.example.jogodavelha.databinding.FragmentJogoBinding;

import java.util.Arrays;
import java.util.Random;

public class JogoFragment extends Fragment {

        //variável p acessar os elementos da view
        private FragmentJogoBinding binding;

        private Button[] botoes;

        private String[][] tabuleiro;

        private String simboloJog1, simboloJog2, simbolo;

        private Random random;

        //variavel para contar o número de jogadas
        private int numeroJogadas;

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

        //instanciar o tabuleiro
        tabuleiro = new String[3][3];

       /* for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; i++){
                tabuleiro[i][j] = "";
            }
        }
        */

        //criar método resetar


        //preenche a matriz com ""
        for (String[] vetor : tabuleiro) {
            Arrays.fill(vetor, "");

        }

        //define os simbolos do jogador 1 e jogador 2
        simboloJog1 = "X";
        simboloJog2 = "O";

        random = new Random();

        sorteia();

        //atualiza vez no placar
        atualizaVez();

        venceu();
        velha();

        return  binding.getRoot();

    }

    private void sorteia(){
        // se gerar um valor verdadeiro jogador 1 começa, caso contrario, jogador 2 começa

        if (random.nextBoolean()) {
            simbolo = simboloJog1;
        }else {
            simbolo = simboloJog2;
        }
    }

    private void atualizaVez(){
        if (simbolo.equals(simboloJog1)){
            binding.linearJogador1.setBackgroundColor(getResources().getColor(R.color.white));
            binding.linearJogador2.setBackgroundColor(getResources().getColor(R.color.cinza_escuro));
        }else{
            binding.linearJogador2.setBackgroundColor(getResources().getColor(R.color.white));
            binding.linearJogador1.setBackgroundColor(getResources().getColor(R.color.cinza_escuro));
        }
    }

    private boolean venceu(){
        //verifica se venceu nas linhas
        for (int li = 0; li < 3; li++) {
            if (tabuleiro[li][0].equals(simbolo) && tabuleiro[li][2].equals(simbolo)){
            return true;
            }
        }

        for (int col = 0; col < 3; col++){
            if (tabuleiro[col][0].equals(simbolo) && tabuleiro[col][2].equals(simbolo)) {
                return true;
            }
        }

        //verifica se ganhou nas verticais
        if(tabuleiro[0][0].equals(simbolo) && tabuleiro [1][1].equals(simbolo) && tabuleiro [2][2].equals(simbolo)){
            return true;
        }

        if(tabuleiro[0][2].equals(simbolo) && tabuleiro [1][1].equals(simbolo) && tabuleiro [2][0].equals(simbolo)){
            return true;
        }

        return false;

    }

    private void resetar(){
        //percorrer os botões do vetor, voltando o background inicial,deixando clicavel e tirando os textos
        for (Button botao : botoes){
            botao.setClickable(true);
            botao.setText("");
            botao.setBackgroundColor(getResources().getColor(R.color.blue_A400));
        }
        //limpar a matriz
        for (String[] vetor:tabuleiro){
            Arrays.fill(vetor, "" );
        }
        numeroJogadas = 0;
    }

    private View.OnClickListener listenerBotoes = btPress -> {

        //obtem o nome do botão
        String nomeBotao = getContext().getResources().getResourceName(btPress.getId());

        String posicao = nomeBotao.substring(nomeBotao.length()-2);

        //extrai linha e coluna da na string posição
        int linha = Character.getNumericValue(posicao.charAt(0));
        int coluna = Character.getNumericValue(posicao.charAt(1));

        tabuleiro[linha][coluna] = simbolo;

        // faz um casting de View pra Botão
        Button botao = (Button) btPress;
        // seta o simbolo no botão pressionado
        botao.setText(simbolo);

        botao.setBackgroundColor(Color.WHITE);

        botao.setClickable(false);

        if (numeroJogadas >= 5 && venceu()){
            Toast.makeText(getContext(), R.string.venceu, Toast.LENGTH_LONG).show();

            resetar();
        } else if (numeroJogadas == 9){
            Toast.makeText(getContext(), R.string.velha, Toast.LENGTH_LONG).show();

            resetar();
        } else {
            //inverte o simbolo
            simbolo = simbolo.equals(simboloJog1) ? simboloJog2 : simboloJog1;
            atualizaVez();
        }
    };
}