package com.pontosa.jar;

import com.pontosa.jar.login.VerificarInicializacao;
import com.pontosa.jar.usuario.Dispositivo;

import java.util.Map;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Login {
    public static void main(String[] args) {

        Dispositivo dispositivo = new Dispositivo();

        Boolean existe = false;

        String email;

        String senha;

        Scanner leitor = new Scanner(System.in);

        do {

            System.out.println("BEM VINDO Á PONTO SA");
            System.out.println("VAMOS FAZER SEU LOGIN");

            System.out.println("INSIRA SEU USUARIO");
            email = leitor.nextLine();

            System.out.println("INSIRA SUA SENHA");
            senha = leitor.nextLine();

            existe =  dispositivo.login(email, senha);

        } while (!existe);



        if (existe) {
            VerificarInicializacao teste = new VerificarInicializacao();
            teste.main(args);
            Map<String, Object> dispositivoId = dispositivo.recuperarDispositivoId(email);
            Integer idDispositivo = Integer.valueOf(String.valueOf(dispositivoId.get("id")));
            dispositivo.updateHostname(idDispositivo);
            dispositivo.especificacao();
            teste.main(args);
            dispositivo.especificacao();
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        dispositivo.loopRegistro();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            },0,5000);
        } else {
            System.out.println("Email e/ou senha inválidos Erro de validação");
        }
    }
}
